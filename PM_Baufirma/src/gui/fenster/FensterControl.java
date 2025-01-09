package gui.fenster;

import business.kunde.Kunde;
import business.kunde.KundeModel;
import business.sonderwunsch.SonderwunschModel;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Modality;
import javafx.stage.Stage;

public final class FensterControl {

	private FensterView fensterView;
	private KundeModel kundeModel;
	private SonderwunschModel sonderwunschModel;

	public FensterControl(KundeModel kundeModel) {
		this.sonderwunschModel = SonderwunschModel.getInstance();
		Stage stageFenster = new Stage();
		this.kundeModel = kundeModel;
		stageFenster.initModality(Modality.APPLICATION_MODAL);
		this.fensterView = new FensterView(this, stageFenster, sonderwunschModel.getSonderwuensche());
	}

	public void oeffneFensterView() {
		this.fensterView.oeffneFensterView();
	}

	public void leseFensterSonderwuensche() {
	}
	public Kunde getCurrentKunde() {
		return this.kundeModel.kunde;
	}

	public boolean pruefeKonstellationFenster(int[] ausgewaehlteSw) {
		Kunde currentKunde = this.kundeModel.kunde;
		if (ausgewaehlteSw[0] == 0) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Fehler");
			alert.setHeaderText(null); // Keine Kopfzeile
			alert.setContentText("""
    			  Standardm\u00e4\u00dfig ist zwischen Essbereich und Terrasse eine Dreht\u00fcr\r
    			 vorgesehen.""" //
                        );
			alert.showAndWait();
			return false;
		}
		if (ausgewaehlteSw[1] == 1) {
			if(currentKunde == null)
			{
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Fehler");
				alert.setHeaderText(null); // Keine Kopfzeile
				alert.setContentText(
						"Kein Kunde ausgewaehlt um zu pruefen, ob DG vorhanden!");
				alert.showAndWait();
				return false;
			}
			boolean hatDachgeschoss = switch (currentKunde.getHausnummer()) {
				case 1, 6, 7, 14, 15, 24 -> false;
				default -> true;
			};
			
			if(!hatDachgeschoss) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Fehler");
				alert.setHeaderText(null); // Keine Kopfzeile
				alert.setContentText(
						"Geht nur, wenn DG vorhanden ist.");
				alert.showAndWait();
				return false;
			}
			if (ausgewaehlteSw[6] == 1 && ausgewaehlteSw[3] == 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Fehler");
				alert.setHeaderText(null); // Keine Kopfzeile
				alert.setContentText(
						"Geht nur, wenn 3.4 ausgesucht wurde.");
				alert.showAndWait();
				return false;
			}
			if (ausgewaehlteSw[7] == 1 && ausgewaehlteSw[4] == 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Fehler");
				alert.setHeaderText(null); // Keine Kopfzeile
				alert.setContentText(
						"Geht nur, wenn 3.5 ausgesucht wurde.");
				alert.showAndWait();
				return false;
			}
			if (ausgewaehlteSw[8] == 1 && ausgewaehlteSw[5] == 0) {
				Alert alert = new Alert(AlertType.ERROR);
				alert.setTitle("Fehler");
				alert.setHeaderText(null); // Keine Kopfzeile
				alert.setContentText(
						"Geht nur, wenn 3.6 ausgesucht wurde.");
				alert.showAndWait();
				return false;
			}
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
