/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 *
 * @author RyuujiMD
 */
public class PruebaRandom {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Random r = new Random();
        Calendar calendar = Calendar.getInstance();
        calendar.set(2015, 0, 1, 9, 0, 0);
        Date inicio = calendar.getTime();
        
        calendar.set(2015, 0, 1, 10, 0, 0);
        Date fin = calendar.getTime();
        
        for(int i = 0; i < 4; i++){
            long l = r.nextInt((int)fin.getTime() - (int)inicio.getTime()) + inicio.getTime();
            System.out.println("PRUEBA "+i+" "+new Date(l));
        }
    }
    
}
