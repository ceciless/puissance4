package p4;
import java.util.*;
public class Option {

	public Scanner cin= new Scanner (System.in);
	int nbligne;
	int nbColum;
	public Option() {
		// TODO 自动生成的构造函数存根
	}
	
	public Player ChosePlayer(){
		Player H2;
		int WhoIsPlayer=cin.nextInt(); // if Who=0, human; if Who=1,PC
		if (WhoIsPlayer==0){
			H2=new PlayerHumain();
		}
		else{
			H2= new PlayerComputer();
		}
		return H2;
	}
	
	private boolean isPC=true;
	private int depth=3;
	
	public boolean getIsPC(){
		return isPC;
	}
	
	public int getDepth(){
		return depth;
	}
	
	public void setPlayer(int a){
		if (a==0)
			isPC=true;
		else
			isPC=false;
	}
	
	public void setDepth(int hardness){
		depth=hardness;
	}
	
	
	public String ChoseColor(){
		int WhichColor=cin.nextInt();
		String color;
		if (WhichColor==2){
			color="pink";
		}
		else{
			color="blue";
		}
		return color;
	}


	

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

	
}
