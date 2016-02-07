package p4;


public class EstimerValEveryStep {

	public EstimerValEveryStep() {
		// TODO Auto-generated constructor stub
	}
	
	/* évaluer le note de la position (ligne,col) aver le joueur "player"*/
	public int EstimerVal(int col,int line,int[][] tab,int player){
		int val= (int)(   ( nbAligneHorzital(col,line,tab,player )*1.5 )+
						  ( nbAligneVertical(col,line,tab,player )*0.8 )+
						  ( nbAligneSlache(col,line,tab,player   )*0.9 )+
						  ( nbAligneAntiSlahe(col,line,tab,player)*0.9 ) );
		/*System.out.println("Player: "+player);
		System.out.println("nbHor "+nbAligneHorzital(col,line,tab,player));
		System.out.println("nbVer "+nbAligneVertical(col,line,tab,player));
		System.out.println("nbSla "+nbAligneSlache(col,line,tab,player));
		System.out.println("nbAntiSla "+nbAligneAntiSlahe(col,line,tab,player)); */
		player=changePlayer(player);
		val+= (int) ((nbAligneHorzital(col,line,tab,player)*1.5))+
				(nbAligneVertical(col,line,tab,player)*0.8)+
				(nbAligneSlache(col,line,tab,player)*0.9)+
				(nbAligneAntiSlahe(col,line,tab,player)*0.9);
	/*	System.out.println("Player: "+player);
		System.out.println("nbHor "+nbAligneHorzital(col,line,tab,player));
		System.out.println("nbVer "+nbAligneVertical(col,line,tab,player));
		System.out.println("nbSla "+nbAligneSlache(col,line,tab,player));
		System.out.println("nbAntiSla "+nbAligneAntiSlahe(col,line,tab,player)); */
		return val;
	}

	/* inutil pour notre porjet
	public int  canAlgneHorzital(int[][] tab,int ligne,int col,int player){
		int ccol=col;
		int count=1;
		while (ccol+1<7){
			ccol++;
			while (tab[ligne][ccol]!=changePlayer(player)){
				count++;
			}
		}
		ccol=col;
		while (ccol-1>=0){
			ccol--;
			while (tab[ligne][ccol]!=changePlayer(player)){
				count++;
			}
		}
		if (count>=4)
			return 1;
		else
			return 100;
	}
	
	public int  canVertical(int[][] tab,int ligne,int col,int player){
		int cligne=ligne;
		int count=1;
		while (cligne+1<6){
			cligne++;
			while (tab[cligne][col]!=changePlayer(player)){
				count++;
			}
		}
		cligne=ligne;
		while (cligne-1>=0){
			cligne--;
			while (tab[cligne][col]!=changePlayer(player)){
				count++;
			}
		}
		if (count>=4)
			return 1;
		else
			return 100;
	}
	
	public int  canSlacheDigonal(int[][] tab,int ligne,int col,int player){
		int ccol=col;
		int cligne=ligne;
		int count=1;
		while (ccol+1<7 && cligne-1>=0){
			ccol++;
			cligne--;
			while (tab[cligne][ccol]!=changePlayer(player)){
				count++;
			}
		}
		cligne=ligne;
		ccol=col;
		while (ccol-1>=0 && cligne+1<=5){
			ccol--;
			cligne++;
			while (tab[cligne][ccol]!=changePlayer(player)){
				count++;
			}
		}
		if (count>=4)
			return 1;
		else
			return 100;
	}
	
	public int  canAntiSlache(int[][] tab,int ligne,int col,int player){
		int ccol=col;
		int cligne=ligne;
		int count=1;
		while (ccol-1>=0 && cligne-1>=0){
			ccol--;
			cligne--;
			while (tab[cligne][ccol]!=changePlayer(player)){
				count++;
			}
		}
		cligne=ligne;
		ccol=col;
		while (ccol+1<=6 && cligne+1<=5){
			ccol++;
			cligne++;
			while (tab[cligne][ccol]!=changePlayer(player)){
				count++;
			}
		}
		if (count>=4)
			return 1;
		else
			return 100;
	}
	*/
	
	/*le changement de joueur*/
	public int changePlayer(int player){
		if (player==1)return 2;
		else return 1;
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

	/*La méthode pour compter le nombre de pions qui ont la même coleur dans le sens Horizontal*/
	public int nbAligneHorzital(int col,int line,int[][] tab,int player){
		int count=1;
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
		return modifierVal(count);
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
	/*La méthode pour compter le nombre de pions qui ont la même coleur dans le sens Vertical*/
	public int nbAligneVertical(int col,int line,int[][] tab,int player){
		int count=1;
		int xx=line;
		while (xx<5){ // verifier s'il y a des pions avec la meme couleur vers le bas
			if(player==tab[xx+1][col]){
				count++;
				xx++;
			}
			else{
				break;
			}
			
		}
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
		return modifierVal(count);
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
	/*La méthode pour compter le nombre de pions qui ont la même coleur dans le sens Slache*/
	public int nbAligneSlache(int col,int line,int[][] tab,int player){
		int count=1;
		int xx=line;
		int yy=col;
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
		return modifierVal(count);
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
	/*La méthode pour compter le nombre de pions qui ont la même coleur dans le sens Anti-slache*/
	public int nbAligneAntiSlahe(int col,int line,int[][] tab,int player){
		int xx=line;
		int yy=col;
		int count=1;
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
		return modifierVal(count);
	}	
	
	/* Différents nombres de pion retourner une valeur différente*/
	public static int modifierVal(int a) {
		switch(a) {
		case 0: return 0;
		case 1: return 5;
		case 2: return 20;
		case 3: return 1000;
		default: return 10000;
		}
	}


}
