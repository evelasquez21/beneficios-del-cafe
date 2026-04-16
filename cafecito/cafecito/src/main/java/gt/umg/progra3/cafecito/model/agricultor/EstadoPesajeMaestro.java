package gt.umg.progra3.cafecito.model.agricultor;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "estado_pesaje_maestro")
public class EstadoPesajeMaestro {
    @Id
    @Column(name = "id_es_pesaje_maestro")
    private int id;
    private String descripcion;
}
