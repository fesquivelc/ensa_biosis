/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package pruebas;

import controladores.AsignacionHorarioControlador;
import controladores.DepartamentoControlador;
import controladores.EmpleadoControlador;
import controladores.HorarioControlador;
import entidades.Horario;
import entidades.escalafon.Departamento;
import entidades.escalafon.Empleado;
import java.util.List;

/**
 *
 * @author RyuujiMD
 */
public class pruebaListaInLista {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        AsignacionHorarioControlador asghorc = new AsignacionHorarioControlador();
        EmpleadoControlador empc = new EmpleadoControlador();
        HorarioControlador horc = new HorarioControlador();
        DepartamentoControlador depc = DepartamentoControlador.getInstance();
        Empleado empleado = empc.buscarXPatron("27900417").get(0);
        Departamento departamento = depc.buscarPorId(230L);
        List<Horario> horarios = horc.buscarXDepartamento(departamento);
        for(Horario horario: horarios){
            System.out.println("HORARIO: "+horario.getNombre());
        }
//        List<AsignacionHorario> asignaciones = asghorc.buscarXEmpleado(empleado);
//        
//        for(AsignacionHorario asignacion : asignaciones){
//            System.out.println("ASIGNACION: "+asignacion.getHorario().getNombre());
//        }
        System.exit(0);
    }
    
}
