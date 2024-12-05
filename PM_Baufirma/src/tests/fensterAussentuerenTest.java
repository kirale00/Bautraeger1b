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
import gui.grundriss.GrundrissControl;

public class fensterAussentuerenTest {

    SonderwunschModel sWunsch;

    @BeforeEach                                         
    void setUp() {
        
    }

    @Test                                               
    @DisplayName("Es sollte mindest ein Sonderwunsch zu Fenster / Aussentueren existieren")   
    void openTest() {
        assertTrue(true);
    }

    // TODO: Wie kann man auf die Hardcoded Sonderwünsche in der View zugreifen um sie zu testen?

    

    @AfterEach                                         
    void tearDown() {
        
    }

    



    
}
