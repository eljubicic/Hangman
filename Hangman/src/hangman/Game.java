package hangman;
import java.util.ArrayList;


import hangman.CustomExceptions.CategoryValidationException;
import hangman.CustomExceptions.GuessValidationException;
import hangman.CustomExceptions.NameValidationException;
import hangman.CustomExceptions.OptionValidationException;

public class Game {

 private Player player;

 public ArrayList < String > categories = new ArrayList < String > ();

 public SecretWord secretWord = new SecretWord();

 public boolean wordEnd = false;
 public boolean newWord = true;




 
 
 public void validateNewPlayer(String name)
 throws NameValidationException {
  if (name.length() < 3 || name.length() > 16 || name == null) {
   throw new NameValidationException("Your name should be 3 - 15 char. long!");
  }
 }
 
 public void validateCategory(int choice, ArrayList < String > categories)
 throws CategoryValidationException {
  if (choice < 0 || choice >= categories.size()) {
   throw new CategoryValidationException("There is no category under that number!");
  }
 }
 
 public void validateGuess(String guess)
 throws GuessValidationException {
  if (guess.length() != 1 || !(guess.matches("^[A-Z]*$")) || guess == null) {
   throw new GuessValidationException("Your guess is not a A-Z character!");
  }
 }

 
 public void validateOption(int option)
 throws OptionValidationException {
  if (option < 0 || option > 2) {
   throw new OptionValidationException("There is no option under that number!");
  }
 }


 public void gameExecution() {

	  welcomeScreen();
	  newPlayer();
	  gameCycle();


	 }
 

 private void welcomeScreen() {

  Print.printStringRow(" ", 2);
  Print.printTitleScreen();
  Print.printStringRow(" ", 2);

 }



 private void newPlayer() {

  boolean isValid = false;
  String name = null;

  while (isValid == false) {
   try {
    System.out.println("Please enter your name: ");
    name = Main.sc.nextLine();
    validateNewPlayer(name);
    isValid = true;
   } catch (NameValidationException nve) {
    isValid = false;
   }

  }


  Player player = new Player(name);
  this.player = player;
  Main.db.insertPlayer(name);

  Print.printStringRow(" ", 2);
  System.out.println("Hi," + this.getPlayer().getName() + "!");


 }



 public void gameCycle() {
  while (newWord != false) {
   categorySelect();
   loadSecretWord();
   guessRepeat();
   evaluateWordEnd();
   optionSelect();
  }
 }




 public void categorySelect() {

  if (this.categories.isEmpty()) {
   this.categories = Main.db.getCategories();
  }

  Print.printStringRow(" ", 2);
  System.out.println("Start by choosing a category: ");
  Print.printArrayList(categories);
  Print.printStringRow(" ", 2);
  System.out.println("Enter the number of a category you wish to play: ");

  int choice = 0;
  boolean isNumber = false;
  boolean isValid = false;



  while (isValid == false || isNumber == false) {
   try {
    choice = Integer.parseInt(Main.sc.nextLine());
    isNumber = true;

    try {
     validateCategory(choice, categories);
     isValid = true;
     Print.printStringRow(" ", 2);

    } catch (CategoryValidationException cve) {
     isValid = false;
    }

   } catch (NumberFormatException ex) {
    isNumber = false;
    Print.printStringRow(" ", 2);
    System.out.println("You have not entered a valid number!");
    System.out.println("Enter the number of a category you wish to play: ");
   }

  }

  secretWord.category = this.categories.get(choice);
  Print.printStringRow(" ", 2);
  System.out.println("Selected category: " + secretWord.category.toUpperCase());


 }
 public void loadSecretWord() {
  Print.printStringRow(" ", 2);
  secretWord.setUnhiddenText(Main.db.getSecretWord(secretWord.category).toUpperCase());
  secretWord.formHiddenText();
  System.out.println("Secret word is loaded!");
  Print.printStringRow(" ", 2);
  Print.printTextAndHangman(secretWord);


 }

 public void guessRepeat() {
  while (wordEnd != true) {
   takeGuess();
   Print.printTextAndHangman(secretWord);
   checkForWordEnd();

  }
 }




 public void takeGuess() {

  boolean isValid = false;
  String guess = null;

  while (isValid == false)
   try {
    Print.printStringRow(" ", 2);
    System.out.println("Take a guess: ");
    guess = Main.sc.nextLine();
    guess = guess.toUpperCase();
    validateGuess(guess);
    isValid = true;

   }
  catch (GuessValidationException gve) {
   isValid = false;
  }

  char guessChar = guess.toUpperCase().charAt(0); 
  
  if (secretWord.getUnhiddenText().contains(guess.toUpperCase())) {

   for (int i = 0; i < secretWord.hiddenText.length; i++) {
    if (guessChar == secretWord.getUnhiddenText().charAt(i)) {
     secretWord.hiddenText[i] = guessChar;
    }

   }
  } else {

   secretWord.hangmanState++;
  }
  secretWord.guess++;


 }

 public void checkForWordEnd() {
  boolean endFlag = true;

  if (secretWord.hangmanState == 6) {
   endFlag = true;
  } else {
   for (char c: secretWord.hiddenText) {
    if (c == '_') {
     endFlag = false;
     break;
    }
   }

  }

  this.wordEnd = endFlag;



 }

 public void evaluateWordEnd() {

  Print.printStringRow(" ", 2);
  Print.printStringRow("-", 2, 80);
  Print.printStringRow(" ", 2);
  if (secretWord.hangmanState != 6) {
   System.out.println("You have guessed the word!");
  } else {
   System.out.println("YOU ARE HANGED!");
  }
  player.setScore(secretWord.hangmanState);

  Print.printStringRow(" ", 2);
  System.out.println("YOUR TOTAL SCORE: ");
  System.out.println(player.getScore());
  Main.db.updateScore(player.getScore(), this.getPlayer().getName());
  Print.printStringRow(" ", 2);
  Print.printStringRow("-", 2, 80);
  Print.printStringRow(" ", 2);
  Print.printStringRow(" ", 2);
  Print.printStringRow(" ", 2);


 }



 public void optionSelect() {


  int option = 0;
  boolean isNumber = false;
  boolean isValid = false;

  while (isValid == false || isNumber == false) {
   try {
    Print.printStringRow(" ", 2);
    System.out.println("Choose one of the following options: ");
    Print.printStringRow(" ", 2);
    System.out.println("0 Guess a new word");
    System.out.println("1 View highscores");
    System.out.println("2 Exit the program");
    Print.printStringRow(" ", 2);

    try {
     option = Integer.parseInt(Main.sc.nextLine());
     validateOption(option);
     isValid = true;
    } catch (OptionValidationException ove) {
     isValid = false;
    }
    isNumber = true;
   } catch (NumberFormatException ex) {
    isNumber = false;
    Print.printStringRow(" ", 2);
    System.out.println("You have not entered a valid number!");

   }
  }


  switch (option) {
   case 0:
    setDefaultValues();
    this.newWord = true;
    Print.printStringRow(" ", 2);
    break;
   case 1:
    Print.printHighscoresList(Main.db.get10Highscores());
    optionSelect();
    break;
   case 2:
    this.newWord = false;
    Print.printCreditsScreen();
    break;
  }



 }

 public void setDefaultValues() {

  wordEnd = false;
  newWord = true;
  this.secretWord = new SecretWord();
  
 }

 public Player getPlayer() {
  return player;
 }

 public void setPlayer(Player player) {
  this.player = player;
 }



}