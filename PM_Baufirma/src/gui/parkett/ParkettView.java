package gui.parkett;

import java.util.ArrayList;

import business.sonderwunsch.Sonderwunsch;
import gui.basis.BasisView;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class ParkettView extends BasisView{
 
	private ParkettControl parkettControl;
  

    public ParkettView (ParkettControl parkettControl, Stage parkettStage, ArrayList<Sonderwunsch> swListe){
    	super(parkettStage);
        this.parkettControl = parkettControl;

        parkettStage.setTitle("Sonderwünsche zu Parkett-Varianten");

		parkettStage.setWidth(700);
		parkettStage.setHeight(600);
                
	    this.initKomponenten(swListe);
	    this.leseParkettSonderwuensche();
    }

    protected void initKomponenten(ArrayList<Sonderwunsch> swListe){
    	super.initKomponenten(); 
       	super.getLblSonderwunsch().setText("Parkett-Varianten");
       	
       	int offset = 1;
       	for(Sonderwunsch s : swListe.subList(33, 43)) {
			
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
	 * macht das ParkettView-Objekt sichtbar.
	 */
	public void oeffneParkettView(){ 
		super.oeffneBasisView();
	}
    
    private void leseParkettSonderwuensche(){
    	this.parkettControl.leseParkettSonderwuensche();
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


