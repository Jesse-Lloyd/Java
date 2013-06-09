// Contains main and operates the program
// it creates the candy machine and all the producer consumer threads and passes by reference the candy machine to them.
public class Simulator {
	
	CandyMachine candyMachine;
	Thread consumers[] = new Thread[4];
	Thread producers[] = new Thread[4];
	
	public Simulator()
	{	
		candyMachine = new CandyMachine();
		System.out.println("Helloworld"); 
		for(int i = 0; i < 4; i++)
		{			
			producers[i] = new Thread(new IngredientProducer(this.candyMachine, i));
			producers[i].start();
		}
		for(int i = 0; i < 4; i++)
		{			
			consumers[i] = new Thread(new CustomerConsumer(this.candyMachine));
			consumers[i].start();
		}
	}
	
	public static void main(String[] args)
	{
		Simulator simulator = new Simulator();
	}

}
