package gt.umg.progra3.cafecito.projection;

import java.time.LocalDate;

public interface TransportistaDetalleProjection {
    String getId();
    String getCui();
    String getNombreCompleto();
    String getTipoLicencia();
    LocalDate getFechaVenLicencia();
    String getEstado();
    String getDisponible();
    String getPesajeA();
}
