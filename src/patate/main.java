package patate;

import java.util.ArrayList;
import java.util.List;

import backtrack.Card;

public class main {

	static List<List<Card>> patateList = new ArrayList<List<Card>>();
	static List<Card> patate = new ArrayList<Card>();

	public static int checkMarkedNeighbors(Card crd, List<Card> cards) {
		int noMarkedNeighbor = 0;
		// To get the the number of "NO" marked neighbors: if(there's a neighbor
		// not marked) markedNeighbor ++;
		for (Card crdTemp : cards) {
			if ((crd.getColor().matches(crdTemp.getColor())) || (crd.getNumber() == crdTemp.getNumber())) {
				if (!crdTemp.passedBy) {
					noMarkedNeighbor++;
				}
			} else {
				return noMarkedNeighbor--;
			}
		}
		return noMarkedNeighbor;
	}

	public static boolean markedList(List<Card> cards) {
		// all elements are marked:
		// TODO fix variable name:
		boolean allElementsMarked = true;
		for (Card crdTemp : cards) {
			allElementsMarked = allElementsMarked && crdTemp.passedBy;
		}
		return allElementsMarked;
	}

	public static int patateCreation(Card crd, List<Card> cards) {
		if (markedList(cards)) {
			System.out.println("All elements are Marked");
			return 0;
		} else {
			crd.setPassedBy(true);
			patate.add(crd);
			int best = 1;
			////////////////////////////////////////////////////////////////////////////
			int count = checkMarkedNeighbors(crd, cards);

			if (count > 0) {
				// Down direction
				for (Card crdTemp : cards) {
					if (!crdTemp.passedBy) {
						if ((crd.getColor().matches(crdTemp.getColor())) || (crd.getNumber() == crdTemp.getNumber())) {
							patate.add(crdTemp);
							int result = patateCreation(crdTemp, cards);
							best = (result + 1 > best) ? result + 1 : best;
						}
					}
				}
			} else if (count == 0) {
				// Up direction
				System.out.println(patate.size());
				patate.remove(patate.size() - 1);
				Card c = patate.get(patate.size() - 1);
				patate.remove(patate.size() - 1);
				patateCreation(c, cards);
			} else {
				// Up & Save direction
				patateList.add(patate);
				patate.remove(patate.size() - 1);
				Card c = patate.get(patate.size() - 1);
				patate.remove(patate.size() - 1);
				patateCreation(c, cards);
			}
			return best;
		}
	}
	/////////////////////////////////////////////////////////////////////////////
	// for (Card crdTemp : cards) {
	// // Nomarked neighbor:
	// if (!crdTemp.passedBy) {
	// if ((crd.getColor().matches(crdTemp.getColor())) || (crd.getNumber() ==
	// crdTemp.getNumber())) {
	//
	// patate.add(crdTemp);
	//
	// int result = patateCreation(crdTemp, cards);
	// best = (result + 1 > best) ? result + 1 : best;
	// }
	// } else if (checkMarkedNeighbors(crdTemp, cards) == 0) {
	// // to store potato since there is no marked neighbor
	// patateList.add(patate);
	// patate.remove(patate.size() - 1);
	// patate.remove(patate.size() - 1);
	// patateCreation(crdTemp, cards);
	// }
	// }
	// System.out.println("for " + crd.showCard() + ", return " + best);
	// return best;
	// }

	public static void main(String[] args) {
		
		List<Card> myCards = new ArrayList<Card>();
		
		Card card = new Card(2,"red");
		
		Card card0 = new Card(2,"yellow");
		Card card1 = new Card(1,"green");
		Card card2 = new Card(3,"black");
		Card card3 = new Card(5,"green");
		
		myCards.add(card0);
		myCards.add(card1);
		myCards.add(card2);
		myCards.add(card3);
		
		patateCreation(card, myCards);
		//System.out.println(checkMarkedNeighbors(card, myCards));
		
		
		
	
	}

}
