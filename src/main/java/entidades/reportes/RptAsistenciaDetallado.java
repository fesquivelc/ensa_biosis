/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.reportes;

import entidades.AsignacionHorario;
import entidades.Feriado;
import entidades.Permiso;
import entidades.escalafon.Empleado;
import entidades.sisgedo.Salida;
import java.util.Date;

/**
 *
 * @author RyuujiMD
 */
public class RptAsistenciaDetallado {
    private String motivo;
    private String tipoAsistencia;
    private String tipoDetalle;
    private Date inicio;
    private Date fin;
    private Date entrada2;
    private Date salida2;
    private String regimenLaboral;
    private String categoriaRemunerativa;
    private int minutosTardanza;
    private int minutosExtra;
    private Date fecha;

    public String getTipoDetalle() {
        return tipoDetalle;
    }

    public void setTipoDetalle(String tipoDetalle) {
        this.tipoDetalle = tipoDetalle;
    }
    
    private Empleado empleado;
    private AsignacionHorario asignacionHorario;
    private Feriado feriado;
    private Permiso permiso;
    private Salida salida;

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
    
    public AsignacionHorario getAsignacionHorario() {
        return asignacionHorario;
    }

    public void setAsignacionHorario(AsignacionHorario asignacionHorario) {
        this.asignacionHorario = asignacionHorario;
    }

    public Feriado getFeriado() {
        return feriado;
    }

    public void setFeriado(Feriado feriado) {
        this.feriado = feriado;
    }

    public Permiso getPermiso() {
        return permiso;
    }

    public void setPermiso(Permiso permiso) {
        this.permiso = permiso;
    }

    public Salida getSalida() {
        return salida;
    }

    public void setSalida(Salida salida) {
        this.salida = salida;
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

    public String getTipoAsistencia() {
        return tipoAsistencia;
    }

    public void setTipoAsistencia(String tipoAsistencia) {
        this.tipoAsistencia = tipoAsistencia;
    }

    public Date getInicio() {
        return inicio;
    }

    public void setInicio(Date inicio) {
        this.inicio = inicio;
    }

    public Date getFin() {
        return fin;
    }

    public void setFin(Date fin) {
        this.fin = fin;
    }

    public Date getEntrada2() {
        return entrada2;
    }

    public void setEntrada2(Date entrada2) {
        this.entrada2 = entrada2;
    }

    public Date getSalida2() {
        return salida2;
    }

    public void setSalida2(Date salida2) {
        this.salida2 = salida2;
    }

    public String getRegimenLaboral() {
        return regimenLaboral;
    }

    public void setRegimenLaboral(String regimenLaboral) {
        this.regimenLaboral = regimenLaboral;
    }

    public String getCategoriaRemunerativa() {
        return categoriaRemunerativa;
    }

    public void setCategoriaRemunerativa(String categoriaRemunerativa) {
        this.categoriaRemunerativa = categoriaRemunerativa;
    }

    public int getMinutosTardanza() {
        return minutosTardanza;
    }

    public void setMinutosTardanza(int minutosTardanza) {
        this.minutosTardanza = minutosTardanza;
    }

    public int getMinutosExtra() {
        return minutosExtra;
    }

    public void setMinutosExtra(int minutosExtra) {
        this.minutosExtra = minutosExtra;
    }

}
