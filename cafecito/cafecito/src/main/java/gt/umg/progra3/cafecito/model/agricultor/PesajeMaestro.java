package gt.umg.progra3.cafecito.model.agricultor;

import gt.umg.progra3.cafecito.model.UnidadMedida;
import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "pesaje_maestro")
public class PesajeMaestro {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_pesaje_maestro")
    private Long id;
    private float pesoTotal;
    @Column(name = "cant_parcialidades")
    private int cantParcialidades;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "nit_agricultor", referencedColumnName = "nit", nullable = false)
    private Agricultor agricultor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_es_pesaje_maestro", nullable = false)
    private EstadoPesajeMaestro estado;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidad_medida", referencedColumnName = "abrev", nullable = false)
    private UnidadMedida unidadMedida;

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

    public Agricultor getAgricultor() {
        return agricultor;
    }

    public void setAgricultor(Agricultor agricultor) {
        this.agricultor = agricultor;
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

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
}
