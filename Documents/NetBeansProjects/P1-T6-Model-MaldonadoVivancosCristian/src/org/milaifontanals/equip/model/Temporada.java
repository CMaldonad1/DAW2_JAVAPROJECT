/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.milaifontanals.equip.model;
/**
 *
 * @author MAVI
 */
public class Temporada {
    private String any_temp;
    
    public Temporada() {

    }
    
    public Temporada(String any_temp) {
        setAny_temp(any_temp);
    }

    public void setAny_temp(String any_temp) {
        this.any_temp = any_temp;
    }

    public String getAny_temp() {
        return any_temp;
    }   

    @Override
    public String toString() {
        return any_temp;
    }
    
}
