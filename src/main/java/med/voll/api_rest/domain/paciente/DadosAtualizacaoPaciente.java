package med.voll.api_rest.domain.paciente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import med.voll.api_rest.domain.endereco.DadosEndereco;

public record DadosAtualizacaoPaciente(
        @NotNull Long id,
        String nome,
        String telefone,
        @Valid DadosEndereco endereco) {
}
