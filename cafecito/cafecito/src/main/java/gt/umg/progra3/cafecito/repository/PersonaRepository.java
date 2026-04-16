package gt.umg.progra3.cafecito.repository;

import gt.umg.progra3.cafecito.model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonaRepository extends JpaRepository<Persona, Long> {
}
