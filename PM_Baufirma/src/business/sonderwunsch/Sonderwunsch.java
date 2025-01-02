package business.sonderwunsch;

public class Sonderwunsch {
	
	private int preis;
	private String name;
	
	public Sonderwunsch(String name, int preis) {
		this.name = name;
		this.preis = preis;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPreis() {
		return preis;
	}
	public void setPreis(int preis) {
		this.preis = preis;
	}

}
