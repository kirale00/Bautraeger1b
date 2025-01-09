package tests;

import business.kunde.Kunde;
import business.kunde.KundeModel;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class kundenCrudTest {
    KundeModel kundeModel;

    @BeforeEach
    void setUp() {
        this.kundeModel = new KundeModel();
    }

    @Test                                               
    @DisplayName("Sollte alle vorhandenen Kundennummern von Kunden ausgeben:")
    void kundennummern() {
        assertTrue(kundeModel.getKundenNummern().size() > 0 && kundeModel.getKundenNummern().get(0) == 0);
    }

    @Test
    @DisplayName("Sollte einen Kunden zurückgeben.")
    void getKunde() {
        kundeModel.getKundeByNummer(4);
        assertEquals("1@1,1", kundeModel.kunde.getEmail());
    }

    @Test
    @DisplayName("Sollte einen Kunden ändern")
    void aendereKunde() throws Exception {
        kundeModel.getKundeByNummer(4);
        var vorname_vorher = kundeModel.kunde.getVorname();
        kundeModel.kunde.setVorname("Neu");
        kundeModel.aendereKunden(kundeModel.kunde, 4);
        kundeModel.getKundeByNummer(4);
        var vorname_nachher = kundeModel.kunde.getVorname();
        assertNotEquals(vorname_nachher, vorname_vorher);
        //resetten (wahrscheinlich sollte man dies in ne separate after each oder so packen aber ja tja egal)
        kundeModel.kunde.setVorname(vorname_vorher);
        kundeModel.aendereKunden(kundeModel.kunde, 4);
        assertEquals(kundeModel.kunde.getVorname(), vorname_vorher);
    }

    @Test
    @DisplayName("Sollte einen Kunden anlegen:")
    void anlegen() throws Exception {
        var before = kundeModel.getKundenNummern().size();
        Kunde kunde = new Kunde();
        kunde.setVorname("Test1");
        kunde.setNachname("TestNachname oder so");
        kunde.setEmail("test@test.test");
        kunde.setTelefonnummer("0123");
        kunde.setHausnummer(9);
        kundeModel.speichereKunden(kunde);
        assertEquals(before+1, kundeModel.getKundenNummern().size());
    }

    @Test
    @DisplayName("Sollte Kunde Loeschen:")
    void loeschen() throws Exception {
        var kunden = kundeModel.getKundenNummern();
        var before = kunden.size();
        kundeModel.loescheKunden(kunden.getLast());
        assertEquals(before - 1, kundeModel.getKundenNummern().size());
    }

    @AfterEach                                         
    void tearDown() {
        this.kundeModel = null;
    }

    



    
}
