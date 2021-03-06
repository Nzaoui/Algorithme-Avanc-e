package backtrack;

import java.util.ArrayList;
import java.util.List;

public class Mymain {
	public static int backTrack(Card crd, List<Card> cards) {
		crd.setPassedBy(true);
		int best = 1;
		for (Card crdTemp : cards) {
			if (!crdTemp.passedBy) {
				if ((crd.getColor().matches(crdTemp.getColor()))
						|| (crd.getNumber() == crdTemp.getNumber())) {
					int result = backTrack(crdTemp, cards);
					best = (result + 1 > best) ? result + 1 : best; // <----
				}
			}
		}
		crd.setPassedBy(false);
		System.out.println("for " + crd.showCard() + ", return " + best);
		return best;
	}

	public static void main(String[] args) {

		List<Card> cards = new ArrayList<>();

		cards.add(new Card(9, "Green"));
		cards.add(new Card(9, "Red"));
		cards.add(new Card(4, "Green"));
		cards.add(new Card(1, "White"));
		System.out.println(backTrack(new Card(4, "White"), cards));
	}

}

