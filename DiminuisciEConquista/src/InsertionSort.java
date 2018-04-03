
public class InsertionSort {
	public void insertionSort(int[] a){
		int n=a.length;
		int v=0;
		int j=0;
		for(int i=1;i<n-1;i++){
			v=a[i];
			j=i-1;
			while((j>0)&&(a[j]<v)){
				a[j+1]=a[j];
				j=j-1;
				
			}
			a[j+1]=v;
		}
	}
}
