package backtrack;
import java.util.ArrayList;
import java.util.List;



public class Node {
	private Card data;
//	private Node<Card> parent;
	private List<Node> children;
	
	public Node() {
		this.children = new ArrayList<Node>();
	}
	public Node(Card NodeData){
		//this.parent = new Node();
		this.data = NodeData;
		this.children = new ArrayList<Node>();
	}
	public void addChild(Node child){
		this.children.add(child);
	}
	public List<Node> getChildren(){
		return this.children;
	}
	public Card getData() {
		return data;
	}
	public void setData(Card data) {
		this.data = data;
	}
	
	public void showTree(Node p){
		System.out.println(p.getData().showCard());
		for(Node c : p.getChildren()){
			showTree(c);
		}
	}
//	public Node getParent() {
//		return parent;
//	}
//	public void setParent(Node parent) {
//		this.parent = parent;
//	}
//	
	
}
