package business.sonderwunsch;

import controller.DatabaseHelper;

import java.sql.SQLException;
import java.util.ArrayList;
import java.nio.charset.StandardCharsets;
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
	
	private ArrayList<Sonderwunsch> fetchSonderwuensche() {
		ArrayList<Sonderwunsch> swArr = new ArrayList<>();
		String query = "SELECT beschreibung, preis FROM Sonderwunsch";
	
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
					swArr.add(new Sonderwunsch(beschreibung, (int) preis));
				}
				System.out.println("Sonderwünsche erhalten.");
			}
	
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
		return swArr;
	}
	
}
