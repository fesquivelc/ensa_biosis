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
    @ManyToOne(targetEntity = TipoContrato.class)
    @JoinColumn(name="tipo_contrato_codigo",referencedColumnName="codigo")
    private TipoContrato tiṕoContrato;
    @ManyToOne(targetEntity = Area.class)
    @JoinColumn(name="area_codigo",referencedColumnName="codigo",nullable=false)
    private Area area;
    @ManyToOne(targetEntity = SituacionTrabajador.class)
    @JoinColumn(name="situacion_trabajador_codigo",referencedColumnName="codigo")
    private SituacionTrabajador situacionTrabajador;
    @OneToOne(targetEntity = Persona.class)
    @JoinColumn(name="persona_nro_documento",referencedColumnName="nro_documento",nullable=false)
    private Persona persona;
    @Column(name="fecha_inicio")
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaInicio;
    @ManyToOne(targetEntity = RegimenLaboral.class)
    @JoinColumn(name="regimen_laboral_codigo",referencedColumnName="codigo")
    private RegimenLaboral regimenLaboral;

    public FichaLaboral() {

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
   
    public Area getArea() {
        return this.area;
    }

    public void setArea(Area area) {
        this.area = area;
    }
   
    public SituacionTrabajador getSituacionTrabajador() {
        return this.situacionTrabajador;
    }

    public void setSituacionTrabajador(SituacionTrabajador situacionTrabajador) {
        this.situacionTrabajador = situacionTrabajador;
    }
   
    public Persona getPersona() {
        return this.persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
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
