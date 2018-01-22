/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import conexionbd.ClaseDatos;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import vista.Principal;

/**
 *
 * @author alumno_n
 */
public class MdlPrincipal {

    private ClaseDatos _cldatos;
    private ResultSet _rs;

    public MdlPrincipal(ClaseDatos cldatos) {
        _cldatos = cldatos;
        _rs = null;
    }

    private ArrayList nomCampos(String nomTabla) {
        ArrayList cabecera = new ArrayList<>();
        String comandoCampos = "SHOW COLUMNS FROM " + nomTabla;
        try {
            _cldatos.Ejecutar_Consulta(comandoCampos);
            _rs = _cldatos.getRs();
            while (_rs.next()) {
                cabecera.add(_rs.getObject("Field"));
            }
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            cabecera = null;
        }
        return cabecera;
    }

    public ArrayList addCabecera(String nomTabla) {
        ArrayList campos = new ArrayList<>();
        campos = this.nomCampos(nomTabla);
        return campos;
    }

    public void addFilas(String nomTabla, int numcampos, DefaultTableModel dfm) {
        Object[] fila = new Object[numcampos];
        try {
            String sql = "Select * from " + nomTabla;
            _cldatos.Ejecutar_Consulta(sql);
            _rs = _cldatos.getRs();
            while (_rs.next()) {
                for (int i = 0; i < numcampos; i++) {
                    fila[i] = _rs.getObject(i + 1);
                }
                dfm.addRow(fila);
            }
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
