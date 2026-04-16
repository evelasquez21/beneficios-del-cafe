package gt.umg.progra3.cafecito.model.agricultor;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pesaje_maestro")
public class PesajeMaestro {
    @Id
    @Column(name = "id_pesaje_maestro")
    private Long id;
    private float pesoTotal;
    @Column(name = "cant_parcialidades")
    private int cantParcialidades;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado", referencedColumnName = "id_es_pesaje_maestro", nullable = false)
    private EstadoPesajeMaestro estado;

    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPesoTotal() {
        return pesoTotal;
    }

    public void setPesoTotal(float pesoTotal) {
        this.pesoTotal = pesoTotal;
    }

    public int getCantParcialidades() {
        return cantParcialidades;
    }

    public void setCantParcialidades(int cantParcialidades) {
        this.cantParcialidades = cantParcialidades;
    }

    public EstadoPesajeMaestro getEstado() {
        return estado;
    }

    public void setEstado(EstadoPesajeMaestro estado) {
        this.estado = estado;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

}
