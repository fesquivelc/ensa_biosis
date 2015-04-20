package entidades;

import entidades.escalafon.Empleado;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="v_marcaciones",schema = "biostar")
public class Marcacion implements Serializable {

//    @Column(name="empleado_nombre")
//    @Basic
//    private String nombre;
    @Column(name="equipo_ip",nullable=false)
    @Basic
    private String equipo;
    @Id
    private Long id;
    @Column(nullable=false)
    @Temporal(TemporalType.TIME)
    @Basic
    private Date hora;
    @Column(nullable=false)
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fecha;
    @ManyToOne(targetEntity = Empleado.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "empleado_nro_documento",referencedColumnName = "nro_documento")
    private Empleado empleado;

    public Marcacion() {

    }
   
//    public String getNombre() {
//        return this.nombre;
//    }
//
//    public void setNombre(String nombre) {
//        this.nombre = nombre;
//    }
   
    public String getEquipo() {
        return this.equipo;
    }

    public void setEquipo(String equipo) {
        this.equipo = equipo;
    }
   
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public Date getHora() {
        return this.hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }
   
    public Date getFecha() {
        return this.fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
   
    
}
