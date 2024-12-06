/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.equip.model;

/**
 *
 * @author MAVI
 */
public class Titular {
    private int equip;
    private int jugador;
    private boolean titular;
    
    public Titular(){
        
    }
    
    public Titular(int equip, int jugador) {
        setEquip(equip);
        setJugador(jugador);
        setTitular(false);
    }

    public Titular(int equip, int jugador, boolean titular) {
        setEquip(equip);
        setJugador(jugador);
        setTitular(titular);
    }

    public int getEquip() {
        return equip;
    }

    public void setEquip(int equip) {
        this.equip = equip;
    }

    public int getJugador() {
        return jugador;
    }

    public void setJugador(int jugador) {
        this.jugador = jugador;
    }

    public boolean isTitular() {
        return titular;
    }

    public void setTitular(boolean titular) {
        this.titular = titular;
    }
    
    
}
