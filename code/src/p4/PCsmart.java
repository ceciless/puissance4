package p4;

public class PCsmart {
	protected int profondeur=1;
	protected int hardness=1;
	Tree cTree = new Tree(); 
	Jeu cJeu = new Jeu();
	EstimerValEveryStep cEValStep = new EstimerValEveryStep();
	public PCsmart() {
		// TODO Auto-generated constructor stub
	}
	/*changer le joueur*/
	public int changePlayer(int player){
		if (player==1)return 2;
		else return 1;
	}
	
	public static void main(String args[]){
		int[][] tab=new int[6][7];
		tab[5][3]=1;tab[4][3]=1;tab[3][3]=1;
		tab[5][2]=2;tab[4][2]=2;
		//tab[4][3]=1;tab[3][3]=1;tab[5][2]=2;tab[4][2]=2;
		/*tab[5][3]=1;tab[5][2]=1;
		tab[5][1]=1;tab[2][0]=1;
		tab[4][1]=1;						//2000000
		tab[3][1]=1;						//2000000      
		tab[5][0]=2;						//1000000
		tab[4][0]=2;						//2100000
		tab[3][0]=2;						//2100000
		tab[1][0]=2;tab[0][0]=2;			//2111000
		*/
		PCsmart test =new PCsmart();
		int ans=test.AutoPlay(5,3,3,tab,3);
		System.out.println(ans);
	} 
	
