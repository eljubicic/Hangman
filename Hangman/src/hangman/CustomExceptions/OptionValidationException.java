package hangman.CustomExceptions;

import hangman.Print;

public class OptionValidationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public  OptionValidationException(String s){  
		  super(s);  
		  Print.printStringRow(" ", 2);
		  System.out.println(s);
		 } 
	
		
	}
	
	

