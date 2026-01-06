package med.voll.api_rest.medico;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api_rest.endereco.DadosEndereco;

public record DadosAtualizacaoMedico(
        @NotNull Long id,
        String nome,
        String telefone,
        @Valid DadosEndereco endereco
) {
}
