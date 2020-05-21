package hangman;
import java.util.Scanner;


public class Main {
	
	public static int running;
	public static final Scanner sc = new Scanner (System.in);
	
	public static void NewGame()
	{
		System.out.println(); System.out.println();
		System.out.println("Enter >>option<< : ");
		System.out.println(">> N << Play a NEW GAME ");
		System.out.println(">> X << EXIT! ");
		
		
		String option= sc.nextLine();
		if ( !(option.toUpperCase().equals("N") || option.toUpperCase().equals("X")))
		{
			System.out.println("Enter >>option<< : ");
			System.out.println(">> N << Play a NEW GAME ");
			System.out.println(">> X << EXIT! ");
		}	
		
		
		
		
		
		if(option.toUpperCase().equals("X"))
		{
			System.out.println(" THANKS FOR PLAYING! ");
			System.out.println(" ~ Emanuel Ljubicic , 2020. ~ ");
			
			Main.running = 0;
			
		}
		else
		Main.running = 1;
			
		
		
	}
	
	
	
	public static void main ( String [] args)
	{
		
		Main.running = 1;
		
		while (Main.running == 1)
		{
		Game game = new Game();	 // System.out.println(game.getPlayer().getName());
		game.CategorySelect();
		game.WordSelect(); 		//System.out.println(game.getWord());
		
		while(game.hangmanState != 6 && game.end != true) 
		{
			game.Guess();
		}
		
		game.EvaluateEnd();
		
		NewGame();
		
		}
		sc.close();
		System.exit(0);
		
	
	}
	
	

}
