
public class TestComplex {

	public static void main(String[] args) {
		Complex a = new Complex(1,0);
		Complex b = new Complex(0,-1);
		
		a.add(b);
		
		System.out.println("a: " + a);
		System.out.println("b: "+ b);

	}

}
