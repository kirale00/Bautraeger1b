package gui.innentüren;

import business.kunde.KundeModel;
import gui.grundriss.GrundrissView;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InnentuerenControl {

	// das View-Objekt des Grundriss-Fensters
	private InnentuerenView innentuerenView;

	/**
	 * erzeugt ein ControlObjekt inklusive View-Objekt und Model-Objekt zum 
	 * Fenster fuer die Sonderwuensche zum Grundriss.
	 * @param grundrissStage, Stage fuer das View-Objekt zu den Sonderwuenschen zum Grundriss
	 */
	public InnentuerenControl(KundeModel kundeModel){  
	   	Stage stageInnentueren = new Stage();
    	stageInnentueren.initModality(Modality.APPLICATION_MODAL);
    	this.innentuerenView = new InnentuerenView(this, stageInnentueren);
	}
	    
	/**
	 * macht das GrundrissView-Objekt sichtbar.
	 */
	public void oeffneInnentuerenView(){ 
		this.innentuerenView.oeffneInnentuerenView();
	}

	public void leseInnentuerenSonderwuensche(){
    } 
	
	public boolean pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw){
		return true;
	}
}
