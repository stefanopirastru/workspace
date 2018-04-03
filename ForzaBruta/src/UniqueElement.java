
public class UniqueElement {
	public boolean uniqueElement(int [] a){
		int n=a.length;
		for(int i=0; i<n-1; i++){
			for(int j=i+1; j<n; j++) {
				if(a[i]==a[j]){
					return false;
				}
			}	
		}
		return true;
	}
}
