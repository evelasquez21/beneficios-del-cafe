package gt.umg.progra3.cafecito.repository.agricultor;

import gt.umg.progra3.cafecito.model.agricultor.Transportista;
import gt.umg.progra3.cafecito.projection.TransportistaDetalleProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TransportistaRepository extends JpaRepository<Transportista, Long> {
    Optional<Transportista> findByPersonaCui(Long cui);

    List<Transportista> findByPersonaEstado(int estado);

    @Query(value = """
                SELECT DISTINCT ON (t.id_transportista)
                  t.id_transportista AS id,
                  t.cui,
                  per.nombre_completo AS nombreCompleto,
                  t.tipo_licencia AS tipoLicencia,
                  t.fecha_vencimiento_licencia AS fechaVenLicencia,
                  CASE
                    WHEN per.estado = 1 THEN 'Activo'
                    WHEN per.estado = 2 THEN 'Eliminado'
                    ELSE 'N/A'
                  END AS estado,
            
                  CASE
                    WHEN par.fecha_entrega IS NULL AND tr.id_es_trans = 5 THEN 'Ocupado'
                    WHEN per.estado = 2 THEN 'N/A'
                    ELSE 'Libre'
                  END AS disponible,
                  COALESCE(CAST(par.id_parcialidad AS VARCHAR), 'N/A') AS pesajeA
                FROM
                  transportistas t
            
                  JOIN
                    personas per
                  ON (
                    per.cui = t.cui
                  )
                  LEFT JOIN
                    parcialidad par
                  ON (
                    par.id_transportista = t.id_transportista
                    AND par.fecha_entrega IS NULL
                  )
                  LEFT JOIN\s
                    transporte tr
                  ON (
                    tr.placa = par.transporte_placa
                    AND tr.id_es_trans = 5
                  )
                 ORDER BY
                   t.id_transportista,
                   par.fecha_envio DESC
            """, nativeQuery = true)
    List<TransportistaDetalleProjection> obtenerDetalleTransportistas();
}
