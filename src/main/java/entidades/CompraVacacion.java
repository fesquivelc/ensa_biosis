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
import javax.persistence.TemporalType;

/**
 *
 * @author fesquivelc
 */
@Entity
@Table(name = "compra_vacacion")
public class CompraVacacion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;        
    @ManyToOne(targetEntity = Empleado.class)
    @JoinColumn(name = "empleado_nro_documento",referencedColumnName = "nro_documento")
    private Empleado empleado;
    @ManyToOne(targetEntity =Periodo.class)
    @JoinColumn(name = "periodo_anio",referencedColumnName = "anio")
    private Periodo periodo;
    @Column(name = "dias_comprados")
    private int diasCompra;
    @Column(name = "fecha")
    @Temporal(TemporalType.DATE)
    private Date fecha;
    @Column(name = "documento_referencia")
    private String documentoReferencia;

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDocumentoReferencia() {
        return documentoReferencia;
    }

    public void setDocumentoReferencia(String documentoReferencia) {
        this.documentoReferencia = documentoReferencia;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }

    public Periodo getPeriodo() {
        return periodo;
    }

    public void setPeriodo(Periodo periodo) {
        this.periodo = periodo;
    }

    public int getDiasCompra() {
        return diasCompra;
    }

    public void setDiasCompra(int diasCompra) {
        this.diasCompra = diasCompra;
    }
    
    
    
}
