package med.voll.api_rest.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import med.voll.api_rest.domain.usuario.DadosAutenticacao;
import med.voll.api_rest.domain.usuario.Usuario;
import med.voll.api_rest.infra.security.DadosTokenJWT;
import med.voll.api_rest.service.TokenService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@AllArgsConstructor
public class AuthenticationController {

    private AuthenticationManager manager;
    private TokenService tokenService;

    @PostMapping
    public ResponseEntity<DadosTokenJWT> login(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);
        var token = tokenService.gerarToken((Usuario) authentication.getPrincipal());
        return ResponseEntity.ok(new DadosTokenJWT(token));
    }
}
