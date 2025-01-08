package gui.innentueren;

import business.kunde.KundeModel;
import business.sonderwunsch.SonderwunschModel;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class InnentuerenControl {

	// das View-Objekt des Grundriss-Fensters
	private InnentuerenView innentuerenView;
	private SonderwunschModel sonderwunschModel;

	/**
	 * erzeugt ein ControlObjekt inklusive View-Objekt und Model-Objekt zum 
	 * Fenster fuer die Sonderwuensche zum Grundriss.
	 * @param grundrissStage, Stage fuer das View-Objekt zu den Sonderwuenschen zum Grundriss
	 */
	public InnentuerenControl(KundeModel kundeModel){  
		this.sonderwunschModel = SonderwunschModel.getInstance();
	   	Stage stageInnentueren = new Stage();
    	stageInnentueren.initModality(Modality.APPLICATION_MODAL);
    	this.innentuerenView = new InnentuerenView(this, stageInnentueren,sonderwunschModel.getSonderwuensche());
	} 
	public boolean pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw){
		//keine Hindernisse in der Auswahl gefunden
		return true;
	}
	/**
	 * macht das GrundrissView-Objekt sichtbar.
	 */
	public void oeffneInnentuerenView(){ 
		this.innentuerenView.oeffneInnentuerenView();
	}

	public void leseInnentuerenSonderwuensche(){
    } 
	public SonderwunschModel getSonderwunschModel() {
		return sonderwunschModel;
	}

	public void setSonderwunschModel(SonderwunschModel sonderwunschModel) {
		this.sonderwunschModel = sonderwunschModel;
	}
	
}
