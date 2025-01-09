package gui.kunde;

import java.sql.SQLException;

import business.kunde.Kunde;
import business.kunde.KundeModel;
import gui.grundriss.GrundrissControl;
import gui.parkett.ParkettControl;
import gui.aussenanlagen.AussenanlagenControl;
import gui.fenster.FensterControl;
import javafx.stage.Stage;

/**
 * Klasse, welche das Grundfenster mit den Kundendaten kontrolliert.
 */
public class KundeControl {

	// das View-Objekt des Grundfensters mit den Kundendaten
	private KundeView kundeView;
	// das Model-Objekt des Grundfensters mit den Kundendaten
	private KundeModel kundeModel;
	/* das GrundrissControl-Objekt fuer die Sonderwuensche
       zum Grundriss zu dem Kunden */
	private GrundrissControl grundrissControl;
	private FensterControl fensterControl;
	private ParkettControl parkettControl;
	private AussenanlagenControl aussenanlagenControl;
	private InnentuerenControl innentuerenControl;
	private HeizungenControl heizungenControl;

	/**
	 * erzeugt ein ControlObjekt inklusive View-Objekt und Model-Objekt zum
	 * Grundfenster mit den Kundendaten.
	 * @param primaryStage, Stage fuer das View-Objekt zu dem Grundfenster mit den Kundendaten
	 */
	public KundeControl(Stage primaryStage) {
		this.kundeModel = KundeModel.getInstance();
		this.kundeView = new KundeView(this, primaryStage, kundeModel);
	}

	/*
	 * erstellt, falls nicht vorhanden, ein Grundriss-Control-Objekt.
	 * Das GrundrissView wird sichtbar gemacht.
	 */
	public void oeffneGrundrissControl(){
		if (this.grundrissControl == null){
			this.grundrissControl = new GrundrissControl(kundeModel);
		}
		this.grundrissControl.oeffneGrundrissView();
	}
	public void oeffneFensterControl(){
		if (this.fensterControl == null){
			this.fensterControl = new FensterControl(kundeModel);
		}
		this.fensterControl.oeffneFensterView();
	}
	public void oeffneParkettControl(){
		if (this.parkettControl == null){
			this.parkettControl = new ParkettControl(kundeModel);
		}
		this.parkettControl.oeffneParkettView();
	}
	public void oeffneAussenanlagenControl(){
		if (this.aussenanlagenControl == null){
			this.aussenanlagenControl = new AussenanlagenControl(kundeModel);
		}
		this.aussenanlagenControl.oeffneAussenanlagenView();
	}
	public void oeffneInnentuerenControl(){
		if (this.innentuerenControl == null){
			this.innentuerenControl = new InnentuerenControl(kundeModel);
		}
		this.innentuerenControl.oeffneInnentuerenView();
	}
	public void oeffneHeizungenControl(){
		if (this.heizungenControl == null){
			this.heizungenControl = new HeizungenControl(kundeModel);
		}
		this.heizungenControl.oeffneHeizungenView();
	}

	/**
	 * speichert ein Kunde-Objekt in die Datenbank
	 * @param kunde, Kunde-Objekt, welches zu speichern ist
	 */
	public void speichereKunden(Kunde kunde){
		try{
			kundeModel.speichereKunden(kunde);
		}
		catch(SQLException exc){
			exc.printStackTrace();
			this.kundeView.zeigeFehlermeldung("SQLException",
					"Fehler beim Speichern in die Datenbank");
		}
		catch(Exception exc){
			exc.printStackTrace();
			this.kundeView.zeigeFehlermeldung("Exception",
					"Unbekannter Fehler");
		}
	}

	public void aendereKunden(Kunde kunde, Integer kundennummer){
		try{
			kundeModel.aendereKunden(kunde, kundennummer);
		}catch(SQLException exc){
			exc.printStackTrace();
			this.kundeView.zeigeFehlermeldung("SQLException",
					"Fehler beim Speichern in die Datenbank");
		}
		catch(Exception exc){
			exc.printStackTrace();
			this.kundeView.zeigeFehlermeldung("Exception",
					"Unbekannter Fehler");
		}
	}

	public void loescheKunden(Integer kundennummer){
		kundeModel.loescheKunden(kundennummer);
	}
}
