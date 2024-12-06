/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.equip.model;

/**
 *
 * @author MAVI
 */
public class Categoria {
    private int id;
    private String nom;
    private int edat_Min;
    private int edat_Max;
    private String any_ini;
    private String any_fi;
    
    public Categoria(){
        
    }
    public Categoria(int id, String nom, int edat_Min, int edat_Max) {
        setId(id);
        setNom(nom);
        setEdat_Min(edat_Min);
        setEdat_Max(edat_Max);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getEdat_Min() {
        return edat_Min;
    }

    public void setEdat_Min(int edat_Min) {
        this.edat_Min = edat_Min;
    }

    public int getEdat_Max() {
        return edat_Max;
    }

    public String getAny_ini() {
        return any_ini;
    }

    public String getAny_fi() {
        return any_fi;
    }

    public void setEdat_Max(int edat_Max) {
        this.edat_Max = edat_Max;
    }

    public void setAny_ini(Temporada temp) {
        int anyTemp=Integer.valueOf(temp.toString().substring(0, 4));
        this.any_ini = String.valueOf(anyTemp-this.edat_Max);
    }

    public void setAny_fi(Temporada temp) {
        int anyTemp=Integer.valueOf(temp.toString().substring(0, 4));
        this.any_fi = String.valueOf(anyTemp-this.edat_Min);
    }
    public String anysCategoria(){
        return this.any_ini+", "+this.any_fi;
    }
    
}
