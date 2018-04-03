package DividiEtImpera;

public class QuickSort {
	public void quicksort(int[] a,int lo, int hi){
		int s=0;
		if(lo<hi){
			s=partition(a,lo,hi);
			quicksort(a,lo,s-1);
			quicksort(a,s,hi);
		}
	}
	public int partition(int[] a, int lo, int hi){
		int x=a[hi];
		int i=lo-1;
		for(int j=lo;j<hi;j++){
			if(a[j]<=x){
				i++;
				swap(a,i,j);
			}
		}
		swap(a,i+1,hi);
		return i+1;
	}
	public void swap(int[] a, int i, int j){
		if(a[i]>a[j]){
			int temp=a[i];
			a[i]=a[j];
			a[j]=temp;
		}
	}
}
