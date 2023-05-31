package object;

public class Userlots {

	private String name;
	private String Ugoods;
	

	public Userlots() {

	}

	public Userlots(String name, String Ugoods) {

		this.name = name;
		this.Ugoods = Ugoods;
		

	}

	public String getName() {
		return this.name;
	}

	public String getUgoods() {
		return this.Ugoods;
	}

	

	public void setName(String name) {
		this.name = name;
	}

	public void setUgoods(String Ugoods) {
		this.Ugoods = Ugoods;
	}

	
}
