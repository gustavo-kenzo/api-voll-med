package med.voll.api_rest.service;

import med.voll.api_rest.medico.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MedicoService {

    private final MedicoRepository repository;

    public MedicoService(MedicoRepository repository) {
        this.repository = repository;
    }

    @Transactional
    public String cadastrar(DadosCadastroMedico dados) {
        if (repository.existsByCrm(dados.crm()))
            return "Médico já cadastrado";
        else
            repository.save(new Medico(dados));
        return "Médico adicionado";
    }

    public Page<DadosListagemMedico> listar(Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable).map(DadosListagemMedico::new);
    }

    @Transactional
    public String atualizar(DadosAtualizacaoMedico dados) {
        if (!repository.existsById(dados.id()))
            return "Médico não existe. Procure outro ou cadastre";
        var medico = repository.getReferenceById(dados.id());
        medico.atualizar(dados);
        return null;
    }
}
