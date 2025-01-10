package tests;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import business.kunde.KundeModel;
import business.sonderwunsch.Sonderwunsch;
import business.sonderwunsch.SonderwunschModel;
//import gui.heizung.HeizungControl;

public class heizungenTest {

    SonderwunschModel sWunsch;

    @BeforeEach                                         
    void setUp() {
        sWunsch = SonderwunschModel.getInstance();
    }

    @Test                                               
    @DisplayName("Singleton Pattern in SonderwunschModel sollte umgesetzt sein und korrekte Rückgabe liefern")   
    void singleton() {
        assertTrue(sWunsch != null);
    }

    @Test                                               
    @DisplayName("Das Sonderwunschmodel sollte mit Sonderwünschen befüllt sein")   
    void isNotEmpty() {
        ArrayList<Sonderwunsch> swArray = sWunsch.getSonderwuensche();
        assertFalse(swArray.isEmpty());
    }

    @Test                                               
    @DisplayName("Alle Heizungs-Sonderwuensche müssen einen Preis vermerkt haben!")   
    void habenPreise() {
        ArrayList<Sonderwunsch> swArray = sWunsch.getSonderwuensche();
        int anzahlOhnePreis = 0;
        for(Sonderwunsch s  : swArray) {
            if(s.getPreis() == 0) {
                anzahlOhnePreis++;
            }

        }
        assertEquals(anzahlOhnePreis, 0);
        
    }

    @Test                                               
    @DisplayName("Alle Heizungs-Sonderwünsche müssen einen Namen vermerkt haben")   
    void habenNamen() {
        ArrayList<Sonderwunsch> swArray = sWunsch.getSonderwuensche();
        for(Sonderwunsch s  : swArray) {
            assertNotEquals(s.getName(), "");
        }
        
    }

    @AfterEach                                         
    void tearDown() {
        sWunsch = null;
    }

    



    
}
