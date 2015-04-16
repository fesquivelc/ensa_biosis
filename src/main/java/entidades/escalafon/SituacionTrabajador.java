package entidades.escalafon;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="situacion_trabajador",schema="parametricas")
public class SituacionTrabajador implements Serializable {

    @Column(name="codigo")
    @Id
    private String codigo;
    @Column(name="descripcion",nullable=false)
    @Basic
    private String descripcion;

    public SituacionTrabajador() {

    }
   
    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
   
    public String getDescripcion() {
        return this.descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
}
