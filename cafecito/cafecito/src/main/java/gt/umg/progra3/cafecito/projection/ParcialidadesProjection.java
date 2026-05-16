package gt.umg.progra3.cafecito.projection;


import java.time.LocalDateTime;

public interface ParcialidadesProjection {
    Long getIdParcialidad();
    String getNoCuenta();
    String getPesoTotal();
    Long getCantParcialidades();
    String getEstado();
    String getFechaEnvio();
}
