package gui.kunde;

import business.kunde.*;

import javafx.geometry.*;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.stage.Stage;


/**
 * Klasse, welche das Grundfenster mit den Kundendaten bereitstellt.
 */
public class KundeView{
 
	// das Control-Objekt des Grundfensters mit den Kundendaten
	private KundeControl kundeControl;
	// das Model-Objekt des Grundfensters mit den Kundendaten
	private KundeModel kundeModel;

    //---Anfang Attribute der grafischen Oberflaeche---
	private BorderPane borderPane 		= new BorderPane();
	private GridPane gridPane 			= new GridPane();
	private Label lblKunde    	      	= new Label("Kunde");
    private Label lblNummerHaus     	= new Label("Plannummer des Hauses");
    private ComboBox<Integer> cmbBxNummerHaus = new ComboBox<Integer>();
	private Label lblKundeDropdown     	= new Label("Vorhandene Kunden");
	public static ComboBox<Integer> cmbKundeDropdown = new ComboBox<Integer>();
    private Label lblVorname        = new Label("Vorname");
    private TextField txtVorname    = new TextField();
	private Label lblNachname       = new Label("Nachname");
	private TextField txtNachname   = new TextField();
	private Label lblEmail         	= new Label("Email");
	private TextField txtEmail     	= new TextField();
	private Label lblTelefon        = new Label("Telefonnummer");
	private TextField txtTelefon    = new TextField();
	private Label lblHausnummer     = new Label("Hausnummer");
	private TextField txtHausnummer = new TextField();
	private Button btnAnlegen	 	  	= new Button("Anlegen");
    private Button btnAendern 	      	= new Button("Ändern");
    private Button btnLoeschen 	 		= new Button("Löschen");
    private MenuBar mnBar 			  	= new MenuBar();
    private Menu mnSonderwuensche    	= new Menu("Sonderwünsche");
    private MenuItem mnItmGrundriss  	= new MenuItem("Grundrissvarianten");
    private MenuItem fensterMenuItem  	= new MenuItem("Fenster/Außentüren");
	private MenuItem parkettMenuItem  	= new MenuItem("Parkett");
    private MenuItem mnItmHeizungen 	= new MenuItem("Heizungsvarianten");
    private MenuItem mnItmInnentueren 	= new MenuItem("Innentuerenvarianten");
    //-------Ende Attribute der grafischen Oberflaeche-------
  
