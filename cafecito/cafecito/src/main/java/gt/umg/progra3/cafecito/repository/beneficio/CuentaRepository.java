package gt.umg.progra3.cafecito.repository.beneficio;

import gt.umg.progra3.cafecito.model.beneficio.Cuenta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CuentaRepository extends JpaRepository<Cuenta, Long> {
}
