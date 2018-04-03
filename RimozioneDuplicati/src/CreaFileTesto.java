import java.util.Random;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class CreaFileTesto {
	public void generaTesto() throws Exception{
	
		int x,y;
		String outFileName = new String("generanumeri"); 
		FileWriter numberFileWriter = new FileWriter(outFileName);
		PrintWriter pw = new PrintWriter(numberFileWriter);
		System.out.print("inserire numero elementi");
		Scanner in = new Scanner(System.in);
		x = in.nextInt();
		System.out.print("inserire estremo destro dell'insieme");
		y = in.nextInt();
		in.close();
		int[] vector = new int[x];
		Random rnd = new Random(y);
		for(int i = 0; i<x; i++){
			vector[i] = rnd.nextInt();
			if(i == 0){
			pw.print(x);
			pw.print(" ");
			pw.println(vector[i]);
			}
			else{
				pw.println(vector[i]);
			}	
		}
		pw.close();
		
		
		
		
	}
}
