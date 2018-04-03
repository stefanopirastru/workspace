
public class FixedSizeStack<Item> {
	private Item[] table;
	private int    top;
	
	public FixedSizeStack(int n) {
		table = (Item[]) new Object[n];
		top = -1;
	}
	
	public Item top() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("Attempt to top an empty stack");
		}
		return table[top];
	}
	
	public void push(Item x) throws ContainerFullException {
		if (top < table.length) {
			throw new ContainerFullException("Attempt to push on a full stack");
		}
		table[top] = x;
		top += 1;
	}
	
	public Item pop() throws NoSuchElementException {
		if (isEmpty()) {
			throw new NoSuchElementException("Attempt to pop from an empty stack");
		}
		Item x = table[top];
		top -= 1;
		return x;
	}
	
	public boolean isEmpty() {
		return (top < 0);
	}

}
