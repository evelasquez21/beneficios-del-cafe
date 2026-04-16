package gt.umg.progra3.cafecito.repository.agricultor;

import gt.umg.progra3.cafecito.model.agricultor.Agricultor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AgricultorRepository extends JpaRepository<Agricultor, Long> {
    Optional<Agricultor> findByPersonaCui(Long cui);

    List<Agricultor> findByPersonaEstado(int estado);
}
