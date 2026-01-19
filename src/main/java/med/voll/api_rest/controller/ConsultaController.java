package med.voll.api_rest.controller;

import jakarta.validation.Valid;
import med.voll.api_rest.domain.consulta.DadosCadastroConsulta;
import med.voll.api_rest.service.ConsultaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("consulta")
public class ConsultaController {

    @Autowired
    private ConsultaService service;

    @PostMapping
    public String agendarConsulta(@RequestBody @Valid DadosCadastroConsulta dados) {
        return service.agendar(dados);
    }
}
