package gt.umg.progra3.cafecito.repository.beneficio;

import gt.umg.progra3.cafecito.model.beneficio.ParcialidadBen;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParcialidadBenRepository extends JpaRepository<ParcialidadBen, Long> {
    Long countByCuentaId(Long idCuenta);
}
