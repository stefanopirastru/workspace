import java.util.Random;

public class TestObject2 {
	
	public static void main(String[] args) {
	
		if (args.length < 1) {
			System.exit(0);
		}
	
		int size = Integer.parseInt(args[0]);
	
		Object[] mixedArray = new Object[size];
		Random rnd = new Random();
	
		for (int i = 0; i < mixedArray.length; ++i) {
			float r = rnd.nextFloat(); 
			if (r < 0.3) {
				mixedArray[i] = new EmptyClass();
			} else if (r > 0.7) {
				mixedArray[i] = new Complex(rnd.nextDouble(), rnd.nextDouble());
			} else {
				mixedArray[i] = new Integer(rnd.nextInt());
			}
		}
	
		for (Object obj : mixedArray) {
			System.out.println(obj);
		}
	}

}
