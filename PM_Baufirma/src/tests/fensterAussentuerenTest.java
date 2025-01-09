package tests;

//import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import business.sonderwunsch.Sonderwunsch;
import business.sonderwunsch.SonderwunschModel;


public class fensterAussentuerenTest {

    SonderwunschModel sWunsch;
    ArrayList<Sonderwunsch> swArrayList;
    List<Sonderwunsch> swArray;

    @BeforeEach                                         
    void setUp() {
        sWunsch = SonderwunschModel.getInstance();
        swArrayList = sWunsch.getSonderwuensche();
        swArray = swArrayList.subList(6, 15); // Sonderwünsche die zu fensterAussentueren gehören
    }

    @Test                                               
    @DisplayName("Singleton Pattern in SonderwunschModel sollte umgesetzt sein und korrekte Rückgabe liefern")   
    void singleton() {
        assertTrue(sWunsch != null);
    }

    @Test                                               
    @DisplayName("Das Sonderwunschmodel sollte mit Sonderwünschen befüllt sein")   
    void isNotEmpty() {
        assertFalse(swArray.isEmpty());
    }

    @Test                                               
    @DisplayName("Die Sonderwuensche zu den fensterAussentueren muessen alle einen Preis vermerkt haben!")   
    void habenPreise() {
        int anzahlOhnePreis = 0;
        for(Sonderwunsch s  : swArray) {
            if(s.getPreis() == 0) {
                anzahlOhnePreis++;
            }

        }
        assertEquals(anzahlOhnePreis, 0);
        
    }

    @Test                                               
    @DisplayName("Alle fenster aussentueren müssen einen Namen vermerkt haben")   
    void habenNamen() {
        for(Sonderwunsch s  : swArray) {
            assertNotEquals(s.getName(), "");
        }
        
    }

    @AfterEach                                         
    void tearDown() {
        sWunsch = null;
    }

    



    
}
