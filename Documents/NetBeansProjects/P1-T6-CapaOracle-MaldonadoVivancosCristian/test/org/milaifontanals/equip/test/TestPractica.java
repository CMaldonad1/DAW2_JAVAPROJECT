package org.milaifontanals.equip.test;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import org.milaifontanals.equip.jdbc.GestorDBEquipJdbc;
import org.milaifontanals.equip.model.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import org.milaifontanals.equip.interficiepersistencia.GestorBDEquipException;
/**
 *
 * @author MAVI
 */
public class TestPractica {
    //variables per el test de proves
    private static Temporada tNova= new Temporada("2024/2025");
    private static Jugador nJugador= new Jugador();
    private static List<Categoria> cats = new ArrayList<>();
    private static GestorDBEquipJdbc eDB;
    
    public static void main(String[] args) throws GestorBDEquipException {
        loginBD();
        /*Calendar dNaix=Calendar.getInstance();
        dNaix.set(2007, 11, 9);
        String foto=null;
        Jugador j=new Jugador(86,"Chia","Maldonado Pages","47107385C","Cta de valls 97", "Montbui","08710",'D',dNaix,dNaix,foto,foto);
        
        eDB.eliminarJugador(j);
        eDB.confirmarCanvis();*/
        carregarCategories();
        printarCategories();
        List<Equip> eq=carregarEquips();
        printarInfoEquips(eq);


    }

    public static void loginBD(){
        //intentem crear la capa de persistencias
        try{
            System.out.println("Creant capa...");
            eDB= new GestorDBEquipJdbc();
        }catch(GestorBDEquipException ex){
            System.out.println("Problema en crear capa de persistència:");
            System.out.println(ex.getMessage());
        }
    }
    public static void carregarCategories(){
        //carregem les categories
        try{
            cats=eDB.llistatCategories();
        }catch(GestorBDEquipException ex){
            System.out.println("Problema en crear obtindre categories");
            System.out.println(ex.getMessage());
        }
        printarCategories();
    }
    public static void printarCategories(){
        System.out.println("LLISTAT DE CATEGORIES:");
        for(Categoria c: cats){
            c.setAny_ini(tNova);
            c.setAny_fi(tNova);
            System.out.println("Nom: "+c.getNom()+" - Edat Min: "+c.getEdat_Min()
                            +" - Edat max: "+c.getEdat_Max()+" - Anys: "+c.anysCategoria());
        }
        System.out.println("_________");
    }
    public static List<Equip> carregarEquips(){
        //Seleccionem els equips de la temporada
        List<Equip> eq=new ArrayList<>();
        Map<String, String> filtersEq=Collections.emptyMap();
        try{
            eq=eDB.llistatEquips(tNova, filtersEq);  
        }catch(GestorBDEquipException ex){
            System.out.println("no es pot recuperar els equips:");
            System.out.println(ex.getMessage());
        }
        return eq;
    }
    public static void printarInfoEquips(List<Equip> eq){
        System.out.println("LLISTAT D'EQUIPS");
        for(Equip e: eq){
            String info="Id: "+e.getId()+" - Nom: "+e.getNom()+" - Categoria: "+e.getCat()+" - Tipus:"+e.getTipus();
            System.out.println(info);
        }
        System.out.println("_________");
        /**
         * Possibles jugadors de l'equip amb ID=1 i donarem d'alta al jugador 
         * que surti a la primera posició
        */
    }
    public static Equip retornarInfoEquip(List<Equip> eq, int id){
        Equip eS=new Equip();
        int i=0;
        boolean trobat=false;
        do{
            if(eq.get(i).getId()==3){
                eS=eq.get(i);
                trobat=true;
            }
            i++;
        }while(i<eq.size() || !trobat);
        return eS;
    }
    public static Categoria retornarCategoriaEquip(int id){
        Boolean trobat=false;
        int i=0;
        Categoria cat=new Categoria();
        do{
            if(cats.get(i).getId()==id){
                cat=cats.get(i);
                trobat=true;
            }
            i++;
        }while(i<cats.size() || !trobat);
        
        return cat;
    }

}
