package DividiEtImpera;
public class MergeSort2 {
	public void mergeSort2(int[] a){
		int n=a.length;
		int[] b= new int[n];
		mergeSortRec(a,b,0,n-1);
	}
	public void mergeSortRec(int[] a, int[] b, int lo, int hi){
		int mid;
		if(hi>lo){
			mid=lo+(hi-lo)/2;
			mergeSortRec(a,b,lo,mid);
			mergeSortRec(a,b,mid+1,hi);
			merge2(a,b,lo,hi,mid);
		}
	}
	public void merge2(int[] a, int[] b, int lo, int hi, int mid){
		int i=0;
		int j=0;
		for(int k=lo;k<=hi;k++){
			i=lo;
			j=mid+1;
		}
		for(int k=lo;k<=hi;k++){
			if(i>mid){
				a[k]=b[j];
				j++;
			}else if(j>hi){
				a[k]=b[i];
				i++;
			}else if(b[j]<b[i]){
				a[k]=b[j];
				j++;
			}else{
				a[k]=b[i];
				i++;
			}
		}
	}
}
