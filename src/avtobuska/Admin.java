/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Admin.java
 *
 * Created on Jan 11, 2013, 8:09:23 PM
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

/**
 *
 * @author psylee
 */
public class Admin extends javax.swing.JFrame {

    AvtobuskaMetodi s;

    public Admin() throws SQLException {
        initComponents();
        this.setLocation(100, 100);
        s = new AvtobuskaMetodi();
        NapuniDropdown();
        Grad();
        NapuniDropdown();
        gradovi.setSelectedItem("Скопје");
        ResultSet rs = s.Zarabotka();
        if (rs.next()) {
            vkupna_zarabotka.setText(rs.getString("ZARABOTKA").toString());
        }

        String t = datum();
        //System.out.println(t);
        ResultSet rs2 = s.DnevnaZarabotka(t);
        if (rs2.next()) {
            if (rs2.getString("ZARABOTKA") == null) {
                dnevna_zarabotka.setText("0");
            } else {
                dnevna_zarabotka.setText(rs2.getString("ZARABOTKA").toString());
            }
        } else {
            dnevna_zarabotka.setText("0");
        }

        NapolniTabelaLogin();
        NapolniTabelaVraboteni();
        napolniregistracii();
    }
    
    
    public void napolniregistracii() throws SQLException
    {
        jComboBox1.addItem("/");    
        ResultSet rs = s.sitereg();        
         while(rs.next())
         {
             jComboBox1.addItem(rs.getString(1));            
         }
         jComboBox1.removeItemAt(0);    
    }
    

    public void Grad() throws SQLException {

        if (gradovi.getItemCount() == 0) {
            gradovi.addItem("Скопје");
        }

        ResultSet rs2 = s.SelektirajGrad(gradovi.getSelectedItem().toString());
        if (rs2.next()) {
            broj_na_bileti.setText(rs2.getString("BROJ_NA_BILETI").toString());
        }
        if (gradovi.getItemCount() == 8) {
            gradovi.removeItem("Скопје");
        }
        // System.out.println(gradovi.getItemCount());
    }

    public void NapuniDropdown() throws SQLException {

        gradovi.removeAllItems();
        ResultSet rs = s.PuniDropdown();
        while (rs.next()) {
            gradovi.addItem(rs.getString(1));
        }
        gradovi.setSelectedItem("Скопје");
    }

    // вработени со пристап до апликацијата
    public void NapolniTabelaLogin() throws SQLException {
        Vector head = new Vector();
        Vector data = new Vector(1, 1);
        head.add("Username");
        head.add("Password");
        head.add("Admin");

        ResultSet rs = s.ListajPristapVraboteni();
        data = new Vector(1, 1);
        while (rs.next()) {
            Vector row = new Vector();
            row.add(rs.getString("USERNAME"));
            row.add(rs.getString("PASSWORD"));
            row.add(rs.getString("ADMIN"));

            data.add(row);
        }

        DefaultTableModel dtm = new DefaultTableModel(data, head) {
            // zabrana za edit na kolonite
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        // postavuvawe na dtm modelot na tabelata
        TabelaLogin.setModel(dtm);

    }

    //сите вработени
    public void NapolniTabelaVraboteni() throws SQLException {
        Vector head = new Vector();
        Vector data = new Vector(1, 1);
        head.add("Е-mail");
        head.add("Име");
        head.add("Презиме");
        head.add("Плата");
        head.add("Возачка дозвола");
        head.add("Специјалност");
        head.add("Диплома");

        ResultSet rs = s.ListajVraboteni();
        data = new Vector(1, 1);
        while (rs.next()) {
            Vector row = new Vector();
            row.add(rs.getString("MAIL"));
            row.add(rs.getString("IME"));
            row.add(rs.getString("PREZIME"));
            row.add(rs.getString("PLATA"));
            row.add(rs.getString("KATEGORIJA_VOZ_DOZVOLA"));
            row.add(rs.getString("TITULA"));
            row.add(rs.getString("DIPLOMA"));

            data.add(row);
        }

        DefaultTableModel dtm = new DefaultTableModel(data, head) {
            // zabrana za edit na kolonite
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        // postavuvawe na dtm modelot na tabelata
        SiteVraboteni.setModel(dtm);

    }

    public String datum() {
        DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date date = new Date();
        String datum = (String) dateFormat.format(date);
        return datum;
    }

    public void postaviemail(String s) {
        email = s;
    }

    public String vratiemail() {
        return email;
    }

    public void najav(String s) {
        najaven.setText(s);
    }

    public void lozinka() {
        lozinka.setVisible(false);
        lozinka_kopce.setVisible(false);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        AdminPanel = new javax.swing.JPanel();
        dodadi_vrab = new javax.swing.JButton();
        logout = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        dnevna_zarabotka = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        vkupna_zarabotka = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        broj_na_bileti = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        SiteVraboteni = new javax.swing.JTable();
        delovi = new javax.swing.JButton();
        jScrollPane3 = new javax.swing.JScrollPane();
        TabelaLogin = new javax.swing.JTable();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        dodadi_vozilo = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        najaven = new javax.swing.JLabel();
        lozinka_kopce = new javax.swing.JButton();
        lozinka = new javax.swing.JPasswordField();
        gradovi = new javax.swing.JComboBox();
        posledni_trgnivanja = new javax.swing.JButton();
        klienti = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        izbrisi_vozilo = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        vrabotenmail = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        izbrisi_vraboten = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        dodadi_vozenred = new javax.swing.JButton();
        proverki = new javax.swing.JButton();
        dodadi_lokacija = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Администратор");
        setResizable(false);

        dodadi_vrab.setText("Додади вработен");
        dodadi_vrab.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodadi_vrabActionPerformed(evt);
            }
        });

