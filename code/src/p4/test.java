package p4;
import java.util.Scanner;




public class test {

	Option opts;
	public int[][] matJeu;
	public test() {
		// TODO 自动生成的构造函数存根
	}

	public boolean diag2Gagne(byte jVal, int ligneM, int colM) {
		int nbAlign = 0;
		int ligneMin = ligneM;
		int ligneMax = ligneM;
		int colMin = colM;
		int colMax = colM;
		
		int compteur = 0;
		while (ligneMax + 1 < opts.getBoardHeight() && colMin >= 1 && compteur <= 2) {  //On va en bas ?gauche du plateau
			ligneMax++;
			colMin--;
			compteur++;   // on ne va que 3 cases en bas ?droite au maximum
		}
		
		compteur = 0;
		while (ligneMin >= 1 && colMax + 1 < opts.getBoardWidth() && compteur <= 2) {  //On va en haut ?droite du plateau de jeu
			ligneMin--;
			colMax++;
			compteur++;   // on ne va que 3 cases en bas ?droite au maximum
		}
		
		ligneM = ligneMax;
		colM = colMin;
		do {
			if (this.matJeu[ligneM][colM] == jVal)
				nbAlign++;
			else
				nbAlign = 0;
			
			if (nbAlign == 4)
				return true;
			
			ligneM--;
			colM++;
					
		} while (ligneM >= ligneMin && colM <= colMax);
		
		return false;
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

		int a;
		Scanner cin =new Scanner(System.in);
		while (true){
			a=cin.nextInt();
			if (a>0){
				a++;
				System.out.println(a);
				break;
			}
		}
	}

}
