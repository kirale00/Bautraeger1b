package gui.parkett;

import business.kunde.KundeModel;
import business.sonderwunsch.SonderwunschModel;
import javafx.stage.Modality;
import javafx.stage.Stage;

public final class ParkettControl {

	private ParkettView parkettView;
	private SonderwunschModel sonderwunschModel;

	public ParkettControl(KundeModel kundeModel) {
		this.sonderwunschModel = SonderwunschModel.getInstance();
		Stage stageParkett = new Stage();
		stageParkett.initModality(Modality.APPLICATION_MODAL);
		this.parkettView = new ParkettView(this, stageParkett, sonderwunschModel.getSonderwuensche());
	}

	public void oeffneParkettView() {
		this.parkettView.oeffneParkettView();
	}

	public void leseParkettSonderwuensche() {
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
