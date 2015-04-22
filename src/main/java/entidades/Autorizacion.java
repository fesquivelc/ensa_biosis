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
    @Column(name = "numero")
    private String numero;
    
    @ManyToOne(targetEntity = Empleado.class)
    @JoinColumn(name = "empleado_nro_documento",referencedColumnName = "nro_documento")
    private Empleado empleado;
    
    @ManyToOne(targetEntity = Turno.class)
    @JoinColumn(name = "turno_id",referencedColumnName = "id")
    private Turno turno;
    
    //PUEDE SER H = HORAS EXTRA, FLEXIBLE A OTROS TIPOS DE AUTORIZACION
    @Column(name = "tipo")
    private char tipo;
    
    @Column(name = "fecha")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date fecha;
    
    @Column(name = "descripcion")
    private String motivo;

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
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
