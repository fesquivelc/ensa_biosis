package entidades.escalafon;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name="persona",schema="personal")
public class Empleado implements Serializable {

    @Column(name="nro_documento",nullable=false)
    @Id
    private String nroDocumento;
    @Column(name="nombres",nullable=false)
    @Basic
    private String nombre;
    @Column(name="condicion")
    @Basic
    private char condicion;
    @OneToOne(fetch = FetchType.LAZY,targetEntity = FichaGeneral.class,mappedBy = "empleado")
    private FichaGeneral fcihaGeneral;
    @Column(name="materno",nullable=false)
    @Basic
    private String materno;
    @ManyToOne(targetEntity = TipoDocumento.class)
    @JoinColumn(name="tipo_documento_codigo",referencedColumnName="codigo")
    private TipoDocumento tipoDocumento;
    @Column(name="sexo",nullable=false)
    @Basic
    private char sexo;
    @Column(name="paterno",nullable=false)
    @Basic
    private String paterno;
    @OneToOne(fetch = FetchType.LAZY,targetEntity = FichaLaboral.class,mappedBy = "empleado")
    private FichaLaboral fichaLaboral;
    @Column(name="fecha_nacimiento")
    @Temporal(TemporalType.DATE)
    @Basic
    private Date fechaNacimiento;

    public Empleado() {

    }
   
    public String getNroDocumento() {
        return this.nroDocumento;
    }

    public void setNroDocumento(String nroDocumento) {
        this.nroDocumento = nroDocumento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
   
    public char getCondicion() {
        return this.condicion;
    }

    public void setCondicion(char condicion) {
        this.condicion = condicion;
    }
   
    public FichaGeneral getFcihaGeneral() {
        return this.fcihaGeneral;
    }

    public void setFcihaGeneral(FichaGeneral fcihaGeneral) {
        this.fcihaGeneral = fcihaGeneral;
    }
   
    public String getMaterno() {
        return this.materno;
    }

    public void setMaterno(String materno) {
        this.materno = materno;
    }
   
    public TipoDocumento getTipoDocumento() {
        return this.tipoDocumento;
    }

    public void setTipoDocumento(TipoDocumento tipoDocumento) {
        this.tipoDocumento = tipoDocumento;
    }
   
    public char getSexo() {
        return this.sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }
   
    public String getPaterno() {
        return this.paterno;
    }

    public void setPaterno(String paterno) {
        this.paterno = paterno;
    }
   
    public FichaLaboral getFichaLaboral() {
        return this.fichaLaboral;
    }

    public void setFichaLaboral(FichaLaboral fichaLaboral) {
        this.fichaLaboral = fichaLaboral;
    }
   
    public Date getFechaNacimiento() {
        return this.fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }
}
