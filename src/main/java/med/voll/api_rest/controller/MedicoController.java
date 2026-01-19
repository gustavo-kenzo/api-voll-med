package med.voll.api_rest.controller;

import jakarta.validation.Valid;
import med.voll.api_rest.domain.medico.DadosAtualizacaoMedico;
import med.voll.api_rest.domain.medico.DadosCadastroMedico;
import med.voll.api_rest.domain.medico.DadosDetalhamentoMedico;
import med.voll.api_rest.domain.medico.DadosListagemMedico;
import med.voll.api_rest.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @PostMapping
    public ResponseEntity cadastrar(@RequestBody @Valid DadosCadastroMedico dados, UriComponentsBuilder uriBuilder) {
        var dadosMedico = service.cadastrar(dados);
        var uri = uriBuilder.path("/medicos/{id}").buildAndExpand(dadosMedico.id()).toUri();
        return ResponseEntity.created(uri).body(dadosMedico);
    }

    @GetMapping
    public ResponseEntity<Page<DadosListagemMedico>> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable) {
        return ResponseEntity.ok(service.listar(pageable));
    }

    @PutMapping
    public ResponseEntity<DadosDetalhamentoMedico> atualizar(@RequestBody @Valid DadosAtualizacaoMedico dados) {
        var dadosMedico = service.atualizar(dados);
        return ResponseEntity.ok(dadosMedico);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DadosDetalhamentoMedico> detalhar(@PathVariable Long id) {
        var dadosMedico = service.detalhar(id);
        return ResponseEntity.ok((dadosMedico));
    }
}
