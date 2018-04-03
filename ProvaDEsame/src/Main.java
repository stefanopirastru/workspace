import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;

public class Main {
	enum Tipologia{MURO, START, FINISH, FREE};
	public static void main(String[] args) throws FileNotFoundException {
		FileReader in= null;
		try{
			in = new FileReader(args[0]);
		}catch(Exception e){
			System.out.println("non riesco ad aprire il file!!");
			System.exit(1);
		}
		assert(in!=null);
		Scanner scanner= new Scanner(in);
		while(scanner.hasNext()){
			scanner.next();
		}
		
		

	}

}
