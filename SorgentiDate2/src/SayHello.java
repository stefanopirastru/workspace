
public class SayHello {

	public static void main(String[] args) {
		if (args.length > 0) {
			System.out.print("Ciao ");
			System.out.print(args[0]);
			System.out.println("!");
		} else {
			System.out.println("Nessun nome specificato");
		}
	}

}
