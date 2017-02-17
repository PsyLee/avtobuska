/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * klienti.java
 *
 * Created on Jan 17, 2013, 9:21:33 AM
 */
package avtobuska;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author psylee
 */
public class Mala_tabela extends javax.swing.JFrame {

    AvtobuskaMetodi s;
    public Mala_tabela() throws SQLException {
        initComponents();
        this.setLocation(200, 150);
         s = new AvtobuskaMetodi();
        //NapolniTabela();
    }

    
    public void NapolniTabela() throws SQLException
    {
    Vector head = new Vector();
    Vector data = new Vector(1, 1);
    head.add("Клиент");
        head.add("Купени билети");
        head.add("Промет");
        ResultSet rs = s.ListajKlientiPromet();
        data = new Vector(1, 1);
         while (rs.next()) {
            Vector row = new Vector();
            row.add(rs.getString("KLIENT"));
            row.add(rs.getString("KUPENI_BILETI"));
            row.add(rs.getString("PROMET"));
            data.add(row);
        }
        DefaultTableModel dtm = new DefaultTableModel(data, head) {
            // zabrana za edit na kolonite
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        TabelaKlienti.setModel(dtm);
    }
    
    
    
    public void NapolniPopravki() throws SQLException
    {
    Vector head = new Vector();
    Vector data = new Vector(1, 1);
    head.add("Регистрација");
        head.add("Последна проверка");
        head.add("Опис");
        ResultSet rs = s.ListajPosledniPopravki();
        data = new Vector(1, 1);
         while (rs.next()) {
            Vector row = new Vector();
            row.add(rs.getString("REGISTRACIJA"));
            row.add(rs.getString("POSLEDNA_PROVERKA"));
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
        TabelaKlienti.setModel(dtm);
    }
    
    public void NapolniVozaci() throws SQLException
    {
        
    Vector head = new Vector();
    Vector data = new Vector(1, 1);
    head.add("Име");
        head.add("Регистрација");
        head.add("Последно поаѓа во");
        ResultSet rs = s.ListajVozaciPosledno();
        data = new Vector(1, 1);
         while (rs.next()) {
            Vector row = new Vector();
            row.add(rs.getString("IME"));
            row.add(rs.getString("REGISTRACIJA"));
            row.add(rs.getString("POSLEDNO_TRGNUVA_VO"));
            data.add(row);
        }
        DefaultTableModel dtm = new DefaultTableModel(data, head) {
            // zabrana za edit na kolonite
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        TabelaKlienti.setModel(dtm);
    }
    
    
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        JPanel = new javax.swing.JPanel();
        ok = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        TabelaKlienti = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("Статистика");
        setResizable(false);

        ok.setText("во ред");
        ok.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okActionPerformed(evt);
            }
        });

        TabelaKlienti.setModel(new javax.swing.table.DefaultTableModel(
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
        jScrollPane1.setViewportView(TabelaKlienti);

        javax.swing.GroupLayout JPanelLayout = new javax.swing.GroupLayout(JPanel);
        JPanel.setLayout(JPanelLayout);
        JPanelLayout.setHorizontalGroup(
            JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(JPanelLayout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 480, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, JPanelLayout.createSequentialGroup()
                        .addComponent(ok)
                        .addGap(219, 219, 219))))
        );
        JPanelLayout.setVerticalGroup(
            JPanelLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(JPanelLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(ok))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(JPanel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(JPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okActionPerformed
        this.dispose();
    }//GEN-LAST:event_okActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel JPanel;
    private javax.swing.JTable TabelaKlienti;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton ok;
    // End of variables declaration//GEN-END:variables
}
