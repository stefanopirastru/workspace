import java.util.Arrays;
import java.util.Random;

public class TestSortReferenceTypeArray {

	public static void main(String[] args) {
		
		if (args.length < 1) {
			System.exit(0);
		}
		
		int size = Integer.parseInt(args[0]);
		
		Complex[] record = new Complex[size];
		Random rnd = new Random();
		
		for (int i = 0; i < record.length; ++i) {
			record[i] = new Complex(rnd.nextDouble(), rnd.nextDouble());
		}

		System.out.println("L'array generato è: ");
		for (Complex c : record) {
			System.out.print(c + " ");
		}
		System.out.println();
		
		Arrays.sort(record, 0, record.length);
		
		System.out.println("L'array ordinato è: ");
		for (Complex c : record) {
			System.out.print(c + "[" + c.absValue() + "]" + " ");
		}
		System.out.println();
		

	}

}
