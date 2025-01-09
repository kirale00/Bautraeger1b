
package gui.innentueren;

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


import gui.basis.BasisView;
import gui.grundriss.GrundrissControl;

import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class InnentuerenView extends BasisView{
 	// das Control-Objekt des Grundriss-Fensters
	private gui.innentueren.InnentuerenControl innentuerenControl;

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

    public InnentuerenView (gui.innentueren.InnentuerenControl innentuerenControl, Stage innentuerenStage, ArrayList<Sonderwunsch> swListe){
    	super(innentuerenStage);
        this.innentuerenControl = innentuerenControl;
        innentuerenStage.setTitle("Sonderwünsche zu Innentueren-Varianten");
        this.swListe=new ArrayList<Sonderwunsch>(swListe.subList(15, 18));        

	    this.initKomponenten(swListe);

	    this.leseInnentuerenSonderwuensche();
    }
  
    /* initialisiert die Steuerelemente auf der Maske */

    protected void initKomponenten(ArrayList<Sonderwunsch> swListe){
    	super.initKomponenten(); 
       	super.getLblSonderwunsch().setText("Innentüren-Varianten");
       	int offset = 1;
       	for(Sonderwunsch s : swListe.subList(15, 18)) {
       		
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
	public void oeffneInnentuerenView(){ 
		super.oeffneBasisView();
	}
    
    private void leseInnentuerenSonderwuensche(){
    	this.innentuerenControl.leseInnentuerenSonderwuensche();
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

  		
  		Boolean berechnePreis = this.innentuerenControl.pruefeKonstellationSonderwuensche(ausgewaehlteSw);
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
  		Boolean speichereSw = this.innentuerenControl.pruefeKonstellationSonderwuensche(ausgewaehlteSw);
  		
  		
  		if(speichereSw) {
            PreparedStatement fehlerStatement = null;
            PreparedStatement insertStatement = null;
            Connection connection = null;
            int merken=0;
            ArrayList<Sonderwunsch> wuensche = swModel.getSonderwuensche();
			try{
				

				connection = new DatabaseHelper().getConnection();
                
				insertStatement = connection.prepareStatement("INSERT INTO Kundenwunsch (Kundennummer, Sonderwunschid, Anzahl) values (?,?,?)");
                fehlerStatement = connection.prepareStatement("UPDATE Kundenwunsch SET Anzahl = Anzahl + ? WHERE Kundennummer = ? AND Sonderwunschid = ?");
            
				for(int i=15; i<checkBoxList.size()+15; i++) {
					if (checkBoxList.get(i-15).isSelected()) {
                        merken = i;
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

	@Override
	protected void exportiereSonderwuensche() {

	}


}
