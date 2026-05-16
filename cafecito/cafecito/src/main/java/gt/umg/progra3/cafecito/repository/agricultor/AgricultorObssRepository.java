package gt.umg.progra3.cafecito.repository.agricultor;

import gt.umg.progra3.cafecito.model.agricultor.ObservacionesAgricultor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AgricultorObssRepository extends JpaRepository<ObservacionesAgricultor, Long> {
        List<ObservacionesAgricultor> findByAgricultorId(Long idAgricultor);
}
