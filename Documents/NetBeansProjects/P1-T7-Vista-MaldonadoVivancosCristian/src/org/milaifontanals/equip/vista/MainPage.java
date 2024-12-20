/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package org.milaifontanals.equip.vista;

import java.awt.Color;
import java.awt.Desktop;
import java.awt.event.ActionEvent;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static java.util.Map.entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import org.milaifontanals.equip.interficiepersistencia.GestorBDEquipException;
import org.milaifontanals.equip.model.*;
import java.io.FileWriter;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.Base64;
import java.util.Properties;
import javax.swing.table.TableColumn;
import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamWriter;
/**
 *
 * @author MAVI
 */
public class MainPage extends javax.swing.JFrame {
    private Map <String, String[]> headers= Map.ofEntries(
            entry("ge",new String[]{"","ID","Nom","Categoria","Tipus","Té Jugadors","*JasperReport",""}),
            entry("gj",new String[]{"","ID","Nom","Cognom","NIE/NIF","Categoria","Sexe","Data Naixement",""}));
    //list dels JCheckbox per a poder treballar-los millor
    private List<JCheckBox> geCB = new ArrayList<>();
    private List<JCheckBox> gjCB = new ArrayList<>();
    private String[] botonsAlta = new String[]{"Alta Equip","Alta Jugador"};
    private String majorOmenor; //per a la vista equip saber com filtra l'edat de Naixement
    private String pathToSave=System.getProperty("user.home")+"\\Desktop";
    // Variables per dades de connexió amb JRS
    private String urlJRS;
    private String userJRS;
    private String passwordJRS;
    
