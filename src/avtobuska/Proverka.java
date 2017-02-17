package avtobuska;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Vector;
import javax.swing.DefaultListModel;
import javax.swing.DefaultListSelectionModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author psylee
 */

//SER_NO	KOLICINA	IME	SERVISI_ID
public class Proverka extends javax.swing.JFrame {

    AvtobuskaMetodi s = new AvtobuskaMetodi();
    public Proverka() throws SQLException {
        initComponents();
        NapuniOstanatite();
        napolniregistracii();
    }
    
    public void napolniregistracii() throws SQLException
    {
        jComboBox2.addItem("/");    
        ResultSet rs = s.sitereg();        
         while(rs.next())
         {
             jComboBox2.addItem(rs.getString(1));            
         }
         jComboBox2.removeItemAt(0);    
    }
    
    
    //VOZ_REG	DATUM	OPIS
    public void NapolniProverki() throws SQLException
    {
    Vector head = new Vector();
    Vector data = new Vector(1, 1);
    head.add("Регистрација");
        head.add("Датум");
        head.add("Опис");
        ResultSet rs = s.ListajProverka();
        data = new Vector(1, 1);
         while (rs.next()) {
            Vector row = new Vector();
            row.add(rs.getString("VOZ_REG"));
            row.add(rs.getString("DATUM"));
            row.add(rs.getString("OPIS"));
            data.add(row);
        }
        DefaultTableModel dtm = new DefaultTableModel(data, head) {
            // zabrana za edit na kolonite
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        TabelaDelovi.setModel(dtm);
    }

    public void NapuniOstanatite() throws SQLException {
        jComboBox1.addItem("/");
        ResultSet rs = s.PuniGarazi();
        while (rs.next()) {
            jComboBox1.addItem(rs.getString("GRAD"));
        }
        jComboBox1.removeItemAt(0);
    }
    
    
    

    
    
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        proverka = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jScrollPane2 = new javax.swing.JScrollPane();
        TabelaDelovi = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        opis = new javax.swing.JTextField();
        jComboBox2 = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        proverka.setText("Додади проверка");
        proverka.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proverkaActionPerformed(evt);
            }
        });

        jButton3.setText("Откажи");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel4.setText("Гаража");

        jComboBox1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox1ActionPerformed(evt);
            }
        });

        TabelaDelovi.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3"
            }
        ));
        jScrollPane2.setViewportView(TabelaDelovi);

        jLabel1.setText("Опис");

        jLabel2.setText("Регистрација");

        jComboBox2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox2ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(jLabel2)
                            .addComponent(jLabel1))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(opis, javax.swing.GroupLayout.PREFERRED_SIZE, 175, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jComboBox1, 0, 112, Short.MAX_VALUE)))
                        .addGap(45, 45, 45)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 467, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(20, 20, 20)
                        .addComponent(jButton3)
                        .addGap(88, 88, 88)
                        .addComponent(proverka)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 144, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel4)
                            .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel2)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel1)
                            .addComponent(opis, javax.swing.GroupLayout.DEFAULT_SIZE, 34, Short.MAX_VALUE))
                        .addGap(27, 27, 27)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton3)
                            .addComponent(proverka))
                        .addGap(52, 52, 52))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jComboBox1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox1ActionPerformed

        // TODO add your handling code here:}//GEN-LAST:event_jComboBox1ActionPerformed
    }
    private void proverkaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proverkaActionPerformed
        try {
   
            String regis = jComboBox2.getSelectedItem().toString();
            String i=opis.getText().toString();
            String grad = jComboBox1.getSelectedItem().toString();
            
            ResultSet rs = s.ZemiIDodGarazi(grad);
            rs.next();
            int ID=rs.getInt(1);
            DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String datum = (String) dateFormat.format(date);
            
            s.DodadiProverska(regis, datum, i, ID);
            opis.setText("");
            
            NapolniProverki();
                  
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        
        
    }//GEN-LAST:event_proverkaActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
          this.dispose();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jComboBox2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jComboBox2ActionPerformed

    
   
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TabelaDelovi;
    private javax.swing.JButton jButton3;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JComboBox jComboBox2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextField opis;
    private javax.swing.JButton proverka;
    // End of variables declaration//GEN-END:variables
}
