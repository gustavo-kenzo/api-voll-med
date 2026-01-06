package med.voll.api_rest.controller;

import jakarta.validation.Valid;
import med.voll.api_rest.paciente.DadosCadastroPaciente;
import med.voll.api_rest.paciente.DadosListagemPacientes;
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
    public Page<DadosListagemPacientes> listar(@PageableDefault(size = 10, sort = {"nome"}) Pageable pageable){
        return service.listar(pageable);
    }


}
