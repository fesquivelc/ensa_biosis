/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import utiles.Encriptador;

/**
 *
 * @author RyuujiMD
 */
public class PruebaEncriptador {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Encriptador.iniciar();
        System.out.println("CLAVE: "+Encriptador.encrypt("p4p3l3t4"));
    }
    
}
