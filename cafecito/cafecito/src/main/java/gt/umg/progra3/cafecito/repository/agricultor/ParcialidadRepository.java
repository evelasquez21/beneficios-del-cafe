package gt.umg.progra3.cafecito.repository.agricultor;

import gt.umg.progra3.cafecito.model.agricultor.Parcialidad;
import gt.umg.progra3.cafecito.projection.ParcialidadesProjection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParcialidadRepository extends JpaRepository<Parcialidad, Long> {

    List<Parcialidad> findByTransportePlaca(String placa);

    @Query(value = """
            SELECT DISTINCT ON (ag.id_agricultor)
                               ag.id_agricultor AS idAgricultor,
                               SUBSTR(ag.nit::TEXT, 1, LENGTH(ag.nit::TEXT) - 1) || '-' ||
                                 RIGHT(ag.nit::TEXT, 1) AS nit,
                               per.nombre_completo AS nombreCompleto,
                               TO_CHAR(COALESCE(ag.fecha_creacion::date), 'DD/MM/YYYY') AS fechaCreacion,
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
    List<ParcialidadesProjection> buscarParcialidades();
}
