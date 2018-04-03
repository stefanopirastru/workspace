
public class Parser {
	public void parseExpression(String s){
		int i=recursiveParseExpression(s,0);
		if(i<s.length()){
			System.err.println("Fail");
		}
	}
	public int recursiveParseExpression(String s,int i){
		if(s.charAt(i)>='0'&&s.charAt(i)<='9'){
			return parseNumbers(s,i);
		}else if(s.charAt(i)=='('){
			i=recursiveParseExpression(s,i+1);
			if(s.charAt(i)!='+'||s.charAt(i)!='-'||s.charAt(i)!='*'||s.charAt(i)!='/'){
				System.err.println("None");
			}
			i=recursiveParseExpression(s,i+1);
			if(s.charAt(i)!=')'){
				System.err.println("Nothing");
			}
			return i+1;
		}else{
			System.err.println("Bad");
			return -1;
		}
	}
	public int parseNumbers(String s, int i){
		if((s.charAt(i)=='0')&&(s.charAt(i+1)>='0'&&s.charAt(i+1)<='9')){
			System.out.print("No Good");
		}else i++;
		int n=s.length();
		while((s.charAt(i)>='0'&&s.charAt(i)<='9')&&i<n){
			i++;
		}
		return i;
	}
}
