import java.util.Scanner;

public class TestString {

	public static void main(String[] args) {
	
		Scanner in = new Scanner(System.in);	
		String text1 = in.nextLine();
		String text2 = in.nextLine();
		in.close();

		if (text1 != null && text2 != null) {
	
			boolean referenceTest = (text1 == text2);
			boolean strictEquivalenceTest = (text1.equals(text2));
			boolean equivalenceTest = (text1.equalsIgnoreCase(text2));
				
			String message = "La lunghezza del primo testo (text1) è " + text1.length() + " caratteri\n" +
					"La lunghezza del secondo testo (text2) è " + text2.length() + "caratteri\n" +
					"Il risultato di text1 == text2 è " + referenceTest + "\n" +
					"Il risultato di text1.equals(text2) è " + strictEquivalenceTest + "\n" +
					"Il risultato di text1.equalsIgnoreCase(text2) è " + equivalenceTest + "\n";
		
			System.out.println(message);
		}	
	
	}

}
