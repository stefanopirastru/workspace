import edu.princeton.cs.algs4.StdDraw;


public class Circle extends Point implements Drawable, Resizable {

	private double radius;
	
	public Circle(double x, double y, double radius) {
		super(x,y);
		this.radius = radius;
	}
	
	public void draw() {
		StdDraw.circle(x, y, radius);
	}
	
	public void resize(double percentage) {
		radius = radius * percentage;
	}
}
