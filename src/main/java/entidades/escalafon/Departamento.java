package entidades.escalafon;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "area", schema = "institucional")
public class Departamento implements Serializable {

    @Column(name = "area_nombre")
    @Basic
    private String nombre;
    @Column(name = "area_id")
    @Id
    private Long codigo;
    @OneToMany(fetch = FetchType.LAZY, targetEntity = FichaLaboral.class, mappedBy = "area")
    private List<FichaLaboral> fichaLaboralList;
    @OneToMany(fetch = FetchType.LAZY, targetEntity = Departamento.class, mappedBy = "departamento")
    private List<Departamento> departamentoList;
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = Departamento.class,optional = true)
    @JoinColumn(name = "area_superior_id", referencedColumnName = "area_id")
    private Departamento departamento;

    public Long getCodigo() {
        return codigo;
    }

    public void setCodigo(Long codigo) {
        this.codigo = codigo;
    }

    

    public List<Departamento> getDepartamentoList() {
        return departamentoList;
    }

    public void setDepartamentoList(List<Departamento> departamentoList) {
        this.departamentoList = departamentoList;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public List<FichaLaboral> getFichaLaboralList() {
        return fichaLaboralList;
    }

    public void setFichaLaboralList(List<FichaLaboral> fichaLaboralList) {
        this.fichaLaboralList = fichaLaboralList;
    }

    public Departamento() {

    }

    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

}
