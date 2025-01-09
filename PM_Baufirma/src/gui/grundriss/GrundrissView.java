package gui.grundriss;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;

import business.kunde.Kunde;
import business.sonderwunsch.Sonderwunsch;
import business.sonderwunsch.SonderwunschModel;
import controller.DatabaseHelper;
import controller.SpeicherHelper;
import gui.basis.BasisView;
import gui.kunde.KundeView;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

/**
 * Klasse, welche das Fenster mit den Sonderwuenschen zu 
 * den Grundrissvarianten bereitstellt.
 */
public class GrundrissView extends BasisView{
 
 	// das Control-Objekt des Grundriss-Fensters
	private GrundrissControl grundrissControl;
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
    public GrundrissView (GrundrissControl grundrissControl, Stage grundrissStage, ArrayList<Sonderwunsch> swListe){
    	super(grundrissStage);
        this.grundrissControl = grundrissControl;
        grundrissStage.setTitle("Sonderwünsche zu Grundriss-Varianten");
        this.swListe=new ArrayList<Sonderwunsch>(swListe.subList(0, 6));        
	    this.initKomponenten(swListe);
	    this.leseGrundrissSonderwuensche();
		
    }
  
    /* initialisiert die Steuerelemente auf der Maske */
    protected void initKomponenten(ArrayList<Sonderwunsch> swListe){
    	super.initKomponenten(); 
       	super.getLblSonderwunsch().setText("Grundriss-Varianten");
       	
       	int offset = 1;
       	for(Sonderwunsch s : swListe.subList(0, 6)) {
       		
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
	public void oeffneGrundrissView(){ 
		super.oeffneBasisView();
	}
    
    private void leseGrundrissSonderwuensche(){
    	this.grundrissControl.leseGrundrissSonderwuensche();
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
		int[] ausgewaehlteSw = fuelleSwListe();

  		
  		Boolean berechnePreis = this.grundrissControl.pruefeKonstellationSonderwuensche(ausgewaehlteSw);
  		if(berechnePreis) {
  			for (int i = 0; i < swListe.size(); i++) {
  	  	        if (checkBoxList.get(i).isSelected()) {
  	  	            gesamtPreis += swListe.get(i).getPreis();
  	  	        }
  	  	    }
  	  	    gesamtPreisTextField.setText(Integer.toString(gesamtPreis));
  	  	    gesamtPreis=0;
  		}
  			
  	    // Es wird erst die Methode pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw)
  		// aus dem Control aufgerufen, dann der Preis berechnet.
  	}
  	
   	/* speichert die ausgesuchten Sonderwuensche in der Datenbank ab */
  	protected void speichereSonderwuensche(){
  		int[] ausgewaehlteSw = fuelleSwListe();
  		Boolean speichereSw = this.grundrissControl.pruefeKonstellationSonderwuensche(ausgewaehlteSw);
  		
  		
  		if(speichereSw) {
			SpeicherHelper.save(swModel.getSonderwuensche(), checkBoxList);
  		}
 		// Es wird erst die Methode pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw)
  		// aus dem Control aufgerufen, dann die Sonderwuensche gespeichert.
  	}
  	
 	
 


	protected void exportiereSonderwünsche(int[] sonderwuenscheArr, Kunde kunde ){
        
        try { 
            String dateiName = kunde.getHausnummer() + "_" + kunde.getNachname() +  "_Grundrisse" + ".csv";
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

