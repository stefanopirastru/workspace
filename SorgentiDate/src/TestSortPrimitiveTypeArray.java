import java.io.FileReader;
import java.util.Scanner;
import java.util.Arrays;

public class TestSortPrimitiveTypeArray  {

	public static final int ERROR = -1;
	public static final int OK = 0;

	
	public static void main(String[] args) throws Exception {

		if (args.length < 1) {
			System.err.println("Non ci sono abbastanza parametri!");
			System.err.println("Utilizzo: SortInputConsole <n>");
			System.err.println("Con n intero positivo (massimo numero di elementi)");
			System.exit(ERROR);
		}
		
		String fileName = args[0];
		FileReader textFileReader = new FileReader(fileName);
		Scanner in = new Scanner(textFileReader);
		
		int numberOfRecords = in.nextInt();
		double[] record = new double[numberOfRecords];
		for (int i = 0; i < numberOfRecords; ++i) {
			record[i] = in.nextDouble();
		}
		
		in.close();
		textFileReader.close();
		
				
		System.out.println("L'array inserito è: ");
		for (int j = 0; j < numberOfRecords; ++j) {
			System.out.print(record[j] + " ");
		}
		System.out.println();
				
		Arrays.sort(record, 0, numberOfRecords);
				
		System.out.println("L'array ordinato è: ");
		for (int j = 0; j < numberOfRecords; ++j) {
			System.out.print(record[j] + " ");
		}
		System.out.println();
				
	}

}
