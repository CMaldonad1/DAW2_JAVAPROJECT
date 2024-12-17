/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.equip.vista;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.milaifontanals.equip.interficiepersistencia.GestorBDEquipException;
import org.milaifontanals.equip.interficiepersistencia.IGestorBDEquip;
import org.milaifontanals.equip.model.Categoria;
import org.milaifontanals.equip.model.Temporada;

/**
 *
 * @author MAVI
 * Idea ha sortit del seguent foro:
 * https://stackoverflow.com/questions/10425962/java-access-main-class-variables
 * 
 */
public class Constants {
    private static IGestorBDEquip gBD = null;
    private static List<Temporada> temp= new ArrayList<>();
    private static List<Categoria> categs= new ArrayList<>();
    private static Temporada tSel=null;
    
    private Constants(){
        
    }

    public static IGestorBDEquip getgBD() {
        return gBD;
    }
    public static List<Temporada> getTemp() {
        return temp;
    }
    public static List<Categoria> getCategs() {
        return categs;
    }
    public static Temporada gettSel() {
        return tSel;
    }
    public static Boolean jugadorTitularAltreEquip(int Equip, int Jugador){
        Boolean hoEs=false;
        try {
            hoEs=gBD.jugadorParticipaEnAltresEquips(Equip, Jugador); 
        } catch (GestorBDEquipException ex) {
            Logger.getLogger(Constants.class.getName()).log(Level.SEVERE, null, ex);
        }
        return hoEs;
    }
    //portem la categoria del jugador en funció de la seva edat de naixement
    public static String jugCategoria(Calendar dNaix){
        int anyNaix=dNaix.get(Calendar.YEAR), i=0;
        String nomCat="";
        do{
            int anyInici=Integer.valueOf(categs.get(i).getAny_ini());
            int anyFi=Integer.valueOf(categs.get(i).getAny_fi());
            if(anyNaix>=anyInici && anyNaix<=anyFi){
                nomCat=categs.get(i).getNom();
            }
            i++;
        }while(nomCat.equals("") && i!=categs.size());
        
        return nomCat;
    }
    //retornem categoria segons l'id
    public static Categoria objecteCategoria(int id){
        int i=0, len=categs.size();
        Categoria catObjecte= new Categoria();
        boolean found=false;
        do{
            if(id==categs.get(i).getId()){
                catObjecte=categs.get(i);
            }
            i++;
        }while(i<len || found);
        return catObjecte;
    }
    //busquem l'id de la categoria seleccionada segons nom
    public static int idCategoria(String nom){
        int id=-1,i=0, len=categs.size();
        boolean found=false;
        do{
            if(nom.contains(categs.get(i).getNom())){
                id=categs.get(i).getId();
            }
            i++;
        }while(i<len || found);
        return id;
    }
    //busquem el nom de la categoria seleccionada segons id
    public static String nomCategoria(int id){
        int i=0, len=categs.size();
        String nom="";
        do{
            if(id==categs.get(i).getId()){
                nom=categs.get(i).getNom();
            }
            i++;
        }while(i!=len && nom.equals(""));
        return nom;
    }
    public static void setgBD(String nomClassePersistencia) {
        connexioBBDD(nomClassePersistencia);
    }
    
    public static void setTemp() {
        try {
            Constants.temp = gBD.llistatTemporades();
        } catch (GestorBDEquipException ex) {
            Logger.getLogger(Constants.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static String tipusNom(char tipus){
        String t=(tipus=='H')?"Masculí":((tipus=='D')?"Femení":"Mixt");
        return t;
    }
    public static char tipusChar(String tipus){
        return (tipus=="Masculí")?'H':((tipus=="Femení")?'D':'M');
    }
    //guardem la temporada amb la que treballem
    public static void settSel(Temporada tSel) {
        Constants.tSel = tSel;
    }
    /**
     * programa per segons la temporada seleccionada, poder determinar els anys 
     * de naixement de les categories i poder asignar-los als jugadors.
     */
    public static void setAnysCategsAmbTemp(){
        for(Categoria c:categs){
            c.setAny_ini(tSel);
            c.setAny_fi(tSel);
        }
    }
    //carregem categories
    public static void setCategs(){
        try {
            Constants.categs=gBD.llistatCategories();
        } catch (GestorBDEquipException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    //verifiquem que l'usuari sigui correcte
    public static String verificarUsuari(String userField, String pswdField){
        String error=null;
        try {
            gBD.loginApp(userField, pswdField);
        } catch (GestorBDEquipException ex) {
            error=ex.getMessage();
        }
        return error;
    } 
    //fem la conexió amb la base de dades
    private static void connexioBBDD(String nomClassePersistencia){
        //showErros.setText("Intentant establir connexió...");
        try {
            // Intent de crear objecte per gestionar la connexió amb la BD
            Constants.gBD = (IGestorBDEquip) Class.forName(nomClassePersistencia).newInstance();;
            System.out.println("Connexió establerta");
        } catch (Exception ex) {
            System.out.println(infoError(ex) + "Finalitzi l'aplicació...");
            System.out.println(ex.getMessage());
        }
    }
    private static String infoError(Throwable ex) {
        String aux;
        String info = ex.getMessage();
        if (info != null) {
            info += "\n";
        }
        while (ex.getCause() != null) {
            aux = ex.getCause().getMessage();
            if (aux != null) {
                aux += "\n";
            }
            info = info + aux;
            ex = ex.getCause();
        }
        return info;
    }
    public static String calendarToString(Calendar date){
        SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
        String formated=format.format(date.getTime());
        return formated;
    }
}
    
