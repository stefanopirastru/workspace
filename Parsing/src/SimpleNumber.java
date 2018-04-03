
public class SimpleNumber extends SimpleExpression<Integer> {
	private int value;
	
	public SimpleNumber(int value) {
		this.value = value;
	}
	
	public Integer evaluate() {
		return value;
	}
	
	public String toString() {
		return String.valueOf(value);
	}
}
