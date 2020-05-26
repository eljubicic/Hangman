package hangman;

import java.util.ArrayList;

public class Print {

 public static void printTitleScreen() {
  printStringRow("-", 2, 80);
  System.out.println("[HANGMAN]");
  printStringRow("-", 2, 80);

 }
 public static void printCreditsScreen() {
  printStringRow("-", 2, 80);
  System.out.println("[THANK YOU FOR PLAYING!]");
  System.out.println("[Author: Emanuel Ljubicic]");
  printStringRow("-", 2, 80);

 }


 public static void printStringRow(String s, int row, int chars) {
  for (int i = 0; i < row; i++) {
   String line = "";
   for (int x = 0; x < chars; x++) {
    line = line + s;
   }
   System.out.println(line);
  }
 }

 public static void printStringRow(String s, int row) {
  for (int i = 0; i < row; i++) {
   String line = "";
   line = line + s;
   System.out.println(line);
  }
 }

 public static void printArrayList(ArrayList < String > al) {
  for (String s: al) {
   System.out.println(al.indexOf(s) + " " + s);
  }
 }

 public static void printHighscoresList(ArrayList < String > al) {
  printStringRow(" ", 4);
  System.out.println("TOP 10 PLAYER SCORES:");
  printStringRow(" ", 2);

  for (String s: al) {
   System.out.println((al.indexOf(s) + 1) + " " + s);
  }
  printStringRow(" ", 4);
 }


 public static void printHiddenText(char[] array) {

  for (int i = 0; i < array.length; i++) {
   System.out.print(array[i]);
   System.out.print(' ');
  }

 }

 public static void printHangmanDrawing(int state) {
  System.out.println();
  switch (state) {
   case 0:

    System.out.println("==========||");
    System.out.println(" O        ||");
    System.out.println("-|-       ||");
    System.out.println("| |       ||");
    System.out.println("          ||");
    break;

   case 1:

    System.out.println("==========||");
    System.out.println(" O        ||");
    System.out.println("-|-       ||");
    System.out.println("  |       ||");
    System.out.println("          ||");
    break;

   case 2:
    System.out.println("==========||");
    System.out.println(" O        ||");
    System.out.println("-|-       ||");
    System.out.println("          ||");
    System.out.println("          ||");
    break;

   case 3:
    System.out.println("==========||");
    System.out.println(" O        ||");
    System.out.println(" |-       ||");
    System.out.println("          ||");
    System.out.println("          ||");
    break;

   case 4:
    System.out.println("==========||");
    System.out.println(" O        ||");
    System.out.println(" |        ||");
    System.out.println("          ||");
    System.out.println("          ||");
    break;

   case 5:
    System.out.println("==========||");
    System.out.println(" O        ||");
    System.out.println("          ||");
    System.out.println("          ||");
    System.out.println("          ||");
    break;

   case 6:
    System.out.println("==========||");
    System.out.println(" X        ||");
    System.out.println("          ||");
    System.out.println("          ||");
    System.out.println("          ||");
    break;
  }
  System.out.println();

 }




 public static void printTextAndHangman(SecretWord secretWord) {
  printStringRow("*", 1, 80);
  System.out.println("GUESS:" + secretWord.guess);
  printStringRow("*", 1, 80);
  printStringRow(" ", 2);
  printHiddenText(secretWord.hiddenText);
  printStringRow(" ", 2);
  printHangmanDrawing(secretWord.hangmanState);
  printStringRow(" ", 2);
  printStringRow("*", 2, 80);

 }

 public static void printScoreIncrease(int increase) {
  String increaseString = "";

  if (increase > 0) {
   increaseString = "[ " + "+" + String.valueOf(increase) + " ]";
  } else {
   increaseString = "[ " + String.valueOf(increase) + " ]";
  }

  System.out.println(increaseString);

 }

}