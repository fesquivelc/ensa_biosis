/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.reportes;

import entidades.AsignacionHorario;
import entidades.Autorizacion;
import entidades.DetalleJornada;
import entidades.Feriado;
import entidades.Permiso;
import entidades.Vacacion;
import entidades.escalafon.Contrato;
import entidades.escalafon.Departamento;
import entidades.escalafon.Empleado;
import entidades.sisgedo.Boleta;
//import entidades.sisgedo.Salida;
import java.util.Date;

/**
 *
 * @author RyuujiMD
 */
public class RptAsistenciaDetallado{
    private String motivo;
    private String tipoAsistencia;
    private String tipoDetalle;
    private Date inicio;
    private Date fin;
    private String regimenLaboral;
    private String categoriaRemunerativa;
    private Integer minutosTardanza;
    private Integer minutosExtra;
    private DetalleJornada detalleJornada;
    private boolean minutosExtraAutorizado;
    private Autorizacion autorizacionExtra;
    private Date fecha;
    private Departamento area;
    private Contrato contrato;
    private Boleta boleta;

    public Boleta getBoleta() {
        return boleta;
    }

    public void setBoleta(Boleta boleta) {
        this.boleta = boleta;
    }

    public Contrato getContrato() {
        return contrato;
    }

    public void setContrato(Contrato contrato) {
        this.contrato = contrato;
    }
    

    public Departamento getArea() {
        return area;
    }

    public void setArea(Departamento area) {
        this.area = area;
    }

    public Integer getMinutosTardanza() {
        return minutosTardanza;
    }

    public void setMinutosTardanza(Integer minutosTardanza) {
        this.minutosTardanza = minutosTardanza;
    }

    public Integer getMinutosExtra() {
        return minutosExtra;
    }

    public void setMinutosExtra(Integer minutosExtra) {
        this.minutosExtra = minutosExtra;
    }

    public Autorizacion getAutorizacionExtra() {
        return autorizacionExtra;
    }

    public void setAutorizacionExtra(Autorizacion autorizacionExtra) {
        this.autorizacionExtra = autorizacionExtra;
    }

    public DetalleJornada getDetalleJornada() {
        return detalleJornada;
    }

    public void setDetalleJornada(DetalleJornada detalleJornada) {
        this.detalleJornada = detalleJornada;
    }

    public boolean isMinutosExtraAutorizado() {
        return minutosExtraAutorizado;
    }

    public void setMinutosExtraAutorizado(boolean minutosExtraAutorizado) {
        this.minutosExtraAutorizado = minutosExtraAutorizado;
    }

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
    private Vacacion vacacion;
//    private Salida salida;

    public Vacacion getVacacion() {
        return vacacion;
    }

    public void setVacacion(Vacacion vacacion) {
        this.vacacion = vacacion;
    }

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

//    public Salida getSalida() {
//        return salida;
//    }
//
//    public void setSalida(Salida salida) {
//        this.salida = salida;
//    }
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

    public void setMinutosTardanza(int minutosTardanza) {
        this.minutosTardanza = minutosTardanza;
    }

    public void setMinutosExtra(int minutosExtra) {
        this.minutosExtra = minutosExtra;
    }

    @Override
    public String toString() {
        return "RptAsistenciaDetallado{" + "tipoAsistencia=" + tipoAsistencia + ", tipoDetalle=" + tipoDetalle + ", inicio=" + inicio + ", fin=" + fin + ", detalleJornada=" + detalleJornada + ", fecha=" + fecha + '}';
    }

    
}
