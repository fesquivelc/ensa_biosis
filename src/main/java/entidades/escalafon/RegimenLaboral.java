package entidades.escalafon;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="regimen_laboral",schema="parametricas")
public class RegimenLaboral implements Serializable {

    @Column(name="nombre",nullable=false)
    @Basic
    private String nombre;
    @Column(name="codigo")
    @Id
    private String codigo;

    public RegimenLaboral() {

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

    @Override
    public String toString() {
        return  nombre;
    }
    
    
}
