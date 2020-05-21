package hangman;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class Game {

	{

		WelcomeScreen();

	}

	public int hangmanState = 0;
	public char[] lineString;
	public boolean end = false;

	private Player player = NewPlayer();
	private boolean running = false;
	private String word;

	private ArrayList<String> selectedList;

	private ArrayList<String> things = new ArrayList<String>(
			Arrays.asList("Broom", "Stadium", "Motorcyle", "Papertowel", "Spoon", "Basket", "Telephone"));

	private ArrayList<String> places = new ArrayList<String>(
			Arrays.asList("Alabama", "Manchester", "Danzig", "Ljubljana", "Albuquerqe", "Bangkok", "Washington"));

	private ArrayList<String> people = new ArrayList<String>(
			Arrays.asList("Bethoveen", "Maradona", "Matisse", "Picasso", "Michelangelo", "Churchill", "Gandhi"));

	private ArrayList<String> animals = new ArrayList<String>(
			Arrays.asList("Stingray", "Monkey", "Parrot", "Tortouise", "Dolphin", "Cheetah", "Alligator"));

	private void WelcomeScreen() {

		System.out.println("------------------------------------------------------------------------");
		System.out.println("-------------------------------HANGMAN----------------------------------");
		System.out.println("------------------------------------------------------------------------");
		System.out.println("");
		System.out.println("Welcome to hangman!");
		System.out.println("");
		System.out.println("");

	}

	private Player NewPlayer() {

		System.out.println("Please enter your name: ");

		@SuppressWarnings("resource")
		Scanner sc = new Scanner(System.in);
		String name = sc.nextLine();

		while (name.length() < 3 || name.length() > 16 || name == null) {
			System.out.println("Your name should be 3 - 15 char. long!");
			System.out.println("Please re-enter your name!");
			name = sc.nextLine();
		}

		Player player = new Player(name);
		return player;

	}

	public void CategorySelect() {
		System.out.println();
		System.out.println("Alright  >> " + this.getPlayer().getName() + " <<  " + "lets play!");
		System.out.println();
		System.out.println("Enter the >> number << of word category you wish to play: ");
		System.out.println();
		System.out.println(" >> 1 << Things");
		System.out.println();
		System.out.println(" >> 2 << Places");
		System.out.println();
		System.out.println(" >> 3 << People");
		System.out.println();
		System.out.println(" >> 4 << Animals");
		System.out.println();

		int category = Integer.parseInt(Main.sc.nextLine());
		String categoryName;

		while (category < 1 || category > 4) {
			System.out.println("Enter the >> number << of a category you wish to play: ");
			System.out.println("Categories are following: ");
			System.out.println();
			System.out.println(" >> 1 << Things");
			System.out.println();
			System.out.println(" >> 2 << Places");
			System.out.println();
			System.out.println(" >> 3 << People");
			System.out.println();
			System.out.println(" >> 4 << Animals");
			System.out.println();

			category = Integer.parseInt(Main.sc.nextLine());
		}

		switch (category) {
		case 1:
			this.selectedList = things;
			categoryName = "Things";
			break;
		case 2:
			this.selectedList = places;
			categoryName = "Places";
			break;
		case 3:
			this.selectedList = people;
			categoryName = "People";
			break;
		case 4:
			this.selectedList = animals;
			categoryName = "Animals";
			break;
		default:
			this.selectedList = things;
			categoryName = "Things";
		}

		System.out.println("You have selected >" + categoryName + "< category!");

	}

	public void WordSelect() {
		String word = this.selectedList.get(ThreadLocalRandom.current().nextInt(selectedList.size()));
		this.setWord(word.toUpperCase());
		System.out.println();
		System.out.println("Your word is selected! ");
		System.out.println("");
		System.out.println("");

		lineString = new char[getWord().length()];
		for (int i = 0; i < this.getWord().length(); i++) {
			lineString[i] = '_';
		}

		PrintLineString();
		System.out.println();
		System.out.println();
		DrawHangman();

	}

	public void PrintLineString() {

		for (int i = 0; i < this.lineString.length; i++) {
			System.out.print(lineString[i]);
			System.out.print(' ');
		}

	}

	public void DrawHangman() {

		System.out.println();
		System.out.println();

		switch (this.hangmanState) {
		case 0:
			System.out.println(" O");
			System.out.println("-|-");
			System.out.println("| |");
			break;

		case 1:
			System.out.println(" O");
			System.out.println("-|-");
			System.out.println("  |");
			break;

		case 2:
			System.out.println(" O");
			System.out.println("-|-");
			break;

		case 3:
			System.out.println(" O");
			System.out.println("-|");
			break;

		case 4:
			System.out.println(" O");
			System.out.println(" |");
			break;

		case 5:
			System.out.println(" O");
			break;

		case 6:
			System.out.println(" X");
			break;
		}

	}

	public void Guess() {

		System.out.println();
		System.out.println();
		System.out.println("************************************************************************");
		System.out.println("Guess a letter: ");
		String guess = Main.sc.nextLine();

		while (guess.length() != 1 && !(guess.matches("[a-zA-Z]*"))) {
			System.out.println("Your guess is invalid, guess a LETTER: ");
			guess = Main.sc.nextLine();
		}

		char guess_char = guess.toUpperCase().charAt(0); // Extract char from guess string
		System.out.println("************************************************************************");
		System.out.println();

		// Check word for this guess char
		if (this.word.contains(guess.toUpperCase())) {
			for (int i = 0; i < this.getWord().length(); i++) {
				char word_char = this.getWord().charAt(i); // char from word at this position

				if (guess_char == word_char) {

					lineString[i] = guess_char;

				}

			}
		}

		else {
			hangmanState++;

		}

		System.out.println();
		System.out.println();

		PrintLineString();

		DrawHangman();

		System.out.println();
		System.out.println();

		CheckForEnd();

	}

	public void CheckForEnd() {
		boolean endFlag = true;
		for (char c : lineString) {
			if (c == '_')
				endFlag = false;
		}

		this.end = endFlag;

	}

	public void EvaluateEnd() {
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println();
		System.out.println("########################################################################");

		switch (hangmanState) {
		case (0):
			System.out.println("PERFECT SCORE! YOU HAVE WON!" + " +150");
			player.setScore(150);
			break;
		case (1):
			System.out.println("YOU HAVE WON!" + " +100");
			player.setScore(100);
			break;
		case (2):
			System.out.println("YOU HAVE WON!" + " +80");
			player.setScore(80);
			break;
		case (3):
			System.out.println("YOU HAVE WON!" + " +60");
			player.setScore(60);
			break;
		case (4):
			System.out.println("YOU HAVE WON!" + " +40");
			player.setScore(40);
			break;
		case (5):
			System.out.println("YOU HAVE WON!" + " +20");
			player.setScore(20);
			break;
		case (6):
			System.out.println("You are hanged! x__x ");
			player.setScore(0);
			break;
		default:
			System.out.println("DEFAULT");
		}

		System.out.println("########################################################################");

	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(boolean running) {
		this.running = running;
	}

	public String getWord() {
		return word;
	}

	public void setWord(String word) {
		this.word = word;
	}

}
