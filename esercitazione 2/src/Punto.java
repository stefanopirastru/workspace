import edu.princeton.cs.algs4.StdDraw;

public class Punto implements Drawable {
	public double x;
	public double y;
	
	public Punto(double x, double y){ //NB ambiguo
		this.x = x;  //disambiguazione
		this.y = y;
	}
	public void draw(){
		StdDraw.point(x, y);
	}
}
