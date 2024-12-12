/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.equip.model;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
/**
 *
 * @author MAVI
 */

public class Jugador {
    private int id;
    private String nom;
    private String cognom;
    private String id_legal;
    private String adreca;
    private String cp;
    private String poblacio;
    private char sexe;
    private Calendar data_naix;
    private Calendar any_fi_revisio_medica;
    private String iban;
    private String foto;
    
    //Aqui tením totes les REGEX per a verificar dades
    private String ptrnIban ="^\\w{2,}(\\d{24})$" ; /*El compte ha de ser Espanyol per fer l'inscripció*/
    private String ptrnNomCog="^\\w{2,}(\\s{1}\\w{2,}){0,1}$"; /*Patern per al nom i cognom*/
    private String ptrnIdLegal="^([XYZ]\\d{7,8}[A-Z]|(\\d{8})([A-Z]))$"; /*Patern per al ID legal on contemplo DNI i NIE*/
    private String codiPostal="\\d{5}";
    static int todayYear=Calendar.getInstance(TimeZone.getTimeZone("Europe/Madrid")).get(Calendar.YEAR);
    static SimpleDateFormat df=new SimpleDateFormat("dd/MM/yyyy");
    
    public Jugador() {
    }

    public Jugador(int id, String nom, String cognom, String id_legal, String adreca, String cp, String poblacio, char sexe, Calendar data_naix, Calendar any_fi_revisio_medica, String iban, String foto) {
        setId(id);
        setNom(nom);
        setCognom(cognom);
        setId_legal(id_legal);
        setAdreca(adreca);
        setCp(cp);
        setPoblacio(poblacio);
        setSexe(sexe);
        setData_naix(data_naix);
        setAny_fi_revisio_medica(any_fi_revisio_medica);
        setIban(iban);
        setFoto(foto);
    }
    public Jugador(String nom, String cognom, String id_legal, String adreca, String cp, String poblacio, char sexe, Calendar data_naix, Calendar any_fi_revisio_medica, String iban, String foto) {
        setNom(nom);
        setCognom(cognom);
        setId_legal(id_legal);
        setAdreca(adreca);
        setCp(cp);
        setPoblacio(poblacio);
        setSexe(sexe);
        setData_naix(data_naix);
        setAny_fi_revisio_medica(any_fi_revisio_medica);
        setIban(iban);
        setFoto(foto);
    }
    public Jugador(String nom, String cognom, String id_legal, String adreca, String cp, String poblacio, char sexe, Calendar data_naix, Calendar any_fi_revisio_medica) {
        setNom(nom);
        setCognom(cognom);
        setId_legal(id_legal);
        setAdreca(adreca);
        setCp(cp);
        setPoblacio(poblacio);
        setSexe(sexe);
        setData_naix(data_naix);
        setAny_fi_revisio_medica(any_fi_revisio_medica);
        setIban(null);
        setFoto(null);
    }
    /*SETTERS*/
    public void setId(int id) {
        this.id = id;
    }
    public String setNom(String nom) {
        String aux=nom.trim();
        if(aux.length()<=50){
            Matcher mtx = Pattern.compile(ptrnNomCog,Pattern.CASE_INSENSITIVE).matcher(nom);
            if(mtx.find()){
                this.nom = aux; 
                return "";
            }
        }
        return "Nom invàlid. Únicament lletres, mínim 2 i máxim 50, i de 0 a 1 espais.";
    }
    public String setCognom(String cognom) {
        String aux=cognom.trim(); //evitem espais en blanc innecesaris al davant i darrere del cognom
        if(aux.length()<=100){
            Matcher mtx = Pattern.compile(ptrnNomCog,Pattern.CASE_INSENSITIVE).matcher(cognom);
            if(mtx.find()){
                this.cognom = aux; 
                return "";
            }
        }
        return "Cognom invàlid. Únicament lletres, mínim 2 i máxim 100, i de 0 a 1 espais.";
    }
    public String setId_legal(String id_legal) {
        Matcher mtx = Pattern.compile(ptrnIdLegal,Pattern.CASE_INSENSITIVE).matcher(id_legal);
        if(mtx.find()){
            this.id_legal = id_legal;
            return "";
        }
        return "ID Legal no valid, no compleix amb els requisits del NIE "
                + "(lletra, 7 números, lletra) o NIF (8 números i una lletra)";
    }
    public String setAdreca(String adreca) {
        int ln =adreca.length();
        if(ln>=2 && ln<=100){
            this.adreca = adreca;
            return "";
        }
        return "l'Adreça no pot contindre mes de 100 characters i no pot contindre simbols especials";
    }
    public String setCp(String cp) {
        Matcher mtx = Pattern.compile(codiPostal,Pattern.CASE_INSENSITIVE).matcher(cp);
        if(mtx.find()){
            this.cp = cp;
            return "";
        }
        return "El codi postal ha d'estar composat per 5 números";
    }
    public String setPoblacio(String poblacio) {
        int ln =poblacio.length();
        if(ln>=2 && ln<=100){
            this.poblacio = poblacio;
            return "";
        }
        return "la Població no pot contindre mes de 100 characters i no pot contindre simbols especials";
    }
    public void setSexe(char sexe) {
        this.sexe = sexe;
    }
    public String setData_naix(Calendar data_naix){
        int anyNaix=data_naix.get(Calendar.YEAR);
        int edat=todayYear-anyNaix;
        if(edat<7 || edat>50){
            return "El jugador no pot tindre menys de 7 anys ni mes de 50 anys.";
        }
        this.data_naix = data_naix;
        return "";
    }
    public void setAny_fi_revisio_medica(Calendar any_fi_revisio_medica) {
        this.any_fi_revisio_medica = any_fi_revisio_medica;
    }
    public String setIban(String iban) {
        /*Eliminem qualsevol espai que pugi haber en blanc en el iban*/
        if(iban!=null){
            iban=iban.replaceAll("\\s", "");
            iban=(iban.length()==0?null:iban);
            Matcher mtx = Pattern.compile(ptrnIban,Pattern.CASE_INSENSITIVE).matcher(iban);
            if(mtx.find()){
                this.iban = iban;
                return "";
            }
            return "L'IBAN indicat no es correcte o compte no es nacional.";
        }
        return "";
    }
    public void setFoto(String foto){
        if(foto!=null){
            foto=(foto.length()==0?null:foto);
            this.foto=foto;
        }
    }
    /*GETTERS*/
    public int getId() {
        return id;
    }
    public String getNom() {
        return nom;
    }
    public String getCognom() {
        return cognom;
    }
    public String getId_legal() {
        return id_legal;
    }
    public String getCp() {
        return cp;
    }
    public String getPoblacio() {
        return poblacio;
    }
    public String getAdreca() {
        return adreca;
    }
    public char getSexe() {
        return sexe;
    }
    public Calendar getData_naix() {
        return data_naix;
    }
    public Calendar getAny_fi_revisio_medica() {
        return any_fi_revisio_medica;
    }
    public String getIban() {
        return iban;
    }
    public String getFoto(){
        return foto;
    }
}
