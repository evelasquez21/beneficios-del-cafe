package gt.umg.progra3.cafecito.model.beneficio;

import gt.umg.progra3.cafecito.model.UnidadMedida;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;

@Entity
@Table(name = "cuentas")
public class Cuenta {
    @Id
    @Column(name = "id_cuenta")
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;
    private String origen;
    @Column(name = "id_pesaje_agricultor")
    private Long idPesajeAgricultor;
    @Column(name = "nit_agricultor")
    private Long nitAgricultor;
    @Column(name = "peso_total_esperado")
    private float pesoTotalEsperado;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "unidad_medida", referencedColumnName = "abrev", nullable = false)
    private UnidadMedida unidadMedida;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_es_cuenta", nullable = false)
    private EstadoCuenta estado;
    private float tolerancia;
    @Column(name = "diferencia-total")
    private float diferenciaTotal;
    @Column(name = "fecha_creacion")
    private LocalDateTime fechaCreacion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public EstadoCuenta getEstado() {
        return estado;
    }

    public void setEstado(EstadoCuenta estado) {
        this.estado = estado;
    }

    public float getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(float tolerancia) {
        this.tolerancia = tolerancia;
    }

    public float getDiferenciaTotal() {
        return diferenciaTotal;
    }

    public void setDiferenciaTotal(float diferenciaTotal) {
        this.diferenciaTotal = diferenciaTotal;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }
}
