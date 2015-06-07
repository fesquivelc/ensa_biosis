/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

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
import javax.persistence.OrderBy;
import javax.persistence.OrderColumn;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author RyuujiMD
 */
@Entity
@Table(name = "detalle_jornada")
public class DetalleJornada implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dtjorn_id")
    private Long id;
    
    @Column(name = "dtjorn_entrada")
    @Temporal(TemporalType.TIME)
    @OrderBy
    private Date entrada;
    
    @Column(name = "dtjorn_entrada_desde")
    @Temporal(TemporalType.TIME)
    private Date entradaDesde;
    
    @Column(name = "dtjorn_entrada_hasta")
    @Temporal(TemporalType.TIME)
    private Date entradaHasta;
    
    @Column(name = "dtjorn_entrada_tolerancia")
    @Temporal(TemporalType.TIME)
    private Date entradaTolerancia;
    
    @Column(name = "dtjorn_salida")
    @Temporal(TemporalType.TIME)
    private Date salida;
    
    @Column(name = "dtjorn_salida_desde")
    @Temporal(TemporalType.TIME)
    private Date salidaDesde;
    
    @Column(name = "dtjorn_salida_hasta")
    @Temporal(TemporalType.TIME)
    private Date salidaHasta;
    
    @Column(name = "dtjorn_salida_dia_siguiente")
    private boolean salidaDiaSiguiente;
    
    @ManyToOne(targetEntity = Jornada.class,fetch = FetchType.EAGER)
    @JoinColumn(name = "jorn_codigo",referencedColumnName = "codigo")
    private Jornada jornada;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public Date getEntradaDesde() {
        return entradaDesde;
    }

    public void setEntradaDesde(Date entradaDesde) {
        this.entradaDesde = entradaDesde;
    }

    public Date getEntradaHasta() {
        return entradaHasta;
    }

    public void setEntradaHasta(Date entradaHasta) {
        this.entradaHasta = entradaHasta;
    }

    public Date getEntradaTolerancia() {
        return entradaTolerancia;
    }

    public void setEntradaTolerancia(Date entradaTolerancia) {
        this.entradaTolerancia = entradaTolerancia;
    }

    public Date getSalida() {
        return salida;
    }

    public void setSalida(Date salida) {
        this.salida = salida;
    }

    public Date getSalidaDesde() {
        return salidaDesde;
    }

    public void setSalidaDesde(Date salidaDesde) {
        this.salidaDesde = salidaDesde;
    }

    public Date getSalidaHasta() {
        return salidaHasta;
    }

    public void setSalidaHasta(Date salidaHasta) {
        this.salidaHasta = salidaHasta;
    }

    public boolean isSalidaDiaSiguiente() {
        return salidaDiaSiguiente;
    }

    public void setSalidaDiaSiguiente(boolean salidaDiaSiguiente) {
        this.salidaDiaSiguiente = salidaDiaSiguiente;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    
    
    
    
}
