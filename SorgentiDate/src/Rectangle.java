import edu.princeton.cs.algs4.StdDraw;

public class Rectangle extends Point implements Drawable, Resizable {

	private double base;
	private double height;
	
	public Rectangle(double x, double y, double base, double height) {
		super(x,y);
		this.base = base;
		this.height = height;
	}
	
	public void draw() {
		StdDraw.rectangle(x, y, base, height);
	}
	
	public void resize(double percentage) {
		base = base * percentage;
		height = height * percentage;
	}
}
