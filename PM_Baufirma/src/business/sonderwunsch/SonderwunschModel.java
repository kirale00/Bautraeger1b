package business.sonderwunsch;

import controller.DatabaseHelper;

import java.util.ArrayList;

import business.kunde.Kunde;

import java.nio.charset.StandardCharsets;
import java.sql.*;

public class SonderwunschModel {
	public Sonderwunsch sonderwunsch;
	public Kunde kunde;
	private ArrayList<Sonderwunsch> sonderwuensche;
	
	private static SonderwunschModel sonderwunschModel;
	
	private SonderwunschModel(){
		super();
		this.setSonderwuensche(fetchSonderwuensche());
	}
	
	public static SonderwunschModel getInstance(){
		if(sonderwunschModel == null){
			sonderwunschModel = new SonderwunschModel();
		}
		return sonderwunschModel;	
	}

	public ArrayList<Sonderwunsch> getSonderwuensche() {
		return sonderwuensche;
	}

	public void setSonderwuensche(ArrayList<Sonderwunsch> sonderwuensche) {
		this.sonderwuensche = sonderwuensche;
	}
	
	/*private ArrayList<Sonderwunsch> fetchSonderwuensche() { // durch datenbankzugriff ersetzen, sobald DB fertig
		ArrayList<Sonderwunsch> swArr = new ArrayList<Sonderwunsch>();
		final int KATEGORIEID=1;
		swArr.add(new Sonderwunsch("Wand zur Abtrennung der Küche von dem Essbereich", 300,KATEGORIEID));
		swArr.add(new Sonderwunsch("Tür in der Wand zwischen Küche und Essbereich", 300,KATEGORIEID));
		swArr.add(new Sonderwunsch("Großes Zimmer im OG statt zwei kleinen Zimmern", 0,KATEGORIEID));
		swArr.add(new Sonderwunsch("Abgetrennter Treppenraum im DG", 890,KATEGORIEID));
		swArr.add(new Sonderwunsch("Vorrichtung eines Bades im DG", 990,KATEGORIEID));
		swArr.add(new Sonderwunsch("Ausführung eines Bades im DG", 8990,KATEGORIEID));
		

		return swArr;
		
	}*/
	
	private ArrayList<Sonderwunsch> fetchSonderwuensche() {
		ArrayList<Sonderwunsch> swArr = new ArrayList<>();
		// Muss noch an die .env-Datei angeschlossen werden
		/*String url = "jdbc:mysql://localhost:3306/SonderwunschVerwaltung?useUnicode=true&characterEncoding=utf8";
		String user = "root";
		String password = "rootpassword";*/
		String query = "SELECT sonderwunschid, beschreibung, preis, anzahlverfuegbar FROM Sonderwunsch";
	
		try (Connection conn = new DatabaseHelper().getConnection()) {
			// Setze den Zeichensatz für die aktuelle Sitzung
			try (Statement stmt = conn.createStatement()) {
				stmt.execute("SET NAMES utf8mb4");
				System.out.println("Zeichensatz auf UTF-8 gesetzt.");
			}
	
			// Bereite die Abfrage vor und führe sie aus
			try (PreparedStatement stmt = conn.prepareStatement(query);
				 ResultSet rs = stmt.executeQuery()) {
	
				System.out.println("Verbindung zur Datenbank hergestellt.");
				while (rs.next()) {
					String beschreibung = new String(rs.getBytes("beschreibung"), StandardCharsets.UTF_8);
					double preis = rs.getDouble("preis");
					int sonderwunschId = rs.getInt("sonderwunschid");
					int anzahlVerfuegbar = rs.getInt("anzahlverfuegbar");
					swArr.add(new Sonderwunsch( (int) preis,beschreibung, sonderwunschId, anzahlVerfuegbar));
				}
				System.out.println("Sonderwünsche erhalten.");
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return swArr;
	}
}
