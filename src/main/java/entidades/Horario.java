package entidades;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Horario implements Serializable {
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,targetEntity = Turno.class,mappedBy = "horario",orphanRemoval = true)
    private List<Turno> turnoList;
    @Column(unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private String nombre;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=45,scale=0,precision=0)
    @Id
    private String codigo;

//    @Column(name="fecha_fin",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
//    @Temporal(TemporalType.DATE)
//    @Basic
//    private Date fechaFin;
    @Column(name="documento",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private String documento;
    @OneToMany(fetch = FetchType.LAZY,targetEntity = AsignacionHorario.class,mappedBy = "horario",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<AsignacionHorario> asignacionHorarioList;
//    @Column(name="fecha_inicio",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
//    @Temporal(TemporalType.DATE)
//    @Basic
//    private Date fechaInicio;
    //EL TIPO DE HORARIO PUEDE SER T = TECNICO O A = ADMINISTRATIVO / TECNICO ADMINISTRATIVO
    @Column(name = "tipo")
    private char tipo;
    
    public List<Turno> getTurnoList() {
        return turnoList;
    }

    public void setTurnoList(List<Turno> turnoList) {
        this.turnoList = turnoList;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public Horario() {

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
   
    public String getDocumento() {
        return this.documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }
   
    public List<AsignacionHorario> getAsignacionHorarioList() {
        return this.asignacionHorarioList;
    }

    public void setAsignacionHorarioList(List<AsignacionHorario> asignacionHorarioList) {
        this.asignacionHorarioList = asignacionHorarioList;
    }
}