    /**
     * erzeugt ein KundeView-Objekt und initialisiert die Steuerelemente der Maske
     * @param kundeControl KundeControl, enthaelt das zugehoerige Control
     * @param primaryStage Stage, enthaelt das Stage-Objekt fuer diese View
     * @param kundeModel KundeModel, enthaelt das zugehoerige Model
    */
    public KundeView (KundeControl kundeControl, Stage primaryStage, 
    	KundeModel kundeModel){
        this.kundeControl = kundeControl;
        this.kundeModel = kundeModel;
        
        primaryStage.setTitle(this.kundeModel.getUeberschrift());	
	    Scene scene = new Scene(borderPane, 550, 600);
	    primaryStage.setScene(scene);
        primaryStage.show();

	    this.initKomponenten();
	    this.initListener();
    }

 
    /* initialisiert die Steuerelemente auf der Maske */
    private void initKomponenten() {
    	borderPane.setCenter(gridPane);
	    gridPane.setHgap(10);
	    gridPane.setVgap(10);
	    gridPane.setPadding(new Insets(25, 25, 25, 25));
	    gridPane.add(lblKunde, 0, 1);
       	lblKunde.setMinSize(150, 40);
	    lblKunde.setFont(Font.font("Arial", FontWeight.BOLD, 24));
	    gridPane.add(lblNummerHaus, 0, 2);
	    gridPane.add(cmbBxNummerHaus, 1, 2);
	    cmbBxNummerHaus.setMinSize(150,  25);
	    cmbBxNummerHaus.setItems(this.kundeModel.getPlannummern());

		gridPane.add(lblKundeDropdown, 0, 4);
		gridPane.add(cmbKundeDropdown, 1, 4);
		cmbKundeDropdown.setItems(this.kundeModel.getKundenNummern());
		cmbKundeDropdown.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
			if(newValue != null){
				this.leseKunde(newValue);
			}
		});
	    gridPane.add(lblVorname, 0, 5);
	    gridPane.add(txtVorname, 1, 5);
		gridPane.add(lblNachname, 0, 7);
		gridPane.add(txtNachname, 1, 7);
		gridPane.add(lblEmail, 0, 9);
		gridPane.add(txtEmail, 1, 9);
		gridPane.add(lblTelefon, 0, 11);
		gridPane.add(txtTelefon, 1, 11);
		gridPane.add(lblHausnummer, 0, 13);
		gridPane.add(txtHausnummer, 1, 13);
	    // Buttons
	    gridPane.add(btnAnlegen, 0, 16);
	    btnAnlegen.setMinSize(150,  25);
	    gridPane.add(btnAendern, 1, 16);
	    btnAendern.setMinSize(150,  25);
	    gridPane.add(btnLoeschen, 2, 16);
	    btnLoeschen.setMinSize(150,  25);
	    // MenuBar und Menu
	    borderPane.setTop(mnBar);
	    mnBar.getMenus().add(mnSonderwuensche);
	    mnSonderwuensche.getItems().add(mnItmGrundriss);
	    mnSonderwuensche.getItems().add(fensterMenuItem);
		mnSonderwuensche.getItems().add(parkettMenuItem);
		mnSonderwuensche.getItems().add(mnItmHeizungen);
	    mnSonderwuensche.getItems().add(mnItmInnentueren);

    }

    /* initialisiert die Listener zu den Steuerelementen auf de Maske */
    private void initListener(){
    	cmbBxNummerHaus.setOnAction(aEvent-> {
    		 holeInfoDachgeschoss();  
    		 leseKunden();
     	});
       	btnAnlegen.setOnAction(aEvent-> {
 	        legeKundenAn();
	    });
    	btnAendern.setOnAction(aEvent-> {
           	aendereKunden();
	    });
       	btnLoeschen.setOnAction(aEvent-> { 
           	loescheKunden();
	    });
      	mnItmGrundriss.setOnAction(aEvent-> {
 	        kundeControl.oeffneGrundrissControl(); 
	    });
      	fensterMenuItem.setOnAction(aEvent-> {
 	        kundeControl.oeffneFensterControl(); 
	    });
		parkettMenuItem.setOnAction(aEvent-> {
			kundeControl.oeffneParkettControl(); 
	   });
      	mnItmHeizungen.setOnAction(aEvent-> {
 	        kundeControl.oeffneHeizungenControl(); 
	    });
      	mnItmInnentueren.setOnAction(aEvent-> {
 	        kundeControl.oeffneInnentuerenControl(); 
	    });
    }
    
    private void holeInfoDachgeschoss(){ 
    }

	private void leseKunden(){}
    
    private void leseKunde(int kundennummer){
		if(kundennummer == 0) {
			txtVorname.setText("");
			txtNachname.setText("");
			txtEmail.setText("");
			txtTelefon.setText("");
			txtHausnummer.setText("");
			return;
		}
		Kunde kunde = this.kundeModel.getKundeByNummer(kundennummer);
		txtVorname.setText(kunde.getVorname());
		txtNachname.setText(kunde.getNachname());
		txtEmail.setText(kunde.getEmail());
		txtTelefon.setText(kunde.getTelefonnummer());
		txtHausnummer.setText(""+kunde.getHausnummer());
    }
    
    private void legeKundenAn(){
         Kunde kunde = new Kunde();
		 //if(cmbKundeDropdown.getValue() != 0) {
			// return;
		 //}
		 kunde.setVorname(txtVorname.getText());
		 kunde.setNachname(txtNachname.getText());
		 kunde.setEmail(txtEmail.getText());
		 kunde.setTelefonnummer(txtTelefon.getText());
		 kunde.setHausnummer(Integer.parseInt(txtHausnummer.getText()));
         // Objekt kunde fuellen
         kundeControl.speichereKunden(kunde);
		 //reset machen:
		txtVorname.setText("");
		txtNachname.setText("");
		txtEmail.setText("");
		txtTelefon.setText("");
		txtHausnummer.setText("");
		cmbKundeDropdown.setItems(this.kundeModel.getKundenNummern());
   	}
    
  	private void aendereKunden(){
		if(cmbKundeDropdown.getValue() == 0) {
			return;
		}
		Kunde kunde = new Kunde();
		kunde.setVorname(txtVorname.getText());
		kunde.setNachname(txtNachname.getText());
		kunde.setEmail(txtEmail.getText());
		kunde.setTelefonnummer(txtTelefon.getText());
		kunde.setHausnummer(Integer.parseInt(txtHausnummer.getText()));
		kundeControl.aendereKunden(kunde, cmbKundeDropdown.getValue());
   	}

	   private void loescheKunden() {
		   kundeControl.loescheKunden(cmbKundeDropdown.getValue());
		   cmbKundeDropdown.setItems(this.kundeModel.getKundenNummern());
		   txtVorname.setText("");
		   txtNachname.setText("");
		   txtEmail.setText("");
		   txtTelefon.setText("");
		   txtHausnummer.setText("");
	   }
   	
   /** zeigt ein Fehlermeldungsfenster an
    * @param ueberschrift, Ueberschrift fuer das Fehlermeldungsfenster
    * @param meldung, String, welcher die Fehlermeldung enthaelt
    */
    public void zeigeFehlermeldung(String ueberschrift, String meldung){
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Fehlermeldung");
        alert.setHeaderText(ueberschrift);
        alert.setContentText(meldung);
        alert.show();
    }

}


