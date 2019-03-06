import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;
public class Painter extends JPanel implements MouseListener,KeyListener,ComponentListener{

	Menu m;
	double MoveX,MoveY; 
	Timer t;
	 static double lowerlimitX=400,upperlimitX=500;
	 static double lowerlimitY=400,upperlimitY=500;
	Integer score=0;
	public double angle=Math.PI/2;
	ArrayList<Worm> bots=new ArrayList<>();
	Worm bot;
	Worm player;
	Food food=new Food(500),nfood=new Food(1000);



	public Painter(Menu m) {
		super();
		addComponentListener(this);
		MoveX=0;
		MoveY=0;
		this.m=m;
		nfood.c=Color.PINK;
		player=new Worm(m.txtname.getText());


		bots.add(new Worm(400,100));
		bots.add(new Worm(700,200));
		bots.add(new Worm(900,300));
		bots.add(new Worm(1000,400));
		bot=bots.get(0);

		t=new Timer(60, new ActionListener() {

			@Override
			public void
			actionPerformed(ActionEvent e) {

				botmove();
				double X=player.head.x,Y=player.head.y;
				Double angle=getAngle(player.head);
				if(angle!=null)
				{
					X+=(player.speed*Math.sin(angle));
					Y+=(player.speed*Math.cos(angle));
					player.move(new Point2D.Double(X, Y));
					
				}
				intersect();
				
				if(lowerlimitX>X)
					moveFrame(lowerlimitX-X,0.0);
				else if(X>upperlimitX)
					moveFrame(upperlimitX-X, 0.0);

				if(lowerlimitY>Y)
					moveFrame(0.0,lowerlimitY-Y);
				else if(Y>upperlimitY)
					moveFrame(0.0,upperlimitY-Y);
					
				repaint();
			}
		});

		t.start();
	}
	Double getAngle(Point2D.Double p) {

		Point mouse=getMousePosition();
		if(mouse==null)
			return null;

		if(mouse.y!=p.y) {
			angle=Math.atan((p.x-mouse.x)/(p.y-mouse.y));
			

		}
		else
			angle=Math.PI/2;
		if(p.y-mouse.y>0)
			angle-=Math.PI;
		 return angle;
	}

	protected void moveFrame(double d, double e) {
		MoveX+=d;
		MoveY+=e;
		player.setLocation(d,e);
		food.setLocation(d, e);
		nfood.setLocation(d, e);
		for(Worm worm:bots) {worm.setLocation(d, e);}
		//bots.get(0).setLocation(d, e);
		//bot.setLocation(d, e);
	}
	protected void botmove() {
		for (int i=0;i<bots.size();i++) {
			Worm worm=bots.get(i);
			
			double angle=food.enemymove(worm.head);
			
			double X = (worm.speed*Math.sin(angle));
			double Y = (worm.speed*Math.cos(angle));
			worm.move(new Point2D.Double(worm.head.getX()+X, worm.head.getY()+Y));
			//worm.setLocation(1*Math.sin(angle),1*Math.cos(angle));
			intersect();
		}
	}
	void intersect() {

		if(food.intersect(player.getShead())) {
			player.inc();
			player.speed-=0.02;
			score++;

		}
		if(nfood.intersect(player.getShead())) {
			if(player.getlenght()>1)
			player.dec();
			player.speed+=0.02;
			score--;

		}

		for (Worm worm :bots) {
			//Worm worm=bot;
			for (int i = 0; i < worm.body.size(); i++) {
				Point2D.Double d=worm.body.get(i);
				Ellipse2D.Double a=new Ellipse2D.Double(d.x, d.y, worm.Radius, worm.Radius);
				if(food.intersect(a))
				{
				worm.inc();
				worm.speed-=0.02;
				}
				if(nfood.intersect(a))
				{
				if(worm.getlenght()>1)
				worm.dec();
				worm.speed+=0.02;
				}
				if(player.intersects(a))
					bot=null;
			}
			
			if(food.intersect(worm.getShead()))
			{
				System.out.println(0);
			}
			for (Worm worms : bots) {
				if(worm==worms)
					continue;
				else
					if(worm.intersects(worms.getShead()))
						{bots.remove(worms);
						intersect();
						return;
						}
			}
			if(worm.intersects(player.getShead()))
			{
				gameover();
				
			}

		}
		for (int i = 0; i <bots.size(); i++) {
			Worm w =bots.get(i);

			if(player.intersects(w.getShead()))

				bots.remove(i);
		}



	}

