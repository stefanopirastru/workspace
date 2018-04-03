public class Casella {
	public int x;
	public int y;
	public enum color{WHITE, GRAY, BLACK} //bianco: non visitato, grigio: visitato, nero: muro
	color flag;
	public Casella(){
		x=0;
		y=0;
		flag= color.WHITE;
	}
	public Casella(int a, int b, color s){
		x=a;
		y=b;
		flag=s;
	}
}
