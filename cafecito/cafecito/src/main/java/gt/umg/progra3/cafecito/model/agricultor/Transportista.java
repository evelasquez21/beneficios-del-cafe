package gt.umg.progra3.cafecito.model.agricultor;

import gt.umg.progra3.cafecito.model.Persona;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "transportistas")
public class Transportista {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_transportista")
    private Long id;
    @Column(name = "tipo_licencia")
    private String tipoLicencia;
    @Column(name = "fecha_vencimiento_licencia")
    private LocalDate fechaVenLicencia;
    @OneToOne
    @JoinColumn(name = "cui", referencedColumnName = "cui", unique = true)
    private Persona persona;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoLicencia() {
        return tipoLicencia;
    }

    public void setTipoLicencia(String tipoLicencia) {
        this.tipoLicencia = tipoLicencia;
    }

    public LocalDate getFechaVenLicencia() {
        return fechaVenLicencia;
    }

    public void setFechaVenLicencia(LocalDate fechaVenLicencia) {
        this.fechaVenLicencia = fechaVenLicencia;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }
}
