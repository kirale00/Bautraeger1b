package gui.grundriss;

import java.util.ArrayList;

import business.sonderwunsch.Sonderwunsch;
import gui.basis.BasisView;
import javafx.scene.control.*;
import javafx.stage.Stage;

/**
 * Klasse, welche das Fenster mit den Sonderwuenschen zu 
 * den Grundrissvarianten bereitstellt.
 */
public class GrundrissView extends BasisView{
 
 	// das Control-Objekt des Grundriss-Fensters
	private GrundrissControl grundrissControl;
  
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
                
	    this.initKomponenten(swListe);
	    this.leseGrundrissSonderwuensche();
    }
  
    /* initialisiert die Steuerelemente auf der Maske */
    protected void initKomponenten(ArrayList<Sonderwunsch> swListe){
    	super.initKomponenten(); 
       	super.getLblSonderwunsch().setText("Grundriss-Varianten");
       	
       	int offset = 1;
       	for(Sonderwunsch s : swListe.subList(0, 6)) {
       		
       		super.getGridPaneSonderwunsch().add(new Label(s.getName()), 0, offset);
       		
       		TextField preisFeld = new TextField(Integer.toString(s.getPreis()));
       		preisFeld.setEditable(false);
        	super.getGridPaneSonderwunsch().add(preisFeld, 1, offset);
        	
        	super.getGridPaneSonderwunsch().add(new Label("Euro"), 2, offset);
        	super.getGridPaneSonderwunsch().add(new CheckBox(), 3, offset);
        	
       		offset++;
       	}
       	
       	

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
    
 	/* berechnet den Preis der ausgesuchten Sonderwuensche und zeigt diesen an */
  	protected void berechneUndZeigePreisSonderwuensche(){
  		// Es wird erst die Methode pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw)
  		// aus dem Control aufgerufen, dann der Preis berechnet.
  	}
  	
   	/* speichert die ausgesuchten Sonderwuensche in der Datenbank ab */
  	protected void speichereSonderwuensche(){
 		// Es wird erst die Methode pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw)
  		// aus dem Control aufgerufen, dann die Sonderwuensche gespeichert.
  	}
  	
 	
 }


