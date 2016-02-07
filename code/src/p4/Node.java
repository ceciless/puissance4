package p4;


public class Node {
	
	private int val;
	private int line;
	private int colum;
	private int player;
	private Node[] son;
	private Node father;
	private int height;
	
	public Node() {
		// TODO Auto-generated constructor stub
		son= new Node[7];
	}
	/*creer un Noeud avec differnet parametres*/
	public Node(int val,int l,int c,int player){
		this.val=val;
		this.player=player;
		line=l;
		colum=c;
		son= new Node[7];
	}
	
	public Node(int l,int c,int player){
		line=l;
		colum=c;
		this.player=player;
		son= new Node[7];
	}
	
	public Node(int l,int c){
		line=l;
		colum=c;
		son= new Node[7];
	}
	
	public int getColum(){
		return colum;
	}
	
	public int getLine(){
		return line;
	}
	
	public int getVal(){
		return val;
	}
	
	public Node getSon(Node parent,int x){
		return parent.son[x];
	}
	
	/*selon la postion de colonne, retourner la position de la ligne*/
	public int searchLine(int col,int[][] tab) {
		for (int i = tab.length - 1; i >= 0; i--) {
			if (tab[i][col] == 0)
				return i;
		}
		return -1; // Aucune ligne n'a ét?trouvée : la colonne est remplie
		
	}
	/*mise a jour des noeuds fils*/
	public void UpdateSon(Node parent,int[][] tab,int player,int depth){
		if (depth==0)return;
		Node n=null;
		for (int c=0;c<7;c++){
			int l=searchLine(c,tab);
			if (l!=-1){
				n= new Node(l,c);
				parent.setSon(parent,n,c);
				tab[l][c]=player;
				UpdateSon(parent.getSon(parent, c),tab,player,depth-1);
				tab[l][c]=0; //UNDO
			}
			else{ 
				n=null;
				parent.setSon(parent,n,c);
			}
			//parent.setSon(parent,n,c);
			//tab[l][c]=player;
			//UpdateSon(parent.getSon(parent, c),tab,player,depth-1);
			//tab[l][c]=0; //UNDO
			
		}
	}
	
	public void setSon(Node parent,Node theSon,int c){
		parent.son[c]=theSon;
	}

	
	public int getHeight() {
		return height;
	}

	

}
