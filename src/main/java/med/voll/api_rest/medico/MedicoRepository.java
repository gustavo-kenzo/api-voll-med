package med.voll.api_rest.medico;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<Medico, Long> {
    boolean existsByCrm(String crm);
}
