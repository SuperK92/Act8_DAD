/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexionmvc;

import conexionbd.ClaseDatos;
import controlador.CtrlPrincipal;
import vista.Principal;

/**
 *
 * @author alumno_n
 */
public class ConexionMVC {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Principal vistaPrincipal = new Principal();
        ClaseDatos cldatos = new ClaseDatos();
        CtrlPrincipal control = new CtrlPrincipal(vistaPrincipal, cldatos);
        control.inicializarPrincipal();
        vistaPrincipal.setVisible(true);
    }
}
