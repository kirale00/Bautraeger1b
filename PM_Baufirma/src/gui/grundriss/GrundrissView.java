package gui.grundriss;

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
   
    //---Anfang Attribute der grafischen Oberflaeche---
    private Label lblWandKueche    	     
        = new Label("Wand zur Abtrennung der Küche von dem Essbereich");
    private TextField txtPreisWandKueche 	= new TextField("300");
    private Label lblWandKuecheEuro 		= new Label("Euro");
    private CheckBox chckBxWandKueche 		= new CheckBox();
    
    private Label lblTuerKueche    	     
    = new Label("Tür in der Wand zwischen Küche und Essbereich");
	private TextField txtPreisTuerKueche 	= new TextField("300");
	private Label lblTuerKuecheEuro 		= new Label("Euro");
	private CheckBox chckBxTuerKueche 		= new CheckBox();

	private Label lblGrossesZimmer    	     
	= new Label("Großes Zimmer im OG statt zwei kleinen Zimmern");
	private TextField txtPreisGrossesZimmer 	= new TextField("0");
	private Label lblGrossesZimmerEuro 		= new Label("Euro");
	private CheckBox chckBxGrossesZimmer 		= new CheckBox();
	
	private Label lblAbgetrennterTreppenraum    	     
	= new Label("Abgetrennter Treppenraum im DG");
	private TextField txtPreisAbgetrennterTreppenraum	= new TextField("890");
	private Label lblAbgetrennterTreppenraumEuro	= new Label("Euro");
	private CheckBox chckBxAbgetrennterTreppenraum= new CheckBox();
	
	private Label lblVorrichtungBad 
	= new Label("Vorrichtung eines Bades im DG");
	private TextField txtPreisVorrichtungBad 	= new TextField("990");
	private Label lblVorrichtungBadEuro	= new Label("Euro");
	private CheckBox chckBxVorrichtungBad 		= new CheckBox();
	
	private Label lblAusfuehrungBad   	     
	= new Label("Ausführung eines Bades im DG");
	private TextField txtPreisAusfuehrungBad 	= new TextField("8990");
	private Label lblAusfuehrungBadEuro	= new Label("Euro");
	private CheckBox chckBxAusfuehrungBad	= new CheckBox();
	
	private Label lblAusgabe
	= new Label("Gesamtpreis");
	private TextField txtPreisGesamt = new TextField();
	private Label lblAusgabeEuro = new Label("Euro");
    //-------Ende Attribute der grafischen Oberflaeche-------
  
    /**
     * erzeugt ein GrundrissView-Objekt, belegt das zugehoerige Control
     * mit dem vorgegebenen Objekt und initialisiert die Steuerelemente der Maske
     * @param grundrissControl GrundrissControl, enthaelt das zugehoerige Control
     * @param grundrissStage Stage, enthaelt das Stage-Objekt fuer diese View
     */
    public GrundrissView (GrundrissControl grundrissControl, Stage grundrissStage){
    	super(grundrissStage);
        this.grundrissControl = grundrissControl;
        grundrissStage.setTitle("Sonderwünsche zu Grundriss-Varianten");
                
	    this.initKomponenten();
	    this.leseGrundrissSonderwuensche();
    }
  
    /* initialisiert die Steuerelemente auf der Maske */
    protected void initKomponenten(){
    	super.initKomponenten(); 
       	super.getLblSonderwunsch().setText("Grundriss-Varianten");
       	
       	super.getGridPaneSonderwunsch().add(lblAusgabe, 0, 7);
       	super.getGridPaneSonderwunsch().add(txtPreisGesamt, 1, 7);
       	super.getGridPaneSonderwunsch().add(lblAusgabeEuro, 2, 7);
       	
       	lblAusgabe.setVisible(false);
       	txtPreisGesamt.setVisible(false);
       	txtPreisGesamt.setEditable(false);
       	lblAusgabeEuro.setVisible(false);
       	
       	super.getGridPaneSonderwunsch().add(lblWandKueche, 0, 1);
    	super.getGridPaneSonderwunsch().add(txtPreisWandKueche, 1, 1);
    	txtPreisWandKueche.setEditable(false);
    	super.getGridPaneSonderwunsch().add(lblWandKuecheEuro, 2, 1);
    	super.getGridPaneSonderwunsch().add(chckBxWandKueche, 3, 1);
    	
       	super.getGridPaneSonderwunsch().add(lblTuerKueche, 0, 2);
    	super.getGridPaneSonderwunsch().add(txtPreisTuerKueche, 1, 2);
    	txtPreisTuerKueche.setEditable(false);
    	super.getGridPaneSonderwunsch().add(lblTuerKuecheEuro, 2, 2);
    	super.getGridPaneSonderwunsch().add(chckBxTuerKueche, 3, 2);
    	
       	super.getGridPaneSonderwunsch().add(lblGrossesZimmer, 0, 3);
    	super.getGridPaneSonderwunsch().add(txtPreisGrossesZimmer, 1, 3);
    	txtPreisGrossesZimmer.setEditable(false);
    	super.getGridPaneSonderwunsch().add(lblGrossesZimmerEuro, 2, 3);
    	super.getGridPaneSonderwunsch().add(chckBxGrossesZimmer, 3, 3);
    	
       	super.getGridPaneSonderwunsch().add(lblAbgetrennterTreppenraum, 0, 4);
    	super.getGridPaneSonderwunsch().add(txtPreisAbgetrennterTreppenraum, 1, 4);
    	txtPreisAbgetrennterTreppenraum.setEditable(false);
    	super.getGridPaneSonderwunsch().add(lblAbgetrennterTreppenraumEuro, 2, 4);
    	super.getGridPaneSonderwunsch().add(chckBxAbgetrennterTreppenraum, 3, 4);
    	
       	super.getGridPaneSonderwunsch().add(lblVorrichtungBad, 0, 5);
    	super.getGridPaneSonderwunsch().add(txtPreisVorrichtungBad, 1, 5);
    	txtPreisVorrichtungBad.setEditable(false);
    	super.getGridPaneSonderwunsch().add(lblVorrichtungBadEuro, 2, 5);
    	super.getGridPaneSonderwunsch().add(chckBxVorrichtungBad, 3, 5);
    	
       	super.getGridPaneSonderwunsch().add(lblAusfuehrungBad, 0, 6);
    	super.getGridPaneSonderwunsch().add(txtPreisAusfuehrungBad, 1, 6);
    	txtPreisAusfuehrungBad.setEditable(false);
    	super.getGridPaneSonderwunsch().add(lblAusfuehrungBadEuro, 2, 6);
    	super.getGridPaneSonderwunsch().add(chckBxAusfuehrungBad, 3, 6);
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
  		
  		//this.grundrissControl.pruefeKonstellationSonderwuensche(null);

  		
  		
  		
  		//Start Preisberechnung
  		int preisAusgabe=0;
  		
  		if(this.chckBxAusfuehrungBad.isSelected()) {
  			 preisAusgabe+=Integer.parseInt(txtPreisAusfuehrungBad.getText());

   		 }
  		if(this.chckBxVorrichtungBad.isSelected()) {
  			 preisAusgabe+=Integer.parseInt(txtPreisVorrichtungBad.getText());

   		 }
  		if(this.chckBxGrossesZimmer.isSelected()) {
			 	preisAusgabe+=Integer.parseInt(txtPreisGrossesZimmer.getText());

		 }
  		if(this.chckBxAbgetrennterTreppenraum.isSelected()) {
  			 preisAusgabe+=Integer.parseInt(txtPreisAbgetrennterTreppenraum.getText());

      		}
  		if(this.chckBxTuerKueche.isSelected()) {
   			preisAusgabe+=Integer.parseInt(txtPreisTuerKueche.getText());

   		}
  		 if(this.chckBxWandKueche.isSelected()) {
			 preisAusgabe+=Integer.parseInt(txtPreisWandKueche.getText());
		 }
  		 //Ende Preisberechnung
  		 
  		 
  		txtPreisGesamt.setText("" + preisAusgabe); 
  		lblAusgabe.setVisible(true);
       	txtPreisGesamt.setVisible(true);
       	lblAusgabeEuro.setVisible(true);
       	
  		// Es wird erst die Methode pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw)
  		// aus dem Control aufgerufen, dann der Preis berechnet.
  	}
  	
   	/* speichert die ausgesuchten Sonderwuensche in der Datenbank ab */
  	protected void speichereSonderwuensche(){
 		// Es wird erst die Methode pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw)
  		// aus dem Control aufgerufen, dann die Sonderwuensche gespeichert.
  	}
  	
 	
 }