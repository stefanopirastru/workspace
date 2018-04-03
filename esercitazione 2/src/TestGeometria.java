
public class TestGeometria {

	public static void main(String[] args) {
		Drawable[] shapes = new Drawable[2];
		shapes[0] = new Punto(0.5, 0.5); // NON RESIZABLE
		shapes[1] = new Circle(0.45,0.45,0.2); //RESIZABLE
		
		for(Drawable shape :shapes){
			shape.draw();    // NON POTREI FARE LO STESSO PER RESIZE
		}
		
		//DEINDE
		
		for(int i=0; i< shapes.length; ++i){
			if(shapes[i] instanceof Resizable){ //se è un istanza di
				Resizable shape = (Resizable)  shapes[i]; //cast da effetture con cautela!!
				shape.resize(1.2);
			}
			shapes[i].draw();  //ridisegna le figure
		}
		

	}

}
