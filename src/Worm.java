import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
public class Worm implements Drawable{
	public Color color;
	double angle=Math.PI/2;
	public String name;
	final static double growthRate=0.3;
	public double Radius=20;
	protected pc head;
	ArrayList<pc>body;
	double speed=Radius*0.5;
	Ellipse2D.Double getShead(){
		return new Ellipse2D.Double(head.getX(),
				head.getY(), Radius, Radius);
	}
	public Worm() {
		
		name="bot";
		Random rand=new
				Random();
		body=new ArrayList<>
		();
		head=new pc(Painter.lowerlimitX,Painter.lowerlimitX);
		body.add(head);
		color=new
				Color(rand.nextInt(255),
						rand.nextInt(255), rand.nextInt(255));
	}
	Worm(String s){
		this();
		name=s;

	}
	Worm(double X,double Y)
	{
		name="bot";
		Random rand=new
				Random();
		body=new ArrayList<>
		();
		head=new pc(X, Y);
		body.add(head);
		color=new Color(rand.nextInt(255),rand.nextInt(255), rand.nextInt(255));
	}
	Worm(String s,double x,double y)
	{
		this(x,y);
		name=s;
	}
	int getlenght() {
		return body.size();
	}
	void inc(){
		body.add(new pc(body.get(body.size()-1).prev));
		Radius+=growthRate;
	}
	void dec() {

		body.remove(body.size()-1);
		Radius-=growthRate;
	}
	void move(Point2D.Double p){
		head.set(p);
		for (int i = 1; i <
				body.size(); i++) {
			body.get(i).set(body.get(i-1).prev);
		}
	}
	public void Draw(Graphics2D g) {
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		int count=0;
		g.setColor(color);
		for (pc pc : body) {

			switch(count%6){
			case 0:
			case 1:

				g.setColor(color.brighter());
				break;
			case 2:
			case 3:

				g.setColor(color);
				break;
			case 4:
			case 5:

				g.setColor(color.darker());
				break;
			}
			count++;
			g.fill(new
					Ellipse2D.Double(pc.x, pc.y,
							Radius,Radius ));
		}

		g.setColor(color.darker());
		g.drawString(name,(float)head.x, (float)(head.y+1.5*Radius));
	}
	public void setLocation(double x,double y) {
		for (pc pc : body) {

			pc.setLocation(pc.x+x,pc.y+y);

			pc.prev.setLocation(pc.prev.x+x,pc.prev.y+y);
		}
	}
	public pc getHead() {
		return head;
	}
	public boolean intersects(Shape s) {
		for (pc pc : body) {

			Ellipse2D.Double d2=new Ellipse2D.Double(pc.x,pc.y,Radius,Radius);

			if(d2.intersects(s.getBounds2D()))
				return true;
		}
		return false;

	}
}