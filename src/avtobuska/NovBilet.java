/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avtobuska;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.AbstractDocument;
import org.apache.commons.validator.EmailValidator;

public class NovBilet extends javax.swing.JFrame {

    /**
     * Creates new form NovBilet
     */
    AvtobuskaMetodi s;
    DefaultTableModel dtm;
    // String najaven;
    Vector head = new Vector();
    Vector data = new Vector(1, 1);

    public void postaviemail(String s) {
        email = s;
    }

    public String vratiemail() {
        return email;
    }

    public void najav(String s) {
        najaven_ime_prezime.setText(s);
    }

    public NovBilet() throws SQLException {
        initComponents();
        this.setLocation(70, 80);

        // najaven_ime_prezime.setText(vratinajaven());
        s = new AvtobuskaMetodi();
        // naslovi na tabelite
        head.add("Име");
        head.add("Презиме");
        head.add("#Билет");
        head.add("Тип билет");
        head.add("Перон");
        head.add("Седиште");
        head.add("Цена");
        head.add("Од");
        head.add("До");
        head.add("Поаѓа");
        head.add("Пристига");
        
        UpdateTable();

        NapuniDropdown();
        
        AbstractDocument document = (AbstractDocument) Sediste.getDocument();
        document.setDocumentFilter(new SamoBrojki());
        
        document = (AbstractDocument) ime.getDocument();
        document.setDocumentFilter(new SamoBukviKirilica());
        
        document = (AbstractDocument) prezime.getDocument();
        document.setDocumentFilter(new SamoBukviKirilica());
        
         document = (AbstractDocument) BiletID.getDocument();
         document.setDocumentFilter(new SamoBrojki());
    }

    // polnenje na tabelata
    public void UpdateTable() throws SQLException {
        ResultSet rs = s.ListajSve();
        data = new Vector(1, 1);
        //polnenje na redovite
        while (rs.next()) {
            Vector row = new Vector();
            row.add(rs.getString("IME"));
            row.add(rs.getString("PREZIME"));
            row.add(rs.getString("BILET_BR"));
            row.add(rs.getString("TIP_BILET"));
            row.add(rs.getString("PERON"));
            row.add(rs.getString("SEDISTE"));
            row.add(rs.getString("CENA"));
            row.add(rs.getString("OD"));
            row.add(rs.getString("DESTINACIJA"));
            row.add(rs.getString("POAGJA"));
            row.add(rs.getString("PRISTIGA"));
           

            data.add(row);
        }
        // izmeni vo DefaultTableModelot
        dtm = new DefaultTableModel(data, head) {
            // zabrana za edit na kolonite

            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        // postavuvawe na dtm modelot na tabelata
        Tabela.setModel(dtm);
    }

    public void NapuniDropdown() throws SQLException {
        jComboBox1.addItem("/");
        jComboBox2.addItem("/");        
        ResultSet rs = s.PuniDropdown();        
         while(rs.next())
         {
             jComboBox1.addItem(rs.getString(1));
             jComboBox2.addItem(rs.getString(1));
             
         }
         jComboBox1.removeItemAt(0);
         jComboBox2.removeItemAt(0);
    }

    public void PolniVreme() throws SQLException {
        jComboBox3.removeAllItems();
        String od = (String) jComboBox1.getSelectedItem();
        String dest = (String) jComboBox2.getSelectedItem();
        ResultSet rs = s.ZemiVreme(od, dest);
        while (rs.next()) {
            jComboBox3.addItem(rs.getString(1));
        }
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        TipBilet = new javax.swing.ButtonGroup();
        jSeparator1 = new javax.swing.JSeparator();
        najaven_ime_prezime = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        logout = new javax.swing.JButton();
        promeni_lozinka = new javax.swing.JButton();
        jLabel7 = new javax.swing.JLabel();
        lozinka = new javax.swing.JPasswordField();
        jPanel1 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tabela = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jComboBox2 = new javax.swing.JComboBox();
        jComboBox3 = new javax.swing.JComboBox();
        prezime = new javax.swing.JTextField();
        mail = new javax.swing.JTextField();
        osvezi_ekran = new javax.swing.JButton();
        Peron = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        Sediste = new javax.swing.JTextField();
        dodadi_bilet = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();
        jRadioButton3 = new javax.swing.JRadioButton();
        ime = new javax.swing.JTextField();
        jPanel3 = new javax.swing.JPanel();
        jLabel6 = new javax.swing.JLabel();
        BiletID = new javax.swing.JTextField();
        brisenje_kopce = new javax.swing.JButton();
        izmeni_kopce = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Меѓуградска Автобуска Станица v2.1");
        setResizable(false);

        jLabel4.setText("Најавен:");

        logout.setText("Одлогирај се");
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
        });

