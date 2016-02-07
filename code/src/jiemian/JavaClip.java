package jiemian;

import java.applet.AudioClip; 

import java.io.*; 
import java.applet.Applet; 
import java.awt.Frame; 
import java.net.MalformedURLException; 
import java.net.URL; 

public class JavaClip extends Frame{ 

public JavaClip(){ 
super(); 
} 
public static void main(String args[]) { 
try { 
URL cb; 
File f = new File("C:\\Documents and Settings\\Administrator\\桌面\\刀出鞘.wav"); //引号里面的是音乐文件所在的绝对鹿筋
cb = f.toURL(); 
AudioClip aau; 
aau = Applet.newAudioClip(cb); 
//aau.play();
aau.loop();
//循环播放 aau.play() 单曲 aau.stop()停止播放 
JavaClip frame=new JavaClip(); 

//frame.setBounds(0, 0, 300, 200); 
//frame.setVisible(true); 

} catch (MalformedURLException e) { 
e.printStackTrace(); 
} 

} 
}