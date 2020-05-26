package hangman.CustomExceptions;

import hangman.Print;

public class GuessValidationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	 public GuessValidationException(String s){  
		  super(s); 
		  Print.printStringRow(" ", 2);
		  System.out.println(s);
		  Print.printStringRow(" ", 2);
		 }  
	
	
	

}
