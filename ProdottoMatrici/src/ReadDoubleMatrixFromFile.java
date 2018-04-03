import java.util.Scanner;
import java.io.FileReader;

public class ReadDoubleMatrixFromFile {
	
	public static void main(String argvs[]) throws Exception {
		String fileName = argvs[0];
		FileReader numberFileReader = new FileReader(fileName);
		Scanner in = new Scanner(numberFileReader);
		int numberOfRog = in.nextInt();
		int numberOfCol = in.nextInt();
		double[][] record= new double[numberOfRog][numberOfCol];
		for(int i=0; i<numberOfRog; i++){
			for(int j=0; i<numberOfCol; j++){
				record[i][j]= in.nextDouble();
			}
		}
		in.close();
		numberFileReader.close();
		
	}

}
