package med.voll.api_rest.consulta;

import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;

public record DadosCadastroConsulta(
        @NotNull Long medicoId,
        @NotNull Long pacienteId,
        @NotNull LocalDateTime data) {
}
