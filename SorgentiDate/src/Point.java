import edu.princeton.cs.algs4.StdDraw;

public class Point implements Drawable {

	public double x;
	public double y;
	
	public Point(double x, double y) {
		this.x = x; this.y = y;
	}
	
	public double distanceTo(Point other) {
		return Math.sqrt(Math.pow(other.x - this.x, 2) + (Math.pow(other.y - this.y, 2)));
	}
	
	public void draw() {
		StdDraw.point(x, y);
	}
	
}
