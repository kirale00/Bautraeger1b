package controller;

import business.kunde.Kunde;
import java.util.ArrayList;

public class ProjektController {
    private DatabaseHelper DbConnection; //todo: das wir pro Controller instanz nur eine MySQL Verbindung brauchen

    public ProjektController() {
        this.DbConnection = new DatabaseHelper();
    }

    //CREATE: Kunde/Projekt erstellen:
    public void create(Kunde kundendaten) {
        //todo: Sollen wir hier das ganze im Controller oder im KundeModel(?)
    }

    //READ: Spezifiziertes Kunde/Projekt zurückgeben:
    public Kunde read(String email) {
        //todo: Nehmen wir Email als Primary Key oder gibt es eine Kundennummer(?)
        return null;
    }

    //UPDATE: Spezifischen Kunden/Projekt updaten:
    public void update(String email, Kunde kundendaten) {
        //todo: zB this.DbConnection... SQL irgendwas halt updaten...
    }

    //DELETE: Kunde/Projekt löschen:
    public void delete(String email) {
        //todo
    }

    //INDEX: Alle Kunden/Projekte zurückgeben:
    public ArrayList<Kunde> index() {
        return new ArrayList<Kunde>();
    }
}
