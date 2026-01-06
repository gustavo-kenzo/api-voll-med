package med.voll.api_rest.service;

import jakarta.validation.Valid;
import med.voll.api_rest.medico.DadosCadastroMedico;
import med.voll.api_rest.medico.Medico;
import med.voll.api_rest.medico.MedicoRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicoService {

    private final MedicoRepository repository;

    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }

    public String cadastrar(@Valid DadosCadastroMedico dados) {
        if (repository.existsByCrm(dados.crm()))
            return "Médico já cadastrado";
        else
            repository.save(new Medico(dados));
        return "Médico adicionado";
    }

}
