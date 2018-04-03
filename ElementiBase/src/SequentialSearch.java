
public class SequentialSearch {
	public int sequentialSearch(int[] a, int k){
		int i= 0;
		int n= a.length;
		while((i<n)&&(a[i]!=k)){
			i+=1;
		}
		return(i<n)?1:-1;
		
	}
}
