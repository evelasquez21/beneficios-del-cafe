package gt.umg.progra3.cafecito.repository.agricultor;

import gt.umg.progra3.cafecito.model.agricultor.EstadoTransporte;
import gt.umg.progra3.cafecito.model.agricultor.Transporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransporteRepository extends JpaRepository<Transporte, String> {
    List<Transporte> findByEstadoId(int estado);
}
