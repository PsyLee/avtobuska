/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * IzmeniBilet.java
 *
 * Created on Jan 12, 2013, 3:10:10 AM
 */
package avtobuska;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ButtonModel;
import javax.swing.JOptionPane;

/**
 *
 * @author psylee
 */
public class IzmenaBilet extends javax.swing.JFrame {

    AvtobuskaMetodi s;

    /** Creates new form IzmeniBilet */
    public IzmenaBilet() throws SQLException {
        initComponents();
        this.setLocation(90, 100);
        s = new AvtobuskaMetodi();
        DoKade="/";
        NapuniDropdown(OdKade);
    }

    public void NapuniDropdown(String gradDo) throws SQLException {
        //System.out.println(gradDo);
        destinacija_drop.removeAllItems();
        destinacija_drop.addItem(DoKade);
        ResultSet rs = s.PuniGradovi(gradDo);
        while (rs.next()) {
            if(!rs.getString(1).toString().equals(DoKade))
            {
                destinacija_drop.addItem(rs.getString(1));
            }
        }
    }
    
    public String smeniMesec(String s)
    {
        String t = "";
             if(s.equals("01"))
        {t="Jan";}
        else if(s.equals("02"))
        {t="Feb";}
        else if(s.equals("03"))
        {t="Mar";}
        else if(s.equals("04"))
        {t="Apr";}
        else if(s.equals("05"))
        {t="May";}
        else if(s.equals("06"))
        {t="Jun";}
        else if(s.equals("07"))
        {t="Jul";}
        else if(s.equals("08"))
        {t="Aug";}
        else if(s.equals("09"))
        {t="Sep";}
        else if(s.equals("10"))
        {t="Oct";}
        else if(s.equals("11"))
        {t="Nov";}
        else if(s.equals("12"))
        {t="Dec";}
        //System.out.println(t);
        return t;
    }

    public void NapuniVreme(String gradOd, String vreme) throws SQLException {
        vreme_drop.removeAllItems();
        String dest = (String) destinacija_drop.getSelectedItem();
        if(DoKade.equals(dest)){vreme_drop.addItem(vreme);}
        ResultSet rs = s.ZemiVreme(gradOd, dest);
        while (rs.next()) {
        vreme_drop.addItem(rs.getString(1));
        }
    }
     


    public void postaviemail(String s) {
        email = s;
    }

    public String vratiemail() {
        return email;
    }

    public void postaviBiletID(String s) {
        biletID_label.setText(s);
        biletID = s;
    }

    public void postaviNajaven(String s) {
        najaven_izmeni.setText(s);
    }

