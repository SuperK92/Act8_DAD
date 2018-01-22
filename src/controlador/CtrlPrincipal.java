/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import conexionbd.ClaseDatos;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import modelo.MdlPrincipal;
import vista.Principal;

/**
 *
 * @author alumno_n
 */
public class CtrlPrincipal implements ActionListener {

    private ClaseDatos _cldatos;
    private ResultSet _rs;
    private Principal vistainicio = new Principal();
    private MdlPrincipal modeloinicio;

    public CtrlPrincipal(Principal vistainicio, ClaseDatos cldatos) {
        this._cldatos = cldatos;
        this.vistainicio = vistainicio;
    }

    public void inicializarPrincipal() {
        this.vistainicio.setLocationRelativeTo(null);
        this.vistainicio.setResizable(false);
        this.vistainicio.setTitle("Mi Base de Datos");
        this.vistainicio.cb_seleccion.setSelectedIndex(-1);
        this._cldatos = new ClaseDatos();
        this.modeloinicio = new MdlPrincipal(this._cldatos);
//para que se auto ajuste los campos
        final int modeKey[] = {JTable.AUTO_RESIZE_ALL_COLUMNS,
            JTable.AUTO_RESIZE_LAST_COLUMN,
            JTable.AUTO_RESIZE_NEXT_COLUMN, JTable.AUTO_RESIZE_OFF,
            JTable.AUTO_RESIZE_SUBSEQUENT_COLUMNS};
        this.vistainicio.tb_libreria.setAutoResizeMode(modeKey[1]);
// el botón sobre el que se produce una acción y genera un evento necesita estar
//    previamente registrado con algún objeto que gestione el evento(ActionListener en este caso) mediante el método:
        this.vistainicio.bt_limpiar.addActionListener(this);
        this.vistainicio.cb_seleccion.addActionListener(this);
    }

    private void consulta(String nomTabla) {
        DefaultTableModel dfm = new DefaultTableModel();
        javax.swing.JTable tabla = this.vistainicio.tb_libreria;

        tabla.setModel(dfm);
        try {
            ArrayList campos = new ArrayList<>();
            campos = this.modeloinicio.addCabecera(nomTabla);
            Object[] cabecera = campos.toArray(new Object[campos.size()]);
            dfm.setColumnIdentifiers(cabecera);
            this.modeloinicio.addFilas(nomTabla, campos.size(), dfm);
        } catch (Exception ex) {
            Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void selectorCombo() {
        int valor = this.vistainicio.cb_seleccion.getSelectedIndex();
        switch (valor) {
            case 0:
                this.consulta("alumnos");
                break;
            case 1:
                this.consulta("libros");
                break;
            case 2:
///fecha con valor nullo a que poner zeroDateTimeBehavior=convertToNull en la conexion
                this.consulta("prestamos");
                break;
        }
    }

    private void limpiarTabla() {
        this.vistainicio.tb_libreria.setModel(new DefaultTableModel());
    }
//Implementa el método acctionPerformed de la interfaz ActionListener

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == this.vistainicio.bt_limpiar) {
            try {
                this.limpiarTabla();
            } catch (Exception ex) {
                Logger.getLogger(CtrlPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (e.getSource() == this.vistainicio.cb_seleccion) {
            try {
                this.selectorCombo();
            } catch (Exception ex) {
                Logger.getLogger(CtrlPrincipal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
