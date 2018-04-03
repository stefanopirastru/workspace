import java.util.Random;

public class Family extends Agent implements Buyer, Seller {

	private int         balance;         
	private int         purchaseCapacity;  // in number of goods
	private int         workCapacity;      // in number of hours
	private Market      jobMarket;
	private Market      goodsMarket;
	private int         offeredWork;
	private int         demandedGoods;

	public Family(int initialBalance,
			int purchaseCapacity,
			int workCapacity,
			Market jobMarket, Market goodsMarket,
			Random randomGenerator) {
		super(randomGenerator);
		this.balance = initialBalance;
		this.purchaseCapacity = purchaseCapacity;
		this.workCapacity = workCapacity;
		this.jobMarket = jobMarket;
		this.goodsMarket = goodsMarket;
		offeredWork = demandedGoods = 0;
	}

	public int offer() {
		offeredWork = randomGenerator.nextInt(workCapacity) + 1;
		return offeredWork;
	}

	public int demand() {
		if (balance > 0) {
			demandedGoods = randomGenerator.nextInt(purchaseCapacity) + 1;
		} else {
			demandedGoods = 0;
		}
		return demandedGoods;
	}

	public void update() {
		int pay = offeredWork * jobMarket.getPrice();
		balance += pay;

		int expenses = demandedGoods * goodsMarket.getPrice();
		balance -= expenses;
	}

	public String toString() {
		return "Famiglia: " + getId() + "; bilancio: " + balance;
	}
}
