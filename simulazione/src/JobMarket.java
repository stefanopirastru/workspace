import java.util.*;

public class JobMarket extends Market {
	public JobMarket(
			Iterable<Buyer> buyers, Iterable<Seller> sellers,
			int equilibriumPrice,
			Random randomGenerator) {
		super(buyers, sellers, equilibriumPrice, randomGenerator);
	}

}
