import java.util.Scanner;

public class ComputeSumConsole {

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);

		System.out.println("Primo numero");
		int m = in.nextInt();
				
		System.out.println("Secondo numero");
		int n = in.nextInt();
				
		in.close();
		
		int z = m + n;
		System.out.println("Somma: " + z);
		
	}

}