	public void paint(Graphics g)
	{
		player.name=m.txtname.getText();
		super.paint(g);
		Graphics2D g2=(Graphics2D)g;
		background(g2);
		g.drawString(score.toString(), getWidth()-8*score.toString().length(), 20);
		
		player.Draw(g2);
		//bot.Draw(g2);
		for (Worm worm : bots){worm.Draw(g2);}
		food.Draw(g2);
		nfood.Draw(g2);
	}
	
	private void background(Graphics2D g2) {
		
		Random rand=new Random(1);
		setBackground(Color.darkGray);
		g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		for(int i=-2;i<=getWidth()/100+2;i++){
			for(int j=-2;j<=getHeight()/100+2;j++){
				
				g2.setColor(new Color(rand.nextInt(255),rand.nextInt(255) , rand.nextInt(255), 100));
				g2.fillOval(i*100, j*100, 200, 200);
			}
		}
		
	}
	void gameover(){
		t.stop();
		JOptionPane.showMessageDialog(this,"Game over");
		SwingUtilities.getWindowAncestor(this).dispose();
		m.addScore(new Score(player.name, score));
		m.setVisible(true);
		
	}
	@Override
	public void
	mouseClicked(MouseEvent arg0) {



	}
	@Override
	public void
	mouseEntered(MouseEvent arg0) {
		t.restart();

	}
	@Override
	public void
	mouseExited(MouseEvent arg0) {
		t.stop();

	}
	@Override
	public void
	mousePressed(MouseEvent arg0) {
		if(!arg0.isConsumed())
		{

			player.Radius=player.Radius/1.3;

			player.speed=player.speed*1.5;
		}
		arg0.consume();

	}
	@Override
	public void
	mouseReleased(MouseEvent arg0) {
		if(!arg0.isConsumed())
		{

			player.Radius=player.Radius*1.3;
			player.speed=player.speed/1.5;
		}
		arg0.consume();
	}
	@Override
	public void
	keyPressed(KeyEvent e ) {

		if(e.getKeyCode()==KeyEvent.VK_LEFT)

			angle+=Math.PI/16;

		if(e.getKeyCode()==KeyEvent.VK_RIGHT)
			angle-=Math.PI/16;
		if(e.getKeyCode()==KeyEvent.VK_ESCAPE)
		{
			m.setVisible(true);
			SwingUtilities.getWindowAncestor(this).dispose();
		}
		if(e.getKeyChar()=='s')
		{
			setting s=new setting();
			
			s.setVisible(true);
			s.setLocation(getMousePosition().x-s.getWidth()/2,getMousePosition().y-s.getHeight()/2);
			}
		if(e.getKeyChar()=='g')
		{
			gameover();
			}

	}
	@Override
	public void
	keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void keyTyped(KeyEvent
			arg0) {
		// TODO Auto-generated method stub

	}
	@Override
	public void componentHidden(ComponentEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentMoved(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void componentResized(ComponentEvent e) {
		moveFrame((getWidth()/2)-player.head.x, (getHeight()/2)-player.head.y);
		upperlimitY=getHeight()/2;
		lowerlimitY=upperlimitY-1;
		upperlimitX=getWidth()/2;
		lowerlimitX=upperlimitX-1;
		
	}
	@Override
	public void componentShown(ComponentEvent e) {
		// TODO Auto-generated method stub
		
	}
}