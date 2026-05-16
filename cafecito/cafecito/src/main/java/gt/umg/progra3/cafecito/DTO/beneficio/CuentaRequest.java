package gt.umg.progra3.cafecito.DTO.beneficio;

import jakarta.persistence.Column;

import java.time.LocalDateTime;

public class CuentaRequest {
    private Long idCuenta;
    private String origen;
    private Long idPesajeAgricultor;
    private Long nitAgricultor;
    private float pesoTotalEsperado;
    private String unidadMedida;
    private LocalDateTime fechaCreacion;

    public Long getIdCuenta() {
        return idCuenta;
    }

    public void setIdCuenta(Long idCuenta) {
        this.idCuenta = idCuenta;
    }

    public String getOrigen() {
        return origen;
    }

    public void setOrigen(String origen) {
        this.origen = origen;
    }

    public Long getIdPesajeAgricultor() {
        return idPesajeAgricultor;
    }

    public void setIdPesajeAgricultor(Long idPesajeAgricultor) {
        this.idPesajeAgricultor = idPesajeAgricultor;
    }

    public Long getNitAgricultor() {
        return nitAgricultor;
    }

    public void setNitAgricultor(Long nitAgricultor) {
        this.nitAgricultor = nitAgricultor;
    }

    public float getPesoTotalEsperado() {
        return pesoTotalEsperado;
    }

    public void setPesoTotalEsperado(float pesoTotalEsperado) {
        this.pesoTotalEsperado = pesoTotalEsperado;
    }

    public String getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(String unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
