package med.voll.api_rest.service;

import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
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
    public DadosDetalhamentoMedico cadastrar(DadosCadastroMedico dados) {
        if (repository.existsByCrm(dados.crm()))
            throw new EntityExistsException("Médico já cadastrado");
        else {
            var medico = new Medico(dados);
            repository.save(medico);
            return new DadosDetalhamentoMedico(medico);
        }
    }

    public Page<DadosListagemMedico> listar(Pageable pageable) {
        return repository.findAllByAtivoTrue(pageable).map(DadosListagemMedico::new);
    }

    //CREATE
    @Transactional
    public DadosDetalhamentoMedico atualizar(DadosAtualizacaoMedico dados) {
        if (!repository.existsById(dados.id()))
            throw new EntityNotFoundException("Médico não existe. Procure outro ou cadastre");
        var medicoAntigo = repository.getReferenceById(dados.id());
        var medicoAtualizado = medicoAntigo.atualizar(dados);
        return new DadosDetalhamentoMedico(medicoAtualizado);
    }

    @Transactional
    public void deletar(Long id) {
        if (!repository.existsById(id))
            throw new EntityNotFoundException("Médico não existe. Procure outro ou cadastre");
        var medico = repository.getReferenceById(id);
        medico.excluir();
    }

    public DadosDetalhamentoMedico detalhar(Long id) {
        var medico = repository.getReferenceById(id);
        return new DadosDetalhamentoMedico(medico);
    }
}
