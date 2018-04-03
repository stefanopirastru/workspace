import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class BitVector{
		public static void main(String args[]) throws Exception{
		CreaFileTesto A = new CreaFileTesto();
		A.generaTesto();
		int maxNum;
			Boolean bVector[] = new Boolean[maxNum];
			for(int i =0; i<(maxNum-1) ;i++ ){
				bVector[i] = false;
			}
		if(args.length > 0){
		String fileName = args[0];
		FileReader numberFileReader = new FileReader(fileName);
		Scanner in = new Scanner(numberFileReader);
		int r=0;
		maxNum = in.nextInt();
		while(numberFileReader.lenght >0){
			if(r < maxNum){
			r=in.nextInt();
			bVector[r]=true;
			}
		}
		in.close();
		FileWriter gi = new FileWriter("bitVector");
		PrintWriter g = new PrintWriter(gi);
		for(int i=0; i<(maxNum-1); i++){
			if(bVector[i]=true){
				if(i==0){
					g.print(maxNum);
					g.print(" ");
					g.println(i);
				}
				else{
				g.println(i);
				}
			}
		}
		gi.close();
		g.close();
	
		
		}
	}

}

	