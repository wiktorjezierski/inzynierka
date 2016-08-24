package gui;

public class Colors {
	
	private String value;

	public String getValue() {
		return value;
	}
	
	public Colors(String value) {
		this.value = value;
	}

	public final static Colors BLACK = new Colors("black");
	public final static Colors RED = new Colors("red");
	public final static Colors GREEN = new Colors("green");

}
