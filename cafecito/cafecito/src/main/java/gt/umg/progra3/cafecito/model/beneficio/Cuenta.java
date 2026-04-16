package gt.umg.progra3.cafecito.model.beneficio;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "cuentas")
public class Cuenta {
    @Id
    @Column(name = "id_cuenta")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "no_cuenta", referencedColumnName = "noCuenta", nullable = false)
    private DetalleCuenta detalleCuenta;
    @Column(name = "id_parcialidad")
    private Long idParcialidad;
    @Column(name = "diferencia")
    private float diferencia;
    @Column(name = "fecha_recepcion")
    private LocalDateTime fechaRecepcion;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public DetalleCuenta getDetalleCuenta() {
        return detalleCuenta;
    }

    public void setDetalleCuenta(DetalleCuenta detalleCuenta) {
        this.detalleCuenta = detalleCuenta;
    }

    public Long getIdParcialidad() {
        return idParcialidad;
    }

    public void setIdParcialidad(Long idParcialidad) {
        this.idParcialidad = idParcialidad;
    }

    public float getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(float diferencia) {
        this.diferencia = diferencia;
    }

    public LocalDateTime getFechaRecepcion() {
        return fechaRecepcion;
    }

    public void setFechaRecepcion(LocalDateTime fechaRecepcion) {
        this.fechaRecepcion = fechaRecepcion;
    }
}
