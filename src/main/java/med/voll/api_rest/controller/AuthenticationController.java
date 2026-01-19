package med.voll.api_rest.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import med.voll.api_rest.domain.usuario.DadosAutenticacao;
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

    @PostMapping
    public ResponseEntity login(@RequestBody @Valid DadosAutenticacao dados) {
        var authenticationToken = new UsernamePasswordAuthenticationToken(dados.login(), dados.senha());
        var authentication = manager.authenticate(authenticationToken);
        return ResponseEntity.ok().build();
    }
}
