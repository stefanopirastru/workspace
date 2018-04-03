
public class Circle extends Punto implements Drawable implements Resizable { /*figlia di punto!!
 														implementatore di 
														Drawable && Resizable!!*/
	
	public double radius;
	
	public Circle(double xc, double yc, double radius){
		super(xc,yc); //puntatore alla superclasse
		this.radius = radius;
	}
	
	public void draw(){
		StdDraw.circle(x, y, radius);
	}
	
	public void resize(double percent){
		radius *= percent;
	}
}
