/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

import com.personal.utiles.PropertiesUtil;
import java.util.Properties;
import vistas.dialogos.DlgLogin;
import javax.swing.UIManager;

/**
 *
 * @author fesquivelc
 */
public class Main {
    public static String APLICACION_TITULO = "";
    public static String APLICACION_FONDO = "";
    public static String LOGIN_TITULO = "";
    public static String LOGIN_SUBTITULO = "";
    public static String LOGIN_IMAGEN = "";
    public static String REPORTE_INSTITUCION = "";
    
    public static void main(String[] args) {
        // TODO code application logic here
        try {
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equalsIgnoreCase(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {

        }
        Properties props = PropertiesUtil.cargarProperties("config/interfaz.properties");
        APLICACION_TITULO = props.getProperty("aplicacion_titulo");
        LOGIN_TITULO = props.getProperty("login_titulo");
        LOGIN_SUBTITULO = props.getProperty("login_subtitulo");
        LOGIN_IMAGEN = props.getProperty("login_imagen");
        APLICACION_FONDO = props.getProperty("aplicacion_fondo");
        REPORTE_INSTITUCION = props.getProperty("reporte_institucion");
        DlgLogin principal = new DlgLogin(null, true);
        principal.setVisible(true);
        
    }

}
