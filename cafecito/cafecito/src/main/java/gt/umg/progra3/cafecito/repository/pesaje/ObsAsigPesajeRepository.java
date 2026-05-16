package gt.umg.progra3.cafecito.repository.pesaje;

import gt.umg.progra3.cafecito.model.pesaje.AsignacionObsPesaje;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ObsAsigPesajeRepository extends JpaRepository<AsignacionObsPesaje, Long> {
}
