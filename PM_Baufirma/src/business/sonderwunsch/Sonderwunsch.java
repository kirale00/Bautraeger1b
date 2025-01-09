package business.sonderwunsch;

public class Sonderwunsch {
	
	private int preis;
	private String beschreibung;
	private int sonderwunschId;
	private int anzahlVerfuegbar;
	
	public Sonderwunsch(int preis, String beschreibung, int sonderwunschId, int anzahlVerfuegbar) {
		this.preis = preis;
		this.beschreibung = beschreibung;
		this.sonderwunschId = sonderwunschId;
		this.anzahlVerfuegbar = anzahlVerfuegbar;
	}

	public int getSonderwunschId() {
		return sonderwunschId;
	}

	public void setSonderwunschId(int sonderwunschId) {
		this.sonderwunschId = sonderwunschId;
	}

	public int getAnzahlVerfuegbar() {
		return anzahlVerfuegbar;
	}

	public void setAnzahlVerfuegbar(int anzahlVerfuegbar) {
		this.anzahlVerfuegbar = anzahlVerfuegbar;
	}
	
	public String getBeschreibung() {
		return beschreibung;
	}
	public void setBeschreibung(String beschreibung) {
		this.beschreibung = beschreibung;
	}
	public int getPreis() {
		return preis;
	}
	public void setPreis(int preis) {
		this.preis = preis;
	}

}