	/*la methode met a jour des noeuds fils de l'arbre de jeu et rappler "searchSolutionFinal"*/
	public int AutoPlay(int nbTurns,int lastColum, int lastLine,int[][] tab,int depth){
		int player=2;//Pc est considéré comme 2
		int col;
		int line;
		int res;
		Node parent = cTree.getRoot();
		cTree.setRoot(lastLine,lastColum,player);
		Node Myroot = cTree.getRoot();
		Myroot.UpdateSon(Myroot, tab, player, depth);
		//cTree.printSon(Myroot, depth); print the tree 
		//test the update of a tree
		return searchSolutionFinal(depth,Myroot,nbTurns,player,tab);
		
	}
	/*rappler la methode MiniMax pour trouver la solution*/
	public int searchSolutionFinal(int depth,Node Myroot,int nbTurns,int player,int[][] tab){
		 Node n=MiniMax(Myroot,depth,true,tab,nbTurns,player);
		return n.getColum();
	}
	
	
	/*           #
	 * 		  /  |  \
			 /   |   \
			/    |    \
		   #     #     #
          /|\   /|\   /|\
   		 # # # # # # # # #
   http://en.wikipedia.org/wiki/Minimax
   */
	//original version MiniMax
	/*MiniMax sert a trouver la bonne colonne pour le joueur de l'ordinateur*/
	public Node MiniMax(Node Current,int depth,boolean ChoseMax,int[][] tab,int nbTurns,int player){
		int bestVal;
		int val,OpPlayer,ValTmp=0;
		int tmp=0;
		if (depth==0 || nbTurns==42){ //soit a la fin de la hauteur on a besoin
									// soit c'est un node qui n'a pas de fils
			//val=EstimerValAll(tab,player);
			player=changePlayer(player);
			val=EstimerValAll(tab,2);
			//System.out.print("Position is: ");
			//System.out.print(" Line is "+Current.getLine()+" Colum is "+ Current.getColum());
			//System.out.println(" val is "+val);
			Node n= new Node(val,Current.getLine(),Current.getColum(),player);
			return n;
		}
		int colFinal=-1;
		if (ChoseMax){
			bestVal=-100000000; // UNDO on a besoin de verifier apres
			//System.out.println(bestVal);
			for (int col=0;col<7;col++){
				//if (depth==3 && (col==0 || col==1 || col==4 || col==5 || col==6))continue;
				//if (depth==1 && (col==0 || col==1 || col==4 || col==5 || col==6))continue;
				//System.out.println("Depth : "+depth);
				//System.out.println("Position:");
				//System.out.println(" Line is "+Current.getLine()+" Colum is "+ Current.getColum());
				//System.out.println("Col "+col+"\n");
				Node Courant=Current.getSon(Current, col);
				if (Courant==null)continue;
				tab[Courant.getLine()][Courant.getColum()]=player;
				nbTurns++;
				
				tmp=cEValStep.EstimerVal(Courant.getColum(), Courant.getLine(),	tab, player);
				
				Node MiniMaxNode;
				player=changePlayer(player);
				//System.out.println("depth : "+depth);
				MiniMaxNode=MiniMax(Current.getSon(Current,col),depth-1,!ChoseMax,tab,nbTurns,player);
				tmp+=MiniMaxNode.getVal();
				
				if (depth==3){
					System.out.println("depth "+depth+" ChoseMax");
					System.out.println("position is line colum "+Courant.getLine()+" "+Courant.getColum());
					System.out.println("tmp "+tmp);
				}
				/*
				if (depth==1){
					System.out.println("depth "+depth+" ChoseMax");
					System.out.println("position is line colum "+Courant.getLine()+" "+Courant.getColum());
					System.out.println("tmp "+tmp);
				}
				*/
				
				if (bestVal<tmp){
					bestVal=tmp;
					//System.out.println("besyVal<tmp");
					//System.out.println("Position is: ");
					//System.out.println(" Line is "+Courant.getLine()+" Colum is "+ Courant.getColum());
					//System.out.println("BestVal "+bestVal);
					//System.out.println("Tmp "+tmp);
					colFinal=col;
					//System.out.println("ChoseMaxFinalColum："+colFinal);
					//System.out.println("BestValMax "+bestVal);
				}
				else{
					//System.out.println("besyVal>tmp");
					//System.out.println("Position is: ");
					//System.out.println(" Line is "+Courant.getLine()+" Colum is "+ Courant.getColum());
					//System.out.println("BestVal "+bestVal);
					//System.out.println("Tmp "+tmp);
				}
				//bestVal=bestVal>val?bestVal:val;
				//tmp-=MiniMaxNode.getVal();
				//tmp-=ValTmp;
				tmp=0;
				tab[Courant.getLine()][Courant.getColum()]=0;
				nbTurns--;
				player=changePlayer(player);
			}
			int lineFinal=searchLine(colFinal,tab);
			Node n=new Node(bestVal,lineFinal,colFinal,player);
			//System.out.println("ChoseMaxFinalColum："+n.getColum());
			
			return n;
		}
		else{
			bestVal=100000000; // UNDO on a besoin de verifier apres
			//System.out.println(bestVal);
			for (int col=0;col<7;col++){
				//if (depth==2 && (col==0 || col==1 || col==4 || col==5 || col==6))continue;
				Node Courant=Current.getSon(Current, col);
				if (Courant==null)continue;
				tab[Courant.getLine()][Courant.getColum()]=player;
				nbTurns++;
				
					tmp=cEValStep.EstimerVal(Courant.getColum(), Courant.getLine(),	tab, player);
				
				Node MiniMaxNode;
				player=changePlayer(player);
				//System.out.println("depth : "+depth);
				MiniMaxNode=MiniMax(Current.getSon(Current, col),depth-1,!ChoseMax,tab,nbTurns,player);
				tmp+=MiniMaxNode.getVal();
				/*
				if (depth==2){
					System.out.println("depth "+depth+" ChoseMin");
					System.out.println("position is line colum "+Courant.getLine()+" "+Courant.getColum());
					System.out.println("tmp "+tmp);
				}*/
				if (bestVal>tmp){
					bestVal=tmp;
					colFinal=col;
					//System.out.println("ChoseMinFinalColum："+colFinal);
					//System.out.println("BestValMin "+bestVal);
				}
				//bestVal=bestVal>val?bestVal:val;
				//tmp=MiniMaxNode.getVal();
				tab[Courant.getLine()][Courant.getColum()]=0;
				nbTurns--;
				player=changePlayer(player);
				tmp=0;
			}
			//System.out.println("ColFinal "+colFinal);
			int lineFinal=searchLine(colFinal,tab);
			Node n=new Node(bestVal,lineFinal,colFinal,player);
			//System.out.println("ChoseMinFinalColum："+n.getColum());
			return n;
		}
	}

