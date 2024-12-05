package business.sonderwunsch;

import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;

public class SonderwunschModel {
	
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
		swArr.add(new Sonderwunsch("Wand zur Abtrennung der Küche von dem Essbereich", 300));
		swArr.add(new Sonderwunsch("Tür in der Wand zwischen Küche und Essbereich", 300));
		swArr.add(new Sonderwunsch("Großes Zimmer im OG statt zwei kleinen Zimmern", 0));
		swArr.add(new Sonderwunsch("Abgetrennter Treppenraum im DG", 890));
		swArr.add(new Sonderwunsch("Vorrichtung eines Bades im DG", 990));
		swArr.add(new Sonderwunsch("Ausführung eines Bades im DG", 8990));
		
		return swArr;
		
	}*/
	
	private ArrayList<Sonderwunsch> fetchSonderwuensche() {
		ArrayList<Sonderwunsch> swArr = new ArrayList<>();
		// Muss noch an die .env-Datei angeschlossen werden
		String url = "jdbc:mysql://localhost:3306/SonderwunschVerwaltung?useUnicode=true&characterEncoding=utf";
		String user = "root";
		String password = "rootpassword";
	
		String query = "SELECT beschreibung, preis FROM Sonderwunsch";
	
		try (Connection conn = DriverManager.getConnection(url, user, password);
			 PreparedStatement stmt = conn.prepareStatement(query);
			 ResultSet rs = stmt.executeQuery()) {
			 System.out.println("Verbindung zur Datenbank hergestellt.");
	
			while (rs.next()) {
				String beschreibung = rs.getString("beschreibung");
				double preis = rs.getDouble("preis");
				swArr.add(new Sonderwunsch(beschreibung, (int) preis));

			}
			System.out.println("Sonderwünsche erhalten.");
	
		} catch (SQLException e) {

			e.printStackTrace();
		}
	
		return swArr;
	}
	
	
	

	
	
}
