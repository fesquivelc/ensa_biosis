/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.sisgedo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author RyuujiMD
 */
@Entity
@Table(name = "vw_sisgedo_salida")
public class Salida implements Serializable{
    @Id
    @Column(name = "sal_id")
    private Long id;
    @Column(name = "sal_empleado")
    private String empleado;   
    @Column(name = "sal_fecha_hora_inicio")
    private String fechaHoraInicio;   
    @Column(name = "sal_fecha_hora_fin")
    private String fechaHoraFin;   
    @Column(name = "sal_motivo")
    private String motivo;   
    @Column(name = "sal_descripcion")
    private String descripciono;   

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmpleado() {
        return empleado;
    }

    public void setEmpleado(String empleado) {
        this.empleado = empleado;
    }

    public String getFechaHoraInicio() {
        return fechaHoraInicio;
    }

    public void setFechaHoraInicio(String fechaHoraInicio) {
        this.fechaHoraInicio = fechaHoraInicio;
    }

    public String getFechaHoraFin() {
        return fechaHoraFin;
    }

    public void setFechaHoraFin(String fechaHoraFin) {
        this.fechaHoraFin = fechaHoraFin;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getDescripciono() {
        return descripciono;
    }

    public void setDescripciono(String descripciono) {
        this.descripciono = descripciono;
    }
    
}
