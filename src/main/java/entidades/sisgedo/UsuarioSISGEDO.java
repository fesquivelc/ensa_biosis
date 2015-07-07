/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.sisgedo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author RyuujiMD
 */
@Entity
@Table(name = "usuario")
public class UsuarioSISGEDO implements Serializable {
    @EmbeddedId
    private UsuarioSISGEDOPK usuarioSISGEDOPK;
    @Column(name = "dni",nullable = true)
    private String personaNroDocumento;
//    @Column(name = "login",nullable = false,insertable = false,updatable = false)
//    private String login;
//
//    public String getLogin() {
//        return login;
//    }
//
//    public void setLogin(String login) {
//        this.login = login;
//    }

    public UsuarioSISGEDOPK getUsuarioSISGEDOPK() {
        return usuarioSISGEDOPK;
    }

    public void setUsuarioSISGEDOPK(UsuarioSISGEDOPK usuarioSISGEDOPK) {
        this.usuarioSISGEDOPK = usuarioSISGEDOPK;
    }

    public String getPersonaNroDocumento() {
        return personaNroDocumento;
    }

    public void setPersonaNroDocumento(String personaNroDocumento) {
        this.personaNroDocumento = personaNroDocumento;
    }
    
    
}
