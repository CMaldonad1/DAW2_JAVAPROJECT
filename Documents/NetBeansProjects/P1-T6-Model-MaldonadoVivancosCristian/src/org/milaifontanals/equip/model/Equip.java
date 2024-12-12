/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.equip.model;
/**
 *
 * @author MAVI
 */
public class Equip {
    private int id;
    private int cat;
    private String nom;
    private Temporada temp;
    private char tipus;
    
    public Equip(){
        
    }
    public Equip(int cat, String nom, Temporada temp, char tipus) {
        setCat(cat);
        setNom(nom);
        setTemp(temp);
        setTipus(tipus);
    }

    public Equip(int id, int cat, String nom, Temporada temp, char tipus) {
        setId(id);
        setCat(cat);
        setNom(nom);
        setTemp(temp);
        setTipus(tipus);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCat() {
        return cat;
    }

    public void setCat(int cat) {
        this.cat = cat;
    }

    public String getNom() {
        return nom;
    }

    public String setNom(String nom) {
        String err="";
        if(!nom.isBlank() && nom.length()>1 && nom.length()<51){
            this.nom = nom;
        }else{
            err="El nom ha de tenir mínim dos caracters i máxim 50";
        }
        return err;
    }

    public Temporada getTemp() {
        return temp;
    }

    public void setTemp(Temporada temp) {
        this.temp = temp;
    }

    public char getTipus() {
        return tipus;
    }

    public void setTipus(char tipus) {
        this.tipus = tipus;
    }
    
    public String tempToString(){
        return temp.toString();
    }
}
