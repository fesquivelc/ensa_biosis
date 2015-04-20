/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades;

import entidades.escalafon.Empleado;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author fesquivelc
 */
@Entity
@Table(name = "cambio_turno")
public class CambioTurno {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @ManyToOne(targetEntity = Empleado.class)
    @JoinColumn(name = "empleado1_nro_documento",referencedColumnName = "nro_documento")
    private Empleado empleado1;
    @ManyToOne(targetEntity = Turno.class)
    @JoinColumn(name = "turno1_id",referencedColumnName = "id")
    private Turno turno1;
    @ManyToOne(targetEntity = Empleado.class)
    @JoinColumn(name = "empleado2_nro_documento",referencedColumnName = "nro_documento")
    private Empleado empleado2;
    @ManyToOne(targetEntity = Turno.class)
    @JoinColumn(name = "turno2_id",referencedColumnName = "id")
    private Turno turno2;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Empleado getEmpleado1() {
        return empleado1;
    }

    public void setEmpleado1(Empleado empleado1) {
        this.empleado1 = empleado1;
    }

    public Turno getTurno1() {
        return turno1;
    }

    public void setTurno1(Turno turno1) {
        this.turno1 = turno1;
    }

    public Empleado getEmpleado2() {
        return empleado2;
    }

    public void setEmpleado2(Empleado empleado2) {
        this.empleado2 = empleado2;
    }

    public Turno getTurno2() {
        return turno2;
    }

    public void setTurno2(Turno turno2) {
        this.turno2 = turno2;
    }
}
