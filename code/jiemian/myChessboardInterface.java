package jiemian;

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.File;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.event.MouseInputListener;

public class myChessboardInterface extends JFrame implements MouseInputListener {
	

	BufferedImage image=null;
	
	public myChessboardInterface() {
		//JFrame jf=new JFrame();
		this.setVisible(true);//see the interface
		this.setTitle("puissence 4");//the title of interface
		this.setSize(600, 400);
		
		this.setResizable(false);//can't change the size of interface
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//when we close the interface, the program also finished
		int width=Toolkit.getDefaultToolkit().getScreenSize().width;
		int height=Toolkit.getDefaultToolkit().getScreenSize().height;
		//System.out.println("width :"+width);
		//System.out.println("height :"+height);
		this.setLocation((width-600)/2, (height-400)/2);//make the interface in the place center
		
		try{
			image=ImageIO.read(new File("C:/images11java。jpg"));
		}catch(IOException e){
			e.printStackTrace();
		}
		this.addMouseListener(this);
		
	}

	
	public void paint(Graphics g){
		//g.drawString("pussance 4", 400, 300);
		//g.drawOval(40, 40, 40, 40);
		
		g.drawImage(image,0,0,this);
		//g.drawImage(image, arg1, arg2, arg3)
	}
	
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		System.out.println("mouse is clicked");
		JOptionPane.showMessageDialog(this, "clicke");
		
	}

	
	
	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("the position of mouse in the axe X is:"+e.getX());
		System.out.println("the position of mouse in the axe X is:"+e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseDragged(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO 自动生成的方法存根
		
	}
}




