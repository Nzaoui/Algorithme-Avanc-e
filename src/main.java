import java.util.*;

public class main {
	
	
	public static String colors[]={"red","black","white","orange","gray","pink","green","purple","blue","yellow"};
	public static int numbers[]={0,1,2,3,4,5,6,7,8,9,10};
	public static TreeMap<Integer, List<Card>> arrays = new TreeMap<Integer,List<Card>>();
	// TO FIX return type
	
	public static Object backTracking(Card card){

		ArrayList<Card> posibilities = new ArrayList<Card>(); 	
		for (String color : colors) {
			if(!card.getColor().equals(color))
			posibilities.add(new Card(card.getNumber(), color));
		}
		for (int number : numbers) {
			if(!(card.getNumber()==number))
				posibilities.add(new Card(number, card.getColor()));
		}
		arrays.put(posibilities.size(), posibilities);
		
		int i = 0;
		List<Card> list = arrays.get(arrays.firstKey());
        		
		
		
		return null;
	}

	public static void main(String[] args) {

		// TODO Auto-generated method stub

	}

}
