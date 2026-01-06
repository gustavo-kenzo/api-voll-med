package med.voll.api_rest.paciente;

import med.voll.api_rest.medico.Medico;

public record DadosListagemPacientes(
        Long id,
        String nome,
        String email,
        String cpf
) {
    public DadosListagemPacientes(Paciente paciente) {
        this(
                paciente.getId(),
                paciente.getNome(),
                paciente.getEmail(),
                paciente.getCpf());
    }

}
