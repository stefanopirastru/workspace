
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;
import java.io.FileReader;


public class Matrice {

	private int r;
	private int c;
	private double[][] matrix;
	
	public  Matrice( int ri, int col){
		r=ri;
		c=col;
		double[][] matrix= new double[r][c];
		int i;
		int j;
		for(i=0; i<r; i++){
			for(j=0; j<c; j++){
				matrix[i][j]=0;
			}
		}
	}
	
	public  void riempiRnd(){
		
		int i,j;
		try{
			for(i=0; i<r; i++){
				for(j=0; j<c; j++){
					matrix[i][j]=ThreadLocalRandom.current().nextDouble(0.0, 9.0);
				}
			}
		}catch(Exception e){
			System.out.print(e);
			System.exit(0);
		}
	}	
	
	public static void prodotto(Matrice a, Matrice b, Matrice c){
		if( a.r == b.c){
			for(int i=0; i<a.r; i++){
				for(int j=0; j<b.c; j++){
					for(int k=0; k<a.c; k++){
						c.matrix[i][j]+= (a.matrix[i][k]*b.matrix[k][j]);
					}
				}
			}
		}
	}
	public void stampa( ){
		
		for(int i=0; i<r; i++){
			for(int j=0; j<c; j++){
				if(j<(c-1))
				System.out.print(matrix[i][j] +"  ");
				else{
				System.out.println(matrix[i][j]+"/n");
				}
			}
		}
	}
	public static void main(String[] args) {
		Matrice mat1= new Matrice(3,3);
		Matrice mat2= new Matrice(3,3);
		mat1.riempiRnd();
		mat2.riempiRnd();
		System.out.println("La matrice A e'");
		mat1.stampa();
		System.out.println("La matrice B e'");
		mat2.stampa();
		Matrice m3= new Matrice(3,3);
		prodotto(mat1,mat2,m3);
		System.out.println( "Il loro prodotto e' uguale a:");
		m3.stampa();
	}

}
