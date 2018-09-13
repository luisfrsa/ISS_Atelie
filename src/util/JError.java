/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package util;

import javax.swing.JOptionPane;

/**
 *
 * @author luis
 */
public class JError {

    public static void alert(String strError) {
        alert(strError, "Erro");
    }

    public static void alert(String strError, String titulo) {
        JOptionPane.showMessageDialog(null, strError, titulo, 0);
    }

}
