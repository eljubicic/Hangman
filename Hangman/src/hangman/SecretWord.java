package hangman;

public class SecretWord {

 public String category;
 public char[] hiddenText;
 
 private String unhiddenText;
 
 public int guess = 0;
 public int hangmanState = 0;

 public void formHiddenText() {

  String word = unhiddenText;

  hiddenText = new char[word.length()];
  for (int i = 0; i < word.length(); i++) {
   hiddenText[i] = '_';
   if (word.charAt(i) == ' ') {
    hiddenText[i] = ' ';
   }
  }

 }

 public String getUnhiddenText() {
  return unhiddenText;
 }
 public void setUnhiddenText(String unhiddenText) {
  this.unhiddenText = unhiddenText;
 }







}