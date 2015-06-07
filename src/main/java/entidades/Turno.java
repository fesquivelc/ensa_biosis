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
@Table(name = "turno")
public class Turno implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne(targetEntity = Horario.class)
    @JoinColumn(name = "horario_codigo",referencedColumnName = "codigo")
    private Horario horario;
    @ManyToOne(targetEntity = Jornada.class)
    @JoinColumn(name = "jornada_codigo",referencedColumnName = "codigo")
    private Jornada jornada;
    
    //DISPOSICION DE DIAS PARA TRABAJAR S = SEMANAL, F = POR FECHA
    @Column(name = "tipo")
    private char tipo;
    
    //POR SI LA GESTION DEL HORARIO SEA POR FECHAS
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_inicio")
    private Date fechaInicio;
    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_fin")
    private Date fechaFin;
    
    //POR SI LA GESTION ES POR DIAS DE LA SEMANA
    @Column(name = "domingo")
    private boolean domingo;
    @Column(name = "lunes")
    private boolean lunes;
    @Column(name = "martes")
    private boolean martes;
    @Column(name = "miercoles")
    private boolean miercoles;
    @Column(name = "jueves")
    private boolean jueves;
    @Column(name = "viernes")
    private boolean viernes;
    @Column(name = "sabado")
    private boolean sabado;

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Horario getHorario() {
        return horario;
    }

    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public Jornada getJornada() {
        return jornada;
    }

    public void setJornada(Jornada jornada) {
        this.jornada = jornada;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public boolean isDomingo() {
        return domingo;
    }

    public void setDomingo(boolean domingo) {
        this.domingo = domingo;
    }

    public boolean isLunes() {
        return lunes;
    }

    public void setLunes(boolean lunes) {
        this.lunes = lunes;
    }

    public boolean isMartes() {
        return martes;
    }

    public void setMartes(boolean martes) {
        this.martes = martes;
    }

    public boolean isMiercoles() {
        return miercoles;
    }

    public void setMiercoles(boolean miercoles) {
        this.miercoles = miercoles;
    }

    public boolean isJueves() {
        return jueves;
    }

    public void setJueves(boolean jueves) {
        this.jueves = jueves;
    }

    public boolean isViernes() {
        return viernes;
    }

    public void setViernes(boolean viernes) {
        this.viernes = viernes;
    }

    public boolean isSabado() {
        return sabado;
    }

    public void setSabado(boolean sabado) {
        this.sabado = sabado;
    }
    
    
    
}
