package med.voll.api_rest.controller;

import jakarta.validation.Valid;
import med.voll.api_rest.paciente.DadosCadastroPaciente;
import med.voll.api_rest.service.PacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("pacientes")
public class PacienteController {
    @Autowired
    private PacienteService service;

    @PostMapping
    public String cadastrar(@RequestBody @Valid DadosCadastroPaciente dados){
        return service.cadastrar(dados);
    }

}
