package hangman;

public class Player {

 private String name;

 private int score = 0;


 Player(String name_) {
  this.setName(name_);
 }

 public String getName() {
  return name;
 }

 public void setName(String name) {
  this.name = name;
 }

 public int getScore() {
  return score;
 }

 public void setScore(int hangmanState) {

  int increase = 0;
  switch (hangmanState) {
   case (0):
    increase = 100;
    break;
   case (1):
    increase = 50;
    break;
   case (2):
    increase = 25;
    break;
   case (3):
    increase = 0;
    break;
   case (4):
    increase = -25;
    break;
   case (5):
    increase = -50;
    break;
   case (6):
    increase = -100;
    break;
   default:
    increase = 0;
  }
  Print.printScoreIncrease(increase);
  this.score += increase;
 }

}