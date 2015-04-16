package entidades.escalafon;

import java.io.Serializable;
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

@Entity
@Table(name="ficha_general",schema="personal")
public class FichaGeneral implements Serializable {

    @Column(name="id")
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;
    @Column(name="direccion")
    @Basic
    private String direccion;
    @ManyToOne(targetEntity = TipoZona.class)
    @JoinColumn(name="tipo_zona_codigo",referencedColumnName="codigo")
    private TipoZona tipoZona;
    @Column(name="telefono_2")
    @Basic
    private String telefono2;
    @Column(name="email")
    @Basic
    private String email;
    @Column(name="estado_civil")
    @Basic
    private char estadoCivil;
    @ManyToOne(targetEntity = Ubigeo.class)
    @JoinColumn(name="ubigeo_residencia_codigo",referencedColumnName="codigo")
    private Ubigeo ubigeoResidencia;
    @Column(name="telefono_1")
    @Basic
    private String telefono1;
    @ManyToOne(targetEntity = TipoVia.class)
    @JoinColumn(name="tipo_via_codigo",referencedColumnName="codigo")
    private TipoVia tipoVia;
    @ManyToOne(targetEntity = NivelEducativo.class)
    @JoinColumn(name="nivel_educativo_codigo",referencedColumnName="codigo")
    private NivelEducativo nivelEducativo;
    @ManyToOne(targetEntity = Nacionalidad.class)
    @JoinColumn(name="nacionalidad_codigo",referencedColumnName="codigo")
    private Nacionalidad nacionalidad;
    @OneToOne(targetEntity = Empleado.class)
    @JoinColumn(name="persona_nro_documento",referencedColumnName="nro_documento",nullable=false)
    private Empleado empleado;

    public FichaGeneral() {

    }
   
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
   
    public String getDireccion() {
        return this.direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }
   
    public TipoZona getTipoZona() {
        return this.tipoZona;
    }

    public void setTipoZona(TipoZona tipoZona) {
        this.tipoZona = tipoZona;
    }
   
    public String getTelefono2() {
        return this.telefono2;
    }

    public void setTelefono2(String telefono2) {
        this.telefono2 = telefono2;
    }
   
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
   
    public char getEstadoCivil() {
        return this.estadoCivil;
    }

    public void setEstadoCivil(char estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
   
    public Ubigeo getUbigeoResidencia() {
        return this.ubigeoResidencia;
    }

    public void setUbigeoResidencia(Ubigeo ubigeoResidencia) {
        this.ubigeoResidencia = ubigeoResidencia;
    }
   
    public String getTelefono1() {
        return this.telefono1;
    }

    public void setTelefono1(String telefono1) {
        this.telefono1 = telefono1;
    }
   
    public TipoVia getTipoVia() {
        return this.tipoVia;
    }

    public void setTipoVia(TipoVia tipoVia) {
        this.tipoVia = tipoVia;
    }
   
    public NivelEducativo getNivelEducativo() {
        return this.nivelEducativo;
    }

    public void setNivelEducativo(NivelEducativo nivelEducativo) {
        this.nivelEducativo = nivelEducativo;
    }
   
    public Nacionalidad getNacionalidad() {
        return this.nacionalidad;
    }

    public void setNacionalidad(Nacionalidad nacionalidad) {
        this.nacionalidad = nacionalidad;
    }
   
    public Empleado getEmpleado() {
        return this.empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
}
