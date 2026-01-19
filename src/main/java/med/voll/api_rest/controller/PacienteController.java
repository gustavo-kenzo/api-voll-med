package med.voll.api_rest.controller;

import jakarta.validation.Valid;
import med.voll.api_rest.paciente.DadosAtualizacaoPaciente;
import med.voll.api_rest.paciente.DadosCadastroPaciente;
import med.voll.api_rest.paciente.DadosDetalhamentoPaciente;
import med.voll.api_rest.paciente.DadosListagemPaciente;
import med.voll.api_rest.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
    @Autowired
    private PacienteService service;

    @PostMapping
    public String cadastrar(@RequestBody @Valid DadosCadastroPaciente dados){
        return service.cadastrar(dados);
    }

    @GetMapping
    public Page<DadosListagemPaciente> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        return service.listar(pageable);
    }

    @PutMapping
    public String atualizar(@RequestBody @Valid DadosAtualizacaoPaciente dados){
        return service.atualizar(dados);
    }

    @DeleteMapping("/{id}")
    public String deletar(@PathVariable Long id){
        return service.deletar(id);
    }

    @GetMapping("/{id}")
    public DadosDetalhamentoPaciente detalhar(@PathVariable Long id){
        return service.detalhar(id);
    }


}
