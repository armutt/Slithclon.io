import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;
public class Food implements Drawable{
	Color c=Color.black;
	ArrayList<Point2D.Double> arr;
	Random rand= new Random();
	int rangeX=1000,rangeY=1000;
	Timer t;
	public Food(int delay) {
		arr=new ArrayList<>();
		t=new Timer(delay, new ActionListener() {

			@Override
			public void
			actionPerformed(ActionEvent e) {

				arr.add(new Point2D.Double(rand.nextInt(rangeX), rand.nextInt(rangeY)));

			}
		});
		t.start();
	}
	boolean intersect(Shape s) {
		for (int i = 0; i <
				arr.size(); i++) {

			if(s.intersects(arr.get(i).getX(),
					arr.get(i).getY(), 10, 10)) {

				arr.remove(i);
				return true;
			}
		}
		return false;
	}
	public void Draw(Graphics2D g) {
		for (Point2D.Double d: arr) {
			g.setColor(c);
			g.fill(new Ellipse2D.Double(d.getX(),d.getY(),10,10));
		}
	}
	public void setLocation(double x,double y) {
		for (Point2D.Double d: arr) {

			d.setLocation(d.x+x, d.y+y);
		}

	}
	public double enemymove(Point2D.Double head) {
		
		if(arr.size()<1)
			return 0;
		int closest=0;
		double closestdis=head.distance(arr.get(closest));
		for (int i = 0; i < arr.size(); i++) {
			Point2D.Double food = arr.get(i);
			if(head.distance(food)<closestdis)
			{
				closest=i;
				closestdis=head.distance(food);
			}

		}
		double closeX=arr.get(closest).getX();double closeY=arr.get(closest).getY();
		double angle;
		if(closeY!=head.y) {
			angle=Math.atan((head.x-closeX)/(head.y-closeY));
		}
		else
			angle=Math.PI/2;
		if(head.y-closeY>0)
			angle-=Math.PI;
		return angle;
	}
}

