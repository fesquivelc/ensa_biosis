package entidades.escalafon;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="v_area",schema = "institucional")
public class Area implements Serializable {

    @Column(name="nombre")
    @Basic
    private String nombre;
    @Column(name="codigo")
    @Id
    private String codigo;
    @OneToMany(fetch = FetchType.LAZY,targetEntity = FichaLaboral.class,mappedBy = "area")
    private List<FichaLaboral> fichaLaboralList;

    public List<FichaLaboral> getFichaLaboralList() {
        return fichaLaboralList;
    }

    public void setFichaLaboralList(List<FichaLaboral> fichaLaboralList) {
        this.fichaLaboralList = fichaLaboralList;
    }

    public Area() {

    }
   
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId() {
        return codigo;
    }

    public void setId(String id) {
        this.codigo = id;
    }
}
