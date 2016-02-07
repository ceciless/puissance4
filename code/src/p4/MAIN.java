package p4;
import java.util.*;
public class MAIN {

	public MAIN() {
		// TODO 自动生成的构造函数存根
	}
	
	public static void main(String args[]){
		Scanner cin = new Scanner(System.in);
		int col;
		int line;
		int nbTurns=0;
		int lastColum;
		int lastLine;
		int depth=1;
		
		Player H1=new PlayerHumain(); // on considere joueur1 est un joueur humain
		Player H2;
		
		Option cOption= new Option();
		Chessboard cChessboard = new Chessboard();
		Jeu cJeu = new Jeu();
		PCsmart cPCsmart = new PCsmart();
		
		System.out.println("Chose Player: 0,computer/ 1,humain");
		int ChosePlayer=cin.nextInt();
		if (ChosePlayer==1){
			H2= new PlayerHumain();
		}
		else{
			H2= new PlayerComputer();
			System.out.println("Please chose your hardness !");
			depth=cin.nextInt();
		}
		
		int player=1;
		while (true){
			player=1;
			System.out.println("Player 1 : Please enter your colum :");
			col=cin.nextInt();
			line=cChessboard.searchLine(col);
			lastLine=line;
			lastColum=col;
			cChessboard.Update(col,player);
			nbTurns++;
			cChessboard.print();
			if (cJeu.Victoria(false,line,col,cChessboard.getMap())){
				System.out.println("H1 Won!");
				break;
			}
			
			if (ChosePlayer==1){
				System.out.println("Player 2 : Please enter your colum :");
				col=cin.nextInt();
				line=cChessboard.searchLine(col);
				player=2;
				cChessboard.Update(col,player);
			}
			else{
				col=cPCsmart.AutoPlay(nbTurns, lastColum, lastLine, cChessboard.getMap(), depth);
				player=2;
				cChessboard.Update(col,player);
			}
			nbTurns++;
			cChessboard.print();
			if (cJeu.Victoria(true,line,col,cChessboard.getMap())){
				System.out.println("H2 Won!");
				break;
			}
		}
		
	}

}