        promeni_lozinka.setText("Промени лозинка");
        promeni_lozinka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                promeni_lozinkaActionPerformed(evt);
            }
        });

        jLabel7.setText("Нова лозинка:");

        lozinka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lozinkaActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Нов билет"));
        jPanel1.setPreferredSize(new java.awt.Dimension(866, 600));

        Tabela.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7", "Title 8", "Title 9", "Title 10", "Title 11", "Title 12"
            }
        ));
        Tabela.setToolTipText("");
        Tabela.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Tabela.getTableHeader().setReorderingAllowed(false);
        Tabela.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                TabelaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Tabela);

        jLabel1.setText("Име");

        jLabel2.setText("Презиме");

        jLabel3.setText("E-mail");

        jComboBox1.setMaximumRowCount(20);
        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        jComboBox2.setMaximumRowCount(20);
        jComboBox2.setToolTipText("");
        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        jComboBox3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox3ActionPerformed(evt);
            }
        });

        osvezi_ekran.setText("Освежи екран");
        osvezi_ekran.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                osvezi_ekranMouseClicked(evt);
            }
        });

        Peron.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
        Peron.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PeronActionPerformed(evt);
            }
        });

        jLabel5.setText("Седиште");

        dodadi_bilet.setText("Додади билет");
        dodadi_bilet.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodadi_biletActionPerformed(evt);
            }
        });

        jLabel8.setText("Поаѓа од");

        jLabel9.setText("Дестинација");

        jLabel10.setText("Перон");

        jLabel11.setText("Време на поаѓање");

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Тип на билетот"));

        TipBilet.add(jRadioButton1);
        jRadioButton1.setText("Единечен");
        jRadioButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jRadioButton1ActionPerformed(evt);
            }
        });

        TipBilet.add(jRadioButton2);
        jRadioButton2.setText("Повратен");

        TipBilet.add(jRadioButton3);
        jRadioButton3.setText("Месечен");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jRadioButton2)
                    .addComponent(jRadioButton3)
                    .addComponent(jRadioButton1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jRadioButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jRadioButton3)
                .addGap(10, 10, 10))
        );

        jPanel3.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        jLabel6.setText("Билет ID");

        brisenje_kopce.setText("Избриши билет");
        brisenje_kopce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                brisenje_kopceActionPerformed(evt);
            }
        });

        izmeni_kopce.setText("Измени податоци");
        izmeni_kopce.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                izmeni_kopceMouseClicked(evt);
            }
        });
        izmeni_kopce.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izmeni_kopceActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(BiletID, javax.swing.GroupLayout.PREFERRED_SIZE, 92, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(brisenje_kopce)
                    .addComponent(izmeni_kopce))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 17, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BiletID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(brisenje_kopce)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(izmeni_kopce)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 830, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(Peron, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel11)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(osvezi_ekran)
                                .addGap(26, 26, 26))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, 78, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(35, 35, 35)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(mail, javax.swing.GroupLayout.DEFAULT_SIZE, 131, Short.MAX_VALUE)
                                    .addComponent(prezime)
                                    .addComponent(ime)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel9)
                                    .addComponent(jLabel8)
                                    .addComponent(jLabel5))
                                .addGap(13, 13, 13)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(Sediste, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(jComboBox1, 0, 78, Short.MAX_VALUE)
                                        .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                        .addGap(36, 36, 36)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dodadi_bilet))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(118, 118, 118))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap(21, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(dodadi_bilet, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jLabel1)
                                .addComponent(ime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(8, 8, 8)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(15, 15, 15))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel2)
                                    .addComponent(prezime, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(mail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel3))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, 18, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel8))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel9)
                                    .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(Sediste, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(13, 13, 13)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel10)
                            .addComponent(Peron, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel11)
                            .addComponent(jComboBox3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(15, 15, 15))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(osvezi_ekran)
                        .addGap(2, 2, 2)))
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 193, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator1, javax.swing.GroupLayout.DEFAULT_SIZE, 882, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(27, 27, 27)
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(najaven_ime_prezime, javax.swing.GroupLayout.PREFERRED_SIZE, 221, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lozinka, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(promeni_lozinka)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(logout))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 864, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 18, Short.MAX_VALUE)))
                .addGap(0, 0, 0))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jLabel7)
                        .addComponent(lozinka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(promeni_lozinka)
                        .addComponent(logout))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(najaven_ime_prezime, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(6, 6, 6)))
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 458, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        Logiranje l = new Logiranje();
        l.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_logoutMouseClicked

    private void promeni_lozinkaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_promeni_lozinkaActionPerformed
        try {
            String pass;
            pass=lozinka.getText().trim();
            //System.out.println(vratiemail()+" "+pass);
            s.PromeniLozinka(vratiemail().toString(), pass.toString());
            lozinka.setText("");
            
            JOptionPane.showMessageDialog(null, "лозинката е променета");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
    }//GEN-LAST:event_promeni_lozinkaActionPerformed

    private void jRadioButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jRadioButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jRadioButton1ActionPerformed

    private void izmeni_kopceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izmeni_kopceActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_izmeni_kopceActionPerformed

    private void izmeni_kopceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_izmeni_kopceMouseClicked
        // КОПЧЕ ЗА ИЗМЕНА
        try {
            String bilet = BiletID.getText().trim();
            int biletot = Integer.parseInt(bilet);
            IzmenaBilet i = new IzmenaBilet();

            i.setVisible(true);
            i.postaviNajaven(najaven_ime_prezime.getText().toString());
            i.postaviemail(email);
            i.postaviBiletID(bilet);
            i.postavipodatoci();
            UpdateTable();
            BiletID.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(NovBilet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_izmeni_kopceMouseClicked

    private void brisenje_kopceActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_brisenje_kopceActionPerformed

        try {

            String bilet = BiletID.getText().trim();
            int biletot = Integer.parseInt(bilet);
            s.BrisiBilet(biletot);
            UpdateTable();
            BiletID.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(NovBilet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_brisenje_kopceActionPerformed

    private void dodadi_biletActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodadi_biletActionPerformed

        // Tip na bilet i cena na bilet
        char tip;
        int cena;
        if (jRadioButton1.isSelected()) {
            tip = 'E';
            cena = 700;
           // System.out.println(tip);
        } else if (jRadioButton2.isSelected()) {
            tip = 'P';
            cena = 900;
          //  System.out.println(tip);
        } else {
            tip = 'M';
            cena = 3000;
         //   System.out.println(tip);
        }
        
        try {
            
           boolean isValidEmail = EmailValidator.getInstance().isValid(mail.getText());
          if(isValidEmail)
          {
            
            // izbor na peronot
            String speron = Peron.getSelectedItem().toString();
            int peron = Integer.parseInt(speron);

            // izbor na sediste
            String ssediste = Sediste.getText().trim();
            int sediste = Integer.parseInt(ssediste);

            //go zema denesniot datum formatiran
            DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd");
            Date date = new Date();
            String datum = (String) dateFormat.format(date);

            //vnesuvanje nov bilet
            // ako postoi klientot (mail) go zema negovoto ID i ne vnesuva nov klient
            ResultSet rst = s.KlientiMail();
            int KlientID = -1;
            while (rst.next()) {
                if (rst.getString("MAIL").equals(mail.getText().trim())) {
                    KlientID = rst.getInt("ID");
                }
            }

            // ako ne nasol ist mail dodadi nov klient i zemi go negovoto ID
            if (KlientID == -1) {
                s.VoKlienti(ime.getText().trim(), prezime.getText().trim(), mail.getText().trim());
                ResultSet rs = s.LastKlientID();
                rs.next();
                KlientID = rs.getInt(1);
            }

            // polnenje na tabelata KupuvaBilet so soodvetnite podatoci
            s.VoKupi_Bilet(tip, KlientID, peron, sediste, cena, datum);

            // go zema ID to na posledniot bilet
            ResultSet rs1 = s.LastBiletID();
            rs1.next();
            int BiletID = rs1.getInt(1);

            // polnenje na relacijata prodava
            // email e mailot na najaveniot vraboten(toj sto go prodal)
            s.VoRelProdava(BiletID, KlientID, email);

            // izbira od koj grad trga avtobusot i go zema negovoto id
            String odGradot = jComboBox1.getSelectedItem().toString();
            ResultSet rs2 = s.GradID(odGradot);
            rs2.next();
            int odGradotID = rs2.getInt(1);

            // polnenje na relacijata Izdava so ID-to od gradot
            s.VoRelIzdava(odGradotID, KlientID, BiletID);

            // go zema gradot destinacija od drop down listata
            String DoGradot = jComboBox2.getSelectedItem().toString();

            // go zema vremete na poagjanje od drop down listata
            String poagja = jComboBox3.getSelectedItem().toString();

            // ID-to na vazniot red za soodvetnata destinacija i vreme na poagjanje
            ResultSet rs3 = s.VozenRedID(DoGradot, poagja);
            rs3.next();
            int VredID = rs3.getInt(1);

            // polnenje na relacijata Za so ID-to na vozniot red
            s.VoRelIZa(KlientID, BiletID, VredID);

            UpdateTable();
          }
          else
          {
              JOptionPane.showMessageDialog(null, "Невалидна e-mail адреса");
          }

        } catch (SQLException ex) {
            Logger.getLogger(NovBilet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_dodadi_biletActionPerformed

    private void PeronActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PeronActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_PeronActionPerformed

    private void osvezi_ekranMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_osvezi_ekranMouseClicked
        try {
            UpdateTable();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_osvezi_ekranMouseClicked

    private void jComboBox3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox3ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox3ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        try {
            // TODO add your handling code here:
            PolniVreme();
        } catch (SQLException ex) {
            Logger.getLogger(NovBilet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBox2ActionPerformed

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed
        // TODO add your handling code here:
        try {
            // TODO add your handling code here:
            PolniVreme();
        } catch (SQLException ex) {
            Logger.getLogger(NovBilet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_jComboBox1ActionPerformed

    private void lozinkaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lozinkaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_lozinkaActionPerformed

    private void TabelaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_TabelaMouseClicked
       
    }//GEN-LAST:event_TabelaMouseClicked

    /**
     * @param args the command line arguments
     */
    String email;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField BiletID;
    private javax.swing.JComboBox Peron;
    private javax.swing.JTextField Sediste;
    private javax.swing.JTable Tabela;
    private javax.swing.ButtonGroup TipBilet;
    private javax.swing.JButton brisenje_kopce;
    private javax.swing.JButton dodadi_bilet;
    private javax.swing.JTextField ime;
    private javax.swing.JButton izmeni_kopce;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JComboBox jComboBox3;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JRadioButton jRadioButton3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JButton logout;
    private javax.swing.JPasswordField lozinka;
    private javax.swing.JTextField mail;
    private javax.swing.JLabel najaven_ime_prezime;
    private javax.swing.JButton osvezi_ekran;
    private javax.swing.JTextField prezime;
    private javax.swing.JButton promeni_lozinka;
    // End of variables declaration//GEN-END:variables
}
