import java.util.Random;

public class TestObject {

	public static void main(String[] args) {
		if (args.length < 1) {
			System.exit(0);
		}
		
		int size = Integer.parseInt(args[0]);
		
		Object[] mixedArray = new Object[size];
		Random rnd = new Random();
		
		for (int i = 0; i < mixedArray.length; ++i) {
			if (rnd.nextFloat() < 0.5) {
				mixedArray[i] = new EmptyClass();
			} else {
				mixedArray[i] = new Complex(rnd.nextDouble(), rnd.nextDouble());
			}
		}
		
		for (Object obj : mixedArray) {
			System.out.println(obj);
		}

	}

}
