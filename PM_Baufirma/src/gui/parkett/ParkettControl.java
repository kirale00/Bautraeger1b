package gui.parkett;

import business.kunde.KundeModel;
import business.sonderwunsch.SonderwunschModel;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Klasse, welche das Fenster mit den Sonderwuenschen zu den Parkett-Varianten
 * kontrolliert.
 */
public final class ParkettControl {
	
	// das View-Objekt des Parkett-Fensters
	private ParkettView parkettView;
	private SonderwunschModel sonderwunschModel;

	/**
	 * erzeugt ein ControlObjekt inklusive View-Objekt und Model-Objekt zum 
	 * Fenster fuer die Sonderwuensche zum Parkett.
	 * @param parkettStage, Stage fuer das View-Objekt zu den Sonderwuenschen zum Parkett
	 */
	public ParkettControl(KundeModel kundeModel){  
		this.sonderwunschModel = SonderwunschModel.getInstance();
	   	Stage stageParkett = new Stage();
    	stageParkett.initModality(Modality.APPLICATION_MODAL);
    	this.parkettView = new ParkettView(this, stageParkett, sonderwunschModel.getSonderwuensche());
	}
	    
	/**
	 * macht das ParkettView-Objekt sichtbar.
	 */
	public void oeffneParkettView(){
		this.parkettView.oeffneParkettView();
	}

	public void leseParkettSonderwuensche(){
    } 
	
	public boolean pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw){
		return true;
	}

	public SonderwunschModel getSonderwunschModel() {
		return sonderwunschModel;
	}

	public void setSonderwunschModel(SonderwunschModel sonderwunschModel) {
		this.sonderwunschModel = sonderwunschModel;
	}
}