    public void postavipodatoci() {
        try {
            String p = biletID;
            String tip;

            ResultSet rs = s.BiletiKorisnici(p);

            if (rs.next()) {

                ime_i_prezime.setText(rs.getString("IME") + " " + rs.getString("PREZIME"));
                tip = rs.getString("TIP_BILET").toString();
                //System.out.println(tip);
                if (tip.equals("E")) {
                    radio_E.setSelected(true);
                } else if (tip.equals("P")) {
                    radio_P.setSelected(true);
                } else {
                    radio_M.setSelected(true);
                }
                grad_trgnuva.setText(rs.getString("OD"));
                dest_label.setText(rs.getString("DESTINACIJA"));
                poagja_label.setText(rs.getString("POAGJA"));
            
                Datum=rs.getString("DATUM_NA_POAGJANJE");
                
                Datum=(String) Datum.subSequence(0, 10);
                String[]temp=Datum.split("-");
                Datum=temp[2]+"/"+smeniMesec(temp[1].toString())+"/"+temp[0];
                
                                
                pole_datum_na_trg.setText(Datum);
                OdKade = rs.getString("OD").toString();
                DoKade = rs.getString("DESTINACIJA").toString();
                KoeVreme = rs.getString("POAGJA").toString();
               
                try {
                    NapuniDropdown(OdKade);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                    Logger.getLogger(IzmenaBilet.class.getName()).log(Level.SEVERE, null, ex);
                }
               
            } else {
                JOptionPane.showMessageDialog(null, "Нема таков ID");
                NovBilet n = new NovBilet();
                n.setVisible(true);
                n.najav(najaven_izmeni.getText().toString());
                n.postaviemail(email);
                this.setVisible(false);
            }


        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        napravi_izmeni = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pole_datum_na_trg = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        biletID_label = new javax.swing.JLabel();
        destinacija = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        najaven_izmeni = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        ime_i_prezime = new javax.swing.JLabel();
        radio_E = new javax.swing.JRadioButton();
        radio_M = new javax.swing.JRadioButton();
        radio_P = new javax.swing.JRadioButton();
        jLabel3 = new javax.swing.JLabel();
        grad_trgnuva = new javax.swing.JLabel();
        destinacija_drop = new javax.swing.JComboBox();
        vreme_drop = new javax.swing.JComboBox();
        dest_label = new javax.swing.JLabel();
        poagja_label = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        otkazi = new javax.swing.JButton();
        datum_denesen = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Измена на билет");
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                formMouseClicked(evt);
            }
        });

        napravi_izmeni.setText("Направи измени");
        napravi_izmeni.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                napravi_izmeniMouseClicked(evt);
            }
        });
        napravi_izmeni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                napravi_izmeniActionPerformed(evt);
            }
        });

        jLabel4.setText("Тип на билет");

        jLabel7.setText("Датум на поаѓање");

        pole_datum_na_trg.setText("  ");
        pole_datum_na_trg.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pole_datum_na_trgActionPerformed(evt);
            }
        });

        jLabel8.setText("Билет ID:");

        biletID_label.setText("0");

        destinacija.setText("Дестинација");

        jLabel11.setText("Време на поаѓање");

        jLabel1.setText("Најавен:");

        najaven_izmeni.setText("Ненајавен");

        jLabel2.setText("Име и презиме:");

        ime_i_prezime.setText("име и презиме");

        buttonGroup1.add(radio_E);
        radio_E.setText("Во еден правец");
        radio_E.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                radio_EActionPerformed(evt);
            }
        });

        buttonGroup1.add(radio_M);
        radio_M.setText("Месечна");

        buttonGroup1.add(radio_P);
        radio_P.setText("Повратна");

        jLabel3.setText("Тргнува од");

        grad_trgnuva.setText("тргнува");

        destinacija_drop.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        destinacija_drop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                destinacija_dropActionPerformed(evt);
            }
        });

        vreme_drop.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        vreme_drop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                vreme_dropActionPerformed(evt);
            }
        });

        dest_label.setText("дестинација");

        poagja_label.setText("време");

        jLabel5.setText("Нова дестинација");

        jLabel6.setText("Ново време за поаѓање");

        otkazi.setText("Откажи");
        otkazi.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                otkaziMouseClicked(evt);
            }
        });
        otkazi.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                otkaziActionPerformed(evt);
            }
        });

        datum_denesen.setText("Внеси денешен датум");
        datum_denesen.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                datum_denesenMouseClicked(evt);
            }
        });
        datum_denesen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                datum_denesenActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(23, 23, 23)
                                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                            .addComponent(destinacija)
                                            .addComponent(jLabel3)
                                            .addComponent(jLabel2)
                                            .addComponent(jLabel8)))
                                    .addComponent(jLabel11))
                                .addGap(47, 47, 47)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(biletID_label, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(dest_label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(ime_i_prezime, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 124, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                        .addComponent(poagja_label, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(grad_trgnuva, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(radio_E)
                            .addComponent(radio_P)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addGap(65, 65, 65)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jLabel5)
                                    .addComponent(jLabel6))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(destinacija_drop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(vreme_drop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(radio_M)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel7)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(pole_datum_na_trg, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(datum_denesen))))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(najaven_izmeni, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(otkazi)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(napravi_izmeni)
                        .addGap(17, 17, 17))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(biletID_label))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(ime_i_prezime))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(grad_trgnuva))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(destinacija)
                    .addComponent(dest_label))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(poagja_label))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radio_E)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radio_P)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(radio_M))
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(destinacija_drop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel5))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(38, 38, 38)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(vreme_drop, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel6))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(pole_datum_na_trg, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(6, 6, 6)
                .addComponent(datum_denesen)
                .addGap(29, 29, 29)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(otkazi)
                    .addComponent(napravi_izmeni)
                    .addComponent(jLabel1)
                    .addComponent(najaven_izmeni))
                .addContainerGap(14, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void napravi_izmeniMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_napravi_izmeniMouseClicked
        try {

            String tip;
            int cena;
            if (radio_E.isSelected()) {
                tip = "E";
                cena = 700;
                //System.out.println(tip);
            } else if (radio_P.isSelected()) {
                tip = "P";
                cena = 900;
                //System.out.println(tip);
            } else {
                tip = "M";
                cena = 3000;
                //System.out.println(tip);
            }
            s.PromeniTip(tip, biletID_label.getText().toString(), cena);
            s.PromeniDatum(Datum, biletID_label.getText().toString());
            String VozR = "";
            try {
                ResultSet rs1 = s.VozenRedID(grad_trgnuva.getText().toString(), destinacija_drop.getSelectedItem().toString(), vreme_drop.getSelectedItem().toString());
                if (rs1.next()) {
                    VozR += rs1.getString("VOZENRED_ID").toString();
                } else {
                    //System.out.println(rs1.getString("VOZENRED_ID"));
                    JOptionPane.showMessageDialog(null, "Не постои таков запис во возниот ред");
                }
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(null, ex);
            }

            s.PromeniVozRedID(VozR, biletID_label.getText().toString());

            NovBilet n = new NovBilet();
           // n.setVisible(true);
            n.najav(najaven_izmeni.getText().toString());
            n.postaviemail(email);
            this.dispose();

        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_napravi_izmeniMouseClicked

    private void napravi_izmeniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_napravi_izmeniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_napravi_izmeniActionPerformed

    private void radio_EActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_radio_EActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_radio_EActionPerformed

    private void vreme_dropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_vreme_dropActionPerformed
        

    }//GEN-LAST:event_vreme_dropActionPerformed

    private void destinacija_dropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_destinacija_dropActionPerformed
       try {
            // TODO add your handling code here:
            NapuniVreme(grad_trgnuva.getText().toString(), KoeVreme);
        } catch (SQLException ex) {
            Logger.getLogger(IzmenaBilet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_destinacija_dropActionPerformed

    private void pole_datum_na_trgActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pole_datum_na_trgActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pole_datum_na_trgActionPerformed

    private void otkaziMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_otkaziMouseClicked
        try {
            NovBilet n = new NovBilet();
         //   n.setVisible(true);
            n.najav(najaven_izmeni.getText().toString());
            n.postaviemail(email);
            this.dispose();
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_otkaziMouseClicked

    private void otkaziActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_otkaziActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_otkaziActionPerformed

    private void datum_denesenMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_datum_denesenMouseClicked
           
       //Внеси датум
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String datum = (String) dateFormat.format(date);
            Datum=datum; 
            dateFormat = new SimpleDateFormat("dd/MMM/yyyy");
            datum = (String) dateFormat.format(date);
            pole_datum_na_trg.setText(datum);       
    }//GEN-LAST:event_datum_denesenMouseClicked

    private void datum_denesenActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_datum_denesenActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_datum_denesenActionPerformed

    private void formMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_formMouseClicked
            
    }//GEN-LAST:event_formMouseClicked

    /**
     * @param args the command line arguments
     */
    
   
 
    String biletID;
    String email;
    String OdKade;
    String DoKade;
    String KoeVreme;
    String Datum;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel biletID_label;
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton datum_denesen;
    private javax.swing.JLabel dest_label;
    private javax.swing.JLabel destinacija;
    private javax.swing.JComboBox destinacija_drop;
    private javax.swing.JLabel grad_trgnuva;
    private javax.swing.JLabel ime_i_prezime;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel najaven_izmeni;
    private javax.swing.JButton napravi_izmeni;
    private javax.swing.JButton otkazi;
    private javax.swing.JLabel poagja_label;
    private javax.swing.JTextField pole_datum_na_trg;
    private javax.swing.JRadioButton radio_E;
    private javax.swing.JRadioButton radio_M;
    private javax.swing.JRadioButton radio_P;
    private javax.swing.JComboBox vreme_drop;
    // End of variables declaration//GEN-END:variables
}