    public MainPage() {
        initComponents();
        settingComponents();
        carregarDadesJasperReport();
        loadTemporadas();
        loadCategories();
        gestorEqListCheckBox();
        gestorJugListCheckBox();
        carregarRBGroup();
        //FONT: https://stackoverflow.com/questions/22713093/how-to-listen-for-visible-property-of-window-in-swing-awt
        this.addComponentListener(new ComponentAdapter(){
            @Override
            public void componentShown(ComponentEvent e) {
                String tableToUpdate="gj";
                if(!gestioEq.isEnabled()){
                    tableToUpdate="ge";
                };
                crearTableModel(tableToUpdate);
            }
        });
    }
    //carregem l'informació per a poder fer la conexió a JasperReport
    private void carregarDadesJasperReport(){
            String fitxerConfigJRS = "informesJRS.xml";
            try {
                Properties props = new Properties();
                props.loadFromXML(new FileInputStream(fitxerConfigJRS));
                String[] claus = {"url", "user", "password"};
                String[] valors = new String[3];
                for (int i = 0; i < claus.length; i++) {
                    valors[i] = props.getProperty(claus[i]);
                    if (valors[i] == null || valors[i].isEmpty()) {
                        errGE.setText(errGE.getText() + "\nNo es troba clau " + valors[i] + " en fitxer " + fitxerConfigJRS);
                    }
                }
                urlJRS = valors[0];
                userJRS = valors[1];
                passwordJRS = valors[2];
            } catch (FileNotFoundException ex) {
                errGE.setText(errGE.getText() + "\nNo es troba fitxer " + fitxerConfigJRS + " - No es podrà executar cap informe");
            } catch (IOException ex) {
                errGE.setText(errGE.getText() + "\n"+ " - Probablement no es podrà executar cap informe");
            }
    }
    //fem un llistat dels comboboxs relacionats amb gestio d'equips per iterar-los
    public void gestorEqListCheckBox(){
        geCB.add(homeCheck);
        geCB.add(donaCheck);
        geCB.add(mixtCheck);
    }
    //fem un llistat dels comboboxs relacionats amb gestio de Jugadors per iterar-los
    public void gestorJugListCheckBox(){
        gjCB.add(benjCheck);
        gjCB.add(aleviCheck);
        gjCB.add(infCheck);
        gjCB.add(cadetCheck);
        gjCB.add(juvCheck);
        gjCB.add(seniorCheck);
    }
    public void carregarRBGroup(){
        //valor per defecte
        majorOmenor=majorIg.getName();
        datNaixGr.add(majorIg);
        datNaixGr.add(inferIg);
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

        datNaixGr = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        temporadaLabel = new javax.swing.JLabel();
        logOut = new javax.swing.JButton();
        listTemp = new javax.swing.JComboBox<>();
        gestioEq = new javax.swing.JButton();
        gestioJug = new javax.swing.JButton();
        tempError = new javax.swing.JLabel();
        afegirTempPanel = new javax.swing.JPanel();
        novaTempLabel = new javax.swing.JLabel();
        tempInput = new javax.swing.JTextField();
        insertTemp = new javax.swing.JButton();
        infoInsertTemp = new javax.swing.JLabel();
        botoAltas = new javax.swing.JButton();
        layeredPanel = new javax.swing.JLayeredPane();
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
        jScrollPane1 = new javax.swing.JScrollPane();
        geTable = new javax.swing.JTable();
        errGE = new javax.swing.JLabel();
        treureFiltres = new javax.swing.JButton();
        jasperReport = new javax.swing.JButton();
        infoInsertTemp1 = new javax.swing.JLabel();
        llistatJugPanel = new javax.swing.JPanel();
        nomJugLabel = new javax.swing.JLabel();
        idLegal = new javax.swing.JTextField();
        jugCatLabel = new javax.swing.JLabel();
        nlegalLabel = new javax.swing.JLabel();
        benjCheck = new javax.swing.JCheckBox();
        infCheck = new javax.swing.JCheckBox();
        aleviCheck = new javax.swing.JCheckBox();
        filtrarJug = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jugTable = new javax.swing.JTable();
        errJUG = new javax.swing.JLabel();
        treureJugFiltres = new javax.swing.JButton();
        nomJug = new javax.swing.JTextField();
        cadetCheck = new javax.swing.JCheckBox();
        juvCheck = new javax.swing.JCheckBox();
        seniorCheck = new javax.swing.JCheckBox();
        dNaixLabel = new javax.swing.JLabel();
        majorIg = new javax.swing.JRadioButton();
        inferIg = new javax.swing.JRadioButton();
        csv = new javax.swing.JButton();
        xml = new javax.swing.JButton();
        datNaix = new com.toedter.calendar.JDateChooser();

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

        listTemp.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(0, 153, 0), 1, true));
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

        botoAltas.setBackground(new java.awt.Color(0, 153, 51));
        botoAltas.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        botoAltas.setForeground(new java.awt.Color(255, 255, 255));
        botoAltas.setText("Alta Equip");
        botoAltas.setToolTipText("");
        botoAltas.setAlignmentX(0.5F);
        botoAltas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        botoAltas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ferAltas(evt);
            }
        });

        llistatEqPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 0)), "LLISTAT D'EQUIPS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bauhaus 93", 0, 14), new java.awt.Color(0, 153, 0))); // NOI18N
        llistatEqPanel.setMaximumSize(new java.awt.Dimension(782, 300));
        llistatEqPanel.setMinimumSize(new java.awt.Dimension(782, 300));
        llistatEqPanel.setPreferredSize(new java.awt.Dimension(782, 300));

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

        geTable.setModel(new javax.swing.table.DefaultTableModel(
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
        geTable.setGridColor(new java.awt.Color(51, 153, 0));
        geTable.setMaximumSize(new java.awt.Dimension(300, 80));
        geTable.setMinimumSize(new java.awt.Dimension(300, 80));
        geTable.setName("geTable"); // NOI18N
        geTable.setShowVerticalLines(true);
        jScrollPane1.setViewportView(geTable);

        errGE.setForeground(new java.awt.Color(255, 0, 0));
        errGE.setName("errGE"); // NOI18N

        treureFiltres.setBackground(new java.awt.Color(0, 153, 51));
        treureFiltres.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        treureFiltres.setForeground(new java.awt.Color(255, 255, 255));
        treureFiltres.setText("Treure Filtres");
        treureFiltres.setToolTipText("");
        treureFiltres.setAlignmentX(0.5F);
        treureFiltres.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        treureFiltres.setName("treureFiltres"); // NOI18N
        treureFiltres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                treureFiltres(evt);
            }
        });

        jasperReport.setBackground(new java.awt.Color(0, 153, 51));
        jasperReport.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        jasperReport.setForeground(new java.awt.Color(255, 255, 255));
        jasperReport.setText("Jasper Report");
        jasperReport.setToolTipText("");
        jasperReport.setAlignmentX(0.5F);
        jasperReport.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jasperReport.setName("treureFiltres"); // NOI18N
        jasperReport.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jasperReport(evt);
            }
        });

        infoInsertTemp1.setFont(new java.awt.Font("Arial", 2, 10)); // NOI18N
        infoInsertTemp1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        infoInsertTemp1.setText("<html>*JASPER_REPORT: el botó generará el report segons la temporada on es treballa, la categoria seleccionada i/o l'equip escollit<html>");
        infoInsertTemp1.setVerticalTextPosition(javax.swing.SwingConstants.TOP);

        javax.swing.GroupLayout llistatEqPanelLayout = new javax.swing.GroupLayout(llistatEqPanel);
        llistatEqPanel.setLayout(llistatEqPanelLayout);
        llistatEqPanelLayout.setHorizontalGroup(
            llistatEqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(llistatEqPanelLayout.createSequentialGroup()
                .addGroup(llistatEqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, llistatEqPanelLayout.createSequentialGroup()
                        .addContainerGap(14, Short.MAX_VALUE)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 752, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(llistatEqPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(llistatEqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(llistatEqPanelLayout.createSequentialGroup()
                                .addGroup(llistatEqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(nomLabel)
                                    .addComponent(catLabel))
                                .addGap(18, 18, 18)
                                .addGroup(llistatEqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(nom, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(69, 69, 69)
                                .addGroup(llistatEqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(llistatEqPanelLayout.createSequentialGroup()
                                        .addComponent(tipusLabel)
                                        .addGap(26, 26, 26)
                                        .addComponent(homeCheck)
                                        .addGap(18, 18, 18)
                                        .addComponent(donaCheck)
                                        .addGap(18, 18, 18)
                                        .addComponent(mixtCheck))
                                    .addComponent(errGE, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(infoInsertTemp1, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(llistatEqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(filtrarEquip, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(treureFiltres, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jasperReport, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
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
                .addGroup(llistatEqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(llistatEqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(llistatEqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(catLabel)
                            .addComponent(categoria, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(errGE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(treureFiltres, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(llistatEqPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jasperReport, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(infoInsertTemp1, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(15, 15, 15))
        );

        treureFiltres.getAccessibleContext().setAccessibleName("desfiltrar");

        llistatJugPanel.setBorder(javax.swing.BorderFactory.createTitledBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(51, 153, 0)), "LLISTAT DE JUGADORS", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Bauhaus 93", 0, 14), new java.awt.Color(0, 153, 0))); // NOI18N
        llistatJugPanel.setMaximumSize(new java.awt.Dimension(782, 300));
        llistatJugPanel.setMinimumSize(new java.awt.Dimension(782, 300));
        llistatJugPanel.setPreferredSize(new java.awt.Dimension(782, 300));

        nomJugLabel.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        nomJugLabel.setText("Nom:");

        idLegal.setName("ID_LEGAL"); // NOI18N

        jugCatLabel.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        jugCatLabel.setText("Categoria:");

        nlegalLabel.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        nlegalLabel.setText("NIF/NIE:");

        benjCheck.setText("Benjamí");
        benjCheck.setName("H"); // NOI18N

        infCheck.setText("Infantil");
        infCheck.setName("M"); // NOI18N

        aleviCheck.setText("Aleví");
        aleviCheck.setName("D"); // NOI18N

        filtrarJug.setBackground(new java.awt.Color(0, 153, 51));
        filtrarJug.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        filtrarJug.setForeground(new java.awt.Color(255, 255, 255));
        filtrarJug.setText("Filtrar");
        filtrarJug.setToolTipText("");
        filtrarJug.setAlignmentX(0.5F);
        filtrarJug.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        filtrarJug.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                filtrarJug(evt);
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
        jugTable.setName("juTable"); // NOI18N
        jugTable.setShowVerticalLines(true);
        jScrollPane2.setViewportView(jugTable);

        errJUG.setForeground(new java.awt.Color(255, 0, 0));
        errJUG.setName("errGE"); // NOI18N
        errJUG.setOpaque(true);

        treureJugFiltres.setBackground(new java.awt.Color(0, 153, 51));
        treureJugFiltres.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        treureJugFiltres.setForeground(new java.awt.Color(255, 255, 255));
        treureJugFiltres.setText("Treure Filtres");
        treureJugFiltres.setToolTipText("");
        treureJugFiltres.setAlignmentX(0.5F);
        treureJugFiltres.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        treureJugFiltres.setName("treureFiltres"); // NOI18N
        treureJugFiltres.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                treureJugFiltres(evt);
            }
        });

        nomJug.setName("nom"); // NOI18N

        cadetCheck.setText("Cadet");
        cadetCheck.setName("H"); // NOI18N

        juvCheck.setText("Juvenil");
        juvCheck.setName("D"); // NOI18N

        seniorCheck.setText("Senior");
        seniorCheck.setName("M"); // NOI18N

        dNaixLabel.setFont(new java.awt.Font("Bauhaus 93", 0, 14)); // NOI18N
        dNaixLabel.setText("D.Naix:");

        majorIg.setSelected(true);
        majorIg.setText("Superior/Igual a..");
        majorIg.setName(">="); // NOI18N
        majorIg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                superiorInferior(evt);
            }
        });

        inferIg.setText("Inferior/Igual a..");
        inferIg.setName("<="); // NOI18N
        inferIg.setOpaque(true);
        inferIg.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                superiorInferior(evt);
            }
        });

        csv.setBackground(new java.awt.Color(0, 153, 51));
        csv.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        csv.setForeground(new java.awt.Color(255, 255, 255));
        csv.setText("CSV");
        csv.setToolTipText("");
        csv.setAlignmentX(0.5F);
        csv.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        csv.setName("treureFiltres"); // NOI18N
        csv.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                csv(evt);
            }
        });

        xml.setBackground(new java.awt.Color(0, 153, 51));
        xml.setFont(new java.awt.Font("Bauhaus 93", 0, 12)); // NOI18N
        xml.setForeground(new java.awt.Color(255, 255, 255));
        xml.setText("XML");
        xml.setToolTipText("");
        xml.setAlignmentX(0.5F);
        xml.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        xml.setName("treureFiltres"); // NOI18N
        xml.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                xml(evt);
            }
        });

        datNaix.setName("data_naix"); // NOI18N

        javax.swing.GroupLayout llistatJugPanelLayout = new javax.swing.GroupLayout(llistatJugPanel);
        llistatJugPanel.setLayout(llistatJugPanelLayout);
        llistatJugPanelLayout.setHorizontalGroup(
            llistatJugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, llistatJugPanelLayout.createSequentialGroup()
                .addGroup(llistatJugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(llistatJugPanelLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 760, Short.MAX_VALUE))
                    .addGroup(llistatJugPanelLayout.createSequentialGroup()
                        .addGap(23, 23, 23)
                        .addGroup(llistatJugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomJugLabel)
                            .addComponent(nlegalLabel)
                            .addComponent(dNaixLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 75, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(llistatJugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nomJug, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(idLegal, javax.swing.GroupLayout.PREFERRED_SIZE, 143, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(llistatJugPanelLayout.createSequentialGroup()
                                .addGroup(llistatJugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(majorIg, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(inferIg, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(datNaix, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGroup(llistatJugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(llistatJugPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 34, Short.MAX_VALUE)
                                .addComponent(errJUG, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE))
                            .addGroup(llistatJugPanelLayout.createSequentialGroup()
                                .addGap(12, 12, 12)
                                .addComponent(jugCatLabel)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                                .addGroup(llistatJugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(cadetCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(benjCheck))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(llistatJugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(aleviCheck, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(juvCheck))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(llistatJugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(infCheck)
                                    .addComponent(seniorCheck))))
                        .addGap(18, 18, 18)
                        .addGroup(llistatJugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, llistatJugPanelLayout.createSequentialGroup()
                                .addComponent(xml, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(csv, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(filtrarJug, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(treureJugFiltres, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );
        llistatJugPanelLayout.setVerticalGroup(
            llistatJugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(llistatJugPanelLayout.createSequentialGroup()
                .addGroup(llistatJugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(llistatJugPanelLayout.createSequentialGroup()
                        .addGroup(llistatJugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nomJugLabel)
                            .addComponent(filtrarJug, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(nomJug, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(llistatJugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(treureJugFiltres, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(llistatJugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(idLegal, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(nlegalLabel))))
                    .addComponent(jugCatLabel)
                    .addGroup(llistatJugPanelLayout.createSequentialGroup()
                        .addGroup(llistatJugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(benjCheck)
                            .addComponent(infCheck)
                            .addComponent(aleviCheck))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(llistatJugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(llistatJugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(seniorCheck)
                                .addComponent(juvCheck))
                            .addComponent(cadetCheck))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(llistatJugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(llistatJugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(errJUG, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(llistatJugPanelLayout.createSequentialGroup()
                            .addComponent(datNaix, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(10, 10, 10))
                        .addGroup(llistatJugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(xml, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(csv, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(inferIg))
                    .addGroup(llistatJugPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(majorIg, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dNaixLabel, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(8, 8, 8)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        layeredPanel.setLayer(llistatEqPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);
        layeredPanel.setLayer(llistatJugPanel, javax.swing.JLayeredPane.DEFAULT_LAYER);

        javax.swing.GroupLayout layeredPanelLayout = new javax.swing.GroupLayout(layeredPanel);
        layeredPanel.setLayout(layeredPanelLayout);
        layeredPanelLayout.setHorizontalGroup(
            layeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(llistatEqPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGroup(layeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layeredPanelLayout.createSequentialGroup()
                    .addComponent(llistatJugPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap()))
        );
        layeredPanelLayout.setVerticalGroup(
            layeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layeredPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(llistatEqPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
            .addGroup(layeredPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layeredPanelLayout.createSequentialGroup()
                    .addContainerGap()
                    .addComponent(llistatJugPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 290, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(9, Short.MAX_VALUE)))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(layeredPanel, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(12, Short.MAX_VALUE))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(botoAltas, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(gestioJug, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(gestioEq, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(105, 105, 105)
                        .addComponent(temporadaLabel)
                        .addGap(18, 18, 18)
                        .addComponent(listTemp, javax.swing.GroupLayout.PREFERRED_SIZE, 112, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(logOut, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(tempError, javax.swing.GroupLayout.PREFERRED_SIZE, 238, javax.swing.GroupLayout.PREFERRED_SIZE)))
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
                .addGap(20, 20, 20)
                .addComponent(botoAltas, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(layeredPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(3, Short.MAX_VALUE))
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
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    /**
     * Com Swing no permet fer modificacions a continuació fem un programa per a
     * definir l'estat de certs objectes
     * @param evt 
     */
    private void settingComponents(){
        llistatEqPanel.setVisible(false);
        llistatJugPanel.setVisible(false);
        botoAltas.setVisible(false);
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
                int vista=(llistatEqPanel.isVisible()?1:0);
                String vistaNom="";
                //1 gestor Equip, 2 gestor Jugador, 0 cap gestor
                if(vista==1){
                    vistaNom="ge";
                }else if(vista==2){
                    vistaNom="gj";
                }
                if(vistaNom.length()!=0){
                    crearTableModel(vistaNom);
                }
            }
        }
    }//GEN-LAST:event_tempSelected

    private void ferAltas(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ferAltas
        if(botoAltas.getText().contains(botonsAlta[0])){
            this.setVisible(false);
            carregarVistaEquip(-1);
        }else{
            this.setVisible(false);
            carregarVistaJugador(-1);
        }
    }//GEN-LAST:event_ferAltas
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
        crearTableModel(modeActual());
    }//GEN-LAST:event_filtrarEquip

    //controlem la visibililtat dels panels de Jugadors i Equips
    private void panelVisibility(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_panelVisibility
        boolean show=true;
        JButton btn = (JButton)evt.getSource();
        String bPressed = btn.getName();
        int i=0;
        String auxNomBoto=botonsAlta[0];
        if(bPressed!="ge"){
            show=false;
            auxNomBoto=botonsAlta[1];
        }
        //si el panel d'equips es visible el de botó está desactivat
        gestioEq.setEnabled(!show);
        llistatEqPanel.setVisible(show);
        botoAltas.setText(auxNomBoto);
        botoAltas.setVisible(true);
        //el mateix per als equips pero fan servir l'inversa del show
        gestioJug.setEnabled(show);
        llistatJugPanel.setVisible(!show);
        crearTableModel(bPressed);
    }//GEN-LAST:event_panelVisibility

    //programa per a sapiguern en quin mode estem
    private String modeActual(){
        String mode="";
        if(!gestioEq.isEnabled()){
            mode=gestioEq.getName();
        }else{
            mode=gestioJug.getName();
        }
        return mode;
    }
    private void treureFiltres(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treureFiltres
        nom.setText("");
        desmarcarCB(geCB);
        categoria.setSelectedIndex(0);
        crearTableModel(modeActual());
    }//GEN-LAST:event_treureFiltres

    private void treureJugFiltres(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_treureJugFiltres
        nomJug.setText("");
        idLegal.setText("");
        desmarcarCB(gjCB);
        datNaix.setCalendar(null);
        crearTableModel(modeActual());
    }//GEN-LAST:event_treureJugFiltres
    private void desmarcarCB(List<JCheckBox> cb){
        for(JCheckBox c : cb){
            c.setSelected(false);
        }
    }
    private void filtrarJug(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_filtrarJug
        crearTableModel(modeActual());
    }//GEN-LAST:event_filtrarJug
    //PROGRAMA TRET DE CHATGPT I ADAPTAT
    private void csv(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_csv
        String err="";
        //retornar llistat de jugadors requereix de filtres, li pasem un llistat vuit perque torni tot
        Map<String, String> auxFilt= new HashMap<>();
        String pthCSV=pathToSave+"\\llistatJugadors.csv";
        try (FileWriter writer = new FileWriter(pthCSV)) {
            writer.append("ID,Nom,Cognoms,Sexe,DataNaixement,NIF/NIE,AnyFiRevisioMedica,IBAN,Adreça,CodiPostal,Poblacio,Foto,Equips\n");
            List<Jugador> jugadors= Constants.getgBD().llistatJugadors(auxFilt);
            for (Jugador j : jugadors) {
                int id=j.getId();
                String llistatEquips=tractarEquipsJugador(id);

                writer.append(id + ",")
                        .append(j.getNom() + ",")
                        .append(j.getCognom() + ",")
                        .append(j.getSexe() + ",")
                        .append(Constants.calendarToString(j.getData_naix()) + ",")
                        .append(j.getId_legal() + ",")
                        .append(Constants.calendarToString(j.getAny_fi_revisio_medica()) + ",")
                        .append(j.getIban() + ",")
                        .append(j.getAdreca() + ",")
                        .append("'"+j.getCp() + ",")
                        .append(j.getPoblacio() + ",")
                        .append(j.getFoto() + ",")
                        .append(llistatEquips + "\n");
            }

            System.out.println("Jugadors exportats correctament a " + pathToSave);
        } catch (IOException e) {
            err="Error al exportar a CSV: "+e.getMessage();
        }catch (GestorBDEquipException ex) {
            err="Error en generar el CSV degut a la conexió amb la BBDD";
        }
        if(err.isEmpty()){
            JOptionPane.showMessageDialog(null, "Fitxer CSV s'ha generat a l'escriptori", "InfoBox: " + "CSV Created", JOptionPane.INFORMATION_MESSAGE);
        }
        errJUG.setText(err);
    }//GEN-LAST:event_csv
    //PROGRAMA TRET DE CHATGPT I ADAPTAT
    private void xml(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_xml
        String pthXML=pathToSave+"\\llistatJugadors.xml";
        //retornar llistat de jugadors requereix de filtres, li pasem un llistat vuit perque torni tot
        Map<String, String> auxFilt= new HashMap<>();
        String err="";
        try {
            List<Jugador> jugadors= Constants.getgBD().llistatJugadors(auxFilt);
            XMLOutputFactory xmlOutputFactory = XMLOutputFactory.newInstance();
            try (FileWriter fileWriter = new FileWriter(pthXML)) {
                XMLStreamWriter xmlWriter = xmlOutputFactory.createXMLStreamWriter(fileWriter);
                xmlWriter.writeStartDocument("UTF-8", "1.0");
                xmlWriter.writeStartElement("Jugadors");

                for (Jugador j : jugadors) {
                    List<Equip> ej = Constants.getgBD().llistatEquipParticipa(j.getId());
                    
                    xmlWriter.writeStartElement("Jugador");

                    xmlWriter.writeStartElement("ID");
                    xmlWriter.writeCharacters(String.valueOf(j.getId()));
                    xmlWriter.writeEndElement();

                    xmlWriter.writeStartElement("Nom");
                    xmlWriter.writeCharacters(j.getNom());
                    xmlWriter.writeEndElement();

                    xmlWriter.writeStartElement("Cognom");
                    xmlWriter.writeCharacters(j.getCognom());
                    xmlWriter.writeEndElement();

                    xmlWriter.writeStartElement("Sexe");
                    xmlWriter.writeCharacters(String.valueOf(j.getSexe()));
                    xmlWriter.writeEndElement();

                    xmlWriter.writeStartElement("DataNaixement");
                    xmlWriter.writeCharacters(Constants.calendarToString(j.getData_naix()));
                    xmlWriter.writeEndElement();

                    xmlWriter.writeStartElement("IdLegal");
                    xmlWriter.writeCharacters(j.getId_legal());
                    xmlWriter.writeEndElement();

                    xmlWriter.writeStartElement("AnyFiRevisioMedica");
                    xmlWriter.writeCharacters(Constants.calendarToString(j.getAny_fi_revisio_medica()));
                    xmlWriter.writeEndElement();
                    
                    xmlWriter.writeStartElement("IBAN");
                    xmlWriter.writeCharacters(j.getIban());
                    xmlWriter.writeEndElement();

                    xmlWriter.writeStartElement("Adreça");
                    xmlWriter.writeCharacters(j.getAdreca());
                    xmlWriter.writeEndElement();

                    xmlWriter.writeStartElement("CodiPostal");
                    xmlWriter.writeCharacters(j.getCp());
                    xmlWriter.writeEndElement();

                    xmlWriter.writeStartElement("Poblacio");
                    xmlWriter.writeCharacters(j.getPoblacio());
                    xmlWriter.writeEndElement();

                    xmlWriter.writeStartElement("Foto");
                    xmlWriter.writeCharacters(j.getFoto());
                    xmlWriter.writeEndElement();

                    xmlWriter.writeStartElement("Equips");
                    for(Equip e: ej){
                        xmlWriter.writeStartElement("Equip");
                        xmlWriter.writeCharacters(e.getNom());
                        xmlWriter.writeEndElement();
                    }
                    xmlWriter.writeEndElement();
                    xmlWriter.writeEndElement();
                }
                xmlWriter.writeEndElement();
                xmlWriter.writeEndDocument();
            }
            
        } catch (Exception e) {
            err="Error al exportar a XML: " + e.getMessage();
        }
        if(err.isEmpty()){
            JOptionPane.showMessageDialog(null, "Fitxer XML s'ha generat a l'escriptori", "InfoBox: " + "XML Created", JOptionPane.INFORMATION_MESSAGE);
        }
        errJUG.setText(err);
    }//GEN-LAST:event_xml
    private String tractarEquipsJugador(int jug){
        String aux="";
        List<Equip> ej;
        try {
            ej = Constants.getgBD().llistatEquipParticipa(jug);
            int numEq=ej.size();
            if(numEq!=0){
                for(int i=0; i<numEq;i++){
                    aux+=ej.get(i).getNom();
                    if(i!=numEq-1){
                        aux+="&";
                    }
                }
            }
        } catch (GestorBDEquipException ex) {
            errJUG.setText(ex.getMessage());
        }
        return aux;
    }
 
    private void superiorInferior(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_superiorInferior
        String auxFiltreDNaix=evt.getComponent().getName();
        if(auxFiltreDNaix.isEmpty()){
            majorOmenor=auxFiltreDNaix;
        }
    }//GEN-LAST:event_superiorInferior

    private void jasperReport(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jasperReport
        String tempAux=Constants.gettSel().toString();
        Boolean varisFiltres=false;
        String err="";
        //preguntem si vol de totes les temporades o només de la selecionada
        int resposta=JOptionPane.showConfirmDialog(null, "Vols fer servir la temporada "+tempAux+"? \n "
            + "Si escolleix:\n- NO='totes les temporades'\n- Cancel='No fer el report'", "Confirmar temporada",
            JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE);
        if(resposta!=2){
            String fileName = "Informe Equip";
            int BUFFER_SIZE = 4096;
            String url = urlJRS + "FitxaEquip.pdf?";
            if(resposta==0){
                varisFiltres=true;
                url+="anyTemporada="+tempAux;
                fileName+=" "+tempAux.replace("/", "_");
            }
            if(categoria.getSelectedIndex()>0){
                if(varisFiltres){
                    url+="&";
                }
                String nomCat=categoria.getSelectedItem().toString();
                url+="codiCategoria="+ Constants.idCategoria(nomCat);
                fileName+=" "+nomCat;
            }
            int rows=geTable.getRowCount(), i=0;
            Boolean actiu=false;
            while(i!=rows && !actiu){
                actiu=(Boolean)((DefaultTableModel)geTable.getModel()).getValueAt(i,6);
                i++;
            }
            if(actiu){
                if(varisFiltres){
                    varisFiltres=true;
                    url+="&";
                }
                url+="codiEquip="+Integer.valueOf(((DefaultTableModel)geTable.getModel()).getValueAt(i-1, 1).toString());
                fileName+=" "+((DefaultTableModel)geTable.getModel()).getValueAt(i-1, 2).toString();
            }
            fileName+=".pdf";
            try {
                URL obj = new URL(url);
                HttpURLConnection con = (HttpURLConnection) obj.openConnection();
                con.setRequestMethod("GET");
                String autenticacio = Base64.getEncoder().encodeToString((userJRS + ":" + passwordJRS).getBytes());
                con.setRequestProperty("Authorization", "Basic " + autenticacio);
                int responseCode = con.getResponseCode();

                if (responseCode == HttpURLConnection.HTTP_OK) {
                    
                    // Una vegada decidit el nom, seguim:
                    // Obrim InputStream des de HTTP connection
                    InputStream inputStream = con.getInputStream();
                    // Obrim OutputStream per enregistrar el fitxer
                    FileOutputStream outputStream = new FileOutputStream(fileName);

                    // Llegim de inputStrem i escrivima outputStream, byte a byte:
                    int bytesRead = -1;
                    byte[] buffer = new byte[BUFFER_SIZE];
                    while ((bytesRead = inputStream.read(buffer)) != -1) {
                        outputStream.write(buffer, 0, bytesRead);
                    }
                    outputStream.close();
                    inputStream.close();

                    JOptionPane.showMessageDialog(null, "JasperReport generat correctament!", "InfoBox: " + "Report Created", JOptionPane.INFORMATION_MESSAGE);
                    // Intentem obrir-lo en alguna aplicació del SO
                    if (Desktop.isDesktopSupported()) {
                        try {
                            Desktop.getDesktop().open(new File(fileName));
                        } catch (IOException ex) {
                            errGE.setText("No hi ha aplicacions disponibles per obrir el fitxer");
                        }
                    }
                } else {
                    errGE.setText("Mètode 'GET' : " + url);
                    errGE.setText(errGE.getText() + "\nCodi resposta: " + responseCode);
                    errGE.setText(errGE.getText() + "\nCap fitxer a descarregar");
                }
                con.disconnect();
            } catch (MalformedURLException ex) {
               err=errGE.getText();
            } catch (ProtocolException ex) {
                err=errGE.getText();
            } catch (IOException ex) {
                err=errGE.getText();
            }
             errGE.setText(err);
        }
    }//GEN-LAST:event_jasperReport
    //programa per revisar els filtres tant per equips com per jugadors
    private Map<String, String> filtresAplicats(String btn){
        Map<String, String> auxFilt= new HashMap<>();
        switch(btn){
            case "ge": //en el cas del gestor d'equips
                //verifiquem si han afegit un nom
                String n=nom.getText();
                if(!n.isEmpty()){
                    auxFilt.put(nom.getName(), "'%"+n.toUpperCase()+"%'");
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
                //verifiquem si han afegit un nom
                String nj=nomJug.getText();
                if(!nj.isEmpty()){
                    auxFilt.put(nomJug.getName(), "'%"+nj.toUpperCase()+"%'");
                }
                String idLeg=idLegal.getText();
                if(!idLeg.isEmpty()){
                    auxFilt.put(idLegal.getName(), "'%"+idLeg.toUpperCase()+"%'");
                }
                String llistatCat="";
                for(JCheckBox c : gjCB){
                    if(c.isSelected()){
                        //recuperem la categoria
                        Categoria cat=Constants.objecteCategoria(Constants.idCategoria(c.getText()));
                        //el toString esta sobreescrit per retornar els anys que corresponen a la categoria
                        if(!llistatCat.isEmpty()){
                            llistatCat+=",";
                        }
                        llistatCat+=cat.anysCategoria();
                    }
                }
                if(!llistatCat.isEmpty()){
                    auxFilt.put("categoria", llistatCat);
                }
                String dNaixSel="";
                if(datNaix.getDate()!=null){
                    dNaixSel+=majorOmenor+"'"+Constants.calendarToString(datNaix.getCalendar())+"'";
                    auxFilt.put(datNaix.getName(), dNaixSel);
                }
                break;
        }
        return auxFilt;
    }
    //programa que revisa quines opcions han sigut escollides en el Gestor D'equips en quan a tipus (sexe)
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
        int numColumns=headers.get(btn).length;
        Map<String, String> filters = new HashMap<>(filtresAplicats(btn));
        DefaultTableModel mt= new DefaultTableModel(new Object[0][numColumns],headers.get(btn));
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment( JLabel.CENTER );
        JScrollPane scrollPane;
        int del=0, sel=0;
        if(btn=="ge"){
            errGE.setText("");//ens asegurem que no queda cap error a la finestra
            del=6;//setejem la columna que fará de botó de delete
            geTable.setModel(mt);//setejem columnes
            //amagem la columna ID que la farem servir per a eliminar/
            geTable.removeColumn(geTable.getColumnModel().getColumn(1));
            try {
                //Centrem les columnes necesaries (Categoria, tipus, te jugadors):
                for(int i=2; i<(numColumns-1);i++){
                    geTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
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
                        false,
                        "Eliminar"
                        };
                    mt.addRow(info);
                }
                TableCellListener tcl = new TableCellListener(geTable, controlCheckbox);
                taulaConfiguration(geTable,sel,del);
                
            } catch (GestorBDEquipException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "InfoBox: " + "DB Connection Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }else{
            errJUG.setText("");//ens asegurem que no queda cap error a la finestra
            del=7;//setejem la columna que fará de botó de delete
            jugTable.setModel(mt);//setejem columnes
            //amagem la columna ID que la farem servir per a eliminar/
            jugTable.removeColumn(jugTable.getColumnModel().getColumn(1));
            try {
                //Centrem les columnes necesaries (Categoria, tipus, te jugadors):
                for(int i=3; i<(numColumns-1);i++){
                    jugTable.getColumnModel().getColumn(i).setCellRenderer( centerRenderer );
                }
                List<Jugador> infoJug =Constants.getgBD().llistatJugadors(filters);
                for(Jugador j : infoJug){
                    Object[] info= new Object[]{
                        "Seleccionar",
                        j.getId(),
                        j.getNom(),
                        j.getCognom(),
                        j.getId_legal(),
                        Constants.jugCategoria(j.getData_naix()),
                        j.getSexe(),
                        Constants.calendarToString(j.getData_naix()),
                        "Eliminar"
                        };
                    mt.addRow(info);
                }
                taulaConfiguration(jugTable,sel,del);
            } catch (GestorBDEquipException ex) {
                JOptionPane.showMessageDialog(null, ex.getMessage(), "InfoBox: " + "DB Connection Error", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    private void taulaConfiguration(JTable t, int sel, int del){
        t.setAutoCreateRowSorter(true);
        if(t.getName()=="geTable"){
            TableColumn tc=t.getColumnModel().getColumn(5);
            tc.setCellEditor(t.getDefaultEditor(Boolean.class));
            tc.setCellRenderer(t.getDefaultRenderer(Boolean.class));
        }
        //fem la primera columna sigui button per seleccionar
        ButtonColumn buttonSel = new ButtonColumn(t,selEq,sel);
        //fem la última columna sigui button per eliminar
        ButtonColumn buttonDel= new ButtonColumn(t,delEq,del);
    }
    private int confirmacióEliminar(String nom, String tName){
        int resposta=JOptionPane.showConfirmDialog(null, "Estas segur que vols eliminar a "+nom+"?", "Compte!",
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
        
        if(tName=="geTable"){
            errGE.setText(err);
        }else{
            errJUG.setText(err);
        }
        return resposta;
    }
    private void carregarVistaEquip(int id){
        this.setVisible(false);
        new VistaEquip(id, this).setVisible(true);
    }
    private void carregarVistaJugador(int id){
        this.setVisible(false);
        try {
            new VistaJugador(id, this).setVisible(true);
        } catch (IOException ex) {
            Logger.getLogger(MainPage.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //source: https://tips4java.wordpress.com/2009/06/07/table-cell-listener/
    Action controlCheckbox= new AbstractAction(){
        public void actionPerformed(ActionEvent e){
            //per veure qui ha fet el canvi
            TableCellListener tcl = (TableCellListener)e.getSource();
            //column que l'ha cridat
            int col=tcl.getColumn();
            //només importa les columnes del checkbox, sino no farem res.
            if(col==6){
                //row que l'ha cridat i mirarem en quin estat es troba.
                int row=tcl.getRow();
                //retornem la taula per a poder editar-la
                JTable table = tcl.getTable(); 
                for(int i=0;i<table.getRowCount();i++){
                    if(i!=row){
                        table.setValueAt(false, i, col-1);
                    }
                    
                }
            }
        }
    };
    //Font: https://tips4java.wordpress.com/2009/07/12/table-button-column/
    Action selEq= new AbstractAction(){
        public void actionPerformed(ActionEvent e){
            JTable table = (JTable)e.getSource(); 
            String name= table.getName();
            int row = Integer.valueOf( e.getActionCommand() );
            int id= Integer.valueOf(((DefaultTableModel)table.getModel()).getValueAt(row, 1).toString());
            //depenent de la taula mostrarem un jugador (gjTable) o un equip (geTable)
            if(name=="geTable"){
                carregarVistaEquip(id);
            }else{
                carregarVistaJugador(id);
            }
        }
    };
    Action delEq= new AbstractAction(){
        public void actionPerformed(ActionEvent e){
            JTable table = (JTable)e.getSource(); 
            String tName= table.getName();
            int row = Integer.valueOf( e.getActionCommand() );
            int id= Integer.valueOf(((DefaultTableModel)table.getModel()).getValueAt(row, 1).toString());
            String nom= ((DefaultTableModel)table.getModel()).getValueAt(row, 2).toString();
            String err="";
            //depenent de la taula eliminarem un jugador (gjTable) o un equip (geTable)
            try {
                if(tName=="geTable"){
                    Constants.getgBD().eliminarEquip(id);
                }else{
                    Constants.getgBD().eliminarJugador(id);
                }
            } catch (GestorBDEquipException ex) {
                err=ex.getMessage();
            }
            if(tName=="geTable"){
                 errGE.setText(err);
            }else{
                 errJUG.setText(err);
            }
            //si no hi ha hagut capp error en intentar eliminar el jugador/equip
            if(err.isBlank()){
                //demanem confirmació abans d'eliminar, si tot ha anat bé retorna 0
                if(confirmacióEliminar(nom, tName)==0){
                    //si confirma, després de fer el delete a la BBDD, ens carregem la row.
                    ((DefaultTableModel)table.getModel()).removeRow(row);
                }
            }

        }
    };

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel afegirTempPanel;
    private javax.swing.JCheckBox aleviCheck;
    private javax.swing.JCheckBox benjCheck;
    private javax.swing.JButton botoAltas;
    private javax.swing.JCheckBox cadetCheck;
    private javax.swing.JLabel catLabel;
    private javax.swing.JComboBox<String> categoria;
    private javax.swing.JButton csv;
    private javax.swing.JLabel dNaixLabel;
    private com.toedter.calendar.JDateChooser datNaix;
    private javax.swing.ButtonGroup datNaixGr;
    private javax.swing.JCheckBox donaCheck;
    private javax.swing.JLabel errGE;
    private javax.swing.JLabel errJUG;
    private javax.swing.JButton filtrarEquip;
    private javax.swing.JButton filtrarJug;
    private javax.swing.JTable geTable;
    private javax.swing.JButton gestioEq;
    private javax.swing.JButton gestioJug;
    private javax.swing.JCheckBox homeCheck;
    private javax.swing.JTextField idLegal;
    private javax.swing.JCheckBox infCheck;
    private javax.swing.JRadioButton inferIg;
    private javax.swing.JLabel infoInsertTemp;
    private javax.swing.JLabel infoInsertTemp1;
    private javax.swing.JButton insertTemp;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton jasperReport;
    private javax.swing.JLabel jugCatLabel;
    private javax.swing.JTable jugTable;
    private javax.swing.JCheckBox juvCheck;
    private javax.swing.JLayeredPane layeredPanel;
    private javax.swing.JComboBox<String> listTemp;
    private javax.swing.JPanel llistatEqPanel;
    private javax.swing.JPanel llistatJugPanel;
    private javax.swing.JButton logOut;
    private javax.swing.JRadioButton majorIg;
    private javax.swing.JCheckBox mixtCheck;
    private javax.swing.JLabel nlegalLabel;
    private javax.swing.JTextField nom;
    private javax.swing.JTextField nomJug;
    private javax.swing.JLabel nomJugLabel;
    private javax.swing.JLabel nomLabel;
    private javax.swing.JLabel novaTempLabel;
    private javax.swing.JCheckBox seniorCheck;
    private javax.swing.JLabel tempError;
    private javax.swing.JTextField tempInput;
    private javax.swing.JLabel temporadaLabel;
    private javax.swing.JLabel tipusLabel;
    private javax.swing.JButton treureFiltres;
    private javax.swing.JButton treureJugFiltres;
    private javax.swing.JButton xml;
    // End of variables declaration//GEN-END:variables
}
