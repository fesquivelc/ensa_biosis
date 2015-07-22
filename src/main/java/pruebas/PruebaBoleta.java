/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import controladores.EmpleadoControlador;
import controladores.sisgedo.BoletaControlador;
import entidades.escalafon.Empleado;
import entidades.sisgedo.Boleta;
import java.util.List;

/**
 *
 * @author RyuujiMD
 */
public class PruebaBoleta {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        EmpleadoControlador ec = new EmpleadoControlador();
        BoletaControlador bc = BoletaControlador.getInstance();
        
        Empleado empleado = ec.buscarPorId("02607628");
        
        System.out.println("EMPLEADO: "+ empleado.getNombreCompleto());
        List<Boleta> boletaList = bc.permisoXEmpleado(empleado);
        
        System.out.println("HOLA: "+boletaList.size());
        
        
    }
    
}
