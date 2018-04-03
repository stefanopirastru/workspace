import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

public class WriteDoublesToFile {

	public static void main(String[] args) throws Exception {

		if (args.length > 1) {
			int numberOfRecords = Integer.parseInt(args[0]);
			String outFileName = args[1];

			FileWriter textFileWriter = new FileWriter(outFileName);
			PrintWriter pw = new PrintWriter(textFileWriter);

			Random rnd = new Random();
			
			pw.println(numberOfRecords);
			for (int i = 0; i < numberOfRecords; ++i) {
				pw.println(rnd.nextDouble());
			}
				
			pw.close();
			textFileWriter.close();
		}
	
	}
	
}
