package gt.umg.progra3.cafecito.model.pesaje;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "boleta")
public class Boleta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    @Column(name = "fecha_boleta")
    private LocalDateTime fechaBoleta;
    @OneToOne
    @JoinColumn(name = "id_pesaje", unique = true)
    private Pesaje pesaje;
    private float merma;
    @Column(name = "porcentaje-merma")
    private float porcentajeMerma;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getFechaBoleta() {
        return fechaBoleta;
    }

    public void setFechaBoleta(LocalDateTime fechaBoleta) {
        this.fechaBoleta = fechaBoleta;
    }

    public Pesaje getPesaje() {
        return pesaje;
    }

    public void setPesaje(Pesaje pesaje) {
        this.pesaje = pesaje;
    }

    public float getMerma() {
        return merma;
    }

    public void setMerma(float merma) {
        this.merma = merma;
    }

    public float getPorcentajeMerma() {
        return porcentajeMerma;
    }

    public void setPorcentajeMerma(float porcentajeMerma) {
        this.porcentajeMerma = porcentajeMerma;
    }
}
