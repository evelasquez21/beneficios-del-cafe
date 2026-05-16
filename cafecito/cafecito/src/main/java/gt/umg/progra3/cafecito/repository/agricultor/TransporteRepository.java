package gt.umg.progra3.cafecito.repository.agricultor;

import gt.umg.progra3.cafecito.DTO.agricultor.TransporteDTO;
import gt.umg.progra3.cafecito.model.agricultor.Transporte;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransporteRepository extends JpaRepository<Transporte, String> {
    List<Transporte> findByEstadoId(int estado);

    @Query("SELECT new gt.umg.progra3.cafecito.DTO.agricultor.TransporteDTO(\n" +
            "  t.placa,\n" +
            "  t.marca,\n" +
            "  t.color,\n" +
            "  t.linea,\n" +
            "  t.modelo,\n" +
            "  es_t.descripcion,\n" +
            "  COALESCE(CAST(p.id AS string), 'N/A'))\n" +
            "From\n" +
            "  Transporte t\n" +
            "      \n" +
            "  LEFT JOIN\n" +
            "    t.estado es_t\n" +
            "  LEFT JOIN\n" +
            "    Parcialidad p\n" +
            "  ON (\n" +
            "    p.transporte.placa = t.placa\n" +
            "    AND t.estado.id = :id\n" +
            "    AND p.fechaEntrega IS NULL \n" +
            "  )")
    List<TransporteDTO> BuscarTransportePorPesajeAsociado(@Param("id") int id);

    long countByEstadoId(int idEstado);
}
