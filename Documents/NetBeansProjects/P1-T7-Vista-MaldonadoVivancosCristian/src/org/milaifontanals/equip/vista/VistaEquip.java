/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.milaifontanals.equip.vista;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;
import org.milaifontanals.equip.interficiepersistencia.GestorBDEquipException;

import org.milaifontanals.equip.model.*;

/**
 *
 * @author MAVI
 */
public class VistaEquip extends javax.swing.JFrame {
    private String[] tableTitles= new String[]{"ID","Nom","Cognom","DNI/NIE","Categoria","Sexe","Assignar","Titular","Titular altres Equips?"};
    private List<JRadioButton> rbList = new ArrayList<>();
    private Equip eqSel;
    private Equip eqAux=new Equip(); //equip auxiliar per fer tracking dels canvis
    private List<Titular> titulars;
    private boolean existeix;
    private MainPage mp;
    
    public VistaEquip(int idEq, MainPage view) {
        //si s'ha pasat un id == -1 vol dir que esta creant un nou equip
        existeix=(idEq==-1)?false:true;
        mp=view;
        //si no es un alta guardem recuperem la informació de l'equip
        if(existeix){
            try {
                eqSel=Constants.getgBD().infoEquip(idEq);
                eqAux.setCat(eqSel.getCat());
                eqAux.setTipus(eqSel.getTipus());
                carregarTitularsEquip();
            } catch (GestorBDEquipException ex) {
                errGE.setText(ex.getMessage());
            }
        }else{
            eqSel=new Equip();
            eqSel.setTemp(Constants.gettSel());
        }
        initComponents();
        carregarLlistatRb();
        prepararFinestra();
        crearTableModel();
    }
    //aquet programa s'ha fet perque hem costa controlar la List de Titulars
    public void carregarTitularsEquip(){
        try {
            titulars=Constants.getgBD().llistatTitularsEquip(eqSel);
        } catch (GestorBDEquipException ex) {
            errGE.setText(ex.getMessage());
        }
    }
    public void carregarLlistatRb(){
        rbList.add(rbFem);
        rbList.add(rbMasc);
        rbList.add(rbMixt);
    }
    public void activarBotonsiCerca(){
        //únicament estarán abilitats aquest botons si l'equip es nou
        eliminarEquip.setVisible(existeix);
        guardarCanvis.setEnabled(existeix);
        filtrarJug.setEnabled(existeix);
        idLegal.setEnabled(existeix);
        nomJug.setEnabled(existeix);
        guardarTitulars.setEnabled(existeix);
    }
    //preparem l'informació de la finestra
    public void prepararFinestra(){
        actualitzarTitol();
        activarBotonsiCerca();
        buttonGroup.add(rbMixt);
        buttonGroup.add(rbMasc);
        buttonGroup.add(rbFem);
        listTemp.addItem(Constants.gettSel().toString());
        listTemp.setEnabled(false);
        comboboxCategoria();//carregem el combobox de categoria
    }
    public void actualitzarTitol(){
        //titol per default si es una alta nova
        String titol = "Alta Equip";
        if(existeix){
            String nomEq=eqSel.getNom();
            titol="Edició Equip - "+nomEq;
            nom.setText(nomEq);
        }
        titolLabel.setText(titol); //fiquel el titol
    }
    //carregem l'info al comboBox
    public void comboboxCategoria(){
        //si el combobox no esta carregat el carregem
        if(categoria.getItemCount()==0){
            List<Categoria> cat=Constants.getCategs();
            //carregem el combobox
            for(Categoria c: cat){
                categoria.addItem(c.getNom());
            }   
        }
        //si es una modificació d'un equip o l'equiup s'ha donat d'alta fixem la categoria
        if(existeix){
            categoria.setSelectedItem(Constants.nomCategoria(eqSel.getCat()));
            char tipus=eqSel.getTipus();
            //seleccionem la categoria
            int i=0;
            while(rbList.get(i).getName().charAt(0)!=tipus && i!=rbList.size()){
                i++;
            }
            rbList.get(i).setSelected(existeix);
            conteJugadors(); //verifiquem si conté jugadors per deixar-li editar certs camps
        }else{
            eqAux.setCat(Constants.idCategoria(categoria.getModel().getElementAt(0)));
            //per defecte masculi si es un equip nou
            rbMasc.setSelected(true);
            eqAux.setTipus(rbMasc.getName().charAt(0));
        }
        categoria.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                selCategoria(evt);
            }
        });
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
        rbMasc = new javax.swing.JRadioButton();
        rbFem = new javax.swing.JRadioButton();
        rbMixt = new javax.swing.JRadioButton();
        idLegalLable = new javax.swing.JLabel();
        idLegal = new javax.swing.JTextField();
        nomJugLabel = new javax.swing.JLabel();
        nomJug = new javax.swing.JTextField();
        guardarTitulars = new javax.swing.JButton();
        guardarTitulars1 = new javax.swing.JButton();
        eliminarEquip = new javax.swing.JButton();
        titolLabel = new javax.swing.JLabel();

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

        listTemp.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(51, 153, 0), 1, true));
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

        nom.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        nom.setName("nom"); // NOI18N
        nom.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                controlNom(evt);
            }
        });

        tipusLabel.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        tipusLabel.setText("Tipus:");

        categoria.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        categoria.setMaximumSize(new java.awt.Dimension(72, 22));
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

        rbMixt.setText("Mixt");
        rbMixt.setName("M"); // NOI18N
        rbMixt.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                seleccioItem(evt);
            }
        });

        idLegalLable.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        idLegalLable.setText("NIE/NIF:");

        idLegal.setName("ID_LEGAL"); // NOI18N

        nomJugLabel.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        nomJugLabel.setText("Nom:");

        nomJug.setName("NOM"); // NOI18N

        guardarTitulars.setBackground(new java.awt.Color(0, 153, 51));
        guardarTitulars.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        guardarTitulars.setForeground(new java.awt.Color(255, 255, 255));
        guardarTitulars.setText("Guardar Titulars");
        guardarTitulars.setToolTipText("");
        guardarTitulars.setAlignmentX(0.5F);
        guardarTitulars.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        guardarTitulars.setName("filtrarJug"); // NOI18N
        guardarTitulars.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                guardarTitulars(evt);
            }
        });

        guardarTitulars1.setBackground(new java.awt.Color(0, 153, 51));
        guardarTitulars1.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        guardarTitulars1.setForeground(new java.awt.Color(255, 255, 255));
        guardarTitulars1.setText("Treure tots de l'equip");
        guardarTitulars1.setToolTipText("");
        guardarTitulars1.setActionCommand("");
        guardarTitulars1.setAlignmentX(0.5F);
        guardarTitulars1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        guardarTitulars1.setName("filtrarJug"); // NOI18N
        guardarTitulars1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                desmarcarTitulars(evt);
            }
        });

        javax.swing.GroupLayout infoEquipLayout = new javax.swing.GroupLayout(infoEquip);
        infoEquip.setLayout(infoEquipLayout);
        infoEquipLayout.setHorizontalGroup(
            infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, infoEquipLayout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(infoEquipLayout.createSequentialGroup()
                        .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomLabel)
                            .addComponent(catLabel))
                        .addGap(18, 18, 18)
                        .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(infoEquipLayout.createSequentialGroup()
                                .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(69, 69, 69)
                                .addComponent(tipusLabel)
                                .addGap(18, 18, 18)
                                .addComponent(rbMasc)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(rbFem)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(rbMixt)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 112, Short.MAX_VALUE)
                                .addComponent(guardarCanvis, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(infoEquipLayout.createSequentialGroup()
                                .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(errGE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, infoEquipLayout.createSequentialGroup()
                        .addComponent(idLegalLable)
                        .addGap(18, 18, 18)
                        .addComponent(idLegal, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nomJugLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(nomJug, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(filtrarJug, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(guardarTitulars, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(infoEquipLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(guardarTitulars1, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(23, 23, 23))
        );
        infoEquipLayout.setVerticalGroup(
            infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(infoEquipLayout.createSequentialGroup()
                .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nom, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE)
                    .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(nomLabel)
                        .addComponent(tipusLabel)
                        .addComponent(guardarCanvis, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(rbMasc)
                        .addComponent(rbFem)
                        .addComponent(rbMixt)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(catLabel)
                        .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(errGE, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(infoEquipLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(idLegal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(idLegalLable)
                    .addComponent(nomJugLabel)
                    .addComponent(nomJug, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(filtrarJug, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(guardarTitulars, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 145, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(guardarTitulars1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(7, 7, 7))
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

        titolLabel.setFont(new java.awt.Font("Bauhaus 93", 0, 24)); // NOI18N
        titolLabel.setForeground(new java.awt.Color(0, 153, 0));
        titolLabel.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        titolLabel.setText("jLabel1");

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
                        .addComponent(titolLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 449, javax.swing.GroupLayout.PREFERRED_SIZE)
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
                    .addComponent(titolLabel, javax.swing.GroupLayout.DEFAULT_SIZE, 52, Short.MAX_VALUE))
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
        activarCatTipus(activar);
    }
    private void activarCatTipus(boolean activar){
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
        String err="";
        eqSel.setTipus(eqAux.getTipus());
        eqSel.setCat(eqAux.getCat());
        int equipNou=Optional.ofNullable(eqSel.getId()).orElse(0);
        try {
            if(equipNou == 0){
                Constants.getgBD().afegirEquip(eqSel);
            }else{
                Constants.getgBD().modificarEquip(eqSel);
            }
        } catch (GestorBDEquipException ex) {
            err=ex.getMessage();
        }
        confirmarCanvis(err);
        //cambiarem el titol únicament si es un equip nou i ara existeix
        if(equipNou==0){
            try {
                eqSel.setId(Constants.getgBD().idEquip(eqSel));
                existeix=true; //ara l'equip existeix
                prepararFinestra();
            } catch (GestorBDEquipException ex) {
                err=ex.getMessage();
            }
        }

        if(err.isBlank()){
            crearTableModel();
            guardarTitulars.setEnabled(true);
            jugTable.setEnabled(true);
        }
    }//GEN-LAST:event_guardarCanvis
    private void confirmarCanvis(String err){
        try {
            errGE.setText(err);
            if(err.isBlank()){
                Constants.getgBD().confirmarCanvis();
            }else{
                Constants.getgBD().desferCanvis();
            }
        } catch (GestorBDEquipException ex) {
            errGE.setText(ex.getMessage());
        }
    }
    public void deixemLaFinestra(){
        this.dispose();
        mp.setVisible(true);
    }
    //tornem al menú principal
    private void tornar(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tornar
        deixemLaFinestra();
    }//GEN-LAST:event_tornar
    private void activarGuardar(){
        boolean activarGuardar=false;
        if(eqSel.getNom()!=null && errGE.getText().isEmpty()){
            activarGuardar=true;
        }
        guardarCanvis.setEnabled(activarGuardar);
        Boolean enabled=true;
        if(eqSel.getTipus()!=eqAux.getTipus() || eqSel.getCat()!=eqAux.getCat()){
            enabled=false;
        }
        guardarTitulars.setEnabled(enabled);
        jugTable.setEnabled(enabled);
    }
    private void seleccioItem(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_seleccioItem
        String tipus=evt.getComponent().getName();
        eqAux.setTipus(tipus.charAt(0));
        activarGuardar();
    }//GEN-LAST:event_seleccioItem

    private void controlNom(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_controlNom
        String err=eqSel.setNom(nom.getText());
        errGE.setText(err);
        activarGuardar();
    }//GEN-LAST:event_controlNom
    private void selCategoria(java.awt.event.ItemEvent evt) {                              
       eqAux.setCat(Constants.idCategoria(categoria.getSelectedItem().toString()));
       activarGuardar();
    }                   //programa per guardar titulars
    private void guardarTitulars(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_guardarTitulars
        String err="";
        //iterem per la taula per veure l'estat dels jugadors
        for(int i=0;i<jugTable.getRowCount();i++){
            //recuperem l'ID del jugador
            int idJug=Integer.valueOf(((DefaultTableModel)jugTable.getModel()).getValueAt(i,0).toString());
            //mirarem l'estat en la taula, si s'ha seleccionat com que pertany o no a l'equip
            boolean pertany=(Boolean)((DefaultTableModel)jugTable.getModel()).getValueAt(i,6);
            //mirarem l'estat en la taula, si s'ha seleccionat a titular de l'equip.
            boolean esTitular=(Boolean)((DefaultTableModel)jugTable.getModel()).getValueAt(i,7);
            Titular titAux=new Titular(eqSel.getId(),idJug, esTitular); //titular aux per facilitar el treball de INSERT, UPDATE, DELETE
            int j=0;
            boolean trobat=false;
            try {
                //verifiquem si ja pertenyia al equip per ajudar a decidir si fem un Insert, Update or Delete
                while(!trobat && titulars.size()!=j){
                    if(titulars.get(j).getJugador()==idJug){
                        trobat=true;
                    }
                    j++;
                }
                /**
                * si era de l'equip farem que es fagi update o delte segons 
                * si segueix marcat que partanya l'equip, si no ho era farem insert
                */
                if(!trobat && pertany){
                    Constants.getgBD().afegirTitular(titAux);
                }else if(trobat){
                    if(pertany){
                        Constants.getgBD().modificarTitular(titAux);
                    }else{
                        Constants.getgBD().eliminarTitular(titAux);
                    }
                }
            } catch (GestorBDEquipException ex) {
                err=ex.getMessage();
            }
        }
        errGE.setText(err);
        //confirmem canvis
        confirmarCanvis(err);
        //mirarem si continua amb titulars
        verificarCanvisTituars(jugTable);
        carregarTitularsEquip();//carregem novament la variable global de titulars
    }//GEN-LAST:event_guardarTitulars

    private void desmarcarTitulars(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_desmarcarTitulars
        //iterem per la taula per treure o afegir tots com titulars
        Boolean activar=false;
        JButton boto=(JButton)evt.getSource();
        String oldtextButton=boto.getText();
        String newtextButton="Fer tots de l'equip";
        if(oldtextButton.equals(newtextButton)){
            newtextButton="Treure tots de l'equip";
            activar=true;
        }
        for(int i=0;i<jugTable.getRowCount();i++){
            ((DefaultTableModel)jugTable.getModel()).setValueAt(activar, i, 6);
            if(!activar){
                ((DefaultTableModel)jugTable.getModel()).setValueAt(false, i, 7);
            }
        }
        boto.setText(newtextButton);
    }//GEN-LAST:event_desmarcarTitulars

    //programa per revisar els filtres per buscar jugadors
    private Map<String, String> filtresAplicats(){
        Map<String, String> auxFilt= new HashMap<>();
        String n=nomJug.getText();
        if(!n.isEmpty()){
            auxFilt.put(nomJug.getName(), "'%"+n.toUpperCase()+"%'");
        }
        String idLeg=idLegal.getText();
        if(!idLeg.isEmpty()){
            auxFilt.put(idLegal.getName(), "'%"+idLeg.toUpperCase()+"%'");
        }
        return auxFilt;
    }

    //creem dinámicament la taula
    private void crearTableModel(){
        int numColumns=tableTitles.length;
        DefaultTableModel mt= new DefaultTableModel(new Object[0][tableTitles.length],tableTitles);
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        Map<String, String> filtres= filtresAplicats();
        errGE.setText("");//ens asegurem que no queda cap error a la finestra
        jugTable.setModel(mt);//setejem columnes
        if(existeix){
            //amagem la columna ID que la farem servir per a eliminar/
            jugTable.removeColumn(jugTable.getColumnModel().getColumn(0));
            try {
                //Centrem les columnes necesaries (Categoria, tipus, te jugadors):
                for(int i=3; i<(numColumns-1);i++){
                    jugTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
                }
                //carregemm els titulars
                List<Jugador> infoTitulars =Constants.getgBD().llistatJugadorsTitulars(eqSel, filtres);
                for(Jugador j : infoTitulars){
                    int id=j.getId();
                    Object[] info= new Object[]{
                        id,
                        j.getNom(),
                        j.getCognom(),
                        j.getId_legal(),
                        Constants.jugCategoria(j.getData_naix()),
                        Constants.tipusNom(j.getSexe()),
                        true,//automaticament true perque son el llistat de titulars
                        esTitularEquip(id),
                        (Constants.getgBD().jugadorParticipaEnAltresEquips(eqSel.getId(), id))?"Si":"No"
                        };
                    mt.addRow(info);
                }
                //carregem els possibles jugadors
                List<Jugador> posiblesJugadors =Constants.getgBD().llistatPossiblesJugadors(Constants.objecteCategoria(eqSel.getCat()), eqSel, filtres);
                for(Jugador j : posiblesJugadors){
                    int id=j.getId();
                    Object[] info= new Object[]{
                        id,
                        j.getNom(),
                        j.getCognom(),
                        j.getId_legal(),
                        Constants.jugCategoria(j.getData_naix()),
                        Constants.tipusNom(j.getSexe()),
                        false,//automaticament false perque NO son el llistat de titulars
                        false,//com son possibles jugadors per defecte no poden ser titulars
                        (Constants.getgBD().jugadorParticipaEnAltresEquips(eqSel.getId(), id))?"Si":"No"
                        };
                    mt.addRow(info);
                }
                checkboxColumn();
                TableCellListener tcl = new TableCellListener(jugTable, controlCheckbox);
            } catch (GestorBDEquipException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "InfoBox: " + "DB Connection Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    private boolean esTitularEquip(int idJug){
        boolean hoEs=false;
        int i=0;
        do{
            if(titulars.get(i).getJugador()==idJug){
                hoEs=titulars.get(i).isTitular();
            }
            i++;
        }while(titulars.size()!=i && !hoEs);
        return hoEs;
    }
    //definim les columnes que serán checkbox
    private void checkboxColumn(){
        jugTable.setAutoCreateRowSorter(true);
        for(int i=5;i<7;i++){
            TableColumn tc=jugTable.getColumnModel().getColumn(i);
            tc.setCellEditor(jugTable.getDefaultEditor(Boolean.class));
            tc.setCellRenderer(jugTable.getDefaultRenderer(Boolean.class));
        }
    }
    private void confirmacióEliminar(){
        int resposta=JOptionPane.showConfirmDialog(null, "Estas segur que vols eliminar l'equip "+eqSel.getNom()+"?", "Compte!",
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
        errGE.setText(err);
    }
    //source: https://tips4java.wordpress.com/2009/06/07/table-cell-listener/
    Action controlCheckbox= new AbstractAction(){
        public void actionPerformed(ActionEvent e){
            //per veure qui ha fet el canvi
            TableCellListener tcl = (TableCellListener)e.getSource();
            //column que l'ha cridat
            int col=tcl.getColumn();
            //només importa les columnes del checkbox, sino no farem res.
            if(col>=6 && col<=7){
                int col_a_modificar=(col==6)?7:6; //depenent de la columna modificarem l'altre
                //row que l'ha cridat i mirarem en quin estat es troba.
                int row=tcl.getRow();
                boolean val=(Boolean)tcl.getNewValue();
                //retornem la taula per a poder editar-la
                JTable table = tcl.getTable(); 
                //si marca que no pertany a l'equip es desactiva l'opció de titular
                if(col==6 && !val){
                     ((DefaultTableModel)table.getModel()).setValueAt(val, row, col_a_modificar);
                }
                //si marca que es titular i está desmarcat com que pertany a l'equip, el fa de l'equip
                if(col==7 && val){
                    Boolean esTitular= (Boolean)((DefaultTableModel)table.getModel()).getValueAt(row, col_a_modificar);
                    if(!esTitular){
                        ((DefaultTableModel)table.getModel()).setValueAt(val, row, col_a_modificar);
                    }
                }
            }
        }
        /**
         * verifiquem si ha fet a algun jugador de l'equip per desactivar l'opció de cambiar la
         * categoria i el tipus d'equip per a no generar inconsistencies.
         */

    };
    public void verificarCanvisTituars(JTable tab){
        boolean activar=false;
        int i=0;
        //verifiquem que al menys un jugador está com a titular
        do{
            activar=(Boolean)tab.getValueAt(i, 5);
            i++;
        }while(!activar && i!=tab.getRowCount());
        //si hi ha un titular desactivem els botons de canvi de Cat i Tipus
        activarCatTipus(!activar);
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup;
    private javax.swing.JLabel catLabel;
    private javax.swing.JComboBox<String> categoria;
    private javax.swing.JButton eliminarEquip;
    private javax.swing.JLabel errGE;
    private javax.swing.JButton filtrarJug;
    private javax.swing.JButton guardarCanvis;
    private javax.swing.JButton guardarTitulars;
    private javax.swing.JButton guardarTitulars1;
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
    private javax.swing.JLabel temporadaLabel;
    private javax.swing.JLabel tipusLabel;
    private javax.swing.JLabel titolLabel;
    private javax.swing.JButton tornar;
    // End of variables declaration//GEN-END:variables
}
