public class ReferenceVsPrimitiveTypes {
	
	// Scorre 'text' e converte tutti i caratteri maiuscoli in minuscolo
	private static void decapitalize(StringBuffer text) {
		for (int i = 0; i < text.length(); ++i)  {
			if (Character.isUpperCase(text.charAt(i))) {
				char c = Character.toLowerCase(text.charAt(i));
				text.setCharAt(i, c);
			}
		}
	}
	
	// Questa procedura è errata: i tipi primitivi sono passati per valore
	private static void square(int n) {
		n = n * n;
	}
	
	// Questa procedura è errata: gli oggetti sono passati per riferimento
	// ma il riferimento stesso è passato per valore
	private static void squareInteger(Integer n) {
		// Autounboxing! Equivale a n.intValue() * n.intValue()
		n = new Integer(n * n);   
	}
	
	public static void main(String[] args) {
		StringBuffer x = new StringBuffer("Questa è una Prova");
		decapitalize(x);
		System.out.println("Dopo la chiamata a decapitalize(): " + x);
		
		int y = 20;
		square(y);
		System.out.println("Dopo la chiamata a square: " + y);
		
		Integer z = 20;  // Autoboxing! Equivale a Integer z = new Integer(20)
		squareInteger(z);
		System.out.println("Dopo la chiamata a squareInteger: " + z);
		
	}
}
