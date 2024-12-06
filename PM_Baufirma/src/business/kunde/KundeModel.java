package business.kunde;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import controller.DatabaseHelper;
import javafx.collections.*;
  
/** 
 * Klasse, welche das Model des Grundfensters mit den Kundendaten enthaelt.
 */
public final class KundeModel {
	
	// enthaelt den aktuellen Kunden
	private Kunde kunde;
	
	/* enthaelt die Plannummern der Haeuser, diese muessen vielleicht noch
	   in eine andere Klasse verschoben werden */
	ObservableList<Integer> plannummern = 
	    FXCollections.observableArrayList(
		0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24);
	

	// enthaelt das einzige KundeModel-Objekt
	private static KundeModel kundeModel;
	
	// privater Konstruktor zur Realisierung des Singleton-Pattern
	private KundeModel(){
		super();
	}
	
	/**
	 *  Methode zum Erhalt des einzigen KundeModel-Objekts.
	 *  Das Singleton-Pattern wird realisiert.
	 *  @return KundeModel, welches das einzige Objekt dieses
	 *          Typs ist.
	 */
	public static KundeModel getInstance(){
		if(kundeModel == null){
			kundeModel = new KundeModel();
		}
		return kundeModel;	
	}
	
	/**
	 * gibt die Ueberschrift zum Grundfenster mit den Kundendaten heraus
	 * @return String, Ueberschrift zum Grundfenster mit den Kundendaten 
	 */
	public String getUeberschrift(){
		return "Verwaltung der Sonderwunschlisten";
	}
	
	/**
	 * gibt saemtliche Plannummern der Haeuser des Baugebiets heraus.
	 * @return ObservableList<Integer> , enthaelt saemtliche Plannummern der Haeuser
	 */
	public ObservableList<Integer> getPlannummern(){
		return this.plannummern; 
	}
		 	
	// ---- Datenbankzugriffe -------------------

	public ObservableList<Integer> getKundenNummern() {
        try {
			var connection = new DatabaseHelper().getConnection();
			Statement selectStatement = null;
            selectStatement = connection.createStatement();
			var sql = "SELECT (Kundennummer) from Kunde where Kundennummer != 0";
			var rs = selectStatement.executeQuery(sql);
			var resultArray = FXCollections.observableArrayList(0);
			while (rs.next()) {
				resultArray.add(rs.getInt("Kundennummer"));
			}
			System.out.println(resultArray);
			return (ObservableList<Integer>) resultArray;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
	}

	public void loescheKunden(int kundennummer) {
		try {
			var connection = new DatabaseHelper().getConnection();
			PreparedStatement deleteStatement = connection.prepareStatement("DELETE FROM Kunde WHERE Kundennummer = ?");
			deleteStatement.setInt(1, kundennummer);
			int rowsAffected = deleteStatement.executeUpdate();

			if (rowsAffected > 0) {
				System.out.println("Kunde mit Kundennummer " + kundennummer + " wurde erfolgreich gelöscht.");
			} else {
				System.out.println("Kein Kunde mit der Kundennummer " + kundennummer + " gefunden.");
			}
		} catch (SQLException e) {
			throw new RuntimeException("Fehler beim Löschen des Kunden: " + e.getMessage(), e);
		}
	}

	/**
	 * speichert ein Kunde-Objekt in die Datenbank
	 * @param kunde, Kunde-Objekt, welches zu speichern ist
	 * @throws SQLException, Fehler beim Speichern in die Datenbank
	 * @throws Exception, unbekannter Fehler
	 */
	public void speichereKunden(Kunde kunde)
	    throws SQLException, Exception{
		//System.out.println(kunde);
        var connection = new DatabaseHelper().getConnection();
		PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO Kunde (Vorname, Nachname, Email, Telefonnummer, Hausnummer) values (?,?,?,?,?)");
		insertStatement.setString(1, kunde.getVorname());
		insertStatement.setString(2, kunde.getNachname());
		insertStatement.setString(3, kunde.getEmail());
		insertStatement.setString(4, kunde.getTelefonnummer());
		insertStatement.setInt(5, kunde.getHausnummer());
		insertStatement.executeUpdate();
   	    this.kunde = kunde;
	}

	public void aendereKunden(Kunde kunde, Integer kundennummer)
			throws SQLException, Exception {
		var connection = new DatabaseHelper().getConnection();
		PreparedStatement updateStatement = connection.prepareStatement(
				"UPDATE Kunde SET Vorname = ?, Nachname = ?, Email = ?, Telefonnummer = ?, Hausnummer = ? WHERE Kundennummer = ?"
		);
		updateStatement.setString(1, kunde.getVorname());
		updateStatement.setString(2, kunde.getNachname());
		updateStatement.setString(3, kunde.getEmail());
		updateStatement.setString(4, kunde.getTelefonnummer());
		updateStatement.setInt(5, kunde.getHausnummer());
		updateStatement.setInt(6, kundennummer); // Kundennummer als Kriterium für die Aktualisierung
		int rowsUpdated = updateStatement.executeUpdate();

		if (rowsUpdated > 0) {
			System.out.println("Kunde erfolgreich aktualisiert.");
		} else {
			System.out.println("Kein Kunde mit der angegebenen Kundennummer gefunden.");
		}

		this.kunde = kunde;
	}

	public Kunde getKundeByNummer(int kundennummer) {
		try {
			var connection = new DatabaseHelper().getConnection();
			PreparedStatement selectStatement = connection.prepareStatement("SELECT * FROM Kunde WHERE Kundennummer = ?");
			selectStatement.setInt(1, kundennummer);
			ResultSet rs = selectStatement.executeQuery();

			if (rs.next()) {
				Kunde kunde = new Kunde();
				kunde.setVorname(rs.getString("Vorname"));
				kunde.setNachname(rs.getString("Nachname"));
				kunde.setEmail(rs.getString("Email"));
				kunde.setTelefonnummer(rs.getString("Telefonnummer"));
				kunde.setHausnummer(rs.getInt("Hausnummer"));

				this.kunde = kunde; // Setzt das aktuelle Kunde-Objekt
				return kunde;
			} else {
				System.out.println("Kunde nicht gefunden");
				return null;
			}
		} catch (SQLException e) {
			throw new RuntimeException("Fehler beim Lesen des Kunden: " + e.getMessage(), e);
		}
	}
}
