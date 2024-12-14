/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.equip.jdbc;
import org.milaifontanals.equip.interficiepersistencia.GestorBDEquipException;
import org.milaifontanals.equip.interficiepersistencia.IGestorBDEquip;
import org.milaifontanals.equip.model.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Map;
import java.util.Properties;
/**
 *
 * @author MAVI
 */
public class GestorDBEquipJdbc implements IGestorBDEquip{
    private Connection conn;
    private PreparedStatement login;
    private PreparedStatement insertTemp;

    private PreparedStatement insertJug;
    private PreparedStatement infoJug;
    private PreparedStatement delJug;
    private PreparedStatement updJug; 
    
    private PreparedStatement insertEq;
    private PreparedStatement idEq;
    private PreparedStatement infoEq;
    private PreparedStatement delEq;
    private PreparedStatement updEq; 
    
    private PreparedStatement llistTit; 
    private PreparedStatement llistJugTit;
    private PreparedStatement llistJugPos;
    private PreparedStatement insertTit;
    private PreparedStatement delTit;
    private PreparedStatement modTit;
    private PreparedStatement equipAmbTitulars;
    private PreparedStatement jugTitularAltresEquips;
    
    public GestorDBEquipJdbc() throws GestorBDEquipException{
        String nomFitxer="equipXML.xml";
        try {
            Properties props = new Properties();
            props.loadFromXML(new FileInputStream(nomFitxer));
            String[] claus = {"url", "user", "password"};
            String[] valors = new String[3];
            for (int i = 0; i < claus.length; i++) {
                valors[i] = props.getProperty(claus[i]);
                if (valors[i] == null || valors[i].isEmpty()) {
                    throw new GestorBDEquipException("L'arxiu " + nomFitxer + " no troba la clau " + claus[i]);
                }
            }
            conn = DriverManager.getConnection(valors[0], valors[1], valors[2]);
            conn.setAutoCommit(false);
        } catch (IOException ex) {
            throw new GestorBDEquipException("Problemes en recuperar l'arxiu de configuració " + nomFitxer, ex);
        } catch (SQLException ex) {
            throw new GestorBDEquipException("No es pot establir la connexió.", ex);
        }
    }
    private static String byteToHex(final byte[] hash){
        Formatter formatter = new Formatter();
        for (byte b : hash)
        {
            formatter.format("%02x", b);
        }
        String result = formatter.toString();
        formatter.close();
        return result.toUpperCase();
    }
    private static String convertToSH1(String pswd){
        String sha1="";
        try{
            MessageDigest crypt= MessageDigest.getInstance("SHA-1");
            crypt.reset();
            crypt.update(pswd.getBytes("UTF-8"));
            sha1= byteToHex(crypt.digest());
        }catch(NoSuchAlgorithmException e){
            System.out.println(e.getMessage());
        }catch(UnsupportedEncodingException e){
            System.out.println(e.getMessage());
        }
        return sha1;
    }
    public String convertDate(Calendar cal){
        Date d=cal.getTime();
        return new SimpleDateFormat("yyyy-MM-dd").format(d);
    }
    private static Jugador prepararInfoJugador(ResultSet rs) throws GestorBDEquipException{
        Jugador jugador=new Jugador();
        try{
            jugador.setId(rs.getInt("id"));
            jugador.setNom(rs.getString("nom"));
            jugador.setCognom(rs.getString("cognoms"));
            jugador.setId_legal(rs.getString("id_legal"));
            jugador.setAdreca(rs.getString("adreca"));
            jugador.setCp(rs.getString("cp"));
            jugador.setPoblacio(rs.getString("poblacio"));
            jugador.setSexe(rs.getString("sexe").charAt(0));

            Calendar dNaix = Calendar.getInstance();
            dNaix.setTime(rs.getDate("data_naix"));
            jugador.setData_naix(dNaix);

            Calendar dMedica = Calendar.getInstance();
            dMedica.setTime(rs.getDate("any_fi_revisio_medica"));
            jugador.setAny_fi_revisio_medica(dMedica);

            jugador.setIban(rs.getString("iban"));
            jugador.setFoto(rs.getString("foto"));
        }catch (SQLException ex) {
            throw new GestorBDEquipException("Error en intentar recuperar la llista de jugadors.", ex);
        } 
        return jugador;
    }
    private static Equip prepararInfoEquip(ResultSet rs) throws GestorBDEquipException{
        Equip equip=new Equip();
        try{
            equip.setId(rs.getInt("id"));
            equip.setNom(rs.getString("nom"));
            equip.setTemp(new Temporada(rs.getString("temp")));
            equip.setTipus(rs.getString("tipus").charAt(0));
            equip.setCat(rs.getInt("cat"));
        }catch (SQLException ex) {
            throw new GestorBDEquipException("Error en intentar recuperar informacio dels equips.", ex);
        } 
        return equip;
    }
    private static String queryLlistatJugadors(Map<String, String> filters){
        String query="SELECT * FROM JUGADOR";
        /**
         * Si el Map te valors vol dir que hi han filtres aplicats i construirem
         * la sentencía SQL depenent d'aquests.
         */
        if(!filters.isEmpty()){
            query+=" WHERE ";
            String[] keys=filters.keySet().toArray(new String[filters.size()]);
            for(int i=0;i<filters.size();i++){
                if(keys[i]!="categoria"){
                    /**
                     * data_naix vindrá amb el condicional "=>" o "=<" 
                     * ja afegit al valor del string i per aixó el+tractament
                     * es diferent als dels altres filtres
                     */
                    query+=(keys[i]=="data_naix")?keys[i]+" "+filters.get(keys[i]):keys[i]+" = '"+filters.get(keys[i])+"'";
                }else{
                    //obtindrem les categories amb valors d'anys concatenats
                    query+=" EXTRACT(YEAR FROM data_naix) in ("+filters.get(keys[i])+")";
                }
                if(i!=(filters.size()-1)){
                    query+=" AND ";
                }
            }
        }
        query+=" ORDER BY NOM";
        return query;
    }
    private static String queryLlistatEquips(Temporada t, Map<String, String> filters){
        String query="SELECT * "
                    + "FROM EQUIP WHERE TEMP='"+t.toString()+"'";
        /**
         * Si el Map te valors vol dir que hi han filtres aplicats i construirem
         * la sentencía SQL depenent d'aquests.
         */
        if(!filters.isEmpty()){
            query+=" AND ";
            String[] keys=filters.keySet().toArray(new String[filters.size()]);
            for(int i=0;i<filters.size();i++){
                if(keys[i]!="tipus"){
                    query+=keys[i]+" = "+filters.get(keys[i]);
                }else{
                    query+=keys[i]+" in ("+filters.get(keys[i])+")";
                }

                if(i!=(filters.size()-1)){
                    query+=" AND ";
                }
            }
        }
        query+=" ORDER BY NOM";
        return query;
    }
    private static String queryPossiblesJugadors(char sexe){
        String query="SELECT * FROM JUGADOR "
                    + "WHERE ID NOT IN (SELECT JUGADOR FROM "
                    + "TITULARS WHERE EQUIP=?) "
                    + "AND DATA_NAIX >=? ";
        if(sexe!='M'){
            query+="AND SEXE = ? ";
        }
        query+="ORDER BY NOM";
        return query;
    }
    private static String queryUpdateEquip(int i){
        String query="UPDATE EQUIP SET NOM=?";
        if(i==0){
            query+=", TEMP=?, TIPUS=?, CAT=? ";
        }
        query+="WHERE ID=?";
        return query;
    }
    @Override
    public void tancarCapa() throws GestorBDEquipException {
        if (conn != null) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new GestorBDEquipException("Error en fer rollback final.", ex);
            }
            try {
                conn.close();
            } catch (SQLException ex) {
                throw new GestorBDEquipException("Error en tancar la connexió.\n", ex);
            }
        }    
    }
    @Override
    public Usuari loginApp(String user, String pswd) throws GestorBDEquipException{
        if(login==null){
            try{
                login = conn.prepareStatement("SELECT LOGIN FROM USUARI "
                                                + "WHERE LOGIN=? AND PSWD=?");
            }catch(SQLException ex){
                throw new GestorBDEquipException("Error en preparar sentència login", ex);
            }
        }
        String sha1=convertToSH1(pswd);
        Savepoint sp = null;
        Usuari u=new Usuari();
        try {
            sp = conn.setSavepoint();
            login.setString(1, user);
            login.setString(2, sha1);
            ResultSet rs=login.executeQuery();
            
            if(rs.next()) {
                u.setNom(user);
                u.setLogin(true);
            }else{
                throw new GestorBDEquipException("Usuari i/o contrasenya erronia");
            }
            
            rs.close();
        } catch (SQLException ex) {
            throw new GestorBDEquipException("Error en intentar fer el login.", ex);
        } 
        return u;
    }
    @Override
    public void confirmarCanvis() throws GestorBDEquipException {
        try {
            conn.commit();
        } catch (SQLException ex) {
            throw new GestorBDEquipException("Error en confirmar canvis", ex);
        }
    }
    @Override
    public void desferCanvis() throws GestorBDEquipException {
        try {
            conn.rollback();
        } catch (SQLException ex) {
            throw new GestorBDEquipException("Error en desfer canvis", ex);
        }
    }
    @Override
    public List<Categoria> llistatCategories() throws GestorBDEquipException {
        List<Categoria> categories = new ArrayList<>();
        Statement q = null;
        try {
            q = conn.createStatement();
            ResultSet rs = q.executeQuery("SELECT * FROM CATEGORIA ORDER BY EDAT_MIN");
            while (rs.next()) {
                Categoria c = new Categoria(rs.getInt("id"),rs.getString("nom"),rs.getInt("edat_min"),rs.getInt("edat_max"));
                categories.add(c);
            }
            rs.close();
        } catch (SQLException ex) {
           throw new GestorBDEquipException("Error en intentar recuperar la llista de categories.", ex);
        } finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEquipException("Error en intentar tancar la sentència que ha recuperat la llista de Categories.", ex);
                }
            }
        }
        return categories;
    }
    @Override
    public List<Temporada> llistatTemporades() throws GestorBDEquipException {
        List<Temporada> temporades = new ArrayList<>();
        Statement q = null;
        try {
            q = conn.createStatement();
            ResultSet rs = q.executeQuery("SELECT * FROM TEMPORADA ORDER BY ANY_TEMP DESC");
            while (rs.next()) {
                String temp = rs.getString("any_temp");
                Temporada t = new Temporada(temp);
                temporades.add(t);
            }
            rs.close();
        } catch (SQLException ex) {
            throw new GestorBDEquipException("Error en intentar recuperar la llista de temporades.", ex);
        } finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEquipException("Error en intentar tancar la sentència que ha recuperat la llista de temporades.", ex);
                }
            }
        }
        return temporades;
    }
    @Override
    public void afegirTemporada(Temporada temp) throws GestorBDEquipException{
        if (insertTemp == null) {
            try {
                insertTemp = conn.prepareStatement("INSERT INTO TEMPORADA (ANY_TEMP) VALUES (?)");
            } catch (SQLException ex) {
                throw new GestorBDEquipException("Error en preparar sentència insertTemp", ex);
            }
        }
        try {
            insertTemp.setString(1, temp.toString());
            insertTemp.execute();
        } catch (SQLException ex) {
            throw new GestorBDEquipException("Error en intentar inserir la temporada " + temp+", ja existeix!", ex);
        }
    }
    @Override
    public List<Equip> llistatEquips(Temporada t, Map<String, String> filters) throws GestorBDEquipException {
        List<Equip> equips = new ArrayList<>();
        String query=queryLlistatEquips(t, filters);
        Statement q = null;
        try {
            q = conn.createStatement();
            ResultSet rs = q.executeQuery(query);
            while (rs.next()) {
                equips.add(prepararInfoEquip(rs));
            }
            rs.close();
        } catch (SQLException ex) {
            throw new GestorBDEquipException("Error en intentar recuperar la llista de d'equips.", ex);
        } finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEquipException("Error en intentar tancar la sentència que ha recuperat la llista d'equips.", ex);
                }
            }
        }
        return equips;
    }
    @Override
    public Equip infoEquip(int e) throws GestorBDEquipException {
        if(infoEq==null){
            try{
                infoEq = conn.prepareStatement("SELECT * FROM EQUIP "
                                            + "WHERE ID=?");
            }catch(SQLException ex){
                throw new GestorBDEquipException("Error en preparar sentència infoEq", ex);
            }
        }
        Savepoint sp = null;
        Equip eq=new Equip();
        try {
            sp = conn.setSavepoint();
            infoEq.setInt(1, e);
            ResultSet rs=infoEq.executeQuery();
            
            if(rs.next()) {
                eq=prepararInfoEquip(rs);
            }else{
                throw new GestorBDEquipException("Equip inexistent!");
            }
            
            rs.close();
        } catch (SQLException ex) {
            throw new GestorBDEquipException("Error en intentar recuperar l'equip.", ex);
        } 
        return eq;
    }
    @Override
    public int idEquip(Equip eq) throws GestorBDEquipException{
        int id=0;
        if (idEq == null) {
            try {
                idEq = conn.prepareStatement("SELECT ID FROM EQUIP WHERE NOM=? "
                                            + "AND TEMP=? AND TIPUS=? AND CAT=?");
            } catch (SQLException ex) {
                throw new GestorBDEquipException("Error en preparar sentència delEq", ex);
            }
        }
        try {
            idEq.setString(1, eq.getNom());
            idEq.setString(2, eq.tempToString());
            idEq.setString(3, String.valueOf(eq.getTipus()));
            idEq.setInt(4, eq.getCat());
            ResultSet rs= idEq.executeQuery();
            if (rs.next()) {
                id=rs.getInt("id");
            }
            rs.close();
        } catch (SQLException ex) {
            throw new GestorBDEquipException("Error en intentar recuperar l'ID de l'equip.", ex);
        }
        return id;
    }
    @Override
    public void afegirEquip(Equip eq) throws GestorBDEquipException {
        if (insertEq == null) {
            try {
                insertEq = conn.prepareStatement("INSERT INTO EQUIP (NOM, TEMP, TIPUS, CAT) VALUES (?,?,?,?)");
            } catch (SQLException ex) {
                throw new GestorBDEquipException("Error en preparar sentència insertTemp", ex);
            }
        }
        try {
            insertEq.setString(1, eq.getNom());
            insertEq.setString(2, eq.tempToString());
            insertEq.setString(3, String.valueOf(eq.getTipus()));
            insertEq.setInt(4, eq.getCat());
            insertEq.executeUpdate();

        } catch (SQLException ex) {
            throw new GestorBDEquipException("Error en intentar inserir l'equip " + eq.getNom(), ex);
        }
    }
    @Override
    public void eliminarEquip(int eq) throws GestorBDEquipException {
        if (delEq == null) {
            try {
                delEq = conn.prepareStatement("DELETE FROM EQUIP WHERE ID = ?");
            } catch (SQLException ex) {
                throw new GestorBDEquipException("Error en preparar sentència delEq", ex);
            }
        }
        int delete;
        try {
            delEq.setInt(1, eq);
            delete= delEq.executeUpdate();
            if (delete==0) {
                throw new GestorBDEquipException("L'equip que s'intenta eliminar no existeix!");
            }
        } catch (SQLException ex) {
            throw new GestorBDEquipException("No es poden eliminar equips amb jugadors!", ex);
        }
    }
    @Override
    public void modificarEquip(Equip eq) throws GestorBDEquipException {

        int nTitulars=equipTeTitulars(eq);
        String query=queryUpdateEquip(nTitulars);

        try {
            updEq = conn.prepareStatement(query);
        } catch (SQLException ex) {
            throw new GestorBDEquipException("Error en preparar sentència updEq", ex);
        }
        try {
            updEq.setString(1, eq.getNom());
            if(nTitulars==0){
                updEq.setString(2, eq.tempToString());
                updEq.setString(3, String.valueOf(eq.getTipus()));
                updEq.setInt(4, eq.getCat());
                updEq.setInt(5, eq.getId());
            }else{
                updEq.setInt(2, eq.getId());
            }
            int q = updEq.executeUpdate();
            if (q == 0) {
                throw new GestorBDEquipException("Equip "+ eq.getNom() +" no existeix!");
            }
        } catch (SQLException ex) {
            throw new GestorBDEquipException("Error en intentar modificar l'equip " + eq.getNom(), ex);
        }

    }
    @Override
    public List<Jugador> llistatJugadors(Map<String, String> filters) throws GestorBDEquipException {
        List<Jugador> jugadors = new ArrayList<>();
        String query=queryLlistatJugadors(filters);
        Statement q = null;
        try {
            q = conn.createStatement();
            ResultSet rs = q.executeQuery(query);
            while (rs.next()) {
                jugadors.add(prepararInfoJugador(rs));
            }
            rs.close();
        } catch (SQLException ex) {
            throw new GestorBDEquipException("Error en intentar recuperar la llista de jugadors.", ex);
        } finally {
            if (q != null) {
                try {
                    q.close();
                } catch (SQLException ex) {
                    throw new GestorBDEquipException("Error en intentar tancar la sentència que ha recuperat la llista de jugadors.", ex);
                }
            }
        }
        return jugadors;
    }
    @Override
    public Jugador infoJugador(int id) throws GestorBDEquipException {
        if(infoJug==null){
            try{
                infoJug = conn.prepareStatement("SELECT * FROM JUGADOR WHERE ID=?");
            }catch(SQLException ex){
                throw new GestorBDEquipException("Error en preparar sentència infoEq", ex);
            }
        }
        Savepoint sp = null;
        Jugador jug=new Jugador();
        try {
            sp = conn.setSavepoint();
            infoEq.setInt(1, id);
            ResultSet rs=infoEq.executeQuery();
            
            if(rs.next()) {
                jug=prepararInfoJugador(rs);
            }else{
                throw new GestorBDEquipException("Equip inexistent!");
            }
            
            rs.close();
        } catch (SQLException ex) {
            throw new GestorBDEquipException("Error en intentar recuperar l'equip.", ex);
        } 
        return jug;
    }
    @Override
    public void afegirJugador(Jugador jug) throws GestorBDEquipException {
        if (insertJug == null) {
            try {
                insertJug = conn.prepareStatement("INSERT INTO JUGADOR "
                                                + "(NOM, COGNOMS, SEXE, DATA_NAIX, ID_LEGAL, IBAN, "
                                                + "ANY_FI_REVISIO_MEDICA, ADRECA, CP, POBLACIO, FOTO) "
                                                + "VALUES (?,?,?,?,?,?,?,?,?,?,?)");
            } catch (SQLException ex) {
                throw new GestorBDEquipException("Error en preparar sentència insertJug", ex);
            }
        }
        try {
            insertJug.setString(1, jug.getNom());
            insertJug.setString(2, jug.getCognom());
            insertJug.setString(3, String.valueOf(jug.getSexe()));
            insertJug.setDate(4, java.sql.Date.valueOf(convertDate(jug.getData_naix())));
            insertJug.setString(5, jug.getId_legal());
            insertJug.setString(6, jug.getIban());
            insertJug.setDate(7, java.sql.Date.valueOf(convertDate(jug.getAny_fi_revisio_medica())));
            insertJug.setString(8, jug.getAdreca());
            insertJug.setString(9, jug.getCp());
            insertJug.setString(10, jug.getPoblacio());
            insertJug.setString(11, jug.getFoto());
            
            insertJug.executeUpdate();
        } catch (SQLException ex) {
            throw new GestorBDEquipException("Error en intentar inserir el jugador " + jug.getNom(), ex);
        }
    }
    @Override
    public void eliminarJugador(int jug) throws GestorBDEquipException{
        try {
            delJug = conn.prepareStatement("DELETE FROM JUGADOR WHERE ID = ?");
        } catch (SQLException ex) {
            throw new GestorBDEquipException("Error en preparar sentència delJug", ex);
        }
        int delete;
        try {
            delJug.setInt(1, jug);
            delete= delJug.executeUpdate();
            if (delete==0) {
                throw new GestorBDEquipException("El jugador no existeix!");
            }
        } catch (SQLException ex) {
            throw new GestorBDEquipException("No es posible eliminar jugadors que pertanyen a Equips.");
        }
    }
    @Override
    public void modificarJugador(Jugador jug) throws GestorBDEquipException {
        if (updJug == null) {
            try {
                updJug = conn.prepareStatement("UPDATE JUGADOR SET NOM=?, COGNOMS=? "
                                                + ", SEXE=?, DATA_NAIX=?, ID_LEGAL=?, IBAN=? "
                                                + ", ANY_FI_REVISIO_MEDICA=?, ADRECA=? "
                                                + ", CP=?, POBLACIO=?, FOTO=? WHERE ID=?");
            } catch (SQLException ex) {
                throw new GestorBDEquipException("Error en preparar sentència updEq", ex);
            }
        }
        try {
            updJug.setString(1, jug.getNom());
            updJug.setString(2, jug.getCognom());
            updJug.setString(3, String.valueOf(jug.getSexe()));
            updJug.setDate(4, java.sql.Date.valueOf(convertDate(jug.getData_naix())));
            updJug.setString(5, jug.getId_legal());
            updJug.setString(6, jug.getIban());
            updJug.setDate(7, java.sql.Date.valueOf(convertDate(jug.getAny_fi_revisio_medica())));
            updJug.setString(8, jug.getAdreca());
            updJug.setString(9, jug.getCp());
            updJug.setString(10, jug.getPoblacio());
            updJug.setString(11, jug.getFoto());
            updJug.setInt(12, jug.getId());
            int q = updJug.executeUpdate();
            if (q == 0) {
                throw new GestorBDEquipException("Jugador "+ jug.getNom() +" no existeix!");
            }
        } catch (SQLException ex) {
            throw new GestorBDEquipException("Error en intentar modificar el/la jugador/a " + jug.getNom(), ex);
        }
    }
    @Override
    public List<Titular> llistatTitularsEquip(Equip eq) throws GestorBDEquipException {
        List<Titular> titulars = new ArrayList<>();
        if (llistJugTit == null) {
            try {
                llistJugTit = conn.prepareStatement("SELECT * FROM TITULARS "
                                                    + "WHERE EQUIP=? "
                                                    + "ORDER BY JUGADOR ASC");
            } catch (SQLException ex) {
                throw new GestorBDEquipException("Error en preparar sentència llistTit", ex);
            }
        }
        try {
            llistJugTit.setInt(1, eq.getId());
            ResultSet rs= llistJugTit.executeQuery();
            while (rs.next()) {
                Boolean esTitular=(rs.getInt("titular")==1)?true:false;
                titulars.add(new Titular(rs.getInt("equip"),rs.getInt("jugador"),esTitular));
            }
            rs.close();
        } catch (SQLException ex) {
            throw new GestorBDEquipException("Error en intentar recuperar la llista de jugadors.", ex);
        } 
        return titulars;
    }
    @Override
    public List<Jugador> llistatJugadorsTitulars(Equip eq) throws GestorBDEquipException {
        List<Jugador> jugadors = new ArrayList<>();
        if (llistTit == null) {
            try {
                llistTit = conn.prepareStatement("SELECT * FROM JUGADOR "
                                                + "WHERE ID IN (SELECT JUGADOR FROM "
                                                + "TITULARS WHERE EQUIP=?) "
                                                + "ORDER BY NOM");
            } catch (SQLException ex) {
                throw new GestorBDEquipException("Error en preparar sentència llistTit", ex);
            }
        }
        try {
            llistTit.setInt(1, eq.getId());
            ResultSet rs= llistTit.executeQuery();
            while (rs.next()) {
                jugadors.add(prepararInfoJugador(rs));
            }
            rs.close();
        } catch (SQLException ex) {
            throw new GestorBDEquipException("Error en intentar recuperar la llista de jugadors.", ex);
        }
        return jugadors;
    }
    @Override
    public List<Jugador> llistatPossiblesJugadors(Categoria cat, Equip eq) throws GestorBDEquipException {
        List<Jugador> jugadors = new ArrayList<>();
        String dLimit=(Integer.parseInt(eq.getTemp().toString().substring(0,4))-cat.getEdat_Max())+"-01-01";

        try {
            llistJugPos = conn.prepareStatement(queryPossiblesJugadors(eq.getTipus()));
        } catch (SQLException ex) {
            throw new GestorBDEquipException("Error en preparar sentència llistJugPos", ex);
        }

        try {
            String sex=String.valueOf(eq.getTipus());
            
            llistJugPos.setInt(1, eq.getId());
            llistJugPos.setDate(2, java.sql.Date.valueOf(dLimit));
            if(!sex.contains("M")){
                 llistJugPos.setString(3, sex);
            }
           
            ResultSet rs= llistJugPos.executeQuery();
            while (rs.next()) {
                jugadors.add(prepararInfoJugador(rs));
            }
            rs.close();
        } catch (SQLException ex) {
            throw new GestorBDEquipException( ex.getMessage());
        }
        return jugadors;
    }
    @Override
    public void afegirTitular(Titular tit) throws GestorBDEquipException {
        if (insertTit == null) {
            try {
                insertTit = conn.prepareStatement("INSERT INTO TITULARS "
                                                + "(JUGADOR, EQUIP, TITULAR) "
                                                + "VALUES (?,?,?)");
            } catch (SQLException ex) {
                throw new GestorBDEquipException("Error en preparar sentència insertTit", ex);
            }
        }
        try {
            insertTit.setInt(1, tit.getJugador());
            insertTit.setInt(2, tit.getEquip());
            insertTit.setInt(3, (tit.isTitular()?1:0));     
            insertTit.executeUpdate();
        } catch (SQLException ex) {
            throw new GestorBDEquipException("El jugador no pot ser titular de l'equip al que se li està inserint.", ex);
        }
    }
    @Override
    public void eliminarTitular(Titular tit) throws GestorBDEquipException {
        try {
            delTit = conn.prepareStatement("DELETE FROM TITULARS "
                                            + "WHERE EQUIP=? AND JUGADOR=?");
        } catch (SQLException ex) {
            throw new GestorBDEquipException("Error en preparar sentència delTit", ex);
        }
        int delete;
        try {
            delTit.setInt(1, tit.getEquip());
            delTit.setInt(2, tit.getJugador());
            delete= delTit.executeUpdate();
            if (delete==0) {
                throw new GestorBDEquipException("La combinació equip-jugador no existeix!");
            }
        } catch (SQLException ex) {
            throw new GestorBDEquipException(ex.getMessage());
        }
    }
    @Override
    public void modificarTitular(Titular tit) throws GestorBDEquipException {
        if (modTit == null) {
            try {
                modTit = conn.prepareStatement("UPDATE TITULARS SET TITULAR=? "
                                                + "WHERE EQUIP=? AND JUGADOR=?");
            } catch (SQLException ex) {
                throw new GestorBDEquipException("Error en preparar sentència updEq", ex);
            }
        }
        try {
            modTit.setInt(1, (tit.isTitular()?1:0));
            modTit.setInt(2, tit.getEquip());
            modTit.setInt(3, tit.getJugador());
            int done = modTit.executeUpdate();
            if (done == 0) {
                throw new GestorBDEquipException("Combinació Equip-jugador no existeix");
            }
        } catch (SQLException ex) {
            throw new GestorBDEquipException("Error en intentar modificar la titularitat", ex);
        }
    }
    @Override
    public int equipTeTitulars(Equip eq) throws GestorBDEquipException {
        if (equipAmbTitulars == null) {
            try {
                equipAmbTitulars = conn.prepareStatement("SELECT COUNT(*) AS \"TOTAL\" FROM "
                                                        + "TITULARS WHERE EQUIP=?");
            } catch (SQLException ex) {
                throw new GestorBDEquipException("Error en preparar sentència equipAmbTitulars", ex);
            }
        }
        int total=0;
        try {
            equipAmbTitulars.setInt(1, eq.getId());
            ResultSet rs = equipAmbTitulars.executeQuery();
            if (rs.next()) {
                total=rs.getInt("total");
            }
        } catch (SQLException ex) {
            throw new GestorBDEquipException("Error al intentar verificar si l'equip "+eq.getNom()+" te jugadors.", ex);
        }
        return total;
    }
    @Override
    public Boolean jugadorParticipaEnAltresEquips(int eqID, int jugID) throws GestorBDEquipException {
        if (jugTitularAltresEquips == null) {
            try {
                jugTitularAltresEquips = conn.prepareStatement("SELECT COUNT(*) AS \"TOTAL\" FROM "
                                                        + "TITULARS WHERE EQUIP!=? AND JUGADOR=? AND TITULAR=1");
            } catch (SQLException ex) {
                throw new GestorBDEquipException("Error en preparar sentència equipAmbTitulars", ex);
            }
        }
        boolean si=false;
        int total=0;
        try {
            jugTitularAltresEquips.setInt(1, eqID);
            jugTitularAltresEquips.setInt(2, jugID);
            ResultSet rs = jugTitularAltresEquips.executeQuery();
            if (rs.next()) {
                total=rs.getInt("total");
            }
        } catch (SQLException ex) {
            throw new GestorBDEquipException("Error al intentar verificar si el jugador es titular en altres equips", ex);
        }
        if(total!=0){
            si=true;
        }
        return si;
    }
}
