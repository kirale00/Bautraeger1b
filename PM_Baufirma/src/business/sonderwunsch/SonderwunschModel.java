package business.sonderwunsch;

import java.sql.SQLException;
import java.util.ArrayList;

import javafx.collections.*;

import business.kunde.KundeModel;

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
	
	private ArrayList<Sonderwunsch> fetchSonderwuensche() { // durch datenbankzugriff ersetzen, sobald DB fertig
		ArrayList<Sonderwunsch> swArr = new ArrayList<Sonderwunsch>();
		final int KATEGORIEID=1;
		swArr.add(new Sonderwunsch("Wand zur Abtrennung der Küche von dem Essbereich", 300,KATEGORIEID));
		swArr.add(new Sonderwunsch("Tür in der Wand zwischen Küche und Essbereich", 300,KATEGORIEID));
		swArr.add(new Sonderwunsch("Großes Zimmer im OG statt zwei kleinen Zimmern", 0,KATEGORIEID));
		swArr.add(new Sonderwunsch("Abgetrennter Treppenraum im DG", 890,KATEGORIEID));
		swArr.add(new Sonderwunsch("Vorrichtung eines Bades im DG", 990,KATEGORIEID));
		swArr.add(new Sonderwunsch("Ausführung eines Bades im DG", 8990,KATEGORIEID));
		

		return swArr;
		
	}

	
	

	
	
}
