/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.equip.interficiepersistencia;
import org.milaifontanals.equip.model.*;
import java.util.List;
import java.util.Map;
/**
 *
 * @author MAVI
 */
public interface IGestorBDEquip {
    
    void tancarCapa() throws GestorBDEquipException;
    public Usuari loginApp(String user, String pswd) throws GestorBDEquipException;
    public void confirmarCanvis() throws GestorBDEquipException;
    public void desferCanvis() throws GestorBDEquipException;
    
    List<Categoria> llistatCategories() throws GestorBDEquipException;
    List<Temporada> llistatTemporades() throws GestorBDEquipException;
    void afegirTemporada(Temporada temp) throws GestorBDEquipException;

    List<Equip> llistatEquips(Temporada t, Map<String, String> filters) throws GestorBDEquipException;
    Equip infoEquip(int id) throws GestorBDEquipException;
    void afegirEquip(Equip eq) throws GestorBDEquipException;
    void eliminarEquip(Equip eq) throws GestorBDEquipException;
    void modificarEquip(Equip eq) throws GestorBDEquipException;
    
    List<Jugador> llistatJugadors(Map<String, String> filters) throws GestorBDEquipException;
    Jugador infoJugador(int id) throws GestorBDEquipException;
    void afegirJugador(Jugador jug) throws GestorBDEquipException;
    void eliminarJugador(Jugador jug) throws GestorBDEquipException;
    void modificarJugador(Jugador jug) throws GestorBDEquipException;
    
    List<Titular> llistatTitularsEquip(Equip eq) throws GestorBDEquipException;
    List<Jugador> llistatJugadorsTitulars(Equip eq) throws GestorBDEquipException;
    List<Jugador> llistatPossiblesJugadors(Categoria cat, Equip eq) throws GestorBDEquipException;
    void afegirTitular(Titular tit) throws GestorBDEquipException;
    void eliminarTitular(Titular tit) throws GestorBDEquipException;
    void modificarTitular(Titular tit) throws GestorBDEquipException;
    
    int equipTeTitulars(Equip eq) throws GestorBDEquipException;
    
}
