package DividiEtImpera;

public class TreeExpr {
	public NodeExpr root;
	public TreeExpr parseExpression(String s){
		TreeExpr t= new TreeExpr();
		t.root= new NodeExpr();
		int i=recursiveParseExpression(s,0,t.root);
		if (i<s.length()){
			System.err.println("fail");
			System.exit(0);
		}
		return t;
	}
	public int recursiveParseExpression(String s, int i, NodeExpr x){
		if((s.charAt(i)==0)||(s.charAt(i)==1)||(s.charAt(i)==2)||(s.charAt(i)==3)||(s.charAt(i)==4)||(s.charAt(i)==5)||(s.charAt(i)==6)||(s.charAt(i)==7)||(s.charAt(i)==8)||(s.charAt(i)==9)){
			return parseNumber(s,i,x);
		}else if(s.charAt(i)=='('){
			NodeExpr xLeft=new NodeExpr();
			i=recursiveParseExpression(s,i,xLeft);
			char xOp='$';
			if((s.charAt(i)=='+')||(s.charAt(i)=='-')||(s.charAt(i)=='*')||(s.charAt(i)=='/')){
				xOp=s.charAt(i);
			}else{
				System.out.print("l'operatore fantastico");
				System.exit(0);
			}
			NodeExpr xRight=new NodeExpr();
			i=recursiveParseExpression(s,i,xRight);
			if(s.charAt(i)!=')'){
				System.out.println("chiudi le parentesi");
				System.exit(0);
			}
			x.tipo=x.tipo.OPERATOR;
			x.op=xOp;
			x.left=xLeft;
			x.right=xRight;
			return i+1;
		}
		return 0;
	}
	public int parseNumber(String s, int i, NodeExpr x){
		int v=0;
		if((s.charAt(i)==0)&&((s.charAt(i+1)==0)||(s.charAt(i+1)==1)||(s.charAt(i+1)==2)||(s.charAt(i+1)==3)||(s.charAt(i+1)==4)||(s.charAt(i+1)==5)||(s.charAt(i+1)==6)||(s.charAt(i+1)==7)||(s.charAt(i+1)==8)||(s.charAt(i+1)==9))){
			System.out.println("cs");
			System.exit(0);
		}
		v=Character.getNumericValue(s.charAt(i));
		i++;
		while((s.charAt(i)==0)||(s.charAt(i)==1)||(s.charAt(i)==2)||(s.charAt(i)==3)||(s.charAt(i)==4)||(s.charAt(i)==5)||(s.charAt(i)==6)||(s.charAt(i)==7)||(s.charAt(i)==8)||(s.charAt(i)==9)){
			v=v*10+Character.getNumericValue(s.charAt(i));
			i++;
		}
		x.tipo=x.tipo.OPERAND;
		x.value=v;
		x.left=x.right=null;
		return i;
	}
	public int evaluate(TreeExpr t){
		return evaluateExpression(t.root);
	}
	public int evaluateExpression(NodeExpr x){
		int vl;
		int vr;
		if(x.tipo==x.tipo.OPERATOR){
			vl=evaluateExpression(x.left);
			vr=evaluateExpression(x.right);
			if(x.op=='+') return vl+vr;
			if(x.op=='-') return vl-vr;
			if(x.op=='*') return vl*vr;
			if(x.op=='/') return vl/vr;
		}else{
			return x.value;
		}
		return 0;
	}
}
