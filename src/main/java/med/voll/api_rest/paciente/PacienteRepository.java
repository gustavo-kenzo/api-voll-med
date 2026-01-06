package med.voll.api_rest.paciente;

import aj.org.objectweb.asm.commons.Remapper;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    boolean existsByCpf(String cpf);

    Page<Paciente> findAllByAtivoTrue(Pageable pageable);
}
