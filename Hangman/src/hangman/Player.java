package hangman;

public class Player {
	
private String name;
private int score = 0;


Player(String name_){
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

public void setScore(int score) {
	this.score = score;
}

}
