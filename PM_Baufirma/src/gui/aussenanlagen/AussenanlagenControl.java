package gui.aussenanlagen;

import business.kunde.KundeModel;
import business.sonderwunsch.SonderwunschModel;
import javafx.stage.Modality;
import javafx.stage.Stage;

public final class AussenanlagenControl {

	private AussenanlagenView aussenanlagenView;
	private SonderwunschModel sonderwunschModel;

	public AussenanlagenControl(KundeModel kundeModel) {
		this.sonderwunschModel = SonderwunschModel.getInstance();
		Stage stageAussenanlagen = new Stage();
		stageAussenanlagen.initModality(Modality.APPLICATION_MODAL);
		this.aussenanlagenView = new AussenanlagenView(this, stageAussenanlagen, sonderwunschModel.getSonderwuensche());
	}

	public void oeffneAussenanlagenView() {
		this.aussenanlagenView.oeffneAussenanlagenView();
	}

	public void leseAussenanlagenSonderwuensche() {
	}

	public boolean pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw) {
		return true;
	}

	public SonderwunschModel getSonderwunschModel() {
		return sonderwunschModel;
	}

	public void setSonderwunschModel(SonderwunschModel sonderwunschModel) {
		this.sonderwunschModel = sonderwunschModel;
	}
}
