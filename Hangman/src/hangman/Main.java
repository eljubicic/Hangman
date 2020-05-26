package hangman;
import java.util.Scanner;


// HANGMAN
// v2.0
//Author: Emanuel Ljubicic


public class Main {


 public static Database db = new Database();
 public static Scanner sc = new Scanner(System.in);



 public static void main(String[] args) {

  db.connectDatabase();

  Game game = new Game();
  game.gameExecution();

  sc.close();
  db.cleanUp();

  System.exit(0);


 }



}