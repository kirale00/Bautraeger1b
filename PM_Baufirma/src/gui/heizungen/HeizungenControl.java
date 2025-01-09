package gui.heizungen;

import business.kunde.KundeModel;
import business.sonderwunsch.SonderwunschModel;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public final class HeizungenControl {
	
 
	
	// das View-Objekt des Grundriss-Fensters
	private HeizungenView heizungenView;
	private SonderwunschModel sonderwunschModel;

	/**
	 * erzeugt ein ControlObjekt inklusive View-Objekt und Model-Objekt zum 
	 * Fenster fuer die Sonderwuensche zum Grundriss.
	 * @param grundrissStage, Stage fuer das View-Objekt zu den Sonderwuenschen zum Grundriss
	 */
	public HeizungenControl(KundeModel kundeModel){  
		this.sonderwunschModel = SonderwunschModel.getInstance();
	   	Stage stageGrundriss = new Stage();
    	stageGrundriss.initModality(Modality.APPLICATION_MODAL);
    	this.heizungenView = new HeizungenView(this, stageGrundriss,sonderwunschModel.getSonderwuensche());
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
		if(ausgewaehlteSw[4]==1 && ausgewaehlteSw[3]==1) {
			Alert alert = new Alert(AlertType.ERROR);
	        alert.setTitle("Fehler");
	        alert.setHeaderText(null);  // Keine Kopfzeile
	        alert.setContentText("Es darf nur eine Variante der Fußbodenheizung geben!");
	        alert.showAndWait();
	        return false;
		}
		return true;
	}
	public SonderwunschModel getSonderwunschModel() {
		return sonderwunschModel;
	}

	public void setSonderwunschModel(SonderwunschModel sonderwunschModel) {
		this.sonderwunschModel = sonderwunschModel;
	}
}
