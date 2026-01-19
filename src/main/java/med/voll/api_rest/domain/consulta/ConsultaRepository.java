package med.voll.api_rest.domain.consulta;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByPacienteIdAndDataBetween(Long pacienteId, LocalDateTime inicio, LocalDateTime fim);

    @Query("""
                select count(c) > 0
                from Consulta c
                where c.medico.id = :medicoId
                  and c.status = true
                  and c.data <= :fim
                  and c.data + 1 hour >= :inicio
            """)
    boolean existsConsultaConflito(Long medicoId, LocalDateTime fim, LocalDateTime inicio);
}