	/*La methode d'evaluation de note de la grille*/
	public int EstimerValAll(int[][] tab,int player){
		int val=0;
		for (int line=tab.length-1;line>=0;line--){
			for (int col=0;col<tab[0].length;col++){
				if (line==5){
					if (tab[line][col]==0){	
						val+=EstimerVal(col,line,tab,player);
					}
				}
				else{
					if ((tab[line][col]==0 && tab[line+1][col]!=0) 
						|| (col+1<7 && tab[line+1][col+1]==0  )
						|| (col-1>=0 && tab[line+1][col-1]==0  )
							)
					{
						val+=EstimerVal(col,line,tab,player);
					}
				}
			}
		}
		return val;
	}
	
	/*La m閠hode d'関alation de note de chaque position possible qui peut 阾re jou�par les joueurs
	 *Ici nous comptons les notes par les consid閞ations ci-dessous 
	 *Le nombre de pion avec la m阭e coleur dans les 4 sens X le coefficient*/
	public int EstimerVal(int col,int line,int[][] tab,int player){
		int val= (int) ((nbAligneHorzital(col,line,tab,player)))+
				(nbAligneVertical(col,line,tab,player))+
				(nbAligneSlache(col,line,tab,player))+
				(nbAligneAntiSlahe(col,line,tab,player));
		/*System.out.println("Player: "+player);
		System.out.println("nbHor "+nbAligneHorzital(col,line,tab,player));
		System.out.println("nbVer "+nbAligneVertical(col,line,tab,player));
		System.out.println("nbSla "+nbAligneSlache(col,line,tab,player));
		System.out.println("nbAntiSla "+nbAligneAntiSlahe(col,line,tab,player)); */
		player=changePlayer(player);
		val-= (int) ((nbAligneHorzital(col,line,tab,player)))+
				(nbAligneVertical(col,line,tab,player))+
				(nbAligneSlache(col,line,tab,player))+
				(nbAligneAntiSlahe(col,line,tab,player));
	/*	System.out.println("Player: "+player);
		System.out.println("nbHor "+nbAligneHorzital(col,line,tab,player));
		System.out.println("nbVer "+nbAligneVertical(col,line,tab,player));
		System.out.println("nbSla "+nbAligneSlache(col,line,tab,player));
		System.out.println("nbAntiSla "+nbAligneAntiSlahe(col,line,tab,player)); */
		return val;
	}
	
	/*La m閠hode pour trouver la ligne quand on a choisi la colonne*/
	public int searchLine(int col,int[][] tab) {
		for (int i = tab.length - 1; i >= 0; i--) {
			if (tab[i][col] == 0)
				return i;
		}
		return -1; // Aucune ligne n'a 閠?trouv閑 : la colonne est remplie
		
	}
	
	/*  x+y
	 *     0 1 2 3 4 5 6 ->y
		
		0  # # # # # # #
		1  # # # # # # #
		2  1 1 1 1 1 1 1
		3  # # # # # # #
		4  # # # # # # #
		5  # # # # # # #
		x
		*/

	/*La m閠hode pour compter le nombre de pions qui ont la m阭e coleur dans le sens Horizontal*/
	public int nbAligneHorzital(int col,int line,int[][] tab,int player){
		int val=0;
		int count=0;
		int yy=col;
		while (yy<6){ // verifier s'il y a des pion avec la meme couleur a droit
			if(player==tab[line][yy+1]){
				count++;
				yy++;
			}
			else{
				break;
			}
			
		}
		val=modifierVal(count);
		count=0;
		yy=col;
		while (yy>0){ // verifier s'il y a des pions avec la meme couleur a gauche
			if (player==tab[line][yy-1]){
				count++;
				yy--;
			}
			else{
				break;
			}
		}
		return val+modifierVal(count);
	}
	
