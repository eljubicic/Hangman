package hangman.CustomExceptions;

import hangman.Print;

public class CategoryValidationException extends Exception {

	private static final long serialVersionUID = 1L;
	
	public  CategoryValidationException(String s){  
		  super(s);  
		  Print.printStringRow(" ", 2);
		  System.out.println("There is no such a category!");
		  System.out.println("Enter the number of a category you wish to play: ");
		 } 
	
		
	}
	
	

