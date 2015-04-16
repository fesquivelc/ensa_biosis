package entidades.escalafon;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="tipo_contrato",schema="parametricas")
public class TipoContrato implements Serializable {

    @Column(name="codigo")
    @Id
    private String codigo;
    @Column(name="descripcion")
    @Basic
    private String descripcion;

    public TipoContrato() {

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
