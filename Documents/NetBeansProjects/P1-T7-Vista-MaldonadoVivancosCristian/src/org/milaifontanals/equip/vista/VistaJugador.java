/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.milaifontanals.equip.vista;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.milaifontanals.equip.interficiepersistencia.GestorBDEquipException;

import org.milaifontanals.equip.model.*;

/**
 * @author MAVI
 */
public class VistaJugador extends javax.swing.JFrame {
    private List<JRadioButton> rbList = new ArrayList<>();
    private List<JLabel> errList= new ArrayList<>();
    private String[] tableTitles= new String[]{"ID","Equip","Temporada","Categ.","Tipus","Eliminar Titularitat"};
    private Jugador jugSel;
    private Jugador jugAux=new Jugador(); //equip auxiliar per fer tracking dels canvis
    private boolean existeix;
    private MainPage mp;
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    JFormattedTextField txtDate = new JFormattedTextField(df);
    BufferedImage getImg;
    private String pathFoto="";
    
    public VistaJugador(int idJug, MainPage view) throws IOException {
        initComponents();
        //si s'ha pasat un id == -1 vol dir que esta creant un nou jugador
        existeix=(idJug==-1)?false:true;
        mp=view;
        //si no es un alta guardem recuperem la informació del jugador
        if(existeix){
            try {
                jugSel=Constants.getgBD().infoJugador(idJug);
                jugAux=jugSel;
                pathFoto=jugSel.getFoto();
            } catch (GestorBDEquipException ex) {
                errGJ.setText(ex.getMessage());
            }
        }else{
            jugSel=new Jugador();
            carregarFoto("./img/default.png");
        }
        carregarLlistatErrors();
        carregarLlistatRb();
        prepararFinestra();
        crearTableModel();
    }
    //aquet programa s'ha fet perque hem costa controlar la List de Titulars
    public void carregarLlistatRb(){
        rbList.add(rbFem);
        rbList.add(rbMasc);
    }
    
