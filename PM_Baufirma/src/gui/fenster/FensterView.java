package gui.fenster;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

import business.kunde.Kunde;
import gui.basis.BasisView;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class FensterView extends BasisView {


    private FensterControl fensterControl;

    private Label lblSchiebetuerenEG = new Label("Schiebetüren im EG zur Terrasse:");
    private TextField txtPreisSchiebetuerenEG = new TextField("590");
    private Label lblSchiebetuerenEGEuro = new Label("Euro");
    private CheckBox chckBxSchiebetuerenEG = new CheckBox();

    private Label lblSchiebetuerenDG = new Label("Schiebetüren im DG zur Dachterrasse:");
    private TextField txtPreisSchiebetuerenDG = new TextField("590");
    private Label lblSchiebetuerenDGEuro = new Label("Euro");
    private CheckBox chckBxSchiebetuerenDG = new CheckBox();

    private Label lblEinbruchschutz = new Label("Erhöhter Einbruchschutz an der Haustür:");
    private TextField txtPreisEinbruchschutz = new TextField("690");
    private Label lblEinbruchschutzEuro = new Label("Euro");
    private CheckBox chckBxEinbruchschutz = new CheckBox();

    private Label lblRolladenVorbereitungEG = new Label("Vorbereitung für elektrische Antriebe Rolläden EG:");
    private TextField txtPreisRolladenVorbereitungEG = new TextField("190");
    private Label lblRolladenVorbereitungEGEuro = new Label("Euro");
    private CheckBox chckBxRolladenVorbereitungEG = new CheckBox();

    private Label lblRolladenVorbereitungOG = new Label("Vorbereitung für elektrische Antriebe Rolläden OG:");
    private TextField txtPreisRolladenVorbereitungOG = new TextField("190");
    private Label lblRolladenVorbereitungOGEuro = new Label("Euro");
    private CheckBox chckBxRolladenVorbereitungOG = new CheckBox();

    private Label lblRolladenVorbereitungDG = new Label("Vorbereitung für elektrische Antriebe Rolläden DG:");
    private TextField txtPreisRolladenVorbereitungDG = new TextField("190");
    private Label lblRolladenVorbereitungDGEuro = new Label("Euro");
    private CheckBox chckBxRolladenVorbereitungDG = new CheckBox();

    private Label lblElektrischeRolladenEG = new Label("Elektrische Rolläden EG:");
    private TextField txtPreisElektrischeRolladenEG = new TextField("990");
    private Label lblElektrischeRolladenEGEuro = new Label("Euro");
    private CheckBox chckBxElektrischeRolladenEG = new CheckBox();

    private Label lblElektrischeRolladenOG = new Label("Elektrische Rolläden OG:");
    private TextField txtPreisElektrischeRolladenOG = new TextField("990");
    private Label lblElektrischeRolladenOGEuro = new Label("Euro");
    private CheckBox chckBxElektrischeRolladenOG = new CheckBox();

    private Label lblElektrischeRolladenDG = new Label("Elektrische Rolläden DG:");
    private TextField txtPreisElektrischeRolladenDG = new TextField("990");
    private Label lblElektrischeRolladenDGEuro = new Label("Euro");
    private CheckBox chckBxElektrischeRolladenDG = new CheckBox();

    public FensterView(FensterControl fensterControl, Stage fensterStage) {
        super(fensterStage);
        this.fensterControl = fensterControl;
        fensterStage.setTitle("Sonderwünsche zu Fenster/Außentüren");
        this.initKomponenten();
        this.leseFensterSonderwuensche();
    }

    protected void initKomponenten() {

        super.initKomponenten();

        super.getLblSonderwunsch().setText("Fenster-Varianten");
        super.getGridPaneSonderwunsch().add(lblSchiebetuerenEG, 0, 1);
        super.getGridPaneSonderwunsch().add(txtPreisSchiebetuerenEG, 1, 1);
        txtPreisSchiebetuerenEG.setEditable(false);
        super.getGridPaneSonderwunsch().add(lblSchiebetuerenEGEuro, 2, 1);
        super.getGridPaneSonderwunsch().add(chckBxSchiebetuerenEG, 3, 1);

        super.getGridPaneSonderwunsch().add(lblSchiebetuerenDG, 0, 2);
        super.getGridPaneSonderwunsch().add(txtPreisSchiebetuerenDG, 1, 2);
        txtPreisSchiebetuerenDG.setEditable(false);
        super.getGridPaneSonderwunsch().add(lblSchiebetuerenDGEuro, 2, 2);
        super.getGridPaneSonderwunsch().add(chckBxSchiebetuerenDG, 3, 2);

        super.getGridPaneSonderwunsch().add(lblEinbruchschutz, 0, 3);
        super.getGridPaneSonderwunsch().add(txtPreisEinbruchschutz, 1, 3);
        txtPreisEinbruchschutz.setEditable(false);
        super.getGridPaneSonderwunsch().add(lblEinbruchschutzEuro, 2, 3);
        super.getGridPaneSonderwunsch().add(chckBxEinbruchschutz, 3, 3);

        super.getGridPaneSonderwunsch().add(lblRolladenVorbereitungEG, 0, 4);
        super.getGridPaneSonderwunsch().add(txtPreisRolladenVorbereitungEG, 1, 4);
        txtPreisRolladenVorbereitungEG.setEditable(false);
        super.getGridPaneSonderwunsch().add(lblRolladenVorbereitungEGEuro, 2, 4);
        super.getGridPaneSonderwunsch().add(chckBxRolladenVorbereitungEG, 3, 4);

        super.getGridPaneSonderwunsch().add(lblRolladenVorbereitungOG, 0, 5);
        super.getGridPaneSonderwunsch().add(txtPreisRolladenVorbereitungOG, 1, 5);
        txtPreisRolladenVorbereitungOG.setEditable(false);
        super.getGridPaneSonderwunsch().add(lblRolladenVorbereitungOGEuro, 2, 5);
        super.getGridPaneSonderwunsch().add(chckBxRolladenVorbereitungOG, 3, 5);

        super.getGridPaneSonderwunsch().add(lblRolladenVorbereitungDG, 0, 6);
        super.getGridPaneSonderwunsch().add(txtPreisRolladenVorbereitungDG, 1, 6);
        txtPreisRolladenVorbereitungDG.setEditable(false);
        super.getGridPaneSonderwunsch().add(lblRolladenVorbereitungDGEuro, 2, 6);
        super.getGridPaneSonderwunsch().add(chckBxRolladenVorbereitungDG, 3, 6);

        super.getGridPaneSonderwunsch().add(lblElektrischeRolladenEG, 0, 7);
        super.getGridPaneSonderwunsch().add(txtPreisElektrischeRolladenEG, 1, 7);
        txtPreisElektrischeRolladenEG.setEditable(false);
        super.getGridPaneSonderwunsch().add(lblElektrischeRolladenEGEuro, 2, 7);
        super.getGridPaneSonderwunsch().add(chckBxElektrischeRolladenEG, 3, 7);

        super.getGridPaneSonderwunsch().add(lblElektrischeRolladenOG, 0, 8);
        super.getGridPaneSonderwunsch().add(txtPreisElektrischeRolladenOG, 1, 8);
        txtPreisElektrischeRolladenOG.setEditable(false);
        super.getGridPaneSonderwunsch().add(lblElektrischeRolladenOGEuro, 2, 8);
        super.getGridPaneSonderwunsch().add(chckBxElektrischeRolladenOG, 3, 8);

        super.getGridPaneSonderwunsch().add(lblElektrischeRolladenDG, 0, 9);
        super.getGridPaneSonderwunsch().add(txtPreisElektrischeRolladenDG, 1, 9);
        txtPreisElektrischeRolladenDG.setEditable(false);
        super.getGridPaneSonderwunsch().add(lblElektrischeRolladenDGEuro, 2, 9);
        super.getGridPaneSonderwunsch().add(chckBxElektrischeRolladenDG, 3, 9);
    }

    public void oeffneFensterView() {
        super.oeffneBasisView();
    }

    private void leseFensterSonderwuensche() {
        this.fensterControl.leseFensterSonderwuensche();
    }


    protected void berechneUndZeigePreisSonderwuensche() {
    }

    protected void speichereSonderwuensche() {
    }

    protected void exportiereSonderwünsche(int[] sonderwuenscheArr, Kunde kunde){
        //  DB Abfrage nach allen gespeicherten Sonderwunsch IDs des kunden und damit array befüllen?
        try { 
            String dateiName = kunde.getHausnummer() + "_" + kunde.getNachname() +  "_Fenster" + ".csv";
            FileWriter writer = new FileWriter(dateiName); 
            BufferedWriter bwr = new BufferedWriter(writer); 
                bwr.write("CSV Export für: " + kunde.getVorname() + " " + kunde.getNachname());
            for(int i : sonderwuenscheArr) {
                bwr.write(i); 
                bwr.write(","); 
            }
    
    
            bwr.close(); 
            System.out.println("Sonderwünsche exportiert in Datei: " + dateiName); 
        } catch (IOException ioe) {
                ioe.printStackTrace(); 
        }
    
    
        }

}
