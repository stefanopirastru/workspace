
public class FindMax {
	public int findMax(int[] a){
		int m= 0;
		int n= a.length;
		for(int i=0; i<n; i++){
			if(a[i]<a[m]){
				m= i;
			}
		}
		return m;
	}
}
