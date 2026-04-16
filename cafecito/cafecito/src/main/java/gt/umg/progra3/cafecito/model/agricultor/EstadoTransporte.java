package gt.umg.progra3.cafecito.model.agricultor;

import jakarta.persistence.*;

@Entity
@Table(name = "estado_transporte")
public class EstadoTransporte {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id_es_trans")
    private int id;
    private String descripcion;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
