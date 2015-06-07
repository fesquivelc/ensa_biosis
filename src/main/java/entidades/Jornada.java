package entidades;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "Jornada")
public class Jornada implements Serializable {

//    @OneToMany(fetch = FetchType.LAZY,targetEntity = Horario.class,mappedBy = "jornada")
//    private List<Horario> horarioList;
    @Column(unique=false,updatable=true,insertable=true,nullable=false,length=45,scale=0,precision=0)
    @Id
    private String codigo;
    @Column(unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private String nombre;
    //PERMITE DEFINIR SI ES UNA JORNADA ADMINISTRATIVA O TECNICA
    @Column(name = "tipo")
    private char tipo = 'A';
    @Column(name = "activo")
    private boolean activo = true;
    @Column(name = "descripcion")
    private String descripcion;
    
    @OneToMany(targetEntity = DetalleJornada.class, mappedBy = "jornada",orphanRemoval = true,cascade = CascadeType.ALL)
    private List<DetalleJornada> detalleJornadaList;

    public List<DetalleJornada> getDetalleJornadaList() {
        return detalleJornadaList;
    }

    public void setDetalleJornadaList(List<DetalleJornada> detalleJornadaList) {
        this.detalleJornadaList = detalleJornadaList;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    
}
