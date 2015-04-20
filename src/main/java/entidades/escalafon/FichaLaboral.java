package entidades.escalafon;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="ficha_laboral",schema="personal")
public class FichaLaboral implements Serializable {

    @Column(name="id")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name = "codigo_trabajador")
    private String codigoTrabajador;
    @ManyToOne(targetEntity = TipoContrato.class)
    @JoinColumn(name="tipo_contrato_codigo",referencedColumnName="codigo")
    private TipoContrato tiṕoContrato;
    @ManyToOne(targetEntity = Departamento.class)
    @JoinColumn(name="area_codigo",referencedColumnName="codigo",nullable=false)
    private Departamento area;
    @ManyToOne(targetEntity = SituacionTrabajador.class)
    @JoinColumn(name="situacion_trabajador_codigo",referencedColumnName="codigo")
    private SituacionTrabajador situacionTrabajador;
    @OneToOne(targetEntity = Empleado.class)
    @JoinColumn(name="persona_nro_documento",referencedColumnName="nro_documento",nullable=false)
    private Empleado empleado;
    @Column(name="fecha_inicio")
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaInicio;
    @ManyToOne(targetEntity = RegimenLaboral.class)
    @JoinColumn(name="regimen_laboral_codigo",referencedColumnName="codigo")
    private RegimenLaboral regimenLaboral;

    public FichaLaboral() {

    }

    public String getCodigoTrabajador() {
        return codigoTrabajador;
    }

    public void setCodigoTrabajador(String codigoTrabajador) {
        this.codigoTrabajador = codigoTrabajador;
    }
   
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public TipoContrato getTiṕoContrato() {
        return this.tiṕoContrato;
    }

    public void setTiṕoContrato(TipoContrato tiṕoContrato) {
        this.tiṕoContrato = tiṕoContrato;
    }
   
    public Departamento getArea() {
        return this.area;
    }

    public void setArea(Departamento area) {
        this.area = area;
    }
   
    public SituacionTrabajador getSituacionTrabajador() {
        return this.situacionTrabajador;
    }

    public void setSituacionTrabajador(SituacionTrabajador situacionTrabajador) {
        this.situacionTrabajador = situacionTrabajador;
    }
   
    public Empleado getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
   
    public Date getFechaInicio() {
        return this.fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
   
    public RegimenLaboral getRegimenLaboral() {
        return this.regimenLaboral;
    }

    public void setRegimenLaboral(RegimenLaboral regimenLaboral) {
        this.regimenLaboral = regimenLaboral;
    }
}
