package DividiEtImpera;

public class RevList extends List {
	public RevList listInit(){
		RevList l= new RevList();
		l.head=l.tail=null;
		return l;
	}
	public RevList reverse(RevList l){
		RevList r= listInit();
		return reverseRec(l.head, r);
	}
	public void insertFront(RevList l, int x){
		Node n= new Node();
		n.key=x;
		n.next=l.head;
		n.prev=null;
		if(empty(l)){
			l.tail=l.head=n;
		}else{
			l.head.prev=n;
			l.head=n;
		}
	}
	public RevList reverseRec(Node x, RevList l){
		if(x==null) return l;
		else{
			int k =x.key;
			l.insertFront(l, k);
			return reverseRec(x.next, l);
		}
	}
}
