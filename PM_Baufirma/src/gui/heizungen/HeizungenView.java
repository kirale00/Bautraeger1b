package gui.heizungen;

import gui.basis.BasisView;
import gui.grundriss.GrundrissControl;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class HeizungenView extends BasisView{
	
 	// das Control-Objekt des Grundriss-Fensters
	private HeizungenControl heizungenControl;
   
    //---Anfang Attribute der grafischen Oberflaeche---
    private Label lblWandKueche    	     
        = new Label("Heizungs-Variante");
    
    private Label lblZusHeizkoerper= new Label("zusätzlichen Standard Heizkörper");
    private Label lblzusHeizkoerperEuro 		= new Label("660,- Euro je Stück");
    private TextField zusHeizkoerper 	= new TextField();
    
    private Label lblglattHeizkoerper		= new Label("Heizkörper mit glatter Oberfläche");
    private Label lblglattHeizkoerperEuro 		= new Label("160,- Euro je Stück ");
    private TextField glattHeizkoerper 	= new TextField();
    
    
    private Label lblHandtuchHeizkoerper		= new Label("Handtuchheizkörper");
    private Label lblhandtuchEuro 		= new Label("660,- Euro je Stück  ");
    private TextField handtuch 	= new TextField();
    
    private Label lblFussbodenheizung		= new Label("Fußbodenheizung DG");
    private Label lblFussbodenheizungEuro 		= new Label("ohne 8.900€; mit 9990€");
    
    private CheckBox chckFußbodenheizungDg		= new CheckBox();
    //-------Ende Attribute der grafischen Oberflaeche-------
  
    /**
     * erzeugt ein GrundrissView-Objekt, belegt das zugehoerige Control
     * mit dem vorgegebenen Objekt und initialisiert die Steuerelemente der Maske
     * @param grundrissControl GrundrissControl, enthaelt das zugehoerige Control
     * @param grundrissStage Stage, enthaelt das Stage-Objekt fuer diese View
     */
    public HeizungenView (HeizungenControl heizungenControl, Stage heizungenStage){
    	super(heizungenStage);
        this.heizungenControl = heizungenControl;
        heizungenStage.setTitle("Sonderw�nsche zu Heizungs-Varianten");
                
	    this.initKomponenten();
	    this.leseHeizungenSonderwuensche();
    }
  
    /* initialisiert die Steuerelemente auf der Maske */
    protected void initKomponenten(){
    	super.initKomponenten(); 
       	super.getLblSonderwunsch().setText("Heizungs-Varianten");
       	
       
        super.getGridPaneSonderwunsch().add(lblZusHeizkoerper, 0, 1);
        super.getGridPaneSonderwunsch().add(lblzusHeizkoerperEuro, 1, 1);
        super.getGridPaneSonderwunsch().add(zusHeizkoerper, 2, 1);
        
        super.getGridPaneSonderwunsch().add(lblglattHeizkoerper, 0, 2);
        super.getGridPaneSonderwunsch().add(lblglattHeizkoerperEuro, 1, 2);
        super.getGridPaneSonderwunsch().add(this.glattHeizkoerper, 2, 2);
        
        super.getGridPaneSonderwunsch().add(lblHandtuchHeizkoerper, 0, 3);
        super.getGridPaneSonderwunsch().add(lblhandtuchEuro, 1, 3);
        super.getGridPaneSonderwunsch().add(this.handtuch, 2, 3);
        
        
        super.getGridPaneSonderwunsch().add(lblFussbodenheizung, 0, 4);
        super.getGridPaneSonderwunsch().add(lblFussbodenheizungEuro, 1, 4);
        super.getGridPaneSonderwunsch().add(this.chckFußbodenheizungDg, 2, 4);
        
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
    
 	/* berechnet den Preis der ausgesuchten Sonderwuensche und zeigt diesen an */
  	protected void berechneUndZeigePreisSonderwuensche(){
  		// Es wird erst die Methode pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw)
  		// aus dem Control aufgerufen, dann der Preis berechnet.
  		int gesamtpreis=0;
  		
        gesamtpreis += Integer.parseInt(zusHeizkoerper.getText())* 660;
        gesamtpreis += Integer.parseInt(glattHeizkoerper.getText()) * 160;
        gesamtpreis += Integer.parseInt(handtuch.getText()) * 660;
        

	    if (this.chckFußbodenheizungDg.isSelected()){
	        gesamtpreis += 9990;
	    }else {
	    	gesamtpreis += 9990;
	    } 
  	}

    @Override
    protected void exportiereSonderwuensche() {
        Kunde kunde = KundeModel.getInstance().kunde;
        try {
            String dateiName = kunde.getHausnummer() + "_" + kunde.getNachname() + "_Heizungen" + ".csv";
            FileWriter writer = new FileWriter(dateiName);
            BufferedWriter bwr = new BufferedWriter(writer);
            bwr.write("CSV Export für: " + kunde.getVorname() + " " + kunde.getNachname());
            bwr.newLine();
            bwr.write("Ausgewählte Heizungen Sonderwünsche (Name, Preis):");
            bwr.newLine();
            int[] ausgewaehlteSw = this.fuelleSwListe();
            for (int i = 0; i < ausgewaehlteSw.length; i++) {
                if (ausgewaehlteSw[i] == 1) { // Nur ausgewählte Parkett
                    bwr.write(swListe.get(i).getName() + ", " + swListe.get(i).getPreis() + " Euro");
                    bwr.newLine();
                }
            }
            bwr.newLine();
            bwr.close();
            System.out.println("Heizungen exportiert in Datei: " + dateiName);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

    @Override
    protected void exportiereSonderwuensche() {
        Kunde kunde = KundeModel.getInstance().kunde;
        try {
            String dateiName = kunde.getHausnummer() + "_" + kunde.getNachname() + "_Heizungen" + ".csv";
            FileWriter writer = new FileWriter(dateiName);
            BufferedWriter bwr = new BufferedWriter(writer);
            bwr.write("CSV Export für: " + kunde.getVorname() + " " + kunde.getNachname());
            bwr.newLine();
            bwr.write("Ausgewählte Heizungen Sonderwünsche (Name, Preis):");
            bwr.newLine();
            int[] ausgewaehlteSw = this.fuelleSwListe();
            for (int i = 0; i < ausgewaehlteSw.length; i++) {
                if (ausgewaehlteSw[i] == 1) { // Nur ausgewählte Parkett
                    bwr.write(swListe.get(i).getName() + ", " + swListe.get(i).getPreis() + " Euro");
                    bwr.newLine();
                }
            }
            bwr.newLine();
            bwr.close();
            System.out.println("Heizungen exportiert in Datei: " + dateiName);
        } catch (IOException ioe) {
            ioe.printStackTrace();
        }
    }

   	/* speichert die ausgesuchten Sonderwuensche in der Datenbank ab */
  	protected void speichereSonderwuensche(){
 		// Es wird erst die Methode pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw)
  		// aus dem Control aufgerufen, dann die Sonderwuensche gespeichert.
  	}

}
