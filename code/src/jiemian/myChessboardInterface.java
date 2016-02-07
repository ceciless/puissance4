package jiemian;


import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputListener;

import p4.*;
public class myChessboardInterface extends JFrame implements  MouseListener {
	

	BufferedImage image=null;
	BufferedImage chessboard=null;
	BufferedImage button=null;
	int player=1;
	int x=0;
	int y=0;
	int nbTurns=0;
	int col;
	int row;
	
	boolean isNotPC=false;
	boolean canPlay=true;  // if someone has won the game and has not click the button yet, canPlay=false
	boolean flag=true;
	boolean Restart=false; // judge when click button new game
	boolean PlayHumain=false;//judge when Humain VS PC, person do not chose his position and click out of board, 
								// in order not to let the PC drop token 
	
	Chessboard board   = new Chessboard ();
	Jeu jeu  = new Jeu();
	Option option = new Option();
	PCsmart PCplay = new PCsmart();
	public myChessboardInterface() {
		//JFrame jf=new JFrame();
		this.setVisible(true);//see the interface
		this.setTitle("puissence 4");//the title of interface
		this.setSize(600, 450);
		
		this.setResizable(false);//can't change the size of interface
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//when we close the interface, the program also finished
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int height=Toolkit.getDefaultToolkit().getScreenSize().height;
		//System.out.println("width :"+width);
		//System.out.println("height :"+height);
		this.setLocation((width-600)/2, (height-400)/2);//make the interface in the place center
		
		try{
			image=ImageIO.read(new File("E:/images.jpg"));
			
		}catch(IOException e){
			e.printStackTrace();
		}
		
		try {
			chessboard=ImageIO.read(new File("E:/chessboard.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			button=ImageIO.read(new File("E:/button.jpg"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.addMouseListener(this);
		
		
	}

	
	public void paint(Graphics g){
		g.drawImage(image,0,0,this);
		
		g.drawImage(chessboard, 70,90,this);
		
		g.drawImage(button, 420, 95, this);
		g.drawImage(button, 420, 145, this);
		g.drawImage(button, 420, 195, this);
		g.drawImage(button, 420, 245, this);
		g.drawImage(button, 420, 295, this);
		g.drawImage(button, 420, 345, this);
		
		g.setColor(Color.pink);
		
		g.setFont(new Font("Garamond",Font.BOLD,20));
		g.drawString("Play", 470, 120);
		g.drawString("Option", 460, 170);
		g.drawString("Give Up", 460, 220);
		g.drawString("Help", 465, 270);
		g.drawString("Others", 465, 320);
		
		g.drawString("Exit", 475, 370);
		
		g.drawString("Joueur :", 135, 75);
		if (player==1){
			g.drawString("Player 1", 245, 75);
		}
		else{
			g.drawString("Player 2", 245, 75);
		}
		int[][] tab=board.getMap();
		//board.print();
		for (int i=0;i<tab.length;i++){
			for (int j=0;j<tab[0].length;j++){
				if (tab[i][j]!=0){
					if (tab[i][j]==1){
						g.setColor(Color.PINK);
						//g.fillOval(108+50*j,120+45*i, 35, 35);  
						g.fillOval(85+41*j,108+46*i, 35, 35);
						//col=85+34*colum;//distance between two chess in the direction of horizontal
						//row=108+line*45;
					}
					else{
						g.setColor(Color.BLUE);
						//g.fillOval(108+50*j,120+45*i, 35, 35); 
						g.fillOval(85+41*j,108+46*i, 35, 35);  
					}
				}
			}
		}
	}
	
	
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		//System.out.println("mouse is clicked");
		//JOptionPane.showMessageDialog(this, "clicke");
		
	}

	
	
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("the position of mouse in the axe X is:"+e.getX());
		System.out.println("the position of mouse in the axe Y is:"+e.getY());
		x=e.getX();
		y=e.getY();
		int colum;
		int line;
		int lastColum;
		int lastLine;
		boolean joueur;
		
		//handle of the zone of board for playing
		if (x>=70 && x<=380 && y>=90 && y<=385){
			if (x-120<0){
				colum=0;
			}else{
				colum=(x-120)/40+1;
			}
			System.out.println("Colum is "+colum);
			if (!board.ColumIsFull(colum)){
				board.Update(colum,player);
				line=board.searchLine(colum);
				lastColum=colum;
				lastLine=line;
				//col=85+34*colum;//distance between two chess in the direction of horizontal
				//row=108+line*45;//distance between two chess in the direction of vertical
				this.repaint();
				nbTurns++;
				if (player==1)joueur=true;
				else joueur=false;
				if(jeu.Victoria(joueur, line, colum, board.getMap() )){
					if (joueur){
						JOptionPane.showMessageDialog(this, "player 1 won!congratulations!! *^o^*", "", JOptionPane.INFORMATION_MESSAGE);
						//JOptionPane.showMessageDialog(this, "Player 1 Won !");
						canPlay=false;
						//System.out.println("H1 won!");
						//t.suspend();
					}
					else{
						JOptionPane.showMessageDialog(this, "Player 2 Won!congratulations!! *^o^*", "", JOptionPane.INFORMATION_MESSAGE);
						//JOptionPane.showMessageDialog(this, "Player 2 Won !");
						canPlay=false;
						//System.out.println("H2 won!");
						//t.suspend();
					}
					if (jeu.tabPlein(board.getMap())){
						JOptionPane.showMessageDialog(this, "Board is full!", "", JOptionPane.INFORMATION_MESSAGE);
						//JOptionPane.showMessageDialog(this, "Board is all filled !");
						canPlay=false;
						//System.out.println("Match nul");
					}
				}
				//player=player
				//player=(player+1)%3;
				//if (player==0){player++;}
			}
			/*else{
				JOptionPane.showMessageDialog(this, "Please click New Game to restart game !", "Error Information", JOptionPane.WARNING_MESSAGE);
				//JOptionPane.showMessageDialog(this, "This colum if full, Please chose other colums !");
			}
		}
		else{
			JOptionPane.showMessageDialog(this, "Please click New Game to restart game !", "Error Information", JOptionPane.ERROR_MESSAGE);
			//JOptionPane.showMessageDialog(this, "Please click New Game to restart game !");
		}*/
	
			
			/*if (player){
				player=2; 
			}
			else{
				player=1;
			}*/
		}
		//handle of button
		else{
			/* 
			g.drawImage(button, 420, 95, this);
			g.drawImage(button, 420, 145, this);
			g.drawImage(button, 420, 195, this);
			g.drawImage(button, 420, 245, this);
			g.drawImage(button, 420, 295, this);
			g.drawImage(button, 420, 345, this);*/
			//handle of button Play 
			if (x>=420 && x<=565 && y>=95 && y<=130){
				int result=JOptionPane.showConfirmDialog(this, "Are you ready?");
				if(result==0){
					JOptionPane.showMessageDialog(this,"Good luck!! ~(*^O^*)~");
					String username=JOptionPane.showInputDialog(this,"please enter your name.");
					if(username!=null){
						JOptionPane.showMessageDialog(this,"your name is "+username);
					//System.out.println(username);
					}
					else{
						JOptionPane.showInputDialog(this,"please enter your name again.");
					}
				}	
			}
		}
		
			//handle of button option
			if (x>=420 && x<=565 && y>=145 && y<=180){
				Object[] ChosePlayer ={"SuperComputerMan(IA)","Humain"};
				Object[] ChoseHardness={"Easy *^O^*","Middle ^-^","Hard ToT"};
				
				int nbChosePlayer=JOptionPane.showOptionDialog(this, "which one you want to play with, humain or IA?", "player", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, ChosePlayer, "Humain VS SuperComputerMan(IA)");
				option.setPlayer(nbChosePlayer);
				if (nbChosePlayer==0){
					int nbChoseHardness=JOptionPane.showOptionDialog(this, "Please Chose the level of the game", "Gmae's level", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, ChoseHardness, "Easy");
					option.setDepth(nbChoseHardness);
					isNotPC=false;
				}
			}
			
			//handle of button Give Up
			if (x>=420 && x<=565 && y>=195 && y<=230){
				int result=JOptionPane.showConfirmDialog(this, "Do you want to give up?");
				if (result==0){
					JOptionPane.showMessageDialog(this, "Regret,You Lose.", "", JOptionPane.WARNING_MESSAGE);
					
					//canPlay=false;
				}
			}
			
			//handle of button help
			if (x>=420 && x<=565 && y>=245 && y<=280){
				JOptionPane.showMessageDialog(this,"use mouse to click the chessborad, and get four pion in a row horizontally,"
						+ " vertically,slash, or antil-slash.And then, you will win. Good luck!!", "Rules of the game", JOptionPane.INFORMATION_MESSAGE);
			}
			// handle of button others
			if (x>=420 && x<=565 && y>=295 && y<=330){
				
				JOptionPane.showMessageDialog(this, "Auther : GUO Xinrui & REN Sisi.", "Auther",JOptionPane.INFORMATION_MESSAGE);
			}
			//handle of button Exit
			if(x>=420 && x<=565 && y>=345 && y<=380){
				int result=JOptionPane.showConfirmDialog(this, "Do you want exit this game?");
				if(result==0){
					System.exit(0);
				}
				
				
			}
		}
	
	

	
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}
	public void mouseEntered(MouseEvent e){
		
	}
	
	public void mouseExited(MouseEvent e){
		
	}
	
	public static void main(String args[]){
		myChessboardInterface cmyChessboardInterface = new myChessboardInterface();
	}
}
	





