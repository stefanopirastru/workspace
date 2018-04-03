import java.util.*;

public class GoodsMarket extends Market {

	public GoodsMarket(
			Iterable<Buyer> buyers, Iterable<Seller> sellers,
			int equilibriumPrice,
			Random randomGenerator) {
		super(buyers, sellers, equilibriumPrice, randomGenerator);
	}

}
