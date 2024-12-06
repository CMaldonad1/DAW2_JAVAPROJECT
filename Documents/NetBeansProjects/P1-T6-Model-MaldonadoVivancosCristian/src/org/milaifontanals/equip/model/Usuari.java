/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.equip.model;

/**
 *
 * @author MAVI
 */
public class Usuari {
    private boolean login;
    private String nom;
    
    public Usuari() {

    }
    public Usuari(String nom) {
        setNom(nom);
        setLogin(false);
    }
    public Usuari(boolean login, String nom) {
        setNom(nom);
        setLogin(login);
    }
    
    /*SETTERS*/
    public void setLogin(boolean login) {
        this.login = login;
    }
    public String setNom(String nom) {
        if(nom.length()>1){
            this.nom = nom;
        }
        return "El camp Usuari no pot ser null";
    }
    /*GETTERS*/
    public boolean isLogin() {
        return login;
    }
    public String getNom() {
        return nom;
    } 
    
}
