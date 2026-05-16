package gt.umg.progra3.cafecito.projection;

public interface AgricultorDetalleProjection {
    Long getIdAgricultor();
    String getNit();
    String getNombreCompleto();
    String getFechaCreacion();
    Long getCuentasReg();
    Long getTransportesReg();
    Long getTransportistasReg();
}
