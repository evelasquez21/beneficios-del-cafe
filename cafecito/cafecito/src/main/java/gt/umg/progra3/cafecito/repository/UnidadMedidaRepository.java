package gt.umg.progra3.cafecito.repository;

import gt.umg.progra3.cafecito.model.UnidadMedida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnidadMedidaRepository extends JpaRepository<UnidadMedida, String> {
}
