/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import entidades.escalafon.Empleado;
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

/**
 *
 * @author fesquivelc
 */
@Entity
@Table(name = "autorizacion")
public class Autorizacion implements Serializable {
    @Id
    @Column(name = "aut_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "aut_documento_referencia",nullable = true)
    private String documentoReferencia;
    
    @ManyToOne(targetEntity = Empleado.class)
    @JoinColumn(name = "pers_nro_documento",referencedColumnName = "nro_documento")
    private Empleado empleado;
    
    @ManyToOne(targetEntity = DetalleJornada.class)
    @JoinColumn(name = "dtjorn_id",referencedColumnName = "dtjorn_id")
    private DetalleJornada detalleJornada;
    
    //PUEDE SER H = HORAS EXTRA, FLEXIBLE A OTROS TIPOS DE AUTORIZACION
    @Column(name = "aut_tipo")
    private char tipo = 'E';
    
    @Column(name = "aut_fecha")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    
    @Column(name = "aut_motivo")
    private String motivo;    

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocumentoReferencia() {
        return documentoReferencia;
    }

    public void setDocumentoReferencia(String documentoReferencia) {
        this.documentoReferencia = documentoReferencia;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public DetalleJornada getDetalleJornada() {
        return detalleJornada;
    }

    public void setDetalleJornada(DetalleJornada detalleJornada) {
        this.detalleJornada = detalleJornada;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    
    
}
