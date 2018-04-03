
public class TestComplex {

	public static void main(String[] args) {
		Complex c1 = new Complex(1,1);   // c1.re = 1  c1.im = 1  
		Complex c2 = new Complex(-1,-1); // c2.re = -1 c2.im = -1
		
		c1.add(c2);  // c1.re = 0 c1.im = 0  c2 invariato
	}

}
