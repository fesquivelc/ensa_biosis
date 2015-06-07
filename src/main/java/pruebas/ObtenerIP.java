/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author RyuujiMD
 */
public class ObtenerIP {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            InetAddress inet = InetAddress.getLocalHost();
            
            System.out.println("INET: "+inet.getHostAddress());
        } catch (UnknownHostException ex) {
            Logger.getLogger(ObtenerIP.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
