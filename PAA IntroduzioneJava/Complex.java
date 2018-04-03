public class Complex {
	private double re;
	private double im;
	
	public Complex(double r, double i) {
		re = r;
		im = i;
	}
	
	public void add(Complex c) {
		this.re += c.re;
		this.im += c.im;
	}

}
