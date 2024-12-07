/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.milaifontanals.equip.vista;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.Map.entry;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;
import org.milaifontanals.equip.interficiepersistencia.GestorBDEquipException;

import org.milaifontanals.equip.model.*;

/**
 *
 * @author MAVI
 */
public class MainPage extends javax.swing.JFrame {
    private Map <String, String[]> headers= Map.ofEntries(
            entry("ge",new String[]{" ","Nom","Categoria","Tipus","Conté Jugadors"," "}),
            entry("gj",new String[]{"Nom","Cognom","Categoria","Sexe","Data Naixement","Revisió Médica",""}));
    private DefaultTableModel tabModel;
    private TableRowSorter<DefaultTableModel> ordenar;
    private List<JCheckBox> geCB = new ArrayList<>();
    private List<JCheckBox> gjCB = new ArrayList<>();
    
    public MainPage() {
        initComponents();
        settingComponents();
        loadTemporadas();
        loadCategories();
        gestorEqListCheckBox();
        gestorJugListCheckBox();
    }
    //fem un llistat dels comboboxs relacionats amb gestio d'equips per iterar-los
    public void gestorEqListCheckBox(){
        geCB.add(homeCheck);
        geCB.add(donaCheck);
        geCB.add(mixtCheck);
    }
    //fem un llistat dels comboboxs relacionats amb gestio de Jugadors per iterar-los
    public void gestorJugListCheckBox(){

    }
    //carregem l'info al comboBox
    public void loadTemporadas(){
        listTemp.removeAllItems();
        List<Temporada> temp=Constants.getTemp();
        Temporada auxSel=Constants.gettSel();
        //carregem el comboboxMavi
        for(Temporada t:temp){
            listTemp.addItem(t.toString());
        }
        //si hi ha una temporada preseleccionada la seleccionem
        if(!auxSel.toString().isEmpty()){
            listTemp.getModel().setSelectedItem(auxSel.toString());
        }
    }
    public void loadCategories(){
        List<Categoria> cat=Constants.getCategs();
        //carregem el comboboxMavi
        categoria.addItem("--Selecciona Categoria--");
        for(Categoria c: cat){
            categoria.addItem(c.getNom());
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

        jPanel1 = new javax.swing.JPanel();
        temporadaLabel = new javax.swing.JLabel();
        logOut = new javax.swing.JButton();
        listTemp = new javax.swing.JComboBox<>();
        gestioEq = new javax.swing.JButton();
        gestioJug = new javax.swing.JButton();
        llistatEqPanel = new javax.swing.JPanel();
        nomLabel = new javax.swing.JLabel();
        nom = new javax.swing.JTextField();
        tipusLabel = new javax.swing.JLabel();
        categoria = new javax.swing.JComboBox<>();
        catLabel = new javax.swing.JLabel();
        homeCheck = new javax.swing.JCheckBox();
        mixtCheck = new javax.swing.JCheckBox();
        donaCheck = new javax.swing.JCheckBox();
        filtrarEquip = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaInfo = new javax.swing.JTable();
        tempError = new javax.swing.JLabel();
        afegirTempPanel = new javax.swing.JPanel();
        novaTempLabel = new javax.swing.JLabel();
        tempInput = new javax.swing.JTextField();
        insertTemp = new javax.swing.JButton();
        infoInsertTemp = new javax.swing.JLabel();
        altaEquip = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setMaximumSize(new java.awt.Dimension(800, 500));
        jPanel1.setMinimumSize(new java.awt.Dimension(800, 500));
        jPanel1.setPreferredSize(new java.awt.Dimension(800, 500));

        temporadaLabel.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        temporadaLabel.setForeground(new java.awt.Color(51, 153, 0));
        temporadaLabel.setText("Temporada");

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

        listTemp.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                tempSelected(evt);
            }
        });

        gestioEq.setBackground(new java.awt.Color(0, 153, 51));
        gestioEq.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        gestioEq.setForeground(new java.awt.Color(255, 255, 255));
        gestioEq.setText("Gestió Equips");
        gestioEq.setAlignmentX(0.5F);
        gestioEq.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        gestioEq.setName("ge"); // NOI18N
        gestioEq.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelVisibility(evt);
            }
        });

        gestioJug.setBackground(new java.awt.Color(0, 153, 51));
        gestioJug.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        gestioJug.setForeground(new java.awt.Color(255, 255, 255));
        gestioJug.setText("Gestió Jugadors");
        gestioJug.setAlignmentX(0.5F);
        gestioJug.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        gestioJug.setName("gj"); // NOI18N
        gestioJug.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                panelVisibility(evt);
            }
        });

        llistatEqPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 0)), "LLISTAT D'EQUIPS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bauhaus 93", 0, 14), new java.awt.Color(0, 153, 0))); // NOI18N
        llistatEqPanel.setMaximumSize(new java.awt.Dimension(717, 283));
        llistatEqPanel.setMinimumSize(new java.awt.Dimension(717, 283));

        nomLabel.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        nomLabel.setText("Nom:");

        nom.setName("nom"); // NOI18N

        tipusLabel.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        tipusLabel.setText("Tipus:");

        categoria.setName("cat"); // NOI18N

        catLabel.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        catLabel.setText("Categories:");

        homeCheck.setText("Masculí");
        homeCheck.setName("H"); // NOI18N

        mixtCheck.setText("Mixt");
        mixtCheck.setName("M"); // NOI18N

        donaCheck.setText("Femení");
        donaCheck.setName("D"); // NOI18N

        filtrarEquip.setBackground(new java.awt.Color(0, 153, 51));
        filtrarEquip.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        filtrarEquip.setForeground(new java.awt.Color(255, 255, 255));
        filtrarEquip.setText("Filtrar");
        filtrarEquip.setToolTipText("");
        filtrarEquip.setAlignmentX(0.5F);
        filtrarEquip.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        filtrarEquip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                filtrarEquip(evt);
            }
        });

        tablaInfo.setAutoCreateColumnsFromModel(false);
        tablaInfo.setModel(new javax.swing.table.DefaultTableModel(
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
        tablaInfo.setName("tEquip"); // NOI18N
        jScrollPane2.setViewportView(tablaInfo);

        javax.swing.GroupLayout llistatEqPanelLayout = new javax.swing.GroupLayout(llistatEqPanel);
        llistatEqPanel.setLayout(llistatEqPanelLayout);
        llistatEqPanelLayout.setHorizontalGroup(
            llistatEqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(llistatEqPanelLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(llistatEqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2)
                    .addGroup(llistatEqPanelLayout.createSequentialGroup()
                        .addGroup(llistatEqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomLabel)
                            .addComponent(catLabel))
                        .addGap(18, 18, 18)
                        .addGroup(llistatEqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(69, 69, 69)
                        .addComponent(tipusLabel)
                        .addGap(26, 26, 26)
                        .addComponent(homeCheck)
                        .addGap(18, 18, 18)
                        .addComponent(donaCheck)
                        .addGap(18, 18, 18)
                        .addComponent(mixtCheck)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 103, Short.MAX_VALUE)
                        .addComponent(filtrarEquip, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        llistatEqPanelLayout.setVerticalGroup(
            llistatEqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(llistatEqPanelLayout.createSequentialGroup()
                .addGroup(llistatEqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomLabel)
                    .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipusLabel)
                    .addComponent(homeCheck)
                    .addComponent(mixtCheck)
                    .addComponent(donaCheck)
                    .addComponent(filtrarEquip, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(llistatEqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(catLabel)
                    .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        tempError.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N

        afegirTempPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 0)), "Afegir nova Temporada:", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bauhaus 93", 0, 12), new java.awt.Color(0, 153, 0))); // NOI18N
        afegirTempPanel.setMaximumSize(new java.awt.Dimension(455, 92));
        afegirTempPanel.setMinimumSize(new java.awt.Dimension(455, 92));

        novaTempLabel.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        novaTempLabel.setText("Nova Temporada:");

        tempInput.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                controlTemp(evt);
            }
        });

        insertTemp.setBackground(new java.awt.Color(0, 153, 51));
        insertTemp.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        insertTemp.setForeground(new java.awt.Color(255, 255, 255));
        insertTemp.setText("Insertar Temporada");
        insertTemp.setToolTipText("");
        insertTemp.setAlignmentX(0.5F);
        insertTemp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        insertTemp.setEnabled(false);
        insertTemp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                afegirTemporada(evt);
            }
        });

        infoInsertTemp.setFont(new java.awt.Font("Arial", 2, 10)); // NOI18N
        infoInsertTemp.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        infoInsertTemp.setText("<html>Inserta únicament l'any d'inici de la nova temporada.<br> No pot ser superior al any actual+1 ni posterior a 1980.</html>");
        infoInsertTemp.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout afegirTempPanelLayout = new javax.swing.GroupLayout(afegirTempPanel);
        afegirTempPanel.setLayout(afegirTempPanelLayout);
        afegirTempPanelLayout.setHorizontalGroup(
            afegirTempPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(afegirTempPanelLayout.createSequentialGroup()
                .addGroup(afegirTempPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(afegirTempPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addComponent(novaTempLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(tempInput, javax.swing.GroupLayout.PREFERRED_SIZE, 116, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(insertTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(afegirTempPanelLayout.createSequentialGroup()
                        .addGap(78, 78, 78)
                        .addComponent(infoInsertTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 288, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(50, Short.MAX_VALUE))
        );
        afegirTempPanelLayout.setVerticalGroup(
            afegirTempPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(afegirTempPanelLayout.createSequentialGroup()
                .addGroup(afegirTempPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(insertTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(afegirTempPanelLayout.createSequentialGroup()
                        .addGroup(afegirTempPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(novaTempLabel)
                            .addComponent(tempInput, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(1, 1, 1)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(infoInsertTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        altaEquip.setBackground(new java.awt.Color(0, 153, 51));
        altaEquip.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        altaEquip.setForeground(new java.awt.Color(255, 255, 255));
        altaEquip.setText("Alta Equip");
        altaEquip.setToolTipText("");
        altaEquip.setAlignmentX(0.5F);
        altaEquip.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        altaEquip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                altaEquip(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(llistatEqPanel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(gestioJug, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gestioEq, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105)
                        .addComponent(temporadaLabel)
                        .addGap(18, 18, 18)
                        .addComponent(listTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 190, Short.MAX_VALUE)
                        .addComponent(logOut, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(tempError, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(altaEquip, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(16, 16, 16))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(62, 62, 62)
                    .addComponent(afegirTempPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(283, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logOut, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(temporadaLabel)
                    .addComponent(listTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gestioEq, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(gestioJug, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(31, 31, 31)
                .addComponent(tempError, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(altaEquip, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(llistatEqPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(22, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(63, 63, 63)
                    .addComponent(afegirTempPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(345, Short.MAX_VALUE)))
        );

        gestioJug.getAccessibleContext().setAccessibleName("gj");
        gestioJug.getAccessibleContext().setAccessibleDescription("gj");

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
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Com Swing no permet fer modificacions a continuació fem un programa per a
     * definir l'estat de certs objectes
     * @param evt 
     */
    private void settingComponents(){
        llistatEqPanel.setVisible(false);

    }
    private void logOut(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOut
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_logOut
    //limitem els valors que es poden afegir com a new string a 4 i només numeros

    private void tempSelected(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_tempSelected
        if(listTemp.getSelectedIndex()!=-1){
            String aux=listTemp.getSelectedItem().toString();
            String tSel=Constants.gettSel().toString();
            if(!aux.equals(tSel)){
                /**
                 * si hi ha hagut un cambi de temporada fixem la nova
                 * temporada seleccionada...
                 */
                Constants.settSel(new Temporada(aux));
                /**
                 * ... i  calculem de nou els anys de naixement de les Categories
                 * en funció de la nova temporada seleccionada
                */
                Constants.setAnysCategsAmbTemp();
            }
        }
    }//GEN-LAST:event_tempSelected

    private void altaEquip(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_altaEquip

    }//GEN-LAST:event_altaEquip
    //únicament permitim números per afegir Temporades
    private void controlTemp(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_controlTemp
        int key=evt.getKeyChar();
        int len=tempInput.getText().length();
        boolean enabled=false;
        /**
         * com la key es detecta abans de que s'afegeixi al textfield controlem
         * la llaragaria real aqui
         * */
        if(key!=8 || (key>=48 && key<=57 && len!=4)){
            len++;
        }
        //unicament permitim borrar i afegir numeros
        if((key>=48 && key<=57 && len<=4) || key==8){
            tempInput.setEditable(true);
        }else{
            tempInput.setEditable(false);
        }
        /**
         * nomes activem el botó si el length del text es de 4 o mes
         * per temes del control ficat per preme les tecles
         */
        if(len>=4){
            enabled=true;
        }
        insertTemp.setEnabled(enabled);
    }//GEN-LAST:event_controlTemp

    private void afegirTemporada(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_afegirTemporada
        String inputTemp=tempInput.getText();
        String auxNewTemp=inputTemp+"/"+(Integer.parseInt(inputTemp)+1);
        Temporada aux= new Temporada();
        String err=aux.setAny_temp(auxNewTemp);
        if(err!=""){
            tempError.setForeground(Color.RED);
        }else{
            try {
                Constants.getgBD().afegirTemporada(aux);
                Constants.getgBD().confirmarCanvis();
                err="<html>Temporada afegida correctament!</html>";
                tempError.setForeground(Color.GREEN);
                Constants.setTemp(); //carregem de nou les temporades
                tempInput.setText("");
                loadTemporadas(); //tornem a carregar el combobox
            } catch (GestorBDEquipException ex) {
                err="<html>"+ex.getMessage()+"</html>";
            }
        }
        tempError.setText(err);
    }//GEN-LAST:event_afegirTemporada

    private void filtrarEquip(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_filtrarEquip
        String mode="";
        if(!gestioEq.isEnabled()){
            mode=gestioEq.getName();
        }else{
            mode=gestioJug.getName();
        }
        crearTableModel(mode);
    }//GEN-LAST:event_filtrarEquip

    //controlem la visibililtat dels panels de Jugadors i Equips
    private void panelVisibility(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelVisibility
        boolean show=true;
        JButton btn = (JButton)evt.getSource();
        String bPressed = btn.getName();
        if(bPressed!="ge"){
            show=false;
        }
        //si el panel d'equips es visible el de botó está desactivat
        gestioEq.setEnabled(!show);
        llistatEqPanel.setVisible(show);
        //el mateix per als equips pero fan servir l'inversa del show
        gestioJug.setEnabled(show);
        crearTableModel(bPressed);
    }//GEN-LAST:event_panelVisibility
    private Map<String, String> filtresAplicats(String btn){
        Map<String, String> auxFilt= new HashMap<>();
        switch(btn){
            case "ge": //en el cas del gestor d'equips
                //verifiquem si han afegit un nom
                String n=nom.getText();
                if(!n.isEmpty()){
                    auxFilt.put(nom.getName(), "'"+n+"'");
                }
                //si han seleccionat una categoria
                if(categoria.getSelectedIndex()>0){
                    int categ = Constants.idCategoria(categoria.getSelectedItem().toString());
                    auxFilt.put(categoria.getName(), String.valueOf(categ));
                }
                //iterem amb la funcio comboBoxTipus() per veure si han seleccionat un combobox o varis
                String tipusList=comboBoxTipus();
                if(tipusList.length()>0){
                    auxFilt.put("tipus", tipusList);
                }
                break;
            case "gj":
                break;
        }
        return auxFilt;
    }
    private String comboBoxTipus(){
        String sel="";
        for(JCheckBox c : geCB){
            if(c.isSelected()){
                if(sel.length()>0){
                    sel+=", ";
                }
                sel+="'"+c.getName()+"'";
            }
        }
        return sel;
    }
    //creem dinámicament la taula
    private void crearTableModel(String btn){
        String[] columns=headers.get(btn);
        Map<String, String> filters = new HashMap<>(filtresAplicats(btn));
        System.out.println(filters.get("tipus"));
        Object[][] data= new Object[][]{};
        if(btn=="ge"){
            try {
                List<Equip> info =Constants.getgBD().llistatEquips(Constants.gettSel(), filters);
            } catch (GestorBDEquipException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "InfoBox: " + "DB Connection Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            
        }
        //fiquem els titols
        tabModel=new DefaultTableModel(headers.get(btn),0);
        
        
        tablaInfo = new JTable(tabModel);
        ordenar = new TableRowSorter<>(tabModel);
        tablaInfo.setRowSorter(ordenar);
    }
   
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel afegirTempPanel;
    private javax.swing.JButton altaEquip;
    private javax.swing.JLabel catLabel;
    private javax.swing.JComboBox<String> categoria;
    private javax.swing.JCheckBox donaCheck;
    private javax.swing.JButton filtrarEquip;
    private javax.swing.JButton gestioEq;
    private javax.swing.JButton gestioJug;
    private javax.swing.JCheckBox homeCheck;
    private javax.swing.JLabel infoInsertTemp;
    private javax.swing.JButton insertTemp;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> listTemp;
    private javax.swing.JPanel llistatEqPanel;
    private javax.swing.JButton logOut;
    private javax.swing.JCheckBox mixtCheck;
    private javax.swing.JTextField nom;
    private javax.swing.JLabel nomLabel;
    private javax.swing.JLabel novaTempLabel;
    private javax.swing.JTable tablaInfo;
    private javax.swing.JLabel tempError;
    private javax.swing.JTextField tempInput;
    private javax.swing.JLabel temporadaLabel;
    private javax.swing.JLabel tipusLabel;
    // End of variables declaration//GEN-END:variables
}
