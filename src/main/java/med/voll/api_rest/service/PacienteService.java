package med.voll.api_rest.service;

import med.voll.api_rest.medico.DadosAtualizacaoMedico;
import med.voll.api_rest.medico.DadosCadastroMedico;
import med.voll.api_rest.medico.DadosListagemMedico;
import med.voll.api_rest.medico.Medico;
import med.voll.api_rest.paciente.DadosCadastroPaciente;
import med.voll.api_rest.paciente.Paciente;
import med.voll.api_rest.paciente.PacienteRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PacienteService {

    private PacienteRepository repository;

    public PacienteService(PacienteRepository repository) {
        this.repository = repository;
    }
    @Transactional
    public String cadastrar(DadosCadastroPaciente dados) {
        if (repository.existsByCpf(dados.cpf()))
            return "Paciente já cadastrado";
        else
            repository.save(new Paciente(dados));
        return "Paciente adicionado";
    }
}