    public void carregarLlistatErrors(){
        errList.add(errNom);
        errList.add(errCognom);
        errList.add(errIdLegal);
        errList.add(errAdreca);
        errList.add(errCP);
        errList.add(errPoblacio);
        errList.add(errDN);
        errList.add(errRevMed);
    }
    public void carregarFoto(String pth){
        File defaultImg=new File(pth);
        BufferedImage img;
        try {
            img = ImageIO.read(defaultImg);
            fotoEnRecuadre(img);
        } catch (IOException ex) {
            errGJ.setText(ex.getMessage());
        }
    }
    public void activarBotonsiCerca(){
        //únicament estarán abilitats aquest botons si el jugador es nou
        eliminarJugador.setVisible(existeix);
    }
    //preparem l'informació de la finestra
    public void prepararFinestra(){
        activarBotonsiCerca();
        buttonGroup.add(rbMasc);
        buttonGroup.add(rbFem);
        sexeDefault();
        revMedica.setCalendar(Calendar.getInstance());
        dNaix.setCalendar(Calendar.getInstance());
        actualitzarTitoliDades();
    }
    public void actualitzarTitoliDades(){
        //titol per default si es una alta nova
        String titol = "Alta Jugador";
        if(existeix){
            //carregem les dades
            String nomJug=jugSel.getNom();
            titol="Edició Jugador - "+nomJug;
            nom.setText(nomJug);
            cognom.setText(jugSel.getCognom());
            idLegal.setText(jugSel.getId_legal());
            dNaix.setCalendar(jugSel.getData_naix());
            iban.setText(jugSel.getIban());
            adreca.setText(jugSel.getAdreca());
            poblacio.setText(jugSel.getPoblacio());
            cp.setText(jugSel.getCp());
            revMedica.setCalendar(jugSel.getAny_fi_revisio_medica());
            feta.setSelected(revMedica.getDate()!=null);
            carregarFoto(jugSel.getFoto());
        }
        titolLabel.setText(titol); //fiquel el titol
    }
    public void sexeDefault(){
        //si es una modificació d'un equip o l'equiup s'ha donat d'alta fixem la categoria
        if(existeix){
            char tipus=jugSel.getSexe();
            //seleccionem la categoria
            int i=0;
            while(rbList.get(i).getName().charAt(0)!=tipus && i!=rbList.size()){
                i++;
            }
            rbList.get(i).setSelected(existeix);
        }else{
            rbMasc.setSelected(true);
            jugAux.setSexe(rbMasc.getName().charAt(0));
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        logOut = new javax.swing.JButton();
        tornar = new javax.swing.JButton();
        infoEquip = new javax.swing.JPanel();
        nomLabel = new javax.swing.JLabel();
        nom = new javax.swing.JTextField();
        sexeLabel = new javax.swing.JLabel();
        guardarCanvis = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        eqTable = new javax.swing.JTable();
        rbMasc = new javax.swing.JRadioButton();
        rbFem = new javax.swing.JRadioButton();
        idLegalLable = new javax.swing.JLabel();
        cognomLabel = new javax.swing.JLabel();
        cognom = new javax.swing.JTextField();
        dNaixLabel = new javax.swing.JLabel();
        iban = new javax.swing.JTextField();
        adrecaLabel = new javax.swing.JLabel();
        poblacioLabel = new javax.swing.JLabel();
        cpLabel = new javax.swing.JLabel();
        cp = new javax.swing.JTextField();
        adreca = new javax.swing.JTextField();
        poblacio = new javax.swing.JTextField();
        dRevMedLabel = new javax.swing.JLabel();
        feta = new javax.swing.JCheckBox();
        ibanLabel1 = new javax.swing.JLabel();
        errGJ = new javax.swing.JLabel();
        dNaix = new com.toedter.calendar.JDateChooser();
        revMedica = new com.toedter.calendar.JDateChooser();
        errIban = new javax.swing.JLabel();
        errDN = new javax.swing.JLabel();
        errCognom = new javax.swing.JLabel();
        errNom = new javax.swing.JLabel();
        errAdreca = new javax.swing.JLabel();
        errIdLegal = new javax.swing.JLabel();
        errPoblacio = new javax.swing.JLabel();
        errCP = new javax.swing.JLabel();
        errRevMed = new javax.swing.JLabel();
        idLegal = new javax.swing.JTextField();
        eliminarJugador = new javax.swing.JButton();
        titolLabel = new javax.swing.JLabel();
        infoLabel = new javax.swing.JLabel();
        jugImatge = new javax.swing.JLabel();
        pujarFoto = new javax.swing.JButton();
        errFoto = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setMaximumSize(new java.awt.Dimension(800, 550));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 550));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 550));

        logOut.setBackground(new java.awt.Color(0, 153, 51));
        logOut.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        logOut.setForeground(new java.awt.Color(255, 255, 255));
        logOut.setText("Log-out");
        logOut.setAlignmentX(0.5F);
        logOut.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        logOut.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logOut(evt);
            }
        });

        tornar.setBackground(new java.awt.Color(0, 153, 51));
        tornar.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        tornar.setForeground(new java.awt.Color(255, 255, 255));
        tornar.setText("Tornar a Menú");
        tornar.setAlignmentX(0.5F);
        tornar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        tornar.setName("gj"); // NOI18N
        tornar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tornar(evt);
            }
        });

        infoEquip.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 0)), "INFORMACIO DEL JUGADOR", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bauhaus 93", 0, 14), new java.awt.Color(0, 153, 0))); // NOI18N
        infoEquip.setMaximumSize(new java.awt.Dimension(782, 297));
        infoEquip.setMinimumSize(new java.awt.Dimension(782, 297));

        nomLabel.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        nomLabel.setText("Nom:");

        nom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        nom.setMaximumSize(new java.awt.Dimension(64, 18));
        nom.setName("nom"); // NOI18N
        nom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                entradaDada(evt);
            }
        });

        sexeLabel.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        sexeLabel.setText("*Sexe:");

        guardarCanvis.setBackground(new java.awt.Color(0, 153, 51));
        guardarCanvis.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        guardarCanvis.setForeground(new java.awt.Color(255, 255, 255));
        guardarCanvis.setText("Guardar");
        guardarCanvis.setToolTipText("");
        guardarCanvis.setAlignmentX(0.5F);
        guardarCanvis.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        guardarCanvis.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guardarCanvis(evt);
            }
        });

        eqTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        eqTable.setGridColor(new java.awt.Color(51, 153, 0));
        eqTable.setMaximumSize(new java.awt.Dimension(300, 80));
        eqTable.setMinimumSize(new java.awt.Dimension(300, 80));
        eqTable.setName("eqTable"); // NOI18N
        eqTable.setShowVerticalLines(true);
        jScrollPane1.setViewportView(eqTable);

        rbMasc.setText("Masculí");
        rbMasc.setName("H"); // NOI18N
        rbMasc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccioItem(evt);
            }
        });

        rbFem.setText("Femení");
        rbFem.setName("D"); // NOI18N
        rbFem.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccioItem(evt);
            }
        });

        idLegalLable.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        idLegalLable.setText("NIE/NIF:");

        cognomLabel.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        cognomLabel.setText("Cognom:");

        cognom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cognom.setMaximumSize(new java.awt.Dimension(64, 18));
        cognom.setName("cognom"); // NOI18N
        cognom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                entradaDada(evt);
            }
        });

        dNaixLabel.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        dNaixLabel.setText("*Data Naix.:");

        iban.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        iban.setMaximumSize(new java.awt.Dimension(64, 18));
        iban.setName("iban"); // NOI18N
        iban.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                entradaDada(evt);
            }
        });

        adrecaLabel.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        adrecaLabel.setText("Adreça:");

        poblacioLabel.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        poblacioLabel.setText("Població:");

        cpLabel.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        cpLabel.setText("Codi Postal:");

        cp.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        cp.setName("cp"); // NOI18N
        cp.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                entradaDada(evt);
            }
        });

        adreca.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        adreca.setMaximumSize(new java.awt.Dimension(64, 18));
        adreca.setName("adreca"); // NOI18N
        adreca.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                entradaDada(evt);
            }
        });

        poblacio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        poblacio.setMaximumSize(new java.awt.Dimension(64, 18));
        poblacio.setName("poblacio"); // NOI18N
        poblacio.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                entradaDada(evt);
            }
        });

        dRevMedLabel.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        dRevMedLabel.setText("Rev.Medica:");

        feta.setForeground(new java.awt.Color(51, 153, 0));
        feta.setEnabled(false);

        ibanLabel1.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        ibanLabel1.setText("IBAN:");

        errGJ.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        errGJ.setForeground(new java.awt.Color(255, 0, 0));
        errGJ.setName("errGJ"); // NOI18N

        dNaix.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        dNaix.setName("dnaix"); // NOI18N

        revMedica.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        revMedica.setName("revmed"); // NOI18N

        errIban.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        errIban.setForeground(new java.awt.Color(255, 0, 0));
        errIban.setName("errGJ"); // NOI18N

        errDN.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        errDN.setForeground(new java.awt.Color(255, 0, 0));
        errDN.setMaximumSize(new java.awt.Dimension(64, 18));
        errDN.setMinimumSize(new java.awt.Dimension(64, 18));
        errDN.setName("errGJ"); // NOI18N
        errDN.setPreferredSize(new java.awt.Dimension(64, 18));

        errCognom.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        errCognom.setForeground(new java.awt.Color(255, 0, 0));
        errCognom.setMaximumSize(new java.awt.Dimension(64, 18));
        errCognom.setMinimumSize(new java.awt.Dimension(64, 18));
        errCognom.setName("errGJ"); // NOI18N
        errCognom.setPreferredSize(new java.awt.Dimension(64, 18));

        errNom.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        errNom.setForeground(new java.awt.Color(255, 0, 0));
        errNom.setMaximumSize(new java.awt.Dimension(64, 18));
        errNom.setMinimumSize(new java.awt.Dimension(64, 18));
        errNom.setName("errGJ"); // NOI18N
        errNom.setPreferredSize(new java.awt.Dimension(64, 18));

        errAdreca.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        errAdreca.setForeground(new java.awt.Color(255, 0, 0));
        errAdreca.setName("errGJ"); // NOI18N

        errIdLegal.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        errIdLegal.setForeground(new java.awt.Color(255, 0, 0));
        errIdLegal.setMaximumSize(new java.awt.Dimension(64, 18));
        errIdLegal.setMinimumSize(new java.awt.Dimension(64, 18));
        errIdLegal.setName("errGJ"); // NOI18N
        errIdLegal.setPreferredSize(new java.awt.Dimension(64, 18));

        errPoblacio.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        errPoblacio.setForeground(new java.awt.Color(255, 0, 0));
        errPoblacio.setName("errGJ"); // NOI18N

        errCP.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        errCP.setForeground(new java.awt.Color(255, 0, 0));
        errCP.setName("errGJ"); // NOI18N

        errRevMed.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        errRevMed.setForeground(new java.awt.Color(255, 0, 0));
        errRevMed.setName("errGJ"); // NOI18N

        idLegal.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        idLegal.setMaximumSize(new java.awt.Dimension(64, 18));
        idLegal.setName("idlegal"); // NOI18N
        idLegal.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                entradaDada(evt);
            }
        });

        javax.swing.GroupLayout infoEquipLayout = new javax.swing.GroupLayout(infoEquip);
        infoEquip.setLayout(infoEquipLayout);
        infoEquipLayout.setHorizontalGroup(
            infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoEquipLayout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(infoEquipLayout.createSequentialGroup()
                        .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(infoEquipLayout.createSequentialGroup()
                                        .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(infoEquipLayout.createSequentialGroup()
                                                .addComponent(dNaixLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(dNaix, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(nomLabel)
                                            .addComponent(cognomLabel)
                                            .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addGroup(infoEquipLayout.createSequentialGroup()
                                                    .addComponent(idLegalLable)
                                                    .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addGroup(infoEquipLayout.createSequentialGroup()
                                                            .addGap(39, 39, 39)
                                                            .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoEquipLayout.createSequentialGroup()
                                                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                            .addComponent(idLegal, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                                .addComponent(cognom, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addComponent(errIdLegal, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                            .addComponent(errNom, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(errCognom, javax.swing.GroupLayout.PREFERRED_SIZE, 242, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGap(2, 2, 2))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(errIban, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(errDN, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGroup(infoEquipLayout.createSequentialGroup()
                                    .addComponent(dRevMedLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                    .addComponent(revMedica, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                    .addComponent(feta)
                                    .addGap(14, 14, 14)))
                            .addComponent(errRevMed, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(53, 53, 53))
                    .addGroup(infoEquipLayout.createSequentialGroup()
                        .addComponent(ibanLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(iban, javax.swing.GroupLayout.PREFERRED_SIZE, 184, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(22, 22, 22)))
                .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(errGJ, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, infoEquipLayout.createSequentialGroup()
                        .addGap(30, 30, 30)
                        .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(errPoblacio, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(infoEquipLayout.createSequentialGroup()
                                .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoEquipLayout.createSequentialGroup()
                                        .addComponent(poblacioLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(25, 25, 25))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoEquipLayout.createSequentialGroup()
                                        .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(cpLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(adrecaLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED))
                                    .addGroup(infoEquipLayout.createSequentialGroup()
                                        .addComponent(sexeLabel)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                                .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(poblacio, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(infoEquipLayout.createSequentialGroup()
                                        .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(cp, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(rbMasc))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                            .addComponent(rbFem)
                                            .addComponent(errCP, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addGroup(infoEquipLayout.createSequentialGroup()
                                        .addComponent(adreca, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(40, 40, 40)
                                        .addComponent(guardarCanvis, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(errAdreca, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addGap(23, 23, 23))
        );
        infoEquipLayout.setVerticalGroup(
            infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoEquipLayout.createSequentialGroup()
                .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardarCanvis, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idLegalLable)
                    .addComponent(adreca, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(adrecaLabel)
                    .addComponent(idLegal, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(7, 7, 7)
                .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(errIdLegal, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(errAdreca, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nomLabel)
                        .addComponent(poblacioLabel)
                        .addComponent(poblacio, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoEquipLayout.createSequentialGroup()
                        .addComponent(errNom, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(2, 2, 2))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoEquipLayout.createSequentialGroup()
                        .addComponent(errPoblacio, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cognomLabel)
                        .addComponent(cognom, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(errCP, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(cp, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(cpLabel)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(errCognom, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(rbMasc)
                        .addComponent(rbFem)
                        .addComponent(sexeLabel))
                    .addComponent(dNaixLabel)
                    .addComponent(dNaix, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoEquipLayout.createSequentialGroup()
                        .addComponent(errDN, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(iban, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ibanLabel1))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(errIban, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(dRevMedLabel)
                            .addComponent(revMedica, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(feta, javax.swing.GroupLayout.Alignment.LEADING))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(errRevMed, javax.swing.GroupLayout.PREFERRED_SIZE, 18, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(infoEquipLayout.createSequentialGroup()
                        .addComponent(errGJ, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        eliminarJugador.setBackground(new java.awt.Color(0, 153, 51));
        eliminarJugador.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        eliminarJugador.setForeground(new java.awt.Color(255, 255, 255));
        eliminarJugador.setText("Eliminar Jugador");
        eliminarJugador.setToolTipText("");
        eliminarJugador.setAlignmentX(0.5F);
        eliminarJugador.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        eliminarJugador.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eliminarJugador(evt);
            }
        });

        titolLabel.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        titolLabel.setForeground(new java.awt.Color(0, 153, 0));
        titolLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titolLabel.setText("jLabel1");

        infoLabel.setFont(new java.awt.Font("Segoe UI", 2, 12)); // NOI18N
        infoLabel.setForeground(new java.awt.Color(102, 102, 102));
        infoLabel.setText("* camps nomès modificables si el jugador no pertany a cap equip");
        infoLabel.setName("infoLabel"); // NOI18N

        jugImatge.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 0), 1, true));

        pujarFoto.setBackground(new java.awt.Color(0, 153, 51));
        pujarFoto.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        pujarFoto.setForeground(new java.awt.Color(255, 255, 255));
        pujarFoto.setText("Pujar Foto");
        pujarFoto.setToolTipText("");
        pujarFoto.setAlignmentX(0.5F);
        pujarFoto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pujarFoto.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pujarFoto(evt);
            }
        });

        errFoto.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        errFoto.setForeground(new java.awt.Color(255, 0, 0));
        errFoto.setName("errGJ"); // NOI18N

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(infoEquip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(6, 6, 6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(eliminarJugador, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(infoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 351, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(tornar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(51, 51, 51)
                                .addComponent(titolLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 371, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(44, 44, 44)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jugImatge, javax.swing.GroupLayout.PREFERRED_SIZE, 102, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(pujarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 90, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(logOut, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(23, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(errFoto, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(15, 15, 15))))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tornar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(eliminarJugador))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(22, 112, Short.MAX_VALUE)
                        .addComponent(infoLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(logOut, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(pujarFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jugImatge, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 99, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(titolLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(errFoto, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0)
                .addComponent(infoEquip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(10, 10, 10))
        );

        tornar.getAccessibleContext().setAccessibleName("gj");
        tornar.getAccessibleContext().setAccessibleDescription("gj");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents
    private void tornar(java.awt.event.MouseEvent evt) {                        
        deixemLaFinestra();
    }   
    private void eliminarJugador(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarJugador
        boolean err=false;
        try {
            Constants.getgBD().eliminarJugador(jugSel.getId());
        } catch (GestorBDEquipException ex) {
            errGJ.setText(ex.getMessage());
            err=true;
        }
        //si no hi ha error al eliminar demanem confirmació
        if(!err){
            confirmacióEliminar();
        }
    }//GEN-LAST:event_eliminarJugador
    //Font: https://www.youtube.com/watch?v=YZ_tQFTMYoQ & 
    //https://stackoverflow.com/questions/72240246/jlabel-not-displaying-image-after-choosing-from-jfilechooser-in-java-netbeans-id
    private void pujarFoto(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pujarFoto
        FileFilter imageFilter=new FileNameExtensionFilter("Image files", ImageIO.getReaderFileSuffixes());
        JFileChooser imageUpload=new JFileChooser();
        imageUpload.setAcceptAllFileFilterUsed(false); //no permitim cap tipus de file...
        imageUpload.addChoosableFileFilter(imageFilter); //...a excepció d'imatges
        int res =imageUpload.showSaveDialog(null); //guardem la resposta després d'obrir el panell
        String err="";
        if(res==JFileChooser.APPROVE_OPTION){
            File imgFile = imageUpload.getSelectedFile();
            String imgPath = imgFile.getAbsolutePath();
            BufferedImage img = null;
            try {
                //carregem la imatge al recuadre
                img = ImageIO.read(new File(imgPath));
                fotoEnRecuadre(img);
                //Obtením l'extensio del file:
                String extension=imgPath.substring(imgPath.indexOf("."));
                //generem el path per guardar l'imatge al projecte i per guardar la ruta a la BBDD.
                File pathToSave;
                if(pathFoto.isEmpty()){
                    String imageName=imgFile.getName();
                    pathToSave= new File("./img/"+imageName);
                }else{
                    pathToSave= new File("./img/"+jugAux.getId_legal()+".png");
                }
                jugAux.setFoto(pathToSave.toString());
                pathFoto=jugAux.getFoto();
                //guardem la imatge a la carpeta del projecte anomenada img
                ImageIO.write(img, "jpg", pathToSave);
                
            } catch (IOException e) {
                err=e.getMessage();
            }
            errFoto.setText(err);
        }
    }//GEN-LAST:event_pujarFoto
    private void fotoEnRecuadre(BufferedImage img){
            //carregem l'imatge per a visualitzar-la a la JLable jugImatge
            Image dimg  = img.getScaledInstance(jugImatge.getWidth(), jugImatge.getHeight(),img.SCALE_SMOOTH);
            ImageIcon icon = new ImageIcon(dimg);
            jugImatge.setIcon(icon);
    }
    private void seleccioItem(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccioItem
        String tipus=evt.getComponent().getName();
        jugAux.setSexe(tipus.charAt(0));
    }//GEN-LAST:event_seleccioItem
    private void carregarDadesNoves(){
        errNom.setText(jugAux.setNom(nom.getText()));
        errCognom.setText(jugAux.setCognom(cognom.getText()));
        errIdLegal.setText(jugAux.setId_legal(idLegal.getText()));
        errAdreca.setText(jugAux.setAdreca(adreca.getText()));
        errCP.setText(jugAux.setCp(cp.getText()));
        errPoblacio.setText(jugAux.setPoblacio(poblacio.getText()));
        if(iban.getText().length()!=0){
            errIban.setText(jugAux.setIban(iban.getText()));
        }
        Date drm=dNaix.getDate();
        if(drm==null){
            errDN.setText("La data de naix es obligatoria");
        }else{
            errDN.setText(jugAux.setData_naix(dNaix.getCalendar()));
        }
       Date dnaix=revMedica.getDate();
        if(dnaix==null){
            errRevMed.setText("La Revisió Médica es obligatoria");
        }else{
            jugAux.setAny_fi_revisio_medica(revMedica.getCalendar());
        }
    }
    private void guardarCanvis(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardarCanvis
        String err="";
        carregarDadesNoves();
        Boolean senseErrors=revisioErrors();
        if(senseErrors && !pathFoto.isEmpty()){
            int jugNou=Optional.ofNullable(jugAux.getId()).orElse(0);
            try {
                if(jugNou == 0){
                    Constants.getgBD().afegirJugador(jugAux);
                }else{
                    Constants.getgBD().modificarJugador(jugAux);
                }
            } catch (GestorBDEquipException ex) {
                err=ex.getMessage();
            }
            confirmarCanvis(err);
            //cambiarem el titol únicament si es un equip nou i ara existeix
            if(jugNou==0){
                try {
                    jugAux.setId(Constants.getgBD().idJugador(jugAux.getId_legal()));
                    jugSel=jugAux;
                    existeix=true; //ara el jugador existeix
                    prepararFinestra();
                } catch (GestorBDEquipException ex) {
                    err=ex.getMessage();
                }
            }
            if(err.isBlank()){
                crearTableModel();
            }
             errGJ.setText(err);
        }else{
            String errF="";
            if(pathFoto.isEmpty()){
                errF="Falta la foto!";
            }
            errFoto.setText(errF);
        }
    }//GEN-LAST:event_guardarCanvis

//GEN-FIRST:event_tornar

//GEN-LAST:event_tornar

    private void logOut(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOut
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_logOut

    private void entradaDada(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_entradaDada
        String objecte=evt.getComponent().getName();
        switch (objecte){
            case "nom":
                errNom.setText(jugAux.setNom(nom.getText()));
                break;
            case "cognom":
                errCognom.setText(jugAux.setCognom(cognom.getText()));
                break;
            case "idlegal":
                errIdLegal.setText(jugAux.setId_legal(idLegal.getText()));
                break;
            case "adreca":
                errAdreca.setText(jugAux.setAdreca(adreca.getText()));
                break;
            case "cp":
                errCP.setText(jugAux.setCp(cp.getText()));
                break;
            case "poblacio":
                errPoblacio.setText(jugAux.setPoblacio(poblacio.getText()));
                break;
            case "iban":
                errIban.setText(jugAux.setIban(iban.getText()));
                break;
        }

    }//GEN-LAST:event_entradaDada
    private void confirmarCanvis(String err){
        try {
            errGJ.setText(err);
            if(err.isBlank()){
                Constants.getgBD().confirmarCanvis();
            }else{
                Constants.getgBD().desferCanvis();
            }
        } catch (GestorBDEquipException ex) {
            errGJ.setText(ex.getMessage());
        }
    }
    public void deixemLaFinestra(){
        this.dispose();
        mp.setVisible(true);
    }           
    private boolean revisioErrors(){
        boolean senseErrors=false;
        int i=0;
        while(!senseErrors && i!=errList.size()){
            if(!errList.get(i).getText().isEmpty()){
                senseErrors=true;
            }
            i++;
        }
        return !senseErrors;
    }
    //creem dinámicament la taula
    private void crearTableModel(){
        int numColumns=tableTitles.length;
        DefaultTableModel mt= new DefaultTableModel(new Object[0][tableTitles.length],tableTitles);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        errGJ.setText("");//ens asegurem que no queda cap error a la finestra
        eqTable.setModel(mt);//setejem columnes
        //amagem la columna ID que la farem servir per a eliminar/
        eqTable.removeColumn(eqTable.getColumnModel().getColumn(0));
        if(existeix){
            try {
                //Centrem les columnes necesaries (Categoria, tipus, te jugadors):
                for(int i=3; i<(numColumns-1);i++){
                    eqTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
                }
                //carregemm els equips als que juga
                List<Equip> equipParticipa =Constants.getgBD().llistatEquipParticipa(jugSel.getId());
                for(Equip e : equipParticipa){
                    Object[] info= new Object[]{
                        e.getId(),
                        e.getNom(),
                        e.getTemp(),
                        Constants.tipusNom(e.getTipus()),
                        Constants.nomCategoria(e.getCat()),
                        "Eliminar"
                        };
                    mt.addRow(info);
                }
                boolean enable=true;
                if(equipParticipa.size()!=0){
                    enable=false;
                }  
                dNaix.setEnabled(enable);
                rbMasc.setEnabled(enable);
                rbFem.setEnabled(enable);
                taulaConfiguration(eqTable,4);
            } catch (GestorBDEquipException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "InfoBox: " + "DB Connection Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    private void taulaConfiguration(JTable t, int del){
        t.setAutoCreateRowSorter(true);
        ButtonColumn buttonDel= new ButtonColumn(t,delTitularitat,del);
    }
    Action delTitularitat= new AbstractAction(){
        public void actionPerformed(ActionEvent e){
            JTable table = (JTable)e.getSource(); 
            String tName= table.getName();
            int row = Integer.valueOf( e.getActionCommand() );
            int id= Integer.valueOf(((DefaultTableModel)table.getModel()).getValueAt(row, 0).toString());
            String nom= ((DefaultTableModel)table.getModel()).getValueAt(row, 1).toString();
            Boolean err=false;
            try {
                Constants.getgBD().eliminarTitular(new Titular(id,jugSel.getId(),false));
            } catch (GestorBDEquipException ex) {
                errGJ.setText(ex.getMessage());
                err=true;
            }
            //si no hi ha hagut capp error en intentar eliminar el jugador/equip
            if(!err){
                //demanem confirmació abans d'eliminar, si tot ha anat bé retorna 0
                if(confirmacióEliminarTitularitat(nom, tName)==0 && !err){
                    //si confirma, després de fer el delete a la BBDD, ens carregem la row.
                    ((DefaultTableModel)table.getModel()).removeRow(row);
                }
                if(table.getRowCount()==0){
                    dNaix.setEnabled(true);
                    rbMasc.setEnabled(true);
                    rbFem.setEnabled(true);
                }
            }

        }
    };
    private int confirmacióEliminarTitularitat(String nom, String tName){
        int resposta=JOptionPane.showConfirmDialog(null, "Estas segur que vols que el jugador "+jugSel.getNom()+" deixi de ser titular de l'equip "+nom+"?", "Compte!",
			JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        String err="";
        try {
            if(resposta==0){
                Constants.getgBD().confirmarCanvis();
            }else{
                Constants.getgBD().desferCanvis();
            }
        } catch (GestorBDEquipException ex) {
            err=ex.getMessage();
            resposta=1;
        }
        errGJ.setText(err);
        return resposta;
    }
    private void confirmacióEliminar(){
        int resposta=JOptionPane.showConfirmDialog(null, "Estas segur que vols eliminar el jugador "+jugSel.getNom()+"?", "Compte!",
			JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        String err="";
        if(resposta==0){
            try {
                Constants.getgBD().confirmarCanvis();
                deixemLaFinestra();
            } catch (GestorBDEquipException ex) {
                err=ex.getMessage();
                resposta=1;
            }
        }else{
            try {
                Constants.getgBD().desferCanvis();
            } catch (GestorBDEquipException ex) {
                 err=ex.getMessage();
                 resposta=1;
            }
        }
        errGJ.setText(err);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField adreca;
    private javax.swing.JLabel adrecaLabel;
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JTextField cognom;
    private javax.swing.JLabel cognomLabel;
    private javax.swing.JTextField cp;
    private javax.swing.JLabel cpLabel;
    private com.toedter.calendar.JDateChooser dNaix;
    private javax.swing.JLabel dNaixLabel;
    private javax.swing.JLabel dRevMedLabel;
    private javax.swing.JButton eliminarJugador;
    private javax.swing.JTable eqTable;
    private javax.swing.JLabel errAdreca;
    private javax.swing.JLabel errCP;
    private javax.swing.JLabel errCognom;
    private javax.swing.JLabel errDN;
    private javax.swing.JLabel errFoto;
    private javax.swing.JLabel errGJ;
    private javax.swing.JLabel errIban;
    private javax.swing.JLabel errIdLegal;
    private javax.swing.JLabel errNom;
    private javax.swing.JLabel errPoblacio;
    private javax.swing.JLabel errRevMed;
    private javax.swing.JCheckBox feta;
    private javax.swing.JButton guardarCanvis;
    private javax.swing.JTextField iban;
    private javax.swing.JLabel ibanLabel1;
    private javax.swing.JTextField idLegal;
    private javax.swing.JLabel idLegalLable;
    private javax.swing.JPanel infoEquip;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel jugImatge;
    private javax.swing.JButton logOut;
    private javax.swing.JTextField nom;
    private javax.swing.JLabel nomLabel;
    private javax.swing.JTextField poblacio;
    private javax.swing.JLabel poblacioLabel;
    private javax.swing.JButton pujarFoto;
    private javax.swing.JRadioButton rbFem;
    private javax.swing.JRadioButton rbMasc;
    private com.toedter.calendar.JDateChooser revMedica;
    private javax.swing.JLabel sexeLabel;
    private javax.swing.JLabel titolLabel;
    private javax.swing.JButton tornar;
    // End of variables declaration//GEN-END:variables
}
