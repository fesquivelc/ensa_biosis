/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.sisgedo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author RyuujiMD
 */
@Table(name = "SPa_boleta")
@Entity
public class Boleta implements Serializable {

    @EmbeddedId
    private BoletaPK boletaPK;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "fecapr")
    private Date aprobacionFechaHora;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "horasal")
    private Date salidaFechaHora;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "horaret")
    private Date retornoFechaHora;
    @Column(name = "descrip")
    private String descripcion;
    @Column(name = "observacion")
    private String observacion;
    
    @ManyToOne(targetEntity = UsuarioSISGEDO.class,optional = true)
    @JoinColumn(name = "login", referencedColumnName = "login",nullable = true)
    private UsuarioSISGEDO usuarioSISGEDO;
    
    @ManyToOne(targetEntity = Motivo.class,optional = false)
    @JoinColumn(name = "idmotivo", referencedColumnName = "idmotivo",insertable = false,updatable = false)
    private Motivo motivo;

    public Motivo getMotivo() {
        return motivo;
    }

    public void setMotivo(Motivo motivo) {
        this.motivo = motivo;
    }

    public BoletaPK getBoletaPK() {
        return boletaPK;
    }

    public void setBoletaPK(BoletaPK boletaPK) {
        this.boletaPK = boletaPK;
    }

    public Date getAprobacionFechaHora() {
        return aprobacionFechaHora;
    }

    public void setAprobacionFechaHora(Date aprobacionFechaHora) {
        this.aprobacionFechaHora = aprobacionFechaHora;
    }

    public Date getSalidaFechaHora() {
        return salidaFechaHora;
    }

    public void setSalidaFechaHora(Date salidaFechaHora) {
        this.salidaFechaHora = salidaFechaHora;
    }

    public Date getRetornoFechaHora() {
        return retornoFechaHora;
    }

    public void setRetornoFechaHora(Date retornoFechaHora) {
        this.retornoFechaHora = retornoFechaHora;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public UsuarioSISGEDO getUsuarioSISGEDO() {
        return usuarioSISGEDO;
    }

    public void setUsuarioSISGEDO(UsuarioSISGEDO usuarioSISGEDO) {
        this.usuarioSISGEDO = usuarioSISGEDO;
    }
    
    

}