	/*  x+y
	 *     0 1 2 3 4 5 6 ->y
		
		0  # # # 1 # # #
		1  # # # 1 # # #
		2  # # # 1 # # #
		3  # # # 1 # # #
		4  # # # 1 # # #
		5  # # # 1 # # #
		x
		*/
	/*La m閠hode pour compter le nombre de pions qui ont la m阭e coleur dans le sens Vertical*/
	public int nbAligneVertical(int col,int line,int[][] tab,int player){
		int count=0;
		int xx=line;
		int val=0;
		while (xx<5){ // verifier s'il y a des pions avec la meme couleur vers le bas
			if(player==tab[xx+1][col]){
				count++;
				xx++;
			}
			else{
				break;
			}
			
		}
		val=modifierVal(count);
		count=0;
		xx=line;
		while (xx>0){ // verifier s'il y a des pion avec la meme couleur a gauche vers l'haut
			if (player==tab[xx-1][col]){
				count++;
				xx--;
			}
			else{
				break;
			}
		}
		return val+modifierVal(count);
	}
	
	/*  x+y
	 *     0 1 2 3 4 5 6 ->y
		
		0  # # # # # 1 #
		1  # # # # 1 # #
		2  # # # 1 # # #
		3  # # 1 # # # #
		4  # 1 # # # # #
		5  1 # # # # # #
		x
		*/
	/*La m閠hode pour compter le nombre de pions qui ont la m阭e coleur dans le sens Slache*/
	public int nbAligneSlache(int col,int line,int[][] tab,int player){
		int count=0;
		int xx=line;
		int yy=col;
		int val=0;
		while (xx>0 && yy<6){ // verifier s'il y a des pions vers RightUp
			if (player==tab[xx-1][yy+1]){
				count++;
				xx--;
				yy++;
			}
			else{
				break;
			}
		}
		val=modifierVal(count);
		count=0;
		xx=line;yy=col;
		while (xx<5 && yy>0){ // verifier s'il y a des pions vers LeftDown
			if (player==tab[xx+1][yy-1]){
				count++;
				xx++;
				yy--;
			}
			else{
				break;
			}
		}
		val+=modifierVal(count);
		return val;
	}
	
	/*  x+y
	 *     0 1 2 3 4 5 6 ->y
		
		0  # 1 # # # # #
		1  # # 1 # # # #
		2  # # # 1 # # #
		3  # # # # 1 # #
		4  # # # # # 1 #
		5  # # # # # # 1
		x
		*/
	/*La m閠hode pour compter le nombre de pions qui ont la m阭e coleur dans le sens Anti-slache*/
	public int nbAligneAntiSlahe(int col,int line,int[][] tab,int player){
		int xx=line;
		int yy=col;
		int count=0;
		int val=0;
		while (xx>0 && yy>0){// verfer s'il y a des pions vers LeftUp
			if (player==tab[xx-1][yy-1]){
				count++;
				xx--;
				yy--;
			}
			else{
				break;
			}
		}
		val=modifierVal(count);
		count=0;
		xx=line;yy=col;
		while (xx<5 && yy<6){
			if (player==tab[xx+1][yy+1]){// verifier s'il y a des pions vers RightDown
				count++;
				xx++;
				yy++;
			}
			else{
				break;
			}
		}
		val+=modifierVal(count);
		return val;
	}	
	
	/* Diff閞ents nombres de pion retourner une valeur diff閞ente*/
	public static int modifierVal(int a) {
		switch(a) {
			case 0: return 0;
			case 1: return 5;
			case 2: return 20;
			case 3: return 1000;
			default: return 100000;
		}
	}

}