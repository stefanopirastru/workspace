
public class TestGeometricShapes {

	public static void main(String[] args) {

		Drawable[] shapes = new Drawable[3];
	
		shapes[0] = new Point(0.5,0.5);
		shapes[1] = new Rectangle(0.4,0.3,0.2,0.2);
		shapes[2] = new Circle(0.6,0.6,0.1);
		
		for (Drawable shape: shapes) {
			shape.draw();
		}
		
			
		for (int i = 0; i < shapes.length;  ++i) {
			if (shapes[i] instanceof Resizable) {
				Resizable shape = (Resizable) shapes[i];
				shape.resize(1.2);
			}
			shapes[i].draw();
		}
		
		
	}

}
