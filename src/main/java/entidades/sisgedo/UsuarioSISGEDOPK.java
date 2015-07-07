/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entidades.sisgedo;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author RyuujiMD
 */
@Embeddable
public class UsuarioSISGEDOPK implements Serializable {
    @Column(name = "login",insertable = false,updatable = false)
    private String login;
    @Column(name = "sede")
    private int sede;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getSede() {
        return sede;
    }

    public void setSede(int sede) {
        this.sede = sede;
    }
    
}
