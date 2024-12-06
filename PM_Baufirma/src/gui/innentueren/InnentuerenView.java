package gui.innentüren;

import gui.basis.BasisView;
import gui.grundriss.GrundrissControl;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InnentuerenView extends BasisView{
 	// das Control-Objekt des Grundriss-Fensters
	private InnentuerenControl innentuerenControl;
   
    //---Anfang Attribute der grafischen Oberflaeche---
    private Label lblText   	     
        = new Label("Innentueren-Variante");
    
    private TextField txfAnzahl1= new TextField();
    private int anzahl1=0;
    private TextField txfAnzahl2= new TextField();
    private int anzahl2 = 0;
    private CheckBox cb= new CheckBox();
    //-------Ende Attribute der grafischen Oberflaeche-------
  
    /**
     * erzeugt ein GrundrissView-Objekt, belegt das zugehoerige Control
     * mit dem vorgegebenen Objekt und initialisiert die Steuerelemente der Maske
     * @param grundrissControl GrundrissControl, enthaelt das zugehoerige Control
     * @param grundrissStage Stage, enthaelt das Stage-Objekt fuer diese View
     */
    public InnentuerenView (InnentuerenControl innentuerenControl, Stage innentuerenStage){
    	super(innentuerenStage);
        this.innentuerenControl = innentuerenControl;
        innentuerenStage.setTitle("Sonderwuensche zu Innentuer-Varianten");
                
	    this.initKomponenten();
	    this.leseInnentuerenSonderwuensche();
    }
  
    /* initialisiert die Steuerelemente auf der Maske */
    protected void initKomponenten(){
    	super.initKomponenten(); 
       	//super.getLblSonderwunsch().setText("Innentueren-Varianten");
       	//super.getGridPaneSonderwunsch().add(lblText, 0, 1);
    
       

        Label lblText1 = new Label("Klarglas Tür");
        Label lblPreis1 = new Label("460" + " € je Tür");
        this.txfAnzahl1 = new TextField();
        this.txfAnzahl1.setEditable(true);
        this.txfAnzahl1.setPromptText("Anzahl");
        Label lblStk1 = new Label("Stk");

        super.getGridPaneSonderwunsch().add(lblText1, 0, 1);
        super.getGridPaneSonderwunsch().add(lblPreis1, 1, 1);
        super.getGridPaneSonderwunsch().add(this.txfAnzahl1, 2, 1);
        super.getGridPaneSonderwunsch().add(lblStk1, 3, 1);

        Label lblText2 = new Label("Milchglas Tür");
        Label lblPreis2 = new Label("560" + " € je Tür");
        this.txfAnzahl2 = new TextField();
        this.txfAnzahl2.setEditable(true);
        this.txfAnzahl2.setPromptText("Anzahl");
        Label lblStk2 = new Label("Stk");

        super.getGridPaneSonderwunsch().add(lblText2, 0, 2);
        super.getGridPaneSonderwunsch().add(lblPreis2, 1, 2);
        super.getGridPaneSonderwunsch().add(this.txfAnzahl2, 2, 2);
        super.getGridPaneSonderwunsch().add(lblStk2, 3, 2);
        
        Label lblText3 = new Label("Innentür zur Garage als Holztür? ");
        
        this.cb.setSelected(false);

        super.getGridPaneSonderwunsch().add(lblText3, 0, 3);

        super.getGridPaneSonderwunsch().add(this.cb, 2, 3);
    }  
    
    /**
	 * macht das GrundrissView-Objekt sichtbar.
	 */
	public void oeffneInnentuerenView(){ 
		super.oeffneBasisView();
	}
    
    private void leseInnentuerenSonderwuensche(){
    	this.innentuerenControl.leseInnentuerenSonderwuensche();
    }
    
 	/* berechnet den Preis der ausgesuchten Sonderwuensche und zeigt diesen an */
  	protected void berechneUndZeigePreisSonderwuensche(){
  		// Es wird erst die Methode pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw)
  		// aus dem Control aufgerufen, dann der Preis berechnet.
        gesamtpreis += Integer.parseInt(txfAnzahl1.getText())* 460;
        

        
        gesamtpreis += Integer.parseInt(txfAnzahl2.getText()) * 560;
    

	    if (this.cb.isSelected()){
	        gesamtpreis += 660;
	    }
  	}
  	
   	/* speichert die ausgesuchten Sonderwuensche in der Datenbank ab */
  	protected void speichereSonderwuensche(){
 		// Es wird erst die Methode pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw)
  		// aus dem Control aufgerufen, dann die Sonderwuensche gespeichert.
  	}
  	
}
