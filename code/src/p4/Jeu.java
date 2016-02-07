package p4;
import java.util.Scanner;


public class Jeu {

	public Scanner cin= new Scanner (System.in);
	Option opts = new Option();
	//Chessboard cChessboard= new Chessboard();
	public int[][] mat;
	public Jeu() {
		// TODO 自动生成的构造函数存根
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根
		/*Jeu testJeu = new Jeu();
		if (testJeu.Victoria(false,2,3,mat)){
			System.out.println("Won!");
		}*/
	}
	
	public boolean tabPlein (int[][] tab){
		for (int i=0;i<tab.length;i++){
			for (int j=0;j<tab[0].length;j++){
				if (tab[i][j]==0)
					return false;
			}
		}
		return true;
	}

	
	public boolean Victoria(boolean joueur, int ligneX, int colX,int[][] mat){//true-->player1; false-->player2; le X majuscule indique que c'est une ligne correspondant a la position dans la matrice
		//mat[5][3]=1;
		//mat[5][3]=1;mat[3][3]=1;mat[5][4]=2;mat[4][4]=2;
		//mat[4][3]=1;mat[2][3]=1;mat[3][4]=2;
		//mat=cChessboard.getMap();
		/*
		System.out.println("############");
		for (int i=0;i<mat.length;i++){
			for (int j=0;j<mat[0].length;j++){
				System.out.print(mat[i][j]+" | ");
			}
			System.out.println();
		}*/
		byte jVal = 1; // Variable contenant la valeur du joueur, un byte suffit
		if (joueur)
			jVal = 2;
		
		if(ligneVictoria(jVal,ligneX, colX,mat)||columVictoria(jVal,ligneX, colX,mat)||diagoVictoria(jVal,ligneX, colX,mat)||antiDiagoVictoria(jVal,ligneX, colX,mat)){
			return true;
		}
			return false;
	}
	
	
	public boolean ligneVictoria(byte jVal, int ligneX, int colX,int[][] mat) {
		
		int  nbChess = 0;  // nombre de pions qui sont aligne les uns a la suite des autres
		int ligneMin = ligneX - 3;//on considere 4 lignes : 0~3; 1~4; 2~5
			if (ligneMin <= 0)
				ligneMin = 0;
			
			int ligneMax = ligneX + 3;
			if (ligneMax >= mat.length)
				ligneMax = mat.length - 1;
			
			
			for (int i = ligneMin; i <= ligneMax; i++) {
				if (mat[i][colX] == jVal)
					nbChess++;
				else
					nbChess = 0;
			
				if (nbChess == 4)
					return true;
			}
			return false;
		}
		
						 

	
	
	public boolean columVictoria(byte jVal, int ligneX, int colX,int[][] mat) {
		int  nbChess = 0;  // nombre de pions qui sont aligne les uns a la suite des autres
		int colMin = colX - 3;//on considere 4 colonnes : 0~3; 1~4; 2~5
		if (colMin <= 0)
			colMin = 0;
			
		int colMax = colX + 3;
		if (colMax >= mat[0].length)
				colMax = mat[0].length - 1;
			
			for (int i = colMin; i <= colMax; i++) {
				if (mat[ligneX][i] == jVal)
					nbChess++;
				else
					nbChess = 0;

				if (nbChess == 4)
					return true;
			}
			return false;
	}
		
	
	
	public boolean diagoVictoria(byte jVal, int ligneX, int colX,int[][] mat){
		int nbChess = 0;
		int ligneMin = ligneX;
		int ligneMax = ligneX;
		int colMin = colX;
		int colMax = colX;
		
		int compteur = 0;
		while (ligneMax + 1 < mat.length && colMin >= 1 && compteur <= 2) {  /*On va en bas a gauche du plateau 边界（不能超出棋盘 行数不超过 6，
		从第一列开始 因为有列减减 所以不能从0开始，因为最多3个棋子连成一线  4个就赢了 所以compteur<=2, 后面有compteur++）
		 0 1 2 3 4 5 6
		0           /
		1          /
		2         /
		3
		4
		5
			*/
			ligneMax++;
			colMin--;
			compteur++;   // on ne va que 3 cases en bas droite au maximum 最多3个棋子连成一线 4个就赢了
		}
		
		compteur = 0;
		while (ligneMin >= 1 && colMax + 1 < mat[0].length && compteur <= 2) {  //On va en haut a droite du plateau de jeu
			ligneMin--;
			colMax++;
			compteur++;   // on ne va que 3 cases en bas ?droite au maximum
		}
		
		ligneX = ligneMax;
		colX = colMin;
		do {
			if (mat[ligneX][colX] == jVal)
				nbChess++;
			else
				nbChess = 0;
			
			if (nbChess == 4)
				return true;
			
			ligneX--;
			colX++;
					
		} while (ligneX >= ligneMin && colX <= colMax);
		
		return false;
	}
	
	
	public boolean antiDiagoVictoria(byte jVal, int ligneX, int colX,int[][] mat){

		int nbChess = 0;
		int ligneMin = ligneX;
		int ligneMax = ligneX;
		int colMin = colX;
		int colMax = colX;
			
		int compteur = 0;
		while (ligneMax + 1 < mat.length && colMax + 1 < mat[0].length && compteur <= 2) {  //On va en bas a droite du plateau
			ligneMax++;
			colMax++;
			compteur++;   // on ne va que 3 cases en bas ?droite au maximum
		}
			
		compteur = 0;
		while (ligneMin >= 1 && colMin >= 1 && compteur <= 2) {  //On va en haut a gauche du plateau de jeu
			ligneMin--;
			colMin--;
			compteur++;   // on ne va que 3 cases en bas a droite au maximum
		}
			
		ligneX = ligneMin;
		colX = colMin;
		do {
			if (mat[ligneX][colX] == jVal)
				nbChess++;
			else
				nbChess = 0;
				
			if (nbChess == 4)
				return true;
					
			ligneX++;
			colX++;
				
		}while (ligneX <= ligneMax && colX <= colMax);
			
		return false;
	}
	
}
