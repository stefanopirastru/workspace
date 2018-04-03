
public class SequentialSearch {
	public int sequentialSearch(int[] a, int k){
		int i=0;
		int n=a.length;
		while((i<n)&&(a[i]!=k)){
			i=i+1;
		}
		return (i<n)?i:-1;
	}
}
