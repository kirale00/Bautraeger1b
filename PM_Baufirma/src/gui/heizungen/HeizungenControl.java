package gui.heizungen;

import business.kunde.KundeModel;
import gui.grundriss.GrundrissView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class HeizungenControl {
	

	
	// das View-Objekt des Grundriss-Fensters
	private HeizungenView heizungenView;

	/**
	 * erzeugt ein ControlObjekt inklusive View-Objekt und Model-Objekt zum 
	 * Fenster fuer die Sonderwuensche zum Grundriss.
	 * @param grundrissStage, Stage fuer das View-Objekt zu den Sonderwuenschen zum Grundriss
	 */
	public HeizungenControl(KundeModel kundeModel){  
	   	Stage stageGrundriss = new Stage();
    	stageGrundriss.initModality(Modality.APPLICATION_MODAL);
    	this.heizungenView = new HeizungenView(this, stageGrundriss);
	}
	    
	/**
	 * macht das GrundrissView-Objekt sichtbar.
	 */
	public void oeffneHeizungenView(){ 
		this.heizungenView.oeffneHeizungenView();
	}

	public void leseHeizungenSonderwuensche(){
    } 
	
	public boolean pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw){
		return true;
	
	}
}
