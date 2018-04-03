import java.io.FileReader;
import java.util.Scanner;

public class Classe {
	boolean spam;
	int[] dVector;
	public void fillVector(String[] args){
		if(args.length < 1){
			System.out.println("manca il nome del file da analizzare");
			System.out.println("chiamata corretta: Classe <nome file analizzare>")
		}
		assert(args.length == 1 ){
		FileReader inputFileReader= new FileReader(args[0]);
		Scanner in = new Scanner(inputFileReader);
		String[] what= new String[10];
		for(int i=0;i<10;i++){
			what[i] = in.next();  
			
		}
		TextAnalyzerWithDictionary A = new TextAnalyzerWithDictionary();
		A.chooseInput(what);
		}
	}
}
