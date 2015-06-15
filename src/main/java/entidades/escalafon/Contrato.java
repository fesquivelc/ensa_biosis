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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author RyuujiMD
 */
@Entity
@Table(schema = "personal",name = "contrato")
public class Contrato implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cont_id")
    private Long id;
    @OrderColumn
    @Temporal(TemporalType.DATE)
    @Column(name = "cont_fecha_inicio", nullable = false)
    private Date fechaInicio;
    @Temporal(TemporalType.DATE)
    @Column(name = "cont_fecha_fin", nullable = true)
    private Date fechaFin;
    @ManyToOne(targetEntity = RegimenLaboral.class,optional = true)
    @JoinColumn(name = "reglab_codigo")
    private RegimenLaboral regimenLaboral;
    @ManyToOne(targetEntity = TipoContrato.class, optional = true)
    @JoinColumn(name = "tcon_codigo",referencedColumnName = "codigo")
    private TipoContrato tipoContrato;
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

    public RegimenLaboral getRegimenLaboral() {
        return regimenLaboral;
    }

    public void setRegimenLaboral(RegimenLaboral regimenLaboral) {
        this.regimenLaboral = regimenLaboral;
    }

    public TipoContrato getTipoContrato() {
        return tipoContrato;
    }

    public void setTipoContrato(TipoContrato tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
