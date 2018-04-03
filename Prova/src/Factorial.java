import java.io.IOException;
import java.util.Scanner;

public class Factorial {
	public static int factorial(int n){
		if(n==0) return 1;
		else return n*factorial(n-1);
	}
	public static void  main(String[] args) throws IOException{
		int n, c;
		System.out.println("enter an integer to calculate it's own factorial");
		Scanner in= new Scanner(System.in);
		n=in.nextInt();
		try{
			c=factorial(n);
			System.out.println("The factorial of "+ n + " is " +c);
		}catch(Exception e){
			System.out.println(e);
			System.out.println("operation denied");
		}
		in.close();
	}
}