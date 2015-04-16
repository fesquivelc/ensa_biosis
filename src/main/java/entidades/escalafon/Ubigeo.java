package entidades.escalafon;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="ubigeo",schema="parametricas")
public class Ubigeo implements Serializable {

    @Column(name="codigo")
    @Id
    private String codigo;
    @Column(name="departamento",nullable=false)
    @Basic
    private String departamento;
    @Column(name="distrito",nullable=false)
    @Basic
    private String distrito;
    @Column(name="provincia",nullable=false)
    @Basic
    private String provincia;

    public Ubigeo() {

    }
   
    public String getCodigo() {
        return this.codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
   
    public String getDepartamento() {
        return this.departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
   
    public String getDistrito() {
        return this.distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }
   
    public String getProvincia() {
        return this.provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
}
