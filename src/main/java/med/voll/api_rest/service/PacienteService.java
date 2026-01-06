package med.voll.api_rest.service;

import med.voll.api_rest.paciente.*;
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

    public Page<DadosListagemPaciente> listar(Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable).map(DadosListagemPaciente::new);
    }

    @Transactional
    public String atualizar(DadosAtualizacaoPaciente dados) {
        if (!repository.existsById(dados.id()))
            return "Paciente não existe";
        var paciente = repository.getReferenceById(dados.id());
        return paciente.atualizar(dados);
    }

    @Transactional
    public String deletar(Long id) {
        if (!repository.existsById(id))
            return "Paciente não existe";
        var paciente = repository.getReferenceById(id);
        return paciente.excluir();
    }
}
