// each instance of this class will refill a single ingredient based on the candyType variable
// 0 is nuts, 1 is caramel, 2 is chocolate and 3 marzipan. 
// Ingredient producer receives this value based upon the current in of the array it was created within.

public class IngredientProducer implements Runnable{
	
	private CandyMachine candyMachine;
	private int candyType;
	
	public IngredientProducer(CandyMachine candyMachine, int type)
	{
		this.candyType = type;
		this.candyMachine = candyMachine;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while(true)
		{
			deliverIngredient();
			try {
				Thread.sleep(300);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	// delivers specific ingredient to the candy Machine 
	private void deliverIngredient()
	{
		candyMachine.addIngredient(candyType);
	}
	

}
