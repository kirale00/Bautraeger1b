package gui.fenster;

import business.kunde.KundeModel;
import business.sonderwunsch.SonderwunschModel;
import javafx.stage.Modality;
import javafx.stage.Stage;

public final class FensterControl {

	private FensterView fensterView;
	private SonderwunschModel sonderwunschModel;

	public FensterControl(KundeModel kundeModel) {
		this.sonderwunschModel = SonderwunschModel.getInstance();
		Stage stageFenster = new Stage();
		stageFenster.initModality(Modality.APPLICATION_MODAL);
		this.fensterView = new FensterView(this, stageFenster, sonderwunschModel.getSonderwuensche());
	}

	public void oeffneFensterView() {
		this.fensterView.oeffneFensterView();
	}

	public void leseFensterSonderwuensche() {
	}

	public boolean pruefeKonstellationFenster(int[] ausgewaehlteSwf) {
		return true;
	}

	public SonderwunschModel getSonderwunschModel() {
		return sonderwunschModel;
	}

	public void setSonderwunschModel(SonderwunschModel sonderwunschModel) {
		this.sonderwunschModel = sonderwunschModel;
	}
}
