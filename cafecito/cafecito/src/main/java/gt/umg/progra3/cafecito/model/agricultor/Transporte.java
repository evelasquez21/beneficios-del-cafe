package gt.umg.progra3.cafecito.model.agricultor;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "transporte")
public class Transporte {
    @Id
    private String placa;
    private String marca;
    private String color;
    private String linea;
    private String modelo;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_es_trans", nullable = false)
    private EstadoTransporte estado;

    @OneToMany(mappedBy = "transporte", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ObservacionesTransporte> observaciones = new HashSet<>();

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

    public EstadoTransporte getEstado() {
        return estado;
    }

    public void setEstado(EstadoTransporte estado) {
        this.estado = estado;
    }

    public Set<ObservacionesTransporte> getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(Set<ObservacionesTransporte> observaciones) {
        this.observaciones = observaciones;
    }
}
