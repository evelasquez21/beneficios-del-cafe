package gt.umg.progra3.cafecito.repository.agricultor;

import gt.umg.progra3.cafecito.model.agricultor.Parcialidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcialidadRepository extends JpaRepository<Parcialidad, Long> {
}
