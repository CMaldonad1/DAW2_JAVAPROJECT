/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.equip.vista;

import javax.swing.JOptionPane;
import org.milaifontanals.equip.model.Temporada;

/**
 *
 * @author MAVI
 */
public class ClaseLogin {
    private static String nomClassePersistencia=null;
    //private static IGestorBDEquip gBD = null;
    
    public static void main(String[] args) {
        setPersistencia(args);
        //nomClassePersistencia="org.milaifontanals.equip.jdbc.GestorDBEquipJdbc";
        //Fem la conexió amb la base de dades amb la clase Singleton anomenada Constants
        Constants.setgBD(nomClassePersistencia);
        /**
         * carregem totes les variables que considerarem Globals per al programa
         * mitjançant la clase Singleton anomenada Constants
        */
        carregarInfo();
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Login().setVisible(true);
            }
        });
        
    }
    //verifiquem que arriben arguemnts i establim la capa de persistencia
    private static void setPersistencia(String[] args){
        if (args.length == 0) {
            JOptionPane.showMessageDialog(null, "Cal passar el nom de la classe que dona la persistència com a primer argument", "InfoBox: " + "DB Connection Error", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
        nomClassePersistencia = args[0];
    }
    private static void carregarInfo(){
        Constants.setTemp();
        Constants.setCategs();
        Temporada tAux= Constants.getTemp().get(0);
        Constants.settSel(tAux);
        Constants.setAnysCategsAmbTemp();
    }
}

