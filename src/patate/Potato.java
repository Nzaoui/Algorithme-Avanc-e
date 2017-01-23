package patate;

import java.util.ArrayList;
import java.util.List;

import backtrack.Card;

public class Potato {
	private List<Card> potato;
	private List<Card> past;
	private List<Card> future;
	
	public Potato(List<Card> potato) {
		super();
		this.potato = potato;
		this.past = new ArrayList<>();
		this.future = new ArrayList<>();
	}
	
	
	public void setPast(List<Card> past) {
		this.past = past;
	}

	public void setFuture(List<Card> future) {
		this.future = future;
	}


	public List<Card> getPast() {
		return past;
	}


	public List<Card> getFuture() {
		return future;
	}


	public List<Card> getPotato() {
		return potato;
	}
	
}