        logout.setText("Одлогирај се");
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
        });

        jLabel3.setText("Денешна заработка:");

        dnevna_zarabotka.setText("/");

        jLabel4.setText("Вкупна заработка:");

        vkupna_zarabotka.setText("/");

        jLabel5.setText("Град");

        jLabel6.setText("Број на издадени билети");

        broj_na_bileti.setText("/");

        SiteVraboteni.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6", "Title 7"
            }
        ));
        SiteVraboteni.setToolTipText("");
        SiteVraboteni.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        SiteVraboteni.getTableHeader().setReorderingAllowed(false);
        jScrollPane2.setViewportView(SiteVraboteni);
        SiteVraboteni.getColumnModel().getColumn(6).setHeaderValue("Title 7");

        delovi.setText("Делови");
        delovi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deloviMouseClicked(evt);
            }
        });
        delovi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deloviActionPerformed(evt);
            }
        });

        TabelaLogin.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null},
                {null, null, null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4", "Title 5", "Title 6"
            }
        ));
        TabelaLogin.setToolTipText("");
        TabelaLogin.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        TabelaLogin.getTableHeader().setReorderingAllowed(false);
        jScrollPane3.setViewportView(TabelaLogin);

        jLabel7.setText("Вработени со пристап до апликација");

        jLabel8.setText("Сите вработени");

        dodadi_vozilo.setText("Додади возило");
        dodadi_vozilo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodadi_voziloActionPerformed(evt);
            }
        });

        jLabel9.setText("Најавен");

        najaven.setText(" ");

        lozinka_kopce.setText("Промени лозинка");
        lozinka_kopce.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lozinka_kopceMouseClicked(evt);
            }
        });

        lozinka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                lozinkaActionPerformed(evt);
            }
        });

        gradovi.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        gradovi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gradoviActionPerformed(evt);
            }
        });

        posledni_trgnivanja.setText("Последни тргнувања");
        posledni_trgnivanja.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                posledni_trgnivanjaMouseClicked(evt);
            }
        });

        klienti.setText("Клиенти");
        klienti.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                klientiMouseClicked(evt);
            }
        });
        klienti.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                klientiActionPerformed(evt);
            }
        });

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Бриши возило"));

        jLabel10.setText("Регистрација на возило");

        izbrisi_vozilo.setText("Избриши возило");
        izbrisi_vozilo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izbrisi_voziloActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel10)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(izbrisi_vozilo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(izbrisi_vozilo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Бриши вработен"));

        jLabel1.setText("Маил на вработениот");

        jLabel2.setText("@gjepek.com");

        izbrisi_vraboten.setText("Избриши вработен");
        izbrisi_vraboten.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                izbrisi_vrabotenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(vrabotenmail, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel2))
                    .addComponent(izbrisi_vraboten, javax.swing.GroupLayout.PREFERRED_SIZE, 142, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(vrabotenmail, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(izbrisi_vraboten)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jButton1.setText("Освежи");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        dodadi_vozenred.setText("Возен ред");
        dodadi_vozenred.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodadi_vozenredActionPerformed(evt);
            }
        });

        proverki.setText("Проверки");
        proverki.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proverkiActionPerformed(evt);
            }
        });

        dodadi_lokacija.setText("Додади локација");
        dodadi_lokacija.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dodadi_lokacijaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout AdminPanelLayout = new javax.swing.GroupLayout(AdminPanel);
        AdminPanel.setLayout(AdminPanelLayout);
        AdminPanelLayout.setHorizontalGroup(
            AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdminPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, AdminPanelLayout.createSequentialGroup()
                        .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AdminPanelLayout.createSequentialGroup()
                                .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, AdminPanelLayout.createSequentialGroup()
                                        .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(AdminPanelLayout.createSequentialGroup()
                                                .addComponent(jLabel9)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(najaven, javax.swing.GroupLayout.PREFERRED_SIZE, 214, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(lozinka, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lozinka_kopce))
                                            .addGroup(AdminPanelLayout.createSequentialGroup()
                                                .addComponent(jLabel5)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(gradovi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(broj_na_bileti, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                        .addGap(15, 15, 15)
                                        .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(AdminPanelLayout.createSequentialGroup()
                                                .addComponent(jLabel3)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(dnevna_zarabotka, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addGroup(AdminPanelLayout.createSequentialGroup()
                                                .addComponent(jLabel4)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(vkupna_zarabotka, javax.swing.GroupLayout.PREFERRED_SIZE, 68, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(logout)))
                                    .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 782, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(AdminPanelLayout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addComponent(dodadi_vrab, javax.swing.GroupLayout.PREFERRED_SIZE, 169, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(12, 12, 12)
                                .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(AdminPanelLayout.createSequentialGroup()
                                        .addGap(12, 12, 12)
                                        .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(delovi)
                                            .addComponent(dodadi_vozilo, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(dodadi_vozenred)
                                            .addComponent(proverki)
                                            .addComponent(dodadi_lokacija))))))
                        .addGap(24, 24, 24))
                    .addGroup(AdminPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 780, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton1)
                            .addGroup(AdminPanelLayout.createSequentialGroup()
                                .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(klienti)
                                    .addComponent(posledni_trgnivanja))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 293, Short.MAX_VALUE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        AdminPanelLayout.setVerticalGroup(
            AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AdminPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(AdminPanelLayout.createSequentialGroup()
                        .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(najaven)
                            .addComponent(lozinka_kopce)
                            .addComponent(logout)
                            .addComponent(lozinka, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AdminPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 9, Short.MAX_VALUE)
                                .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(gradovi, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel6)
                                    .addComponent(broj_na_bileti))
                                .addGap(23, 23, 23)
                                .addComponent(jLabel7)
                                .addGap(3, 3, 3))
                            .addGroup(AdminPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(vkupna_zarabotka))
                                .addGap(2, 2, 2)
                                .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel3)
                                    .addComponent(dnevna_zarabotka))))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(AdminPanelLayout.createSequentialGroup()
                        .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(dodadi_vrab)
                            .addComponent(dodadi_vozilo))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(AdminPanelLayout.createSequentialGroup()
                        .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(klienti)
                            .addComponent(proverki))
                        .addGap(18, 18, 18)
                        .addComponent(delovi)
                        .addGap(18, 18, 18)
                        .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(posledni_trgnivanja)
                            .addComponent(dodadi_vozenred))
                        .addGroup(AdminPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(AdminPanelLayout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 46, Short.MAX_VALUE)
                                .addComponent(jButton1))
                            .addGroup(AdminPanelLayout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(dodadi_lokacija))))
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 198, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(19, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AdminPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(AdminPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        Logiranje l = new Logiranje();
        l.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_logoutMouseClicked

    private void dodadi_vrabActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodadi_vrabActionPerformed
        try {
            DodadiVraboten v = new DodadiVraboten();
            v.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_dodadi_vrabActionPerformed

    private void dodadi_voziloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodadi_voziloActionPerformed
        // TODO add your handling code here:    
        try {
            DodadiVozilo v = new DodadiVozilo();
            v.setVisible(true);
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_dodadi_voziloActionPerformed

    private void lozinkaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_lozinkaActionPerformed
    }
        // TODO add your handling code here:}//GEN-LAST:event_lozinkaActionPerformed

    private void lozinka_kopceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lozinka_kopceMouseClicked
        try {
            String pass;
            pass = lozinka.getText().trim();
           // System.out.println(vratiemail() + " " + pass);
            //->tuka
            s.PromeniLozinka(vratiemail().toString(), pass.toString());
            lozinka.setText("");
            JOptionPane.showMessageDialog(null, "Лозинката е променета");
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_lozinka_kopceMouseClicked

    private void klientiMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_klientiMouseClicked
        try {
            Mala_tabela k = new Mala_tabela();
            k.setVisible(true);
            k.NapolniTabela();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_klientiMouseClicked

    private void gradoviActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gradoviActionPerformed
        try {

            Grad();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_gradoviActionPerformed

    private void posledni_trgnivanjaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_posledni_trgnivanjaMouseClicked
        try {
            Mala_tabela k = new Mala_tabela();
            k.setVisible(true);
            k.NapolniVozaci();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_posledni_trgnivanjaMouseClicked

    private void izbrisi_vrabotenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izbrisi_vrabotenActionPerformed
        try {
            // TODO add your handling code here:
            String mailot = vrabotenmail.getText().trim() + jLabel2.getText();

            ResultSet rs = s.vratiTitula(mailot);
            while (rs.next()) {

                if (rs.getString(1).equals("Шалтер")) {
                    s.brisiSalterILISef(mailot);
                } else if (rs.getString(1).equals("Сервисер")) {
                    s.brisiServiser(mailot);
                } else if (rs.getString(1).equals("Шофер")) {
                    s.brisiSofer(mailot);
                } else if (rs.getString(1).equals("Шеф")) {
                    s.brisiSalterILISef(mailot);
                }
            }

            NapolniTabelaLogin();
            NapolniTabelaVraboteni();

            vrabotenmail.setText("");
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_izbrisi_vrabotenActionPerformed

    private void izbrisi_voziloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_izbrisi_voziloActionPerformed
        try {
            // TODO add your handling code here:
            String registracija = jComboBox1.getSelectedItem().toString();
            s.BrisiVozilo(registracija);
            jComboBox1.removeAllItems();
            napolniregistracii();
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_izbrisi_voziloActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        try {
            // TODO add your handling code here:
            NapolniTabelaLogin();
            NapolniTabelaVraboteni();
            jComboBox1.removeAllItems();
            napolniregistracii();
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_jButton1ActionPerformed

    private void klientiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_klientiActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_klientiActionPerformed

    private void deloviMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deloviMouseClicked
       try {
            DodadiDel d = new DodadiDel();
            d.setVisible(true);
            d.NapolniDelovi();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_deloviMouseClicked

    private void dodadi_vozenredActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodadi_vozenredActionPerformed
        try {
            DodadiVozenRed v = new DodadiVozenRed();
            v.setVisible(true);
            v.NapolniTabela();
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_dodadi_vozenredActionPerformed

    private void deloviActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deloviActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_deloviActionPerformed

    private void proverkiActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proverkiActionPerformed
       try {
            Proverka p = new Proverka();
            p.setVisible(true);
            p.NapolniProverki();
        } catch (SQLException ex) {
            Logger.getLogger(Admin.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_proverkiActionPerformed

    private void dodadi_lokacijaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dodadi_lokacijaActionPerformed
        try {
            DodadiLokacija p = new DodadiLokacija();
            p.setVisible(true);
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
       
    }//GEN-LAST:event_dodadi_lokacijaActionPerformed
    /**
     * @param args the command line arguments
     */
    String email;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel AdminPanel;
    private javax.swing.JTable SiteVraboteni;
    private javax.swing.JTable TabelaLogin;
    private javax.swing.JLabel broj_na_bileti;
    private javax.swing.JButton delovi;
    private javax.swing.JLabel dnevna_zarabotka;
    private javax.swing.JButton dodadi_lokacija;
    private javax.swing.JButton dodadi_vozenred;
    private javax.swing.JButton dodadi_vozilo;
    private javax.swing.JButton dodadi_vrab;
    private javax.swing.JComboBox gradovi;
    private javax.swing.JButton izbrisi_vozilo;
    private javax.swing.JButton izbrisi_vraboten;
    private javax.swing.JButton jButton1;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
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
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton klienti;
    private javax.swing.JButton logout;
    private javax.swing.JPasswordField lozinka;
    private javax.swing.JButton lozinka_kopce;
    private javax.swing.JLabel najaven;
    private javax.swing.JButton posledni_trgnivanja;
    private javax.swing.JButton proverki;
    private javax.swing.JLabel vkupna_zarabotka;
    private javax.swing.JTextField vrabotenmail;
    // End of variables declaration//GEN-END:variables
}
