/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vistas.modelos;

import entidades.Horario;
import com.personal.utiles.ModeloTabla;
import entidades.Turno;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 *
 * @author RyuujiMD
 */
public class MTHorarioRA extends ModeloTabla<Horario>{

    private final DateFormat dfHora;
    public MTHorarioRA(List<Horario> datos) {
        super(datos);
        dfHora = new SimpleDateFormat("HH:mm");
        this.nombreColumnas = new String[]{"HE","HSR","HER","HS","L","M","M","J","V","S","D"};
    }

    @Override
    public Object getValorEn(int rowIndex, int columnIndex) {
        Horario horario = this.datos.get(rowIndex);
        if(horario.getTipo() == 'A'){
            Turno turno = horario.getTurnoList().get(0);
            switch(columnIndex){
//            case 0:
//                return dfHora.format(turno.getJornada().getTurnoHE());
//            case 1:
//                return dfHora.format(turno.getJornada().getRefrigerioHS());
//            case 2:
//                return dfHora.format(turno.getJornada().getRefrigerioHE());
//            case 3:
//                return dfHora.format(turno.getJornada().getTurnoHS());
            case 4:
                return turno.isLunes();
            case 5:
                return turno.isMartes();
            case 6:
                return turno.isMiercoles();
            case 7:
                return turno.isJueves();
            case 8:
                return turno.isViernes();
            case 9:
                return turno.isSabado();
            case 10:
                return turno.isDomingo();
            default:
                return null;
        }
        }else{
            return null;
        }
        
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        if(columnIndex >= 4){
            return Boolean.class;
        }else{
            return String.class;
        }
    }
    
}
