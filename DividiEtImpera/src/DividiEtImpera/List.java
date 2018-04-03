package DividiEtImpera;

public class List {
	public Node head;
	public Node tail;
	public List listInit(){
		List l= new List();
		l.head=l.tail=null;
		return l;
	}
	public boolean empty(List l){
		return(l.head==null);
	}
	public int head(List l){
		return l.head.key;
	}
	public int tail(List l){
		return l.tail.key;
	}
	public void insertFront(List l, int x){
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
	public void insertBack(List l, int x){
		Node n= new Node();
		n.key= x;
		n.next=null;
		n.prev=l.tail;
		if(empty(l)){
			l.tail=l.head=n;
		}else{
			l.tail.next=n;
			l.tail=n;
		}
		
	}
	public boolean search(List l, int x){
		Node n = listSearch(l,x);
		return n!=null;
	}
	public Node listSearch(List l, int x){
		Node n= l.head;
		while((n!=null)&&(n.key!=x)){
			n= n.next;
		}
		return n;
		
	}
	public void listDelete(List l, Node n){
		if(n.prev!=null){
			n.prev.next= n.next;
		}else{
			l.head= n.next;
		}
		if(n.next!=null){
			n.next.prev= n.prev;
		}else{
			l.tail=n.prev;
		}
	}
	public int deleteFront(List l){
		if(l==null){
			System.err.println("Daje");
			return -1;
		}else{
			int x= l.head.key;
			listDelete(l,l.tail);
			return x;
		}
	}
	public int deleteBack(List l){
		if(l==null){
			System.err.println("GG");
			return -1;
		}else{
			int x= l.tail.key;
			listDelete(l,l.tail);
			return x;
		}
	}
	public void delete(List l, int x){
		Node n= listSearch(l,x);
		if(n!=null){
			listDelete(l,n);
		}
	}
}
