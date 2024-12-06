package gui.grundriss;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
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
        this.swListe=swListe;        
	    this.initKomponenten(swListe);
	    this.leseGrundrissSonderwuensche();
    }
  
    /* initialisiert die Steuerelemente auf der Maske */
    protected void initKomponenten(ArrayList<Sonderwunsch> swListe){
    	super.initKomponenten(); 
       	super.getLblSonderwunsch().setText("Grundriss-Varianten");
       	
       	int offset = 1;
       	for(Sonderwunsch s : swListe) {
       		
       		super.getGridPaneSonderwunsch().add(new Label(s.getName()), 0, offset);
       		
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
  			
  			
  			
  			String url = "jdbc:mysql://localhost:3306/SonderwunschVerwaltung";
  	        String benutzername = "user";
  	        String passwort = "password";
  	        
  	        String sql = "INSERT INTO SonderWunsch (OptionName, Price, KategorieID) VALUES (?,?,?)";
  			
  	        
  	      try (Connection conn = DriverManager.getConnection(url, benutzername, passwort);
  	             PreparedStatement stmt = conn.prepareStatement(sql)) {
  	    	

  	            // Setze die Parameter für die SQL-Abfrage
  	    	  for(int i = 0;i<swListe.size();i++) {
  	    		  
  	    		  stmt.setString(1, swListe.get(i).getName());
  	    		  stmt.setDouble(2, swListe.get(i).getPreis());
  	    		  stmt.setInt(3, swListe.get(i).getKategorieId());
  	    		  stmt.addBatch();
  	    	  }
  	    	  
  	    	  stmt.executeBatch();
  	    	  
  	    	  conn.commit();
	          System.out.println("Sonderwünsche wurden gespeichert!");


  	            
  	          

  	        } catch (SQLException e) {
  	            e.printStackTrace();
  	            System.out.println("Fehler beim Speichern der Sonderwünsche in der Datenbank.");
  	            
  	          
  	        }
  	        
  	        
  	        
  	        
  		}
 		// Es wird erst die Methode pruefeKonstellationSonderwuensche(int[] ausgewaehlteSw)
  		// aus dem Control aufgerufen, dann die Sonderwuensche gespeichert.
  	}
  	
 	
 }
