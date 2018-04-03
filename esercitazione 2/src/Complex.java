
public class Complex {
	int re;
	int im;
	public Complex(){
		re = 0;
		im = 0;
	}
	public Complex(int r, int i){
		re = r;
		im = i;
	}
	
	public add(Complex c){
		re += c.re;
		im += c.im;
	}
	public String toString(){
		return "(" + re + "," + im +")"; 
	}
}
