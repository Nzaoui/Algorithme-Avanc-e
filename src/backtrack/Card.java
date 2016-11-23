package backtrack;

public class Card {
	
	public int number;
	public String color;
	public boolean passedBy;
	
	public Card(int number, String color) {
		super();
		this.number = number;
		this.color = color;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public boolean isPassedBy() {
		return passedBy;
	}
	public void setPassedBy(boolean passedBy) {
		this.passedBy = passedBy;
	}
	public String showCard(){
		return this.color+","+this.number;
	}
	public boolean same(Card otherCard){
		boolean b;
		if((this.getNumber()==otherCard.getNumber()) && (this.getColor().matches(otherCard.getColor())))
			b = true;
		else b = false;
		return b;
		}
}


