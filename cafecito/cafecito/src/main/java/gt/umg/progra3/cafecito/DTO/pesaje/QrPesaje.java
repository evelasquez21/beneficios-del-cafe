package gt.umg.progra3.cafecito.DTO.pesaje;

import gt.umg.progra3.cafecito.model.UnidadMedida;

public class QrPesaje {
    private Long idPesaje;
    private Long noCuenta;
    private float pesoObtenido;
    private float pesoEsperado;

    public Long getIdPesaje() {
        return idPesaje;
    }

    public void setIdPesaje(Long idPesaje) {
        this.idPesaje = idPesaje;
    }

    public Long getNoCuenta() {
        return noCuenta;
    }

    public void setNoCuenta(Long noCuenta) {
        this.noCuenta = noCuenta;
    }

    public float getPesoObtenido() {
        return pesoObtenido;
    }

    public void setPesoObtenido(float pesoObtenido) {
        this.pesoObtenido = pesoObtenido;
    }

    public float getPesoEsperado() {
        return pesoEsperado;
    }

    public void setPesoEsperado(float pesoEsperado) {
        this.pesoEsperado = pesoEsperado;
    }
}
