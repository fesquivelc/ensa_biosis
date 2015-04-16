package entidades.escalafon;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo_via",schema="parametricas")
public class TipoVia implements Serializable {

    @Column(name="nombre",nullable=false)
    @Basic
    private String nombre;
    @Column(name="codigo")
    @Id
    private String codigo;
    @Column(name="abreviatura",length=140)
    @Basic
    private String abreviatura;

    public TipoVia() {

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
   
    public String getAbreviatura() {
        return this.abreviatura;
    }

    public void setAbreviatura(String abreviatura) {
        this.abreviatura = abreviatura;
    }
}
