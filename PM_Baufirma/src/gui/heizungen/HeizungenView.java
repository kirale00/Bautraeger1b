package gui.heizungen;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import business.sonderwunsch.Sonderwunsch;
import business.sonderwunsch.SonderwunschModel;
import controller.DatabaseHelper;
import gui.basis.BasisView;
import gui.kunde.KundeView;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HeizungenView extends BasisView{
	
 	// das Control-Objekt des Grundriss-Fensters
	private HeizungenControl heizungenControl;

    private SonderwunschModel swModel = SonderwunschModel.getInstance();
    private ArrayList<Sonderwunsch> swListe;
	private ArrayList<CheckBox> checkBoxList = new ArrayList<>(); 
	private TextField gesamtPreisTextField;
	private int gesamtPreis=0;

  
    /**
     * erzeugt ein GrundrissView-Objekt, belegt das zugehoerige Control
     * mit dem vorgegebenen Objekt und initialisiert die Steuerelemente der Maske
     * @param grundrissControl GrundrissControl, enthaelt das zugehoerige Control
     * @param grundrissStage Stage, enthaelt das Stage-Objekt fuer diese View
     */

    public HeizungenView (HeizungenControl heizungenControl, Stage heizungenStage, ArrayList<Sonderwunsch> swListe){
    	super(heizungenStage);
        this.heizungenControl = heizungenControl;
        heizungenStage.setTitle("Sonderwünsche zu Heizungs-Varianten");
        this.swListe=new ArrayList<Sonderwunsch>(swListe.subList(18,23));        

                
	    this.initKomponenten(swListe);

	    this.leseHeizungenSonderwuensche();
    }
  
    /* initialisiert die Steuerelemente auf der Maske */

    protected void initKomponenten(ArrayList<Sonderwunsch> swListe){
    	super.initKomponenten(); 
       	super.getLblSonderwunsch().setText("Heizungs-Varianten");
       	
       	int offset = 1;
       	for(Sonderwunsch s : swListe.subList(18, 23)) {
       		
       		super.getGridPaneSonderwunsch().add(new Label(s.getBeschreibung()), 0, offset);
       		
       		TextField preisFeld = new TextField(Double.toString(s.getPreis()));
       		preisFeld.setEditable(false);
        	super.getGridPaneSonderwunsch().add(preisFeld, 1, offset);
        	
        	super.getGridPaneSonderwunsch().add(new Label("Euro"), 2, offset);
        	CheckBox c = new CheckBox();
        	super.getGridPaneSonderwunsch().add(c, 3, offset);
        	
        	checkBoxList.add(c);
        	//c.setOnAction(e -> berechneUndZeigePreisSonderwuensche());
       		offset++;
       	}
     // Füge die Gesamtpreis-Zeile hinzu
        Label gesamtLabel = new Label("Gesamtpreis:");
        super.getGridPaneSonderwunsch().add(gesamtLabel, 0, offset);
        
   		gesamtPreisTextField = new TextField(Integer.toString(gesamtPreis));
   		gesamtPreisTextField.setEditable(false);
    	super.getGridPaneSonderwunsch().add(gesamtPreisTextField, 1, offset);

        Label gesamtpreisLabel = new Label("Euro");
        super.getGridPaneSonderwunsch().add(gesamtpreisLabel, 2, offset);
        
    }  
    
    /** 

	 * macht das GrundrissView-Objekt sichtbar.
	 */
	public void oeffneHeizungenView(){ 
		super.oeffneBasisView();
	}
    
    private void leseHeizungenSonderwuensche(){
    	this.heizungenControl.leseHeizungenSonderwuensche();
    }

    private int[] fuelleSwListe() {
    	int[] ausgewaehlteSw= new int[swListe.size()];
    	for (int i = 0; i < swListe.size(); i++) {
  			if(checkBoxList.get(i).isSelected()) {
  				ausgewaehlteSw[i]=1;
  			}else {
  				ausgewaehlteSw[i]=0;
  			}
  		
  		}
    	return ausgewaehlteSw;
    }

 	/* berechnet den Preis der ausgesuchten Sonderwuensche und zeigt diesen an */
  	protected void berechneUndZeigePreisSonderwuensche(){
  		// Es wird erst die Methode pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw)
  		// aus dem Control aufgerufen, dann der Preis berechnet.

          int[] ausgewaehlteSw = fuelleSwListe();

  		
  		Boolean berechnePreis = this.heizungenControl.pruefeKonstellationSonderwuensche(ausgewaehlteSw);
  		if(berechnePreis) {
  			for (int i = 0; i < swListe.size(); i++) {
  	  	        if (checkBoxList.get(i).isSelected()) {
  	  	            gesamtPreis += swListe.get(i).getPreis();
  	  	        }
  	  	    }
  	  	    gesamtPreisTextField.setText(Integer.toString(gesamtPreis));
  	  	    gesamtPreis=0;
  		}

  	}
  	
   	/* speichert die ausgesuchten Sonderwuensche in der Datenbank ab */
  	protected void speichereSonderwuensche(){
 		// Es wird erst die Methode pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw)
  		// aus dem Control aufgerufen, dann die Sonderwuensche gespeichert.

  		
  		int[] ausgewaehlteSw = fuelleSwListe();
  		Boolean speichereSw = this.heizungenControl.pruefeKonstellationSonderwuensche(ausgewaehlteSw);
  		
  		
  		if(speichereSw) {
			ArrayList<Sonderwunsch> wuensche = swModel.getSonderwuensche();

			Connection connection = null;
			PreparedStatement insertStatement = null;
			PreparedStatement fehlerStatement = null;
			int merken=0;


			try {

				connection = new DatabaseHelper().getConnection();

				insertStatement = connection.prepareStatement("INSERT INTO Kundenwunsch (Kundennummer, Sonderwunschid, Anzahl) values (?,?,?)");
				fehlerStatement = connection.prepareStatement("UPDATE Kundenwunsch SET Anzahl = Anzahl + ? WHERE Kundennummer = ? AND Sonderwunschid = ?");

				for(int i=18; i<checkBoxList.size()+18; i++) {
					if (checkBoxList.get(i-18).isSelected()) {
						merken=i;
						insertStatement.setInt(1, KundeView.cmbKundeDropdown.getValue());
						insertStatement.setInt(2, wuensche.get(i).getSonderwunschId());
						insertStatement.setInt(3, 1);
						insertStatement.executeUpdate();
					}
							
				}	
				System.out.println("Die Sonderwünsche wurden erfolgreich gespeichert.");
				//schliesseBasisView();
			}
			 catch (SQLIntegrityConstraintViolationException e) {
				if(merken==21 || merken==22) {
					if(merken==0){
						Alert alert = new Alert(AlertType.ERROR);
	        			alert.setTitle("Fehler");
	        			alert.setHeaderText(null);  // Keine Kopfzeile
	        			alert.setContentText("Die Fußbodenheizung ist bereits vorhanden!");
	        			alert.showAndWait();
					}
				}
                try {
                    fehlerStatement.setInt(1,1);
                    fehlerStatement.setInt(2, KundeView.cmbKundeDropdown.getValue());
                    fehlerStatement.setInt(3, wuensche.get(merken).getSonderwunschId());
                    fehlerStatement.executeUpdate();
                } catch (SQLException e1) {
                    e1.printStackTrace();
                }
            }
			catch (Exception e) {
				System.out.println("Fehler beim Speichern der Sonderwuensche");
				e.printStackTrace();
			}
			 
  			
  		}

  	}

}
