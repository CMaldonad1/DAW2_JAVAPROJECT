/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.milaifontanals.equip.vista;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.milaifontanals.equip.interficiepersistencia.GestorBDEquipException;

import org.milaifontanals.equip.model.*;

/**
 *
 * @author MAVI
 */
public class VistaEquip extends javax.swing.JFrame {
    private String[] tableTitles= new String[]{"ID","Nom","Cognom","Categoria","Sexe","Assignar","Titular","Altres Equips?"};
    private List<JRadioButton> rbList = new ArrayList<>();
    private Equip eqSel;
    private boolean existeix;
    private MainPage mp;
    
    public VistaEquip(int idEq, MainPage view) {
        //si s'ha pasat un id == -1 vol dir que esta creant un nou equip
        if(idEq==-1){
            existeix=false;
        }
        mp=view;
        //si no es un alta guardem recuperem la informació de l'equip
        if(existeix){
            try {
                eqSel=Constants.getgBD().infoEquip(idEq);
            } catch (GestorBDEquipException ex) {
                errGE.setText(ex.getMessage());
            }
        }
        initComponents();
        prepararFinestra();
        crearTableModel();
    }
    public void carregarLlistatRb(){
        rbList.add(rbFem);
        rbList.add(rbMasc);
        rbList.add(rbMixt);
    }
    public void activarBotonsiCerca(boolean activar){
        //únicament estarán abilitats aquest botons si l'equip es nou
        eliminarEquip.setVisible(activar);
        guardarCanvis.setEnabled(activar);
        filtrarJug.setEnabled(activar);
        idLegal.setEnabled(activar);
        nomJug.setEnabled(activar);
    }
    //carregem l'info al comboBox
    public void prepararFinestra(){
        String titol = "Alta Equip";
        activarBotonsiCerca(existeix);
        buttonGroup.add(rbMixt);
        buttonGroup.add(rbMasc);
        buttonGroup.add(rbFem);
        listTemp.addItem(Constants.gettSel().toString());
        listTemp.setEnabled(false);
        comboboxCategoria();//carregem el combobox de categoria
    }
    public void comboboxCategoria(){
        //si el combobox no esta carregat el carregem
        if(categoria.getItemCount()==0){
            List<Categoria> cat=Constants.getCategs();
            //carregem el combobox
            categoria.addItem("--Selecciona Categoria--");
            for(Categoria c: cat){
                categoria.addItem(c.getNom());
            }   
        }
        //si es una modificació d'un equip o l'equiup s'ha donat d'alta fixem la categoria
        if(existeix){
            categoria.getModel().setSelectedItem(Constants.nomCategoria(eqSel.getCat()));
        }
        //verifiquem si conté jugadors per deixar-li editar certs camps
        conteJugadors();
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
        temporadaLabel = new javax.swing.JLabel();
        logOut = new javax.swing.JButton();
        listTemp = new javax.swing.JComboBox<>();
        tornar = new javax.swing.JButton();
        infoEquip = new javax.swing.JPanel();
        nomLabel = new javax.swing.JLabel();
        nom = new javax.swing.JTextField();
        tipusLabel = new javax.swing.JLabel();
        categoria = new javax.swing.JComboBox<>();
        catLabel = new javax.swing.JLabel();
        guardarCanvis = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jugTable = new javax.swing.JTable();
        errGE = new javax.swing.JLabel();
        filtrarJug = new javax.swing.JButton();
        tempError = new javax.swing.JLabel();
        rbMasc = new javax.swing.JRadioButton();
        rbFem = new javax.swing.JRadioButton();
        rbMixt = new javax.swing.JRadioButton();
        idLegalLable = new javax.swing.JLabel();
        idLegal = new javax.swing.JTextField();
        nomJugLabel = new javax.swing.JLabel();
        nomJug = new javax.swing.JTextField();
        eliminarEquip = new javax.swing.JButton();
        title = new javax.swing.JLabel();

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

        listTemp.setMaximumSize(new java.awt.Dimension(72, 22));
        listTemp.setOpaque(true);

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

        infoEquip.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 0)), "INFORMACIO DE L'EQUIP", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bauhaus 93", 0, 14), new java.awt.Color(0, 153, 0))); // NOI18N
        infoEquip.setMaximumSize(new java.awt.Dimension(782, 297));
        infoEquip.setMinimumSize(new java.awt.Dimension(782, 297));

        nomLabel.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        nomLabel.setText("Nom:");

        nom.setName("nom"); // NOI18N

        tipusLabel.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        tipusLabel.setText("Tipus:");

        categoria.setName("cat"); // NOI18N

        catLabel.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        catLabel.setText("Categories:");

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

        jugTable.setModel(new javax.swing.table.DefaultTableModel(
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
        jugTable.setGridColor(new java.awt.Color(51, 153, 0));
        jugTable.setMaximumSize(new java.awt.Dimension(300, 80));
        jugTable.setMinimumSize(new java.awt.Dimension(300, 80));
        jugTable.setName("jugTable"); // NOI18N
        jugTable.setShowVerticalLines(true);
        jScrollPane1.setViewportView(jugTable);

        errGE.setForeground(new java.awt.Color(255, 0, 0));
        errGE.setName("errGE"); // NOI18N

        filtrarJug.setBackground(new java.awt.Color(0, 153, 51));
        filtrarJug.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        filtrarJug.setForeground(new java.awt.Color(255, 255, 255));
        filtrarJug.setText("Filtrar Jugador");
        filtrarJug.setToolTipText("");
        filtrarJug.setAlignmentX(0.5F);
        filtrarJug.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        filtrarJug.setName("filtrarJug"); // NOI18N
        filtrarJug.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                filtrarJug(evt);
            }
        });

        tempError.setFont(new java.awt.Font("Arial", 3, 14)); // NOI18N

        rbMasc.setText("Masculí");
        rbMasc.setName("H"); // NOI18N

        rbFem.setText("Femení");
        rbFem.setName("F"); // NOI18N

        rbMixt.setText("Mixt");
        rbMixt.setName("M"); // NOI18N

        idLegalLable.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        idLegalLable.setText("NIE/NIF:");

        idLegal.setName("nom"); // NOI18N

        nomJugLabel.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        nomJugLabel.setText("Nom:");

        nomJug.setName("nom"); // NOI18N

        javax.swing.GroupLayout infoEquipLayout = new javax.swing.GroupLayout(infoEquip);
        infoEquip.setLayout(infoEquipLayout);
        infoEquipLayout.setHorizontalGroup(
            infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoEquipLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoEquipLayout.createSequentialGroup()
                        .addComponent(tempError, javax.swing.GroupLayout.PREFERRED_SIZE, 588, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoEquipLayout.createSequentialGroup()
                        .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(infoEquipLayout.createSequentialGroup()
                                .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nomLabel)
                                    .addComponent(catLabel))
                                .addGap(18, 18, 18)
                                .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(69, 69, 69)
                                .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(infoEquipLayout.createSequentialGroup()
                                        .addComponent(tipusLabel)
                                        .addGap(18, 18, 18)
                                        .addComponent(rbMasc)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(rbFem)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(rbMixt)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                                        .addComponent(guardarCanvis, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(errGE, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, infoEquipLayout.createSequentialGroup()
                                .addComponent(idLegalLable)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(idLegal, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(60, 60, 60)
                                .addComponent(nomJugLabel)
                                .addGap(18, 18, 18)
                                .addComponent(nomJug, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(filtrarJug, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(23, 23, 23))))
        );
        infoEquipLayout.setVerticalGroup(
            infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoEquipLayout.createSequentialGroup()
                .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nomLabel)
                    .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tipusLabel)
                    .addComponent(guardarCanvis, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(rbMasc)
                    .addComponent(rbFem)
                    .addComponent(rbMixt))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(catLabel)
                        .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(errGE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 10, Short.MAX_VALUE)
                .addComponent(tempError, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(filtrarJug, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nomJug, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(nomJugLabel))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(idLegal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(idLegalLable)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 157, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(32, 32, 32))
        );

        filtrarJug.getAccessibleContext().setAccessibleName("desfiltrar");

        eliminarEquip.setBackground(new java.awt.Color(0, 153, 51));
        eliminarEquip.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        eliminarEquip.setForeground(new java.awt.Color(255, 255, 255));
        eliminarEquip.setText("Eliminar Equip");
        eliminarEquip.setToolTipText("");
        eliminarEquip.setAlignmentX(0.5F);
        eliminarEquip.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        eliminarEquip.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                eliminarEquip(evt);
            }
        });

        title.setFont(new java.awt.Font("Bauhaus 93", 0, 36)); // NOI18N
        title.setForeground(new java.awt.Color(0, 153, 0));
        title.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        title.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(tornar, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(224, 224, 224)
                        .addComponent(temporadaLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(listTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logOut, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(eliminarEquip, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(title, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(infoEquip, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(logOut, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(temporadaLabel)
                    .addComponent(listTemp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tornar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(eliminarEquip, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(title, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(infoEquip, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29))
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
    }// </editor-fold>//GEN-END:initComponents
    private void conteJugadors(){
        /**
         * si l'equip te jugador no deixarem que editi la categoria o el tipus
         * d'equip que es (sexe)
         */
        int conteJugadors=0;
        try {
            conteJugadors = Constants.getgBD().equipTeTitulars(eqSel);
        } catch (GestorBDEquipException ex) {
            errGE.setText(ex.getMessage());
        }
        boolean activar=false;
        if(conteJugadors==0){
            activar=true;
        }
        categoria.setEnabled(activar);
        for(JRadioButton rb:rbList){
            rb.setEnabled(activar);
        }
    }
    private void logOut(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logOut
        this.dispose();
        new Login().setVisible(true);
    }//GEN-LAST:event_logOut

    private void eliminarEquip(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_eliminarEquip
        boolean err=false;
        try {
            Constants.getgBD().eliminarEquip(eqSel.getId());
        } catch (GestorBDEquipException ex) {
            errGE.setText(ex.getMessage());
            err=true;
        }
        //si no hi ha error al eliminar demanem confirmació
        if(!err){
            confirmacióEliminar();
        }
    }//GEN-LAST:event_eliminarEquip

    private void filtrarJug(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_filtrarJug
        nom.setText("");
        categoria.setSelectedIndex(0);
        crearTableModel();
    }//GEN-LAST:event_filtrarJug

    private void guardarCanvis(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardarCanvis

    }//GEN-LAST:event_guardarCanvis

    //tornem al menú principal
    private void tornar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tornar
        this.dispose();
        mp.setVisible(true);
    }//GEN-LAST:event_tornar

    //programa per revisar els filtres tant per equips com per jugadors
    private Map<String, String> filtresAplicats(){
        Map<String, String> auxFilt= new HashMap<>();
        String n=nomJug.getText();
        if(!n.isEmpty()){
            auxFilt.put(nomJug.getName(), "'"+n+"'");
        }
        String idLeg=idLegal.getText();
        if(!n.isEmpty()){
            auxFilt.put(idLegal.getName(), "'"+idLeg+"'");
        }
        return auxFilt;
    }

    //creem dinámicament la taula
    private void crearTableModel(){
        int numColumns=tableTitles.length;
        Map<String, String> filters = new HashMap<>(filtresAplicats());
        DefaultTableModel mt= new DefaultTableModel(new Object[0][tableTitles.length],tableTitles);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        if(existeix){
            errGE.setText("");//ens asegurem que no queda cap error a la finestra
            jugTable.setModel(mt);//setejem columnes
            //amagem la columna ID que la farem servir per a eliminar/
            jugTable.removeColumn(jugTable.getColumnModel().getColumn(0));
            try {
                //Centrem les columnes necesaries (Categoria, tipus, te jugadors):
                for(int i=3; i<(numColumns);i++){
                    if(i<5 && i>6){
                        jugTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
                    }
                }
                List<Equip> infoEq =Constants.getgBD().llistatEquips(Constants.gettSel(), filters);
                for(Equip e : infoEq){
                    Object[] info= new Object[]{
                        "Seleccionar",
                        e.getId(),
                        e.getNom(),
                        Constants.nomCategoria(e.getCat()),
                        Constants.tipusNom(e.getTipus()),
                        (Constants.getgBD().equipTeTitulars(e)==0)?"No":"Si",
                        "Eliminar"
                        };
                    mt.addRow(info);
                }
                jugTable.setAutoCreateRowSorter(true);
            } catch (GestorBDEquipException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "InfoBox: " + "DB Connection Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    private void confirmacióEliminar(){
        int resposta=JOptionPane.showConfirmDialog(null, "Estas segur que vols eliminar l'equip "+eqSel.getNom()+"?", "Compte!",
			JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        String err="";
        if(resposta==0){
            try {
                Constants.getgBD().confirmarCanvis();
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
        errGE.setText(err);
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JLabel catLabel;
    private javax.swing.JComboBox<String> categoria;
    private javax.swing.JButton eliminarEquip;
    private javax.swing.JLabel errGE;
    private javax.swing.JButton filtrarJug;
    private javax.swing.JButton guardarCanvis;
    private javax.swing.JTextField idLegal;
    private javax.swing.JLabel idLegalLable;
    private javax.swing.JPanel infoEquip;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jugTable;
    private javax.swing.JComboBox<String> listTemp;
    private javax.swing.JButton logOut;
    private javax.swing.JTextField nom;
    private javax.swing.JTextField nomJug;
    private javax.swing.JLabel nomJugLabel;
    private javax.swing.JLabel nomLabel;
    private javax.swing.JRadioButton rbFem;
    private javax.swing.JRadioButton rbMasc;
    private javax.swing.JRadioButton rbMixt;
    private javax.swing.JLabel tempError;
    private javax.swing.JLabel temporadaLabel;
    private javax.swing.JLabel tipusLabel;
    private javax.swing.JLabel title;
    private javax.swing.JButton tornar;
    // End of variables declaration//GEN-END:variables
}
