package patate;

import java.util.*;

import backtrack.Card;

public class PotatoMain {

	static List<Potato> patateList = new ArrayList<>();
	static List<Card> patate = new ArrayList<>();
	public static boolean lastMoveWasDown;
	public static final String LINE_BREAK = "--------------------";
	// private static final Logger LOGGER =
	// Logger.getLogger(PotatoMain.class.getName());

	public static List<Card> potatoesIntersection(List<Card> previous, List<Card> current) {
		List<Card> intersection = new ArrayList<>();
		for (Card crtCard : current) {
			for (Card prvCard : previous) {
				if (prvCard.same(crtCard))
//					System.out.println(crtCard.showCard());
					intersection.add(crtCard);
			}
		}
		return intersection;

	}

	// To get the the number of "NO" marked neighbors: if(there's a neighbor
	// not marked) markedNeighbor ++;
	public static int checkMarkedNeighbors(Card crd, List<Card> cards) {
		int noMarkedNeighbor = 0;

		for (Card crdTemp : cards) {
			if ((!crd.same(crdTemp))
					&& (((crd.getColor().matches(crdTemp.getColor())) || (crd.getNumber() == crdTemp.getNumber()))
							&& (!crdTemp.passedBy))) {
				noMarkedNeighbor++;
			}
		}
		return noMarkedNeighbor;
	}

	public static int patateCreation(Card crd, List<Card> cards) {

		crd.setPassedBy(true);
		patate.add(crd);
		int best = 1;
		////////////////////////////////////////////////////////////////////////////
		int count = checkMarkedNeighbors(crd, cards);

		if (count > 0) {
			// Down direction
			lastMoveWasDown = true;
			for (Card crdTemp : cards) {
				if (!crdTemp.passedBy) {
					if ((crd.getColor().matches(crdTemp.getColor())) || (crd.getNumber() == crdTemp.getNumber())) {
						int result = patateCreation(crdTemp, cards);
						best = (result + 1 > best) ? result + 1 : best;
					}
				}
			}
		} else {
			// Up & Save direction
			// -> Save Part
			// --> (instead of: "patateList.add(patate)" we will do:)
			if (lastMoveWasDown) {
				List<Card> patatetmp = new ArrayList<>();
				for (Card c : patate) {
					patatetmp.add(c);
				}
				patateList.add(new Potato(patatetmp));

				// -> Show the Potato:
				// System.out.println(LINE_BREAK + "\n [count == -1] Patate:");
				// for (Card c : patate) {
				// System.out.println(c.showCard());
				// }
				// System.out.println(LINE_BREAK);
			}

			// -> Stopping Algo when we have 0 possible way from the root.
			if (patate.size() == 1) {
				System.out.println("[count == 0] On a epuisé tout les chemin possible a partir de la racine");
				return best;
			}

			// -> Up Part
			patate.remove(patate.size() - 1);
			Card c = patate.get(patate.size() - 1);
			patate.remove(patate.size() - 1);
			lastMoveWasDown = false;
			patateCreation(c, cards);
		}
		return best;
	}

	public static void setIntersections() {
		int lastElement = patateList.size() - 1;
		patateList.get(patateList.size() - 1).setPast(potatoesIntersection(patateList.get(lastElement - 1).getPotato(),
				patateList.get(lastElement).getPotato()));

		patateList.get(0).setFuture(potatoesIntersection(patateList.get(0).getPotato(), patateList.get(1).getPotato()));
		for (int i = 1; i < patateList.size() - 1; i++) {
//			System.out.println("Past Cards:");
			patateList.get(i)
					.setPast(potatoesIntersection(patateList.get(i - 1).getPotato(), patateList.get(i).getPotato()));
//			System.out.println("Future Cards:");
			patateList.get(i)
					.setFuture(potatoesIntersection(patateList.get(i).getPotato(), patateList.get(i + 1).getPotato()));
		}
	}

	public static void main(String[] args) {

		List<Card> myCards = new ArrayList<Card>();

		Card card = new Card(1, "red");

		Card card0 = new Card(1, "pink");
		Card card1 = new Card(2, "red");
		Card card2 = new Card(4, "pink");
		Card card3 = new Card(5, "red");
		Card card4 = new Card(2, "green");
		Card card5 = new Card(6, "green");
		Card card6 = new Card(6, "red");
		Card card7 = new Card(6, "blue");
		Card card8 = new Card(10, "blue");
		Card card9 = new Card(6, "yellow");
		Card card10 = new Card(5, "white");
		Card card11 = new Card(5, "orange");
		Card card12 = new Card(3, "white");

		myCards.add(card0);
		myCards.add(card1);
		myCards.add(card2);
		myCards.add(card3);
		myCards.add(card4);
		myCards.add(card5);
		myCards.add(card6);
		myCards.add(card7);
		myCards.add(card8);
		myCards.add(card9);
		myCards.add(card10);
		myCards.add(card11);
		myCards.add(card12);
		patateCreation(card, myCards);
		setIntersections();
		// System.out.println(checkMarkedNeighbors(card, myCards));

		// We used to add the same object (patate) in patateList so when our
		// patateCreation is
		// done the List patate contains only the root. So to solve this problem
		// we used a new
		// ArrayList each time we wanted to save the potato in our patateList.
		int i = 0;
		for (Potato p : patateList) {
			i++;
			System.out.println(LINE_BREAK + "\n Patate " + i +"\n");
			System.out.println("Past Intersection size: " + p.getPast().size());
			System.out.println("Future Intersection size: " + p.getFuture().size() + "\n");

			for (Card c : p.getPotato()) {
				System.out.println(c.showCard());
			}
			System.out.println(LINE_BREAK);
		}

	}

}
