package patate;

import java.util.ArrayList;
import java.util.List;

import backtrack.Card;

public class main {

	static List<List<Card>> patateList = new ArrayList<List<Card>>();
	static List<Card> patate = new ArrayList<Card>();

	public static int patateCreation(Card crd, List<Card> cards) {
		
		
		
		crd.setPassedBy(true);
		patate.add(crd);
		boolean noMarkedneighbor = true;
		int best = 1;
		for (Card crdTemp : cards) {
			//Nomarked neighbor:
			if (!crdTemp.passedBy) {
				if ((crd.getColor().matches(crdTemp.getColor()))
						|| (crd.getNumber() == crdTemp.getNumber())) {
					
					patate.add(crdTemp);

					int result = patateCreation(crdTemp, cards);
					best = (result + 1 > best) ? result + 1 : best;
				}
			} else {
				// to store potato since there is no marked neighbor
				patateList.add(patate);
				patate.remove(patate.size() - 1);
				patate.remove(patate.size() - 1);
				patateCreation(crdTemp, cards);
			}
		}
		System.out.println("for " + crd.showCard() + ", return " + best);
		return best;
	}

	public static void main(String[] args) {

	}

}
