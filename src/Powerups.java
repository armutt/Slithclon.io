import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Point2D;

import javax.swing.Timer;

public class Powerups implements MouseListener,KeyListener{
	int powercount;
	private boolean turbo_on;
	Worm player;
	Timer foodtimer,turbotimer;
	Point2D.Double superfood;
	boolean isTurbo() {
		return turbo_on;
	}
	@Override
	public void keyPressed(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{
			if(!turbo_on)
			{

				player.Radius=player.Radius/1.3;
				player.speed=player.speed*1.5;
				turbo_on=true;
			}
			
		}
		
	}
	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode()==KeyEvent.VK_SPACE)
		{
			if(turbo_on)
			{

				player.Radius=player.Radius*1.3;
				player.speed=player.speed/1.5;
				turbo_on=false;
			}
			
		}
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		if(!turbo_on)
		{

			player.Radius=player.Radius/1.3;
			player.speed=player.speed*1.5;
			turbo_on=true;
		}
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		if(turbo_on)
		{

			player.Radius=player.Radius*1.3;
			player.speed=player.speed/1.5;
			turbo_on=false;
		}
		
	}
	

}
