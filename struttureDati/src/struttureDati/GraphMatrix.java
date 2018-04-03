package struttureDati;

public class GraphMatrix {
	public int[][] A;
	public GraphMatrix graphMInit(int n) {
		GraphMatrix b = new GraphMatrix();
		 b.A = new int[n][n];
		int i,j;
		for(i=0;i<n;i++){
			for(j=0;j<n;j++){
				b.A[i][j]=0;
			}
		}
		return b;
	}
	public void link(GraphMatrix b, int u, int v){
		b.A[u][v]= 1;
	}
	public void unLink(GraphMatrix b, int u, int v){
		b.A[u][v]=0;
	}
	public boolean areLinked(GraphMatrix b, int u, int v){
		return b.A[u][v]!=0;
	}
	
	
}
