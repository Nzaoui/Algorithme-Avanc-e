package coloring;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import backtrack.Card;

public class ColorCodingMain {
	private static List<Tuple<Integer, Card>> labeledCards;
	private static final int LABEL_K = 3;

	public static List<Tuple<Integer, Card>> assignLabel(List<Card> cards) {
		List<Tuple<Integer, Card>> labeledCards = new ArrayList<>();
		int label;
		for (Card card : cards) {
			label = (int) (Math.random() * (LABEL_K) + 1);
			labeledCards.add(new Tuple<Integer, Card>(label, card));
		}
		return labeledCards;
	}

	public static List<Card> setOfCards(int label) {
		List<Card> setWithLabel = new ArrayList<>();
		for (Tuple<Integer, Card> labeledCard : labeledCards) {
			if (labeledCard.getLeft() == label) {
				setWithLabel.add(labeledCard.getRight());
			}
		}
		return setWithLabel;
	}

	public static List<Card> compareTwoSets(List<Card> previous, List<Card> current) {
		List<Card> possibleCards = new ArrayList<>();
		for (Card currentCard : current) {
			for (Card previousCard : previous) {
				if (previousCard.compareTo(currentCard)) {
					System.out.println(currentCard.showCard());
					possibleCards.add(currentCard);
					break;
				}
			}
		}
		return possibleCards;
	}

	public static boolean execute(Set<Integer> sequence, List<Card> previous) {
		System.out.println("-- New Iteration --");
		List<Card> lastCardsInNewSequence;
		List<Card> setOfCards;
		for (int label = 1; label <= LABEL_K; label++) {
			boolean toDo = true;
			for (int labelInSequence : sequence) {
				System.out.println(
						"Comparing current Label : " + label + " with Label (in sequence) : " + labelInSequence);
				if (label == labelInSequence) {
					toDo = false;
					System.out.println("TO DO is false for label : " + label);
					break;
				}
			}
			if (toDo) {
				System.out.println("I'am doing label : " + label);
				System.out.println("Current sequence Lenght : " + sequence.size());
				setOfCards = setOfCards(label);
				System.out.println("The cards in label : " + label + " that i can reach using the sequence are:");
				lastCardsInNewSequence = compareTwoSets(previous, setOfCards);
				if (!lastCardsInNewSequence.isEmpty()) {
					sequence.add(label);
					
					/*
					 * If we want to look for the longest path with different
					 * labels, we just have to call execute(), without testting
					 * on sequence.size(), until it stops:
					 */
					
					if (sequence.size() < LABEL_K) {
						System.out.println("My new sequence's lenght is : " + sequence.size());
						System.out.println("Recursive call of execute!");
						execute(sequence, lastCardsInNewSequence);
					} else {
						System.out.println("My new sequence's lenght is : " + sequence.size());
						System.out.println("\n");
						System.out.println(">>>>>>>>>>>>>>>>>>>>> \t Path of size K : FOUND");
						System.out.println("------------------------------------------------");
						return true;
					}
				}
			}
		}
		return false;
	}

	public static void main(String[] args) {

		Card card0 = new Card(2, "red");
		Card card1 = new Card(4, "red");
		Card card2 = new Card(4, "blue");
		Card card3 = new Card(8, "red");
		Card card4 = new Card(10, "blue");
		Card card5 = new Card(2, "green");
		Card card6 = new Card(7, "green");
		Card card7 = new Card(7, "yellow");
		Card card8 = new Card(6, "yellow");
		Card card9 = new Card(1, "green");

		 List<Tuple<Integer, Card>> ListTuple = new ArrayList<>();
		 ListTuple.add(new Tuple<Integer, Card>(1, card0));
		 ListTuple.add(new Tuple<Integer, Card>(3, card1));
		 ListTuple.add(new Tuple<Integer, Card>(2, card2));
		 ListTuple.add(new Tuple<Integer, Card>(2, card3));
		 ListTuple.add(new Tuple<Integer, Card>(3, card4));
		 ListTuple.add(new Tuple<Integer, Card>(1, card5));
		 ListTuple.add(new Tuple<Integer, Card>(2, card6));
		 ListTuple.add(new Tuple<Integer, Card>(1, card7));
		 ListTuple.add(new Tuple<Integer, Card>(1, card8));
		 ListTuple.add(new Tuple<Integer, Card>(3, card9));
		 labeledCards = ListTuple;

//		List<Card> myCards = new ArrayList<>();
//		myCards.add(card0);
//		myCards.add(card1);
//		myCards.add(card2);
//		myCards.add(card3);
//		myCards.add(card4);
//		myCards.add(card5);
//		myCards.add(card6);
//		myCards.add(card7);
//		myCards.add(card8);
//		myCards.add(card9);
//		labeledCards = assignLabel(myCards);

		Set<Integer> sequence;

		for (int label = 1; label <= LABEL_K; label++) {
			sequence = new HashSet<>();
			sequence.add(label);

			System.out.println("\n");
			System.out.println(
					"////////////////////// [Main] New execute call for Label: " + label + " !! ////////////////////");
			System.out.println("\n");

			execute(sequence, setOfCards(label));
		}
	}
}
