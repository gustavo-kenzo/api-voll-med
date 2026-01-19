package med.voll.api_rest.service;

import med.voll.api_rest.domain.consulta.Consulta;
import med.voll.api_rest.domain.consulta.ConsultaRepository;
import med.voll.api_rest.domain.consulta.DadosCadastroConsulta;
import med.voll.api_rest.domain.medico.Medico;
import med.voll.api_rest.domain.medico.MedicoRepository;
import med.voll.api_rest.domain.paciente.Paciente;
import med.voll.api_rest.domain.paciente.PacienteRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
public class ConsultaService {

    private PacienteRepository pacienteRepository;
    private MedicoRepository medicoRepository;
    private ConsultaRepository consultaRepository;

    public ConsultaService(PacienteRepository pacienteRepository, MedicoRepository medicoRepository, ConsultaRepository consultaRepository) {
        this.pacienteRepository = pacienteRepository;
        this.medicoRepository = medicoRepository;
        this.consultaRepository = consultaRepository;
    }


    @Transactional
    public String agendar(DadosCadastroConsulta dados) {
        // Busca paciente e médico
        Paciente paciente = pacienteRepository.getReferenceById(dados.pacienteId());
        Medico medico = medicoRepository.getReferenceById(dados.medicoId());
        LocalDateTime data = dados.data();

        // 1️⃣ Não permitir agendamento de pacientes inativos
        if (!paciente.getAtivo())
            return "Paciente está inativo";

        // 2️⃣ Não permitir agendamento de médicos inativos
        if (!medico.getAtivo())
            return "Médico não é mais filiado";

        LocalTime horario = data.toLocalTime();

        // 3️⃣ Horário de funcionamento da clínica: segunda a sábado, 07:00–19:00
        if (data.getDayOfWeek() == DayOfWeek.SUNDAY ||
                horario.isBefore(LocalTime.of(7, 0)) ||
                horario.isAfter(LocalTime.of(18, 0)))
            return "Horário da consulta está fora do padrão (segunda - sabado das 7:00 as 19:00)";

        // 4️⃣ Antecedência mínima de 30 minutos
        if (!LocalDateTime.now().isBefore(data.minusMinutes(30)))
            return "Consulta deve ser marcada com até 30 min de antecedência";

        LocalDate diaConsulta = data.toLocalDate();
        LocalDateTime inicio = diaConsulta.atStartOfDay();
        LocalDateTime fim = diaConsulta.atTime(23, 59, 59);

        // 5️⃣ Apenas uma consulta por paciente/dia
        if (consultaRepository.existsByPacienteIdAndDataBetween(paciente.getId(), inicio, fim))
            return "Paciente já tem uma consulta marcada para esse dia";

        // 6️⃣ Médico não pode ter outra consulta na mesma data/hora
        LocalDateTime fimConsulta = data.plusHours(1);
        if (consultaRepository.existsConsultaConflito(medico.getId(), data, fimConsulta))
            return "Médico já possui consulta nesse periodo";

        consultaRepository.save(new Consulta(medico, paciente, data));
        return null;
    }

}
