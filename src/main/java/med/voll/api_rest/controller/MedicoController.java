package med.voll.api_rest.controller;

import jakarta.validation.Valid;
import med.voll.api_rest.medico.DadosAtualizacaoMedico;
import med.voll.api_rest.medico.DadosCadastroMedico;
import med.voll.api_rest.medico.DadosDetalhamentoMedico;
import med.voll.api_rest.medico.DadosListagemMedico;
import med.voll.api_rest.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @PostMapping
    public String cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        return service.cadastrar(dados);
    }

    @GetMapping
    public Page<DadosListagemMedico> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        return service.listar(pageable);
    }

    @PutMapping
    public String atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados){
        return service.atualizar(dados);
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id){
        return service.deletar(id);
    }

    @GetMapping("/{id}")
    public DadosDetalhamentoMedico detalhar(@PathVariable Long id){
        return service.detalhar(id);
    }
}
