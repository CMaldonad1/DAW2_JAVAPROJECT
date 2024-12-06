/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.equip.interficiepersistencia;

/**
 *
 * @author MAVI
 */
public class GestorBDEquipException extends Exception {
    
    public GestorBDEquipException(String message){
        super(message);
    }
    public GestorBDEquipException(String message, Throwable cause){
        super(message);
    }
}
