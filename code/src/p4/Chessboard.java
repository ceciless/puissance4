package p4;

public class Chessboard {

	private int[][] map= new int[6][7] ;
	
	public Chessboard() {
		// TODO 自动生成的构造函数存根
	}
	
	public boolean ColumIsFull(int col){
		if (map[0][col]!=0)return true;
		else return false;
	}
	
	public void init(){
		for (int i=0;i<map.length;i++){
			for (int j=0;j<map[0].length;j++){
				map[i][j]=0;
			}
		}
	}
	
	public int searchLine(int col){
		for (int line=5;line>=0;line--){
			if (map[line][col]==0)
				return line;
		}
		return -1;
	}
	
	public void Update(int col,int player){
		int row=searchLine(col);
		map[row][col]=player;
	}
	
	public void print(){
		for (int i=0;i<map.length;i++){
			for (int j=0;j<map[0].length;j++){
				System.out.print(map[i][j]+" | ");
			}
			System.out.println();
		}
	}
	
	public int[][] getMap(){
		return map;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO 自动生成的方法存根

	}

}
