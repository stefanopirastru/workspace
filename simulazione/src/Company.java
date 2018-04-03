import java.util.Random;

public class Company extends Agent implements Buyer, Seller {

	private int         balance;  
	private int         capacity;        // maximum number of hours of work that can be demanded
	private int         efficiency;      // number of goods produced per hour of work
	private Market      jobMarket;
	private Market      goodsMarket;
	private int         demandedWork;    // in number of hours
	private int         offeredGoods;    // in quantity

	public Company(
			int initialBalance,
			int capacity,
			int efficiency,
			Market jm, Market gm,
			Random randomGenerator) {
		super(randomGenerator);
		this.balance = initialBalance;
		this.capacity = capacity;
		this.efficiency = efficiency;
		jobMarket = jm;
		goodsMarket = gm;
		offeredGoods= demandedWork = 0;
	}

	public int offer() {
		offeredGoods = efficiency * demandedWork;
		return offeredGoods;
	}

	public int demand() {
		demandedWork = randomGenerator.nextInt(capacity) + 1;
		return demandedWork;
	}

	public void update() {
		int revenue = offeredGoods * goodsMarket.getPrice();
		balance += revenue;

		int salaries = demandedWork * jobMarket.getPrice();
		balance -= salaries;
	}

	public String toString() {
		return "Impresa: " + getId() + "; capitale: " + balance;
	}
	
}
