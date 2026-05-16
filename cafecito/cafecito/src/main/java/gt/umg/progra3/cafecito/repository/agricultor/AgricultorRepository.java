package gt.umg.progra3.cafecito.repository.agricultor;

import gt.umg.progra3.cafecito.model.agricultor.Agricultor;
import gt.umg.progra3.cafecito.projection.AgricultorDetalleProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface AgricultorRepository extends JpaRepository<Agricultor, Long> {
    Optional<Agricultor> findByPersonaCui(Long cui);

    Optional<Agricultor> findByNit(Long nit);

    List<Agricultor> findByPersonaEstado(int estado);

    @Query(value = """
            SELECT DISTINCT ON (ag.id_agricultor)
              ag.id_agricultor AS idAgricultor,
              SUBSTR(ag.nit::TEXT, 1, LENGTH(ag.nit::TEXT) - 1) || '-' ||\s
                RIGHT(ag.nit::TEXT, 1) AS nit,
              per.nombre_completo AS nombreCompleto,
              ag.fecha_creacion AS fechaCreacion,
              COUNT(
                CASE WHEN bag.accion LIKE '%nueva parcialidad%' THEN 1 END
              ) AS cuentasReg,
              COUNT(
                CASE WHEN bag.accion LIKE '%nuevo transporte%' THEN 1 END
              ) AS transportesReg,
              COUNT(
                CASE WHEN bag.accion LIKE '%nuevo transportista%' THEN 1 END
              ) AS transportistasReg
            FROM
              agricultores ag
            
              INNER JOIN
                personas per
              ON (
                per.cui = ag.cui
              )
              INNER JOIN
                usuarios u
              ON (
                u.cui = ag.cui
              )
              LEFT JOIN
                bitacora_agricultor bag
              ON (
                bag.id_usuario = u.id_usuario
              )
            GROUP BY
              ag.id_agricultor,
              ag.nit,
              per.nombre_completo,
              ag.fecha_creacion
            ORDER BY
              ag.id_agricultor
            """, nativeQuery = true)
    List<AgricultorDetalleProjection> obtenerListadoAgricultores();
}
