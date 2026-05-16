package gt.umg.progra3.cafecito.DTO.beneficio;

public class QrParcialidad {
    private Long idParcialidad;
    private Long idPesoM;
    private String placa;
    private Long nitAgricultor;
    private float pesoEsperado;

    public QrParcialidad(Long idParcialidad, Long idPesoM, String placa, Long nitAgricultor, float pesoEsperado) {
        this.idParcialidad = idParcialidad;
        this.idPesoM = idPesoM;
        this.placa = placa;
        this.nitAgricultor = nitAgricultor;
        this.pesoEsperado = pesoEsperado;
    }

    public Long getIdParcialidad() {
        return idParcialidad;
    }

    public void setIdParcialidad(Long idParcialidad) {
        this.idParcialidad = idParcialidad;
    }

    public Long getIdPesoM() {
        return idPesoM;
    }

    public void setIdPesoM(Long idPesoM) {
        this.idPesoM = idPesoM;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public Long getNitAgricultor() {
        return nitAgricultor;
    }

    public void setNitAgricultor(Long nitAgricultor) {
        this.nitAgricultor = nitAgricultor;
    }

    public float getPesoEsperado() {
        return pesoEsperado;
    }

    public void setPesoEsperado(float pesoEsperado) {
        this.pesoEsperado = pesoEsperado;
    }
}
