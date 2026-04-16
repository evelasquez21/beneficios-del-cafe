package gt.umg.progra3.cafecito.model.beneficio;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "estado_cuenta")
public class EstadoCuenta {
    @Id
    @Column(name = "id_es_cuenta")
    private int id;
    private String descripcion;
}
