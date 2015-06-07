/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.biostar;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author RyuujiMD
 */

@Entity
@Table(name = "TB_EVENT_LOG")
public class Evento implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nEventLogIdn")
    private Long id;
    @Column(name = "nDateTime")
    private int fechaHora;
    @Column(name = "nReaderIdn")
    private int equipoID = 0;
    @Column(name = "nEventIdn")
    private int evento = 55;
    @Column(name = "nUserID")
    private int empleadoNroDocumento;
    @Column(name = "nIsLog")
    private int log = 1;
    @Column(name = "nTNAEvent")
    private int tnaEvent = 255;
    @Column(name = "nIsUseTA")
    private int useTA = 0;
    @Column(name = "nType")
    private int type = 0;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(Date valorDate) {        
        Long tiempo = (valorDate.getTime()-18000000)/1000;
        this.fechaHora = tiempo.intValue();
    }

    public int getEquipoID() {
        return equipoID;
    }

    public void setEquipoID(int equipoID) {
        this.equipoID = equipoID;
    }

    public int getEvento() {
        return evento;
    }

    public void setEvento(int evento) {
        this.evento = evento;
    }

    public int getEmpleadoNroDocumento() {
        return empleadoNroDocumento;
    }

    public void setEmpleadoNroDocumento(int empleadoNroDocumento) {
        this.empleadoNroDocumento = empleadoNroDocumento;
    }

    public int getLog() {
        return log;
    }

    public void setLog(int log) {
        this.log = log;
    }

    public int getTnaEvent() {
        return tnaEvent;
    }

    public void setTnaEvent(int tnaEvent) {
        this.tnaEvent = tnaEvent;
    }

    public int getUseTA() {
        return useTA;
    }

    public void setUseTA(int useTA) {
        this.useTA = useTA;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Evento{" + "fechaHora=" + fechaHora + ", equipoID=" + equipoID + ", evento=" + evento + ", empleadoNroDocumento=" + empleadoNroDocumento + '}';
    }

    
    
    
}
