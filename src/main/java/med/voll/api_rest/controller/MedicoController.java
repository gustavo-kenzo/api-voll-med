package med.voll.api_rest.controller;

import jakarta.validation.Valid;
import med.voll.api_rest.medico.DadosCadastroMedico;
import med.voll.api_rest.service.MedicoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("medicos")
public class MedicoController {

    @Autowired
    private MedicoService service;

    @PostMapping
    @Transactional
    public String cadastrar(@RequestBody @Valid DadosCadastroMedico dados) {
        return service.cadastrar(dados);
    }
}
