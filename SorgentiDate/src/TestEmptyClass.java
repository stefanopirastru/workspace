
public class TestEmptyClass {

	public static void main(String[] args) {
		EmptyClass empty = new EmptyClass();
		
		System.out.println("empty.equals(empty): " + empty.equals(empty));
		System.out.println("empty.toString():    " + empty.toString());
		System.out.println("empty.toString():    " + empty);
		System.out.println("empty.getClass():    " + empty.getClass());
		System.out.println("empty.hashCode():    " + empty.hashCode());

	}

}
