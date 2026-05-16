package gt.umg.progra3.cafecito.DTO.agricultor;

public class TransporteDTO {
    private String placa;
    private String marca;
    private String color;
    private String linea;
    private String modelo;
    private String estado;
    private String pesajeA;

    public TransporteDTO(String placa, String marca, String color, String linea, String modelo, String estado, String pesajeA) {
        this.placa = placa;
        this.marca = marca;
        this.color = color;
        this.linea = linea;
        this.modelo = modelo;
        this.estado = estado;
        this.pesajeA = pesajeA;
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getLinea() {
        return linea;
    }

    public void setLinea(String linea) {
        this.linea = linea;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getPesajeA() {
        return pesajeA;
    }

    public void setPesajeA(String pesajeA) {
        this.pesajeA = pesajeA;
    }
}
