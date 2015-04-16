package entidades.escalafon;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="area",schema="institucional")
public class Area implements Serializable {

    @Column(name="nombre")
    @Basic
    private String nombre;
    @Column(name="codigo")
    @Id
    private String codigo;

    public Area() {

    }
   
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
}
