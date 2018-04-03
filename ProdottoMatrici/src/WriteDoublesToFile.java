import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Random;

public class WriteDoublesToFile {

	public static void main(String[] args) throws Exceptions {
			if(args.length > 1){
				int numberOfCol = Integer.parseInt(args[0]);
				int numberofRog = Integer.parseInt(args[1]);
				String outFileName = args[2];
				
				FileWriter textFileWriter = new FileWriter(outFileName);
				PrintWriter pw = new PrintWriter(textFileWriter);
				
				Random rnd = new Random(); //Guarda che devi stampare la matrice data!!
				
				pw.println(numberOfCol);
				pw.println(numberOfRog);
				for(int i=0; i<numberOfRog; i++){
					for(int j=0; j<numberOfCol; j++){
						if(j<(numberOfCol-1)){
						pw.print(rnd.nextDouble()); //NB non devo usare il randomize
						}
						else
							pw.println(rnd.nextDouble()); 
					}
				}
				
			}

	}

}
