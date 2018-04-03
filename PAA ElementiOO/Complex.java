
public class Complex implements Comparable< Complex >{
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

	public double absValue() {
		return Math.sqrt(Math.pow(re,2) + Math.pow(im,2));
	}
	
	public String toString() {
		return "(" + Double.toString(re) + "," + Double.toString(im) + ")";
	}
	
	public int compareTo(Complex c) {
		if (this.absValue() < c.absValue()) {
			return -1;
		} else if (this.absValue() > c.absValue()) {
			return 1;
		} else {
			return 0;
		}
	}
}
