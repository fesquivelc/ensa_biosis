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
public class BoletaPK implements Serializable {
    @Column(name="tipoBoleta")
    private int tipoBoleta;
    @Column(name="login")
    private String login;
    @Column(name="idboleta")
    private int id;
    @Column(name="periodo")
    private String periodo;

    public int getTipoBoleta() {
        return tipoBoleta;
    }

    public void setTipoBoleta(int tipoBoleta) {
        this.tipoBoleta = tipoBoleta;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }
    
}
