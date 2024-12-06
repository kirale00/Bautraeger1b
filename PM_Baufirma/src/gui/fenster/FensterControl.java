package gui.fenster;

import business.kunde.KundeModel;
import javafx.stage.Modality;
import javafx.stage.Stage;

public final class FensterControl {

	private FensterView fensterView;

	public FensterControl(KundeModel kundeModel) {
		Stage stageFenster = new Stage();
		stageFenster.initModality(Modality.APPLICATION_MODAL);
		this.fensterView = new FensterView(this, stageFenster);
	}

	public void oeffneFensterView() {
		this.fensterView.oeffneFensterView();
	}

	public void leseFensterSonderwuensche() {
	}

	public boolean pruefeKonstellationFenster(int[] ausgewaehlteSwf) {
		return true;
	}
}
