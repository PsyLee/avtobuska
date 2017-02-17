/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package avtobuska;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class AvtobuskaMetodi {

    String baza_DRIVER = "oracle.jdbc.driver.OracleDriver";
    String baza_STRING = "jdbc:oracle:thin:@localhost:1521:";
    String baza_USERNAME = "DB_2012_ZIM_030";
    String baza_PASSWORD = "DB2012!!!";
    Connection con;
    Statement st;

    public AvtobuskaMetodi() {
        try {
            Class.forName(baza_DRIVER).newInstance();
            con = DriverManager.getConnection(baza_STRING, baza_USERNAME, baza_PASSWORD);
            st = con.createStatement();
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException e) {
            System.out.println(e);
        }
    }

    public ResultSet ListajSve() throws SQLException {
        String sql = ""
                + "SELECT * "
                + "FROM   (SELECT k.ime, "
                + "               k.prezime, "
                + "               kb.bilet_id             AS Bilet_br, "
                + "               kb.tip_bilet, "
                + "               kb.peron, "
                + "               kb.sediste, "
                + "               kb.cena, "
                + "               l.grad                  AS OD, "
                + "               vr.vreme_na_poagjanje   AS poagja, "
                + "               vr.vreme_na_pristiganje AS pristiga, "
                + "               vr.destinacija "
                + "        FROM   klienti k, "
                + "               kupuva_bileti kb, "
                + "               rel_izdava, "
                + "               lokacii l, "
                + "               rel_za, "
                + "               vozni_redovi vr "
                + "        WHERE  k.id = kb.klient_id "
                + "               AND rel_izdava.klient_id = k.id "
                + "               AND rel_izdava.bilet_id = kb.bilet_id "
                + "               AND l.id = rel_izdava.id_lokacija "
                + "               AND rel_za.klient_id = k.id "
                + "               AND rel_za.bilet_id = kb.bilet_id "
                + "               AND vr.id = rel_za.vr_id "
                + "        ORDER  BY bilet_br DESC) ";

        return st.executeQuery(sql);
    }

    public ResultSet Login(String s) throws SQLException {
        String sql = "select IME,PREZIME,MAIL,USERNAME,PASSWORD,ADMIN from LOGIN_VRABOTENI where USERNAME = '" + s + "'";
        return st.executeQuery(sql);
    }

    public ResultSet PuniDropdown() throws SQLException {
        String sql = "select distinct Grad from LOKACII";
        return st.executeQuery(sql);
    }
    
    public ResultSet PuniGarazi() throws SQLException {
        String sql = "select * from servisi_gradovi";
        return st.executeQuery(sql);
    }
    
    public ResultSet ZemiIDodGarazi(String GRAD) throws SQLException {
        String sql = "select SERVIS_ID from servisi_gradovi where GRAD = '"+GRAD+"'";
        return st.executeQuery(sql);
    }

    public ResultSet PuniGradovi(String s) throws SQLException {
        String sql = "select distinct DESTINACIJA from NAJDIVOZENRED where GRAD = '" + s + "'";
        return st.executeQuery(sql);
    }

    public ResultSet PuniVreme(String gradOd, String gradDo, String vreme) throws SQLException {
        String sql = "select VREME_NA_POAGJANJE from NAJDIVOZENRED where GRAD = '" + gradOd + "' and DESTINACIJA='" + gradDo + "' and VREME_NA_POAGJANJE<>'" + vreme + "'";
        return st.executeQuery(sql);
    }

    public ResultSet ZaVozenRed(String gradOd, String gradDo) throws SQLException {
        String sql = "select VREME_NA_POAGJANJE,VOZENRED_ID from NAJDIVOZENRED where GRAD = '" + gradOd + "' and DESTINACIJA='" + gradDo + "'";
        return st.executeQuery(sql);
    }

    public ResultSet ZemiVreme(String od, String dest) throws SQLException {
        String sql = "select VREME_NA_POAGJANJE  from VOZNI_REDOVI "
                + "where DESTINACIJA = '" + dest + "'and ID in (select VOZNI_REDOVI_ID from REL_OD_DO where LOKACII_ID = (select ID from LOKACII "
                + "where GRAD = '" + od + "'))";
        return st.executeQuery(sql);
    }
// ID na posledniot vlesen klient

    public ResultSet LastKlientID() throws SQLException {
        String sql = "select max(ID) from KLIENTI";
        return st.executeQuery(sql);
    }

    public ResultSet LastBiletID() throws SQLException {
        String sql = "select max(BILET_ID) from KUPUVA_BILETI";
        return st.executeQuery(sql);
    }

    public void VoKlienti(String ime, String prezime, String mail) throws SQLException {
        String sql = String.format("Insert into KLIENTI (IME,PREZIME,MAIL) values ('%s','%s','%s')", ime, prezime, mail);
        st.executeUpdate(sql);
    }

    public void VoKupi_Bilet(char tip, int klid, int peron, int sediste, int cena, String datum) throws SQLException {
        String sql = "Insert into KUPUVA_BILETI (TIP_BILET,KLIENT_ID,PERON,SEDISTE,CENA,DATUM_NA_POAGJANJE) values ('" + tip + "','" + klid + "','" + peron + "','" + sediste + "','" + cena + "',to_date('" + datum + "','yyyy/mm/dd'))";
        st.executeUpdate(sql);
    }

    public void VoRelProdava(int bilid, int klid, String mail) throws SQLException {
        String sql = "Insert into REL_PRODAVA (BILET_ID,KLIENT_ID,VRABOTEN_MAIL) values ('" + bilid + "','" + klid + "','" + mail + "')";
        st.executeUpdate(sql);
    }

    public ResultSet GradID(String grad) throws SQLException {
        String sql = "Select ID from Lokacii where GRAD = '" + grad + "'";
        return st.executeQuery(sql);
    }

    public void VoRelIzdava(int lokID, int klId, int bilID) throws SQLException {
        String sql = "insert into REL_IZDAVA (ID_LOKACIJA,KLIENT_ID,BILET_ID) values ('" + lokID + "','" + klId + "','" + bilID + "')";
        st.executeUpdate(sql);
    }

    public void PromeniTip(String tip, String B_ID, int cena) throws SQLException {
        String sql = "UPDATE KUPUVA_BILETI SET TIP_BILET='" + tip + "', CENA='" + cena + "' WHERE BILET_ID='" + B_ID + "'";
        st.executeUpdate(sql);
    }

    public void PromeniVozRedID(String vozenRed_ID, String bilet_ID) throws SQLException {
        String sql = "UPDATE REL_ZA SET VR_ID=" + vozenRed_ID + " WHERE BILET_ID=" + bilet_ID;
        st.executeUpdate(sql);
    }

    public void PromeniDatum(String data, String bilet_ID) throws SQLException {
        String sql = "UPDATE KUPUVA_BILETI SET DATUM_NA_POAGJANJE=to_date('" + data + "','dd/mm/yyyy') WHERE BILET_ID=" + bilet_ID + "";
        st.executeUpdate(sql);
    }

    public ResultSet VozenRedID(String odKade, String doKade, String voKolku) throws SQLException {
        String sql = "select * from NAJDIVOZENRED where GRAD = '" + odKade + "' and DESTINACIJA='" + doKade + "' and VREME_NA_POAGJANJE ='" + voKolku + "'";
        return st.executeQuery(sql);
    }

    public ResultSet VozenRedID(String grad, String poagja) throws SQLException {
        String sql = "Select ID from VOZNI_REDOVI where DESTINACIJA = '" + grad + "' and VREME_NA_POAGJANJE = '" + poagja + "'";
        return st.executeQuery(sql);
    }

    public void VoRelIZa(int klId, int bilID, int vozred) throws SQLException {
        String sql = "insert into REL_ZA (KLIENT_ID,BILET_ID,VR_ID) values ('" + klId + "','" + bilID + "','" + vozred + "')";
        st.executeUpdate(sql);
    }
    // mail na klienti

    public ResultSet KlientiMail() throws SQLException {
        String sql = "Select MAIL,ID from KLIENTI";
        return st.executeQuery(sql);
    }

    public void BrisiBilet(int BiletID) throws SQLException {
        String sql = "Delete from REL_ZA  where BILET_ID = " + BiletID + "";
        st.executeUpdate(sql);

        String sql1 = "Delete from REL_IZDAVA  where BILET_ID = " + BiletID + "";
        st.executeUpdate(sql1);

        String sql2 = "Delete from REL_PRODAVA  where BILET_ID = " + BiletID + "";
        st.executeUpdate(sql2);

        String sql3 = "Delete from KUPUVA_BILETI where BILET_ID = " + BiletID + "";
        st.executeUpdate(sql3);
    }

    public ResultSet BiletiKorisnici(String s) throws SQLException {
        String sql = "select * from BILETI_KORISNICI where BILET_BR = " + s;
        return st.executeQuery(sql);
    }

    public ResultSet Zarabotka() throws SQLException {
        String sql = "select ZARABOTKA from DNEVNA_ZARABOTKA";
        return st.executeQuery(sql);
    }

    public ResultSet DnevnaZarabotka(String s) throws SQLException {
        String sql = "select sum(CENA) as ZARABOTKA from KUPUVA_BILETI where DATUM_NA_POAGJANJE=to_date('" + s + "','dd/mm/yyyy')";

        return st.executeQuery(sql);
    }

    public ResultSet IzdadeniBiletiPoGradovi() throws SQLException {
        String sql = "select GRAD, BROJ_NA_BILETI from IZDADENI_PO_GRADOVI";
        return st.executeQuery(sql);
    }
    //оние кои имаат пристап до апликацијата

    public ResultSet ListajPristapVraboteni() throws SQLException {
        String sql = "select * from LOGIN";
        return st.executeQuery(sql);
    }

    public ResultSet ListajKlientiPromet() throws SQLException {
        String sql = "select * from KLIENT_PROMET";
        return st.executeQuery(sql);
    }
    //select * from REL_PROVERKA
    public ResultSet ListajProverka() throws SQLException {
        String sql = "select * from REL_PROVERKA";
        return st.executeQuery(sql);
    }
    
    public ResultSet ListajLokacii() throws SQLException {
        String sql = "select * from LOKACII";
        return st.executeQuery(sql);
    }
    
    public ResultSet ListajDelovi() throws SQLException {
        String sql = "select * from POGLED";
        return st.executeQuery(sql);
    }
    
    public ResultSet ListajVozenRed() throws SQLException {
        String sql = "select * from VOZENRED_OD_DO ";
        return st.executeQuery(sql);
    }
    
    public ResultSet ListajPosledniPopravki() throws SQLException {
        String sql = "select * from POSLEDNI_POPRAVKI";
        return st.executeQuery(sql);
    }

    public ResultSet ListajVozaciPosledno() throws SQLException {
        String sql = "select * from SEKOJ_SOFER_POSLEDNO";
        return st.executeQuery(sql);
    }

    public ResultSet SelektirajGrad(String s) throws SQLException {
        String sql = "select * from  IZDADENI_PO_GRADOVI where GRAD='" + s + "'";
        return st.executeQuery(sql);
    }

    //листање на сите вработени
    public ResultSet ListajVraboteni() throws SQLException {
        String sql = "select * from LISTAJ_VRABOTENI ";
        return st.executeQuery(sql);
    }

    public ResultSet LoginVraboteni() throws SQLException {
        String sql = "select IME, PREZIME, MAIL, USERNAME, PASSWORD, ADMIN from LOGIN_VRABOTENI";
        return st.executeQuery(sql);
    }

    public void PromeniLozinka(String mail, String pass) throws SQLException {
        String sql = "UPDATE LOGIN SET PASSWORD='" + pass + "' WHERE USERNAME='" + mail + "'";

        st.executeUpdate(sql);
    }

    public void VoLogin(String USERNAME, String PASSWORD, String ADMIN) throws SQLException {
        String sql = "";
        //"insert into LOGIN (ID_LOKACIJA,KLIENT_ID,BILET_ID) values ('" + lokID + "','" + klId + "','" + bilID + "')"
        st.executeUpdate(sql);
    }

    public ResultSet DeloviListaj() throws SQLException {
        String sql = "select SER_NO, KOLICINA, IME, SERVISI_ID from DELOVI";
        return st.executeQuery(sql);
    }

    public void BrisiDelovi(int DeloviID) throws SQLException {
        String sql = "Delete from REL_SODRZI  where SER_NO = " + DeloviID;
        st.executeUpdate(sql);

        String sql1 = "Delete from DELOVI  where SER_NO = " + DeloviID;
        st.executeUpdate(sql1);
    }

    public ResultSet RelProverkaListaj() throws SQLException {
        String sql = "select VOZ_REG, DATUM, OPIS, SERVIS_ID, RED_BR from REL_PROVERKA";
        return st.executeQuery(sql);
    }

    public void RelProverkaBrisi(String registracija) throws SQLException {
        String sql = "Delete from REL_PROVERKA  where VOZ_REG = '" + registracija + "'";
        st.executeUpdate(sql);
    }

    public void BrisiVozilo(String reg) throws SQLException {
        String sql1 = "Delete from REL_VOZI  where VOZILO_REGISTRACIJA = '" + reg + "'";
        st.executeUpdate(sql1);

        String sql2 = "Delete from REL_PROVERKA  where VOZ_REG = '" + reg + "'";
        st.executeUpdate(sql2);

        String sql3 = "Delete from REL_ZA2  where REGISTRACIJA_VOZILO = '" + reg + "'";
        st.executeUpdate(sql3);

        String sql4 = "Delete from REL_IMA2  where VOZILO_REGISTRACIJA = '" + reg + "'";
        st.executeUpdate(sql4);

        String sql = "Delete from VOZILA  where REGISTRACIJA = '" + reg + "'";
        st.executeUpdate(sql);
    }

    public void BrisiVraboten(String vrab) throws SQLException {
        String sql1 = "Delete from REL_PRODAVA  where MAIL = '" + vrab + "'";
        st.executeUpdate(sql1);

        String sql2 = "Delete from REL_VOZI  where MAIL = '" + vrab + "'";
        st.executeUpdate(sql2);

        String sql3 = "Delete from REL_RABOTI  where MAIL = '" + vrab + "'";
        st.executeUpdate(sql3);

        String sql = "Delete from VRABOTEN  where MAIL = '" + vrab + "'";
        st.executeUpdate(sql);
    }
    
    public ResultSet ZemiKolicina(String SER_NO) throws SQLException {
        String sql = "select KOLICINA from DELOVI where SER_NO = '"+SER_NO+"'";
        return st.executeQuery(sql);
    }
    
    public void IzmeniKolicina(String KOLICINA, String SER_NO) throws SQLException {
        String sql2 = "UPDATE DELOVI SET KOLICINA='" + KOLICINA + "' WHERE SER_NO='" + SER_NO + "'";
        st.executeUpdate(sql2);
    }
    
     public void BrisiVozenRed(String vozredID) throws SQLException {

        String sql2 = "Delete from REL_OD_DO  where VOZNI_REDOVI_ID = '" + vozredID + "'";
        st.executeUpdate(sql2);

        String sql3 = "Delete from REL_ZA2  where VOZNI_REDOVI_ID = '" + vozredID + "'";
        st.executeUpdate(sql3);
        
        String sql1 = "Delete from VOZNI_REDOVI  where ID = '" + vozredID + "'";
        st.executeUpdate(sql1);

    }

    public void BrisiLoginVraboteni(String vrab) throws SQLException {
        String sql = "Delete from LOGIN where MAIL = '" + vrab + "'";
        st.executeUpdate(sql);
    }

    public void DodadiVozilo(String registracija, int sedista, String marka, String gorivo, String servisno, int lokacijaID) throws SQLException {
        String sql = "Insert into Vozila (REGISTRACIJA,BROJ_NA_SEDISTA,MARKA,GORIVO,TIP_SERV_VOZ) values ('" + registracija + "'," + sedista + ", '" + marka + "','" + gorivo + "','" + servisno + "')";
        st.executeUpdate(sql);

        String sql2 = "Insert into REL_IMA2 (VOZILO_REGISTRACIJA,LOKACIJA_ID) values ('" + registracija + "'," + lokacijaID + ")";
        st.executeUpdate(sql2);
    }
    
 
    public ResultSet PosledenVozenRed() throws SQLException {
        String sql = "select * from  POSLEDEN_VOZENRED";
        return st.executeQuery(sql);
    }
    
    public void DodadiVozenRed(String VREME_NA_POAGJANJE, String VREME_NA_PRISTIGANJE, String DESTINACIJA) throws SQLException {
        String sql = "Insert into VOZNI_REDOVI (VREME_NA_POAGJANJE, VREME_NA_PRISTIGANJE, DESTINACIJA) values ('" + VREME_NA_POAGJANJE + "', '" + VREME_NA_PRISTIGANJE + "' , '" + DESTINACIJA + "')";
        st.executeUpdate(sql);
    }
    
    public void Dodadi_OD_DO_RELZA2(String Registracija, String VozRedID, int LokacijaID) throws SQLException {
      
        String sql2 = "Insert into REL_ZA2 (VOZNI_REDOVI_ID, REGISTRACIJA_VOZILO) values ('" + VozRedID + "','" + Registracija + "')";
        st.executeUpdate(sql2);
        
        String sql3 = "Insert into REL_OD_DO (LOKACII_ID, VOZNI_REDOVI_ID) values ('" + LokacijaID + "','" + VozRedID + "')";
        st.executeUpdate(sql3);
    }
    
    
    public void DodadiLokacija(String ime, String grad, String ulica) throws SQLException {
      
        String sql2 = "Insert into LOKACII (IME, ULICA, GRAD) values ('" + ime + "','" + ulica + "','"+ grad +"')";
        st.executeUpdate(sql2);
        
    }
    
    public void DodadiProverska(String registracija, String datum, String opis, int servisID) throws SQLException {
      
        String sql2 = "Insert into REL_PROVERKA (VOZ_REG, DATUM, OPIS, SERVIS_ID) values ('" + registracija + "',to_date('" + datum + "','dd/mm/yyyy'),'" + opis +"','"+servisID+ "')";
        st.executeUpdate(sql2);
        
    }
    
    public void DodadiDelovi(String KOLICINA, String IME, int SERVISI_ID) throws SQLException {
      
        String sql2 = "Insert into DELOVI (SER_NO, KOLICINA, IME, SERVISI_ID) values (1,'" + KOLICINA + "','"+IME+"','" + SERVISI_ID + "')";
        st.executeUpdate(sql2);
        
    }
    
    public void PolniRelZa2(int vozenredID, String registracija) throws SQLException {
        String sql = "Insert into REL_ZA2 (VOZNI_REDOVI_ID,REGISTRACIJA_VOZILO) values (" + vozenredID + ", '" + registracija + "')";
        st.executeUpdate(sql);
    }
    // izbira site gradovi koi imaat garaza

    public ResultSet GradSoGaraza() throws SQLException {
        String sql = "select grad from LOKACII where GARAZA_KAPAC <>0";
        return st.executeQuery(sql);
    }
    //site soferi i koj kolku avtobusa vozi

    public ResultSet SoferiKolicina() throws SQLException {

        String sql = "select MAIL,concat(Ime,concat(' ',Prezime)) as ime from vraboteni where TITULA = 'Шофер'";
        return st.executeQuery(sql);

    }

    public void relacijaVozila(String registracija, String mail) throws SQLException {
        String sql = "Insert into REL_VOZI (VOZILO_REGISTRACIJA,VRABOTEN_MAIL) values ('" + registracija + "','" + mail + "')";
        //System.out.println(sql);
        st.executeUpdate(sql);
    }

    // mail od sofer spored ime i prezime
    public ResultSet mailodSofer(String ime) throws SQLException {
        String sql = "select a.mail from ("
                + "select MAIL,concat(Ime,concat(' ',Prezime)) as ime from vraboteni where TITULA = 'Шофер') a "
                + "where a.ime = '" + ime + "' ";
        return st.executeQuery(sql);
    }

    public ResultSet napolniAvtobusi() throws SQLException {
        String sql = "select REGISTRACIJA from VOZILA where TIP_SERV_VOZ is NULL";
        return st.executeQuery(sql);
    }

    public ResultSet napolniServisni() throws SQLException {
        String sql = "select REGISTRACIJA from VOZILA where TIP_SERV_VOZ is not NULL";
        return st.executeQuery(sql);
    }

    //dodavanje na vraboten
    public void DodadiVraboten(String mail, String ime, String prezime, int plata, String vozacka, String titula, String diploma) throws SQLException {
        String sql = "Insert into VRABOTENI (MAIL,IME,PREZIME,PLATA,KATEGORIJA_VOZ_DOZVOLA,TITULA,DIPLOMA)"
                + " values ('" + mail + "','" + ime + "','" + prezime + "'," + plata + ",'" + vozacka + "','" + titula + "','" + diploma + "')";
        st.executeUpdate(sql);
    }

    public void logiranje(String user, String pass, char admin) throws SQLException {
        String sql = "Insert into LOGIN (USERNAME,PASSWORD,ADMIN)"
                + " values ('" + user + "','" + pass + "','" + admin + "')";
        //System.out.println(sql);
        st.executeUpdate(sql);
    }

    public void REL_RABOTI(String mail, int servisID) throws SQLException {
        String sql = "Insert into REL_RABOTI (VRAB_MAIL,SERV_ID)"
                + " values ('" + mail + "'," + servisID + ")";
        // System.out.println(sql);
        st.executeUpdate(sql);
    }

    public ResultSet servisi() throws SQLException {
        String sql = "select LOKACII.GRAD as Grad,SERVISI.ID as ID "
                + "from SERVISI,REL_IMA,LOKACII "
                + "where LOKACII.ID=REL_IMA.LOKACII_ID and REL_IMA.SERVISI_ID=SERVISI.ID";
        return st.executeQuery(sql);
    } 
    // brisi vraboten na salter
    public void brisiSalterILISef(String mail) throws SQLException
    {
        String sql = "delete from LOGIN where USERNAME = '"+mail+"' ";
        st.executeUpdate(sql);
        String sql2 = "delete from VRABOTENI where MAIL = '"+mail+"' ";
        st.executeUpdate(sql2);            
    }
    
    public void brisiSofer(String mail) throws SQLException
    {
        String sql = "delete from REL_VOZI where VRABOTEN_MAIL = '"+mail+"' ";
        st.executeUpdate(sql);
        String sql2 = "delete from VRABOTENI where MAIL = '"+mail+"' ";
        st.executeUpdate(sql2);            
    }
    
    public void brisiServiser(String mail) throws SQLException
    {
        String sql = "delete from REL_VOZI where VRABOTEN_MAIL = '"+mail+"' ";
        st.executeUpdate(sql);
        String sql1 = "delete from REL_RABOTI where VRAB_MAIL = '"+mail+"' ";
        st.executeUpdate(sql1);       
        String sql2 = "delete from VRABOTENI where MAIL = '"+mail+"' ";
        st.executeUpdate(sql2);            
    }    
    
    public ResultSet vratiTitula(String mail) throws SQLException {
        String sql = "select titula from vraboteni where mail = '"+mail+"'";
        return st.executeQuery(sql);
    } 
    
    public ResultSet sitereg() throws SQLException {
        String sql = "select registracija from vozila";
        return st.executeQuery(sql);
    } 
    
}
