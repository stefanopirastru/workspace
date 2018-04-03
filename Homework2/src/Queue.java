public class Queue {
	public GridWorld.Coordinate[] table;
	public int head;
	public int tail;
	public Queue QueueInit(int n){
		Queue q = new Queue();
		q.table = new GridWorld.Coordinate[n];
		q.head = q.tail = 0;	
		return q;
	}
	public boolean Empty(Queue q){
		return (q.head==q.tail);
	}
	public GridWorld.Coordinate Head(Queue q){
		return q.table[q.head];
	}
	public GridWorld.Coordinate Tail(Queue q){
		return q.table[q.tail];
	}
	public void enQueue(GridWorld.Coordinate x, Queue q){
		try{
		if(((q.tail+1)%q.table.length)!=q.head){
			q.table[q.tail] = x;
			q.tail=(q.tail+1)%q.table.length;
		}
		else {
			System.out.println("Error");
		}
		}catch(Exception e){
			System.err.println("Errore"+e);
		}
	}
	public GridWorld.Coordinate deQueue(Queue q){
		if(Empty(q)){
			System.err.println("Non Funziona");
			return null;
		}else{
			GridWorld.Coordinate x=q.table[q.head];
			q.head=(q.head%q.table.length);
			return x;
		}
	}
}
