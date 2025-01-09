package gui.fenster;

import business.kunde.Kunde;
import business.sonderwunsch.Sonderwunsch;
import gui.basis.BasisView;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class FensterView extends BasisView {

    private FensterControl fensterControl;
    private ArrayList<Sonderwunsch> swListe;
    private ArrayList<CheckBox> checkBoxList = new ArrayList<>();
    private int gesamtPreis = 0;
    private TextField gesamtPreisTextField;

    public FensterView(FensterControl fensterControl, Stage fensterStage, ArrayList<Sonderwunsch> swListe) {
        super(fensterStage);
        this.fensterControl = fensterControl;
        this.swListe = new ArrayList<>(swListe.subList(6, 15));

        fensterStage.setTitle("Sonderwünsche zu Fenster-Varianten");
        this.initKomponenten();
        this.leseFensterSonderwuensche();
    }

    protected void initKomponenten() {
        super.initKomponenten();
        super.getLblSonderwunsch().setText("Fenster-Varianten");

        int row = 1;
        for (Sonderwunsch s : swListe) {
            Label lblName = new Label(s.getName());
            TextField txtPreis = new TextField(Integer.toString(s.getPreis()));
            txtPreis.setEditable(false);
            Label lblEuro = new Label("Euro");
            CheckBox checkBox = new CheckBox();

            super.getGridPaneSonderwunsch().add(lblName, 0, row);
            super.getGridPaneSonderwunsch().add(txtPreis, 1, row);
            super.getGridPaneSonderwunsch().add(lblEuro, 2, row);
            super.getGridPaneSonderwunsch().add(checkBox, 3, row);

            checkBoxList.add(checkBox);
            row++;
        }

        Label lblGesamt = new Label("Gesamtpreis:");
        gesamtPreisTextField = new TextField("0");
        gesamtPreisTextField.setEditable(false);
        Label lblEuro = new Label("Euro");

        super.getGridPaneSonderwunsch().add(lblGesamt, 0, row);
        super.getGridPaneSonderwunsch().add(gesamtPreisTextField, 1, row);
        super.getGridPaneSonderwunsch().add(lblEuro, 2, row);
    }

    public void oeffneFensterView() {
        super.oeffneBasisView();
    }

    private void leseFensterSonderwuensche() {
        this.fensterControl.leseFensterSonderwuensche();
    }

    private int[] fuelleSwListe() {
        int[] ausgewaehlteSw = new int[swListe.size()];
        for (int i = 0; i < swListe.size(); i++) {
            ausgewaehlteSw[i] = checkBoxList.get(i).isSelected() ? 1 : 0;
        }
        return ausgewaehlteSw;
    }

    protected void berechneUndZeigePreisSonderwuensche() {
        int[] ausgewaehlteSw = fuelleSwListe();
        boolean berechnePreis = this.fensterControl.pruefeKonstellationFenster(ausgewaehlteSw);

        if (berechnePreis) {
            gesamtPreis = 0;
            for (int i = 0; i < swListe.size(); i++) {
                if (checkBoxList.get(i).isSelected()) {
                    gesamtPreis += swListe.get(i).getPreis();
                }
            }
            gesamtPreisTextField.setText(Integer.toString(gesamtPreis));
        }
    }

    protected void speichereSonderwuensche() {
    }

    protected void exportiereSonderwünsche(int[] sonderwuenscheArr, Kunde kunde) {
        try {
            String dateiName = kunde.getHausnummer() + "_" + kunde.getNachname() + "_fenster.csv";
            FileWriter writer = new FileWriter(dateiName);
            BufferedWriter bwr = new BufferedWriter(writer);

            bwr.write("Fenster/Tueren CSV Export für: " + kunde.getVorname() + " " + kunde.getNachname());
            bwr.newLine();
            for (int i : sonderwuenscheArr) {
                bwr.write(Integer.toString(i));
                bwr.write(",");
            }
            bwr.close();
            System.out.println("Sonderwünsche exportiert in Datei: " + dateiName);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
