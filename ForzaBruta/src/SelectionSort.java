
public class SelectionSort {
	public void swap(int[] c,int a, int b){
		int temp=0;
		temp=c[a];
		c[a]=c[b];
		c[b]=temp;
	}
	public void selectionSort(int[] a){
		int n=a.length;
		int min;
		for(int i=0; i<n-1; i++){
			min= i;
			for(int j=i+1; j<n; j++){
				if(a[j]<a[min]){
					min=j;
				}
				swap(a,i,min);
			}
		}
	}
}
