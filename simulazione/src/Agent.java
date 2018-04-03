import java.util.Random;

public abstract class Agent {
	private static int lastId = 0;
	
	private   int     id;
	protected Random  randomGenerator;
	
	public Agent(Random randomGenerator) {
		lastId = lastId + 1;
		id  = lastId;
		this.randomGenerator = randomGenerator;
	}
	
	public int getId() {
		return id;
	}

	public abstract void update();
	
}
