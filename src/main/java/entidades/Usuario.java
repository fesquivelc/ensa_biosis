package entidades;

import entidades.escalafon.Empleado;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Usuario implements Serializable {

    @Column(name="cambiar_password",unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private boolean cambiarPassword;
    @ManyToOne(targetEntity = Empleado.class)
    @JoinColumn(name = "empleado_nro_documento",referencedColumnName = "nro_documento")
    private Empleado empleado;
    @Column(name="ultimo_inicio")
    @Temporal(TemporalType.TIMESTAMP)
    private Date ultimoInicio;
    @ManyToOne(optional=false,targetEntity = Rol.class)
    @JoinColumn(name="rol_codigo",referencedColumnName="codigo",insertable=true,nullable=false,unique=false,updatable=true)
    private Rol rol;
    @Column(unique=false,updatable=true,insertable=true,nullable=false,length=45,scale=0,precision=0)
    @Id
    private String login;
    @Column(unique=false,updatable=true,insertable=true,nullable=true,length=255,scale=0,precision=0)
    @Basic
    private String password;
    @Column(unique=false,updatable=true,insertable=true,nullable=false,length=255,scale=0,precision=0)
    @Basic
    private boolean activo;

    public Usuario() {

    }

    public Date getUltimoInicio() {
        return ultimoInicio;
    }

    public void setUltimoInicio(Date ultimoInicio) {
        this.ultimoInicio = ultimoInicio;
    }
    
    public boolean isCambiarPassword() {
        return this.cambiarPassword;
    }

    public void setCambiarPassword(boolean cambiarPassword) {
        this.cambiarPassword = cambiarPassword;
    }

    public Empleado getEmpleado() {
        return empleado;
    }

    public void setEmpleado(Empleado empleado) {
        this.empleado = empleado;
    }
   
    public Rol getRol() {
        return this.rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
   
    public String getLogin() {
        return this.login;
    }

    public void setLogin(String login) {
        this.login = login;
    }
   
    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    public boolean isActivo() {
        return this.activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }
}
