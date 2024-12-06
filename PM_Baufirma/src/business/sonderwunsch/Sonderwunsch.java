package business.sonderwunsch;

public class Sonderwunsch {
	
	private double preis;
	private String name;
	private int kategorieId;
	
	public Sonderwunsch(String name, int preis, int kategorieId) {
		this.name = name;
		this.preis = preis;
		this.kategorieId=kategorieId;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getPreis() {
		return preis;
	}
	public void setPreis(double preis) {
		this.preis = preis;
	}
	public int getKategorieId() {
		return kategorieId;
	}
	public void setKategorieId(int kategorieId) {
		this.kategorieId = kategorieId;
	}
}
