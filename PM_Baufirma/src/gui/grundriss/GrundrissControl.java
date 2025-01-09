package gui.grundriss;


import business.kunde.KundeModel;
import business.sonderwunsch.SonderwunschModel;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;


/**
 * Klasse, welche das Fenster mit den Sonderwuenschen zu den Grundriss-Varianten
 * kontrolliert.
 */


public final class GrundrissControl {

	private GrundrissView grundrissView;
	private SonderwunschModel sonderwunschModel;

	public GrundrissControl(KundeModel kundeModel) {
		this.sonderwunschModel = SonderwunschModel.getInstance();
		Stage stageGrundriss = new Stage();
		stageGrundriss.initModality(Modality.APPLICATION_MODAL);
		this.grundrissView = new GrundrissView(this, stageGrundriss, sonderwunschModel.getSonderwuensche());
	}

	public void oeffneGrundrissView() {
		this.grundrissView.oeffneGrundrissView();
	}

	public void leseGrundrissSonderwuensche() {
	}

	public boolean pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw) {
		if (ausgewaehlteSw[1] == 1 && ausgewaehlteSw[0] == 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Fehler");
			alert.setHeaderText(null); // Keine Kopfzeile
			alert.setContentText("Für die Tür muss vorher eine Wand existieren!");
			alert.showAndWait();
			return false;
		} else if (ausgewaehlteSw[4] == 1 && ausgewaehlteSw[5] == 1) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Fehler");
			alert.setHeaderText(null); // Keine Kopfzeile
			alert.setContentText(
					"Es darf entweder nur eine Vorrichtung eines Bades im DG oder eine Ausführung eines Bades im DG geben!");
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
