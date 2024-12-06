package gui.parkett;

import java.util.ArrayList;

import business.sonderwunsch.Sonderwunsch;
import gui.basis.BasisView;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ParkettView extends BasisView {

	private ParkettControl parkettControl;

	private ArrayList<Sonderwunsch> swListe;
	private ArrayList<CheckBox> checkBoxList = new ArrayList<>();
	private TextField gesamtPreisTextField;
	private int gesamtPreis = 0;

	public ParkettView(ParkettControl parkettControl, Stage parkettStage, ArrayList<Sonderwunsch> swListe) {
		super(parkettStage);
		this.parkettControl = parkettControl;
		this.swListe = new ArrayList<Sonderwunsch>(swListe.subList(33, 43));

		parkettStage.setTitle("Sonderwünsche zu Parkett-Varianten");
		parkettStage.setWidth(700);
		parkettStage.setHeight(600);

		this.initKomponenten(this.swListe);
		this.leseParkettSonderwuensche();
	}

	protected void initKomponenten(ArrayList<Sonderwunsch> swListe) {
		super.initKomponenten();
		super.getLblSonderwunsch().setText("Parkett-Varianten");

		int offset = 1;
		for (Sonderwunsch s : swListe) {

			super.getGridPaneSonderwunsch().add(new Label(s.getName()), 0, offset);

			TextField preisFeld = new TextField(Integer.toString(s.getPreis()));
			preisFeld.setEditable(false);
			super.getGridPaneSonderwunsch().add(preisFeld, 1, offset);

			super.getGridPaneSonderwunsch().add(new Label("Euro"), 2, offset);
			super.getGridPaneSonderwunsch().add(new CheckBox(), 3, offset);
			
			CheckBox box = new CheckBox();
			super.getGridPaneSonderwunsch().add(box, 3, offset);

			checkBoxList.add(box);
			offset++;
		}


		Label gesamtLabel = new Label("Gesamtpreis:");
		super.getGridPaneSonderwunsch().add(gesamtLabel, 0, offset);

		gesamtPreisTextField = new TextField(Integer.toString(gesamtPreis));
		gesamtPreisTextField.setEditable(false);
		super.getGridPaneSonderwunsch().add(gesamtPreisTextField, 1, offset);

		Label gesamtpreisLabel = new Label("Euro");
		super.getGridPaneSonderwunsch().add(gesamtpreisLabel, 2, offset);

	}

	public void oeffneParkettView() {
		super.oeffneBasisView();
	}

	private void leseParkettSonderwuensche() {
		this.parkettControl.leseParkettSonderwuensche();
	}

	private int[] fuelleSwListe() {
		int[] ausgewaehlteSw = new int[swListe.size()];
		for (int i = 0; i < swListe.size(); i++) {
			if (checkBoxList.get(i).isSelected()) {
				ausgewaehlteSw[i] = 1;
			} else {
				ausgewaehlteSw[i] = 0;
			}

		}
		return ausgewaehlteSw;
	}

	protected void berechneUndZeigePreisSonderwuensche() {
		int[] ausgewaehlteSw = fuelleSwListe();

		Boolean berechnePreis = this.parkettControl.pruefeKonstellationSonderwuensche(ausgewaehlteSw);
		if (berechnePreis) {
			for (int i = 0; i < swListe.size(); i++) {
				if (checkBoxList.get(i).isSelected()) {
					gesamtPreis += swListe.get(i).getPreis();
				}
			}
			gesamtPreisTextField.setText(Integer.toString(gesamtPreis));
			gesamtPreis = 0;
		}
	}

	protected void speichereSonderwuensche() {
		// Es wird erst die Methode pruefeKonstellationSonderwuensche(int[]
		// ausgewaehlteSw)
		// aus dem Control aufgerufen, dann die Sonderwuensche gespeichert.
	}
}