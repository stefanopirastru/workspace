import java.util.*;

public class Simulator {
	
	private static final int SIMULATION_CYCLES = 50;
	
	private static final int FAMILIES_NO = 10;
	private static final int COMPANIES_NO = 3;
	
	private static final int EQUILIBRIUM_PRICE_GOODS = 10;
	private static final int EQUILIBRIUM_PRICE_JOB = 200;
	
	private static final int INITIAL_BALANCE = 100;
	private static final int PURCHASE_CAPACITY = 100;
	private static final int WORK_CAPACITY = 10;
	
	private static final int INITIAL_FUNDS = 10000;
	private static final int PRODUCTIVE_CAPACITY = 50;
	private static final int PRODUCTIVE_EFFICIENCY = 5;
	
	public static void main(String[] args) {
		Random randomGenerator = new Random();
		
		// Index to families and companies as agents (for updating)
		ArrayList<Agent> families = new ArrayList<Agent>();
		ArrayList<Agent> companies = new ArrayList<Agent>();
			
		// Index to actors in the job market
		ArrayList<Buyer> jobBuyers = new ArrayList<Buyer>();
		ArrayList<Seller> jobSellers = new ArrayList<Seller>();
		
		// Index to actors in the goods market
		ArrayList<Buyer> goodBuyers = new ArrayList<Buyer>();
		ArrayList<Seller> goodSellers = new ArrayList<Seller>();
		
		// Create job and goods markets
		Market jobMarket = new JobMarket(jobBuyers, jobSellers, EQUILIBRIUM_PRICE_JOB, randomGenerator);
		Market goodsMarket = new GoodsMarket(goodBuyers, goodSellers, EQUILIBRIUM_PRICE_GOODS, randomGenerator);
		
		// Initialize family agents and add them to the indexes
		for (int i = 0; i < FAMILIES_NO; ++i) {
			// Each family is an agent, it sells job and it buys goods
			Family f = new Family(INITIAL_BALANCE, PURCHASE_CAPACITY, WORK_CAPACITY, 
					jobMarket, goodsMarket, randomGenerator);
			families.add(f);
			jobSellers.add(f);
			goodBuyers.add(f);
		}
		
		// Initialize company agents and add them to the indexes
		for (int i = 0; i < COMPANIES_NO; ++i) {
			// Each company is an agent, it sells good and it buys job
			Company c = new Company(INITIAL_FUNDS, PRODUCTIVE_CAPACITY, PRODUCTIVE_EFFICIENCY,
					jobMarket, goodsMarket, randomGenerator);
			companies.add(c);
			jobBuyers.add(c);
			goodSellers.add(c);
		}
		
		printCurrentStatus(-1, families, companies, jobMarket, goodsMarket);
		
		// Start the simulation
		for (int i = 0; i < SIMULATION_CYCLES; ++i) {
		
			// Adjust price in the job and goods market
			jobMarket.computeMarketPrice();
			goodsMarket.computeMarketPrice();
			
			// Update companies and families
			for (Agent a : companies) {
				a.update();
			}
			for (Agent a : families) {
				a.update();
			}
	
			// Print status
			printCurrentStatus(i, families, companies, jobMarket, goodsMarket);
		
		}
	}
	
	private static void printCurrentStatus(
			int iteration,
			Iterable<Agent> families, Iterable<Agent> companies,
			Market jobMarket, Market goodsMarket) {
		System.out.println("INIZIO ITERAZIONE " + iteration + "\n");
		System.out.println("LAVORO domanda: " + jobMarket.getTotalDemand() + 
						   " offerta: " + jobMarket.getTotalOffer() + 
						   " prezzo: " + jobMarket.getPrice());
		System.out.println("BENI   domanda: " + goodsMarket.getTotalDemand() + 
				   		   " offerta: " + goodsMarket.getTotalOffer() + 
				           " prezzo: " + goodsMarket.getPrice() + "\n");
		for (Agent a : families) {
			System.out.println(a);
		}
		for (Agent a : companies) {
			System.out.println(a);
		}
		System.out.println("FINE ITERAZIONE " + iteration + "\n");	
	}
	
}
