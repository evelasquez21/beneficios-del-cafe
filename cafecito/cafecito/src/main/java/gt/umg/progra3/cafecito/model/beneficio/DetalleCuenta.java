package gt.umg.progra3.cafecito.model.beneficio;

import gt.umg.progra3.cafecito.model.UnidadMedida;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "detalle_cuenta")
public class DetalleCuenta {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "noCuenta")
    private Long noCuenta;
    @Column(name = "nit_agricultor")
    private Long nitAgricultor;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "estado_actual", referencedColumnName = "id_es_cuenta")
    private EstadoCuenta estadoActual;
    @Column(name = "cantidad_parciales")
    private int cantidadParciales;
    @Column(name = "diferencia_total")
    private float diferenciaTotal;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "abrev", referencedColumnName = "abrev", nullable = false)
    private UnidadMedida unidadMedida;
    private float tolerancia;

    @OneToMany(mappedBy = "detalleCuenta", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Cuenta> cuentas = new HashSet<>();

    public Long getNoCuenta() {
        return noCuenta;
    }

    public void setNoCuenta(Long noCuenta) {
        this.noCuenta = noCuenta;
    }

    public Long getNitAgricultor() {
        return nitAgricultor;
    }

    public void setNitAgricultor(Long nitAgricultor) {
        this.nitAgricultor = nitAgricultor;
    }

    public EstadoCuenta getEstadoActual() {
        return estadoActual;
    }

    public void setEstadoActual(EstadoCuenta estadoActual) {
        this.estadoActual = estadoActual;
    }

    public int getCantidadParciales() {
        return cantidadParciales;
    }

    public void setCantidadParciales(int cantidadParciales) {
        this.cantidadParciales = cantidadParciales;
    }

    public float getDiferenciaTotal() {
        return diferenciaTotal;
    }

    public void setDiferenciaTotal(float diferenciaTotal) {
        this.diferenciaTotal = diferenciaTotal;
    }

    public float getTolerancia() {
        return tolerancia;
    }

    public void setTolerancia(float tolerancia) {
        this.tolerancia = tolerancia;
    }

    public Set<Cuenta> getCuentas() {
        return cuentas;
    }

    public void setCuentas(Set<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public UnidadMedida getUnidadMedida() {
        return unidadMedida;
    }

    public void setUnidadMedida(UnidadMedida unidadMedida) {
        this.unidadMedida = unidadMedida;
    }
}
