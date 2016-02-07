package p4;


public class Tree {

	protected Node root;
	public Tree() {
		// TODO Auto-generated constructor stub
	}
	
	public void setRoot(int l,int c,int player){
		Node n = new Node(-1,l,c,player);
		setRoot(n);
	}
	
	public void setRoot(int val,int l,int c,int player){
		Node n = new Node(val,l,c,player);
		setRoot(n);
	}
	
	public void setRoot(Node n){
		root=n;
	}
	
	public Node getRoot(){
		return root;
	}
	/*afficher tous les noeuds fils d'une noeud "parent"*/
	public void printSon(Node parent,int depth){
		if (depth==0)return;
		System.out.println("Depth :"+depth+" Mysons: ");
		for (int c=0;c<7;c++){
			System.out.print("line: "+parent.getSon(parent, c).getLine());
			System.out.println(" colum: "+parent.getSon(parent, c).getColum());
			printSon(parent.getSon(parent, c),depth-1);
		}
		
	}
}
