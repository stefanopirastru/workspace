import java.util.*;

public class Market {

	private   Iterable<Buyer>  buyers;
	private   Iterable<Seller> sellers;
	private   int              equilibriumPrice;
	private   int              price;
	private   int              totalDemand;
	private   int              totalOffer;
	protected Random           randomGenerator;

	public Market(
			Iterable<Buyer> buyers, Iterable<Seller> sellers,
			int equilibriumPrice,
			Random randomGenerator) {
		this.buyers = buyers;
		this.sellers = sellers;
		this.equilibriumPrice = price = equilibriumPrice;
		totalDemand = totalOffer = 0;
		this.randomGenerator = randomGenerator;
	}

	public void computeMarketPrice() {
		totalDemand = 0;
		for (Buyer b : buyers) {
			totalDemand += b.demand();
		}
		totalOffer = 0;
		for (Seller s : sellers) {
			totalOffer += s.offer();
		}
		price = equilibriumPrice + ((totalDemand - totalOffer) * 100 / totalDemand); 
	}

	public int getPrice() {
		return price;
	}
	public int getTotalDemand() {
		return totalDemand;
	}
	public int getTotalOffer() {
		return totalOffer;
	}

}
