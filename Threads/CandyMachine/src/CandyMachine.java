
public class CandyMachine {

	
	private final int NutsMAX = 4;
	private final int CaramelMAX = 5;	
	private final int ChocolateMAX = 6;
	private final int MarzipanMAX = 3;
		
	private final int[] chocolateBarReq = {1, 2, 2, 0};
	private final int[] marzipanBarReq = {0, 0, 2, 1};
	
	private int nuts;
	private int caramel;
	private int chocolate;
	private int marzipan;
	
	
	
	public CandyMachine()
	{		
		this.nuts = 0;
		this.caramel = 0;
		this.chocolate = 0;		
		this.marzipan = 0;
	}
	
	// adds ingredient to candyMachine based upon which thread(or ingredient deliverer) calls this method
	public synchronized void addIngredient(int candyType)
	{
		if((candyType == 0 && nuts == NutsMAX) || 
				(candyType == 1 && caramel == CaramelMAX) ||
				(candyType == 2 && chocolate == ChocolateMAX) ||
				(candyType == 3 && marzipan == MarzipanMAX))
			switch (candyType)
			{
				case 0:
					nuts++;
					break;
				case 1:
					caramel++;
					break;
				case 2:
					chocolate++;
					break;
				case 3:
					marzipan++;
			}
	}
	
	public synchronized int produceOrder()
	{
		return 0;
	}
	
	public void tallyOrder(int marz, int cara)
	{
		
	}
	
}
