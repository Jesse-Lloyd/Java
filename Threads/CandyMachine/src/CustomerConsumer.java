// consumer class, puts in orders for candy bars and eats them
public class CustomerConsumer implements Runnable{
	
	/*
	 *  This dual array represents all possible orders a consumer can make based on the requirements
	 *  of making each bar vs how much of each ingredient the chocolate machine can hold.
	 *  the first number of each element represents the number of chocolate bars and the second is marzipan.
	 */
	private final int[][] arrayOfPossibleOrders = {{2,1},{2,0},{1,2},{1,1},{1,0},{0,3},{0,2},{0,1}};
	
	// when a consumer receives their order they need some time to consume that delicious goodness
	private final int timeToEatChocoBar = 800;
	private final int timeToEatMarzipanBar = 500;
	
	// denote the number of each bars the consumer is in possession of
	private int[] inventory = new int[2];
	
	private CandyMachine candyMachine;
	
	public CustomerConsumer( CandyMachine candyMachine)
	{
		this.candyMachine = candyMachine;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Consumer Ran");
	}
	
	// Decides on an order from arrayOfPossbleOrders
	public void decideOnOrder()
	{
		int[] order = arrayOfPossibleOrders[(int) (Math.random() * arrayOfPossibleOrders.length)];		
	}

}
