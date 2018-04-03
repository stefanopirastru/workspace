import java.util.Scanner;
import java.io.FileReader;

public class ReadDoublesFromFile {

	public static void main(String[] args) throws Exception {
		
		if (args.length > 0) {
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
			
			for (int i = 0; i < numberOfRecords; ++i) {
				System.out.println(record[i]);
			}
		}

	}

}
