package tests;

<<<<<<< HEAD
//import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
=======
import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
>>>>>>> main

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import business.kunde.KundeModel;
import business.sonderwunsch.Sonderwunsch;
import business.sonderwunsch.SonderwunschModel;
import gui.grundriss.GrundrissControl;

public class grundrissTest {

    


    SonderwunschModel sWunsch;
    ArrayList<Sonderwunsch> swArrayList;
    List<Sonderwunsch> swArray;

    @BeforeEach                                         
    void setUp() {
        sWunsch = SonderwunschModel.getInstance();
        swArrayList = sWunsch.getSonderwuensche();
        swArray = swArrayList.subList(0, 6); // Sonderwünsche die zu Grundrisse gehören
    }

    @Test                                               
    @DisplayName("Singleton Pattern in SonderwunschModel sollte umgesetzt sein und korrekte Rückgabe liefern")   
    void singleton() {
        assertTrue(swArray != null);
    }

    @Test                                               
    @DisplayName("Das Sonderwunschmodel sollte mit Sonderwünschen befüllt sein")   
    void isNotEmpty() {
        assertFalse(swArray.isEmpty());
    }

    @Test                                               
    @DisplayName("Nur eine Grundrissvariante darf einen Preis von 0 vermerkt haben")   
    void habenPreise() {

        int anzahlGrundrissvariantenOhnePreis = 0;
        for(Sonderwunsch s  : swArray) {
            if(s.getPreis() == 0) {
                anzahlGrundrissvariantenOhnePreis++;
            }

        }
        assertEquals(anzahlGrundrissvariantenOhnePreis, 1);
        
    }

    @Test                                               
    @DisplayName("Alle Grundrissvarianten müssen einen Namen vermerkt haben")   
    void habenNamen() {

        for(Sonderwunsch s  : swArray) {
<<<<<<< HEAD
            assertNotEquals(s.getBeschreibung(), "");
=======
            assertNotEquals(s.getName(), "");
>>>>>>> main
        }
        
    }

    @AfterEach                                         
    void tearDown() {
        swArray = null;
    }

    



    
}
