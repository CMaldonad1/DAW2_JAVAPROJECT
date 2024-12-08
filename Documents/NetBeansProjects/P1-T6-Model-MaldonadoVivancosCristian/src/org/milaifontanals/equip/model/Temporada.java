/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package org.milaifontanals.equip.model;

import java.util.Calendar;
import java.util.TimeZone;

/**
 *
 * @author MAVI
 */
public class Temporada {
    private String any_temp;
    private int TODAYYEAR=Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid")).get(Calendar.YEAR);
    private int MAXANYS=1980;
    
    public Temporada() {

    }
    
    public Temporada(String any_temp) {
        setAny_temp(any_temp);
    }

    public String setAny_temp(String any_temp) {
        int anyTemp = Integer.valueOf(any_temp.substring(0,4));
        String err="";
        if(anyTemp>=MAXANYS && anyTemp<=TODAYYEAR){
            this.any_temp = any_temp;
        }else{
            err="<html>Any ha de ser posterior a 1980 i, com a máxim, "+TODAYYEAR+"</html>";
        }
        return err;
    }

    public String getAny_temp() {
        return any_temp;
    }   

    @Override
    public String toString() {
        return any_temp;
    }
    
}
