import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.applet.*;

public class Car extends Canvas implements ActionListener,Runnable
{
	JFrame f;
	JButton b1,b2,b3;
	Graphics g;
	Thread t;
	int rx=5;
	int ox1=25;
	int ox2=180;
	int ix=40;	
	String d;
	String p="s";
	Car()
	{
		System.out.println("Constructor");
		t=new Thread(this);
		f=new JFrame("Moving Car");
		b1=new JButton("Start");
		b2=new JButton("Stop");
		b3=new JButton("Back");
		b1.setBounds(100,550,100,25);
		b2.setBounds(250,550,100,25);
		b3.setBounds(400,550,100,25);
		f.setBounds(0,0,1365,1000);
		this.setBounds(0,0,1355,500);
		this.setBackground(Color.yellow);
		this.setForeground(Color.red);
		f.add(b1);
		f.add(b2);
		f.add(b3);
		f.add(this);
		b1.addActionListener(this);
		b2.addActionListener(this);
		b3.addActionListener(this);
		f.setLayout(null);
		f.setVisible(true);
	}
	public void actionPerformed(ActionEvent ae)
	{
		try
		{
			if(ae.getSource()==b1)
			{
				d="forward";
				if(p.equals("s"))
				{
					t.start();
				}
				else if(p.equals("stop"))
				{
					t.resume();
				}
				t.resume();
				
			}
			else if(ae.getSource()==b2)
			{
				t.suspend();
				p="stop";
				b1.setEnabled(true);
			}
			else
			{
				if(p.equals("stop"))
				{
					t.resume();	
				}
				d="back";
			}
		}
		catch(Exception e)
		{
			System.out.println("Error"+e);
		}
	}
	public void run()
	{
		while(true)
		{
			if(d.equals("forward"))
			{
				rx+=1;
				ox1+=1;
				ox2+=1;
				ix+=1;
			}
			else
			{
				rx-=1;
				ox1-=1;
				ox2-=1;
				ix-=1;
			}
			repaint();	
			try
			{
				if((rx+300)==1290)
				{
					t.sleep(5000);
				}
				else
				{
					t.sleep(20);
				}
			}
			catch(Exception e){}
		}
	}
	public void paint(Graphics g)
	{
		Toolkit t=Toolkit.getDefaultToolkit();
		Image i=t.getImage("D:/photo/c8.png");

		if((rx+300)<1290)
		{
			System.out.println("Run");
			g.setColor(Color.blue);
			g.fillRect(rx,175,300,150);
			g.drawImage(i,ix,100,this);
			g.setColor(Color.black);
			g.fillOval(ox1,275,100,100);
			g.fillOval(ox2,275,100,100);
			g.drawLine(5,375,1300,375);
			g.drawLine(1300,125,1300,375);
			g.drawLine(1290,125,1290,375);
			g.setColor(Color.green);
			g.fillOval(1245,25,100,100);
			g.setColor(Color.black);
			g.drawString("STOP",1280,80);
		}
		else if((rx+300)==1290)
		{
			g.setColor(Color.blue);
			g.fillRect(rx,175,300,150);
			g.drawImage(i,ix,100,this);
			g.setColor(Color.black);
			g.fillOval(ox1,275,100,100);
			g.fillOval(ox2,275,100,100);
			g.drawLine(5,375,1300,375);
			g.drawLine(1300,125,1300,375);
			g.drawLine(1290,125,1290,375);
			
			g.setColor(Color.red);
			g.fillOval(1245,25,100,100);
			g.setColor(Color.black);
			g.drawString("STOP",1280,80);
		}
		else
		{
			rx=5;
			ox1=25;
			ox2=180;
			ix=40;
			g.setColor(Color.white);
			g.fillRect(rx,175,300,150);
			g.drawImage(i,ix,100,this);
			g.setColor(Color.black);
			g.fillOval(ox1,275,100,100);
			g.fillOval(ox2,275,100,100);
			g.drawLine(5,375,1300,375);
			g.drawLine(1300,125,1300,375);
			g.drawLine(1290,125,1290,375);
			
			g.setColor(Color.green);
			g.fillOval(1245,25,100,100);
			g.setColor(Color.black);
			g.drawString("STOP",1280,80);
		}
	}
	public static void main(String st[])
	{
		Car c=new Car();
	}
}