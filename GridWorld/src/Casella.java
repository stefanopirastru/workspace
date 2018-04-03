
public class Casella {
	public int x;
	public int y;
	public enum colore{WHITE, GREY, BLACK} //bianco== non visitato ,grigio==  visitato, nero  è un muro!
	colore flag;
	public Casella(){
		x=0;
		y=0;
		flag= colore.WHITE;
	}
	public Casella(int a, int b, colore stato){
		x=a;
		y=b;
		flag=stato;
	}
}
