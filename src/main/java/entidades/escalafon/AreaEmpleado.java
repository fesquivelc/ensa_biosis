/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.escalafon;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "area_persona", schema = "personal")
public class AreaEmpleado implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "aper_id")
    private Long id;
    @Temporal(TemporalType.DATE)
    @Column(name = "aper_fecha_inicio", nullable = false)
    private Date fechaInicio;
    @Temporal(TemporalType.DATE)
    @Column(name = "aper_fecha_fin", nullable = true)
    private Date fechaFin;
    
    @ManyToOne(targetEntity = Departamento.class, optional = false)
    @JoinColumn(name = "area_id", referencedColumnName = "area_id")
    private Departamento departamento;
    @ManyToOne(targetEntity = Empleado.class, optional = false)
    @JoinColumn(name = "pers_nro_documento", referencedColumnName = "nro_documento")
    private Empleado empleado;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Departamento getDepartamento() {
        return departamento;
    }

    public void setDepartamento(Departamento departamento) {
        this.departamento = departamento;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    
}
