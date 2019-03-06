import java.awt.geom.Point2D;

public class pc extends Point2D.Double {
	Point2D.Double prev;
	pc(Point2D.Double p)
	{
		super(p.x, p.y);
		prev=this;
	}
	pc(double x,double y)
	{
		super(x,y);
		prev=this;
	}
void set(Point2D.Double p) {
	prev=new Point2D.Double(this.x,this.y);
	setLocation(p.x,p.y);
}
}
