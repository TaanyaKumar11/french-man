import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

public class Game 
{
	//Provides ability to read a file.
	private static FileReader fileReader;
	private static BufferedReader bufferedReader;
	
	//Stores all of the available words. Automatically filled using the loadDictionary() method.
	private ArrayList<String> dictionary = new ArrayList<String>();
	//Stores the user's progress. To begin it's loaded with all underscores using the CreateTemplate() method.
	private ArrayList<Character> letters = new ArrayList<Character>();
	//Stores the user's previous guesses.
	private ArrayList<Character> previousGuesses = new ArrayList<Character>();
	
	//Maximum number of attempts.
	private int maxAttempts = 6;
	//Current attempt. Increases by 1 if the user guesses an incorrect letter.
	private int currentAttempt = 0;
	//Stores the word to be guessed.
	private String word;
	
	//Constructor
	public Game() throws IOException
	{
		loadDictionary();	//Loads ArrayList dictionary with all words from dictionary.txt.
		
		//ASSIGN RANDOM WORD
		word = getRandomWord(); 
		
		createTemplate();	//Loads ArrayList letters with all underscores, one per letter from the random word.
	}
	
	public String getLetters()
	{
		//GET LETTERS
		String current = ""; 
		
		for(int i = 0; i<letters.size(); i++) {
		    current += letters.get(i); 
            
            if (i < letters.size()-1) {
                current += " ";
            }		
		}
		
		return current; 
	}
	
	public String getLettersNoSpaces()
	{
		//GET LETTERS NO SPACES
		String current = ""; 
		
		for(int i = 0; i<letters.size(); i++) {
		    current += letters.get(i); 
		}
		
		return current; 
	}
	
	public String getWord()
	{
		//GET WORD
		return word;
	}
	
	public String getRandomWord()
	{
		//GET RANDOM WORD 
		return dictionary.get((int)(Math.random() * dictionary.size())); 
	}
	
	public boolean guessedLetterAlready(char g)
	{
		//GUESSED LETTER ALREADY
		for(int i = 0; i<previousGuesses.size(); i++) {
		    if(previousGuesses.get(i)==g) {
		        return true; 
		    }
		}
		
		return false; 
	}
	
	public boolean guess(char g)
	{
		//GUESS 
		previousGuesses.add(g); 
		
		String word = getWord(); 
		boolean found = false; 
		
		for(int i = 0; i<word.length(); i++) {
		    char current = word.charAt(i); 
		    
		    if(current == g) {
		        letters.set(i, g); 
		        found = true; 
		    }
		}
		
		if(!found) {
		    currentAttempt++; 
		}
		
		return found; 
	}
	
	public String draw()
	{
		//DRAW
		if (currentAttempt == 0) {
            return "";
        } 
        else if (currentAttempt == 1) {
            return "  0 \n" +
                   " /|\\ \n" +
                   "  | \n" +
                   " / \\";
        } 
        else if (currentAttempt == 2) {
            return "  0 \n" +
                   " /|\\ \n" +
                   "  | \n" +
                   " /";
        } 
        else if (currentAttempt == 3) {
            return "  0\n" +
                   " /|\\\n" +
                   "  |";
        } 
        else if (currentAttempt == 4) {
            return "  0\n" +
                   " /|\\";
        } 
        else if (currentAttempt == 5) {
            return "  0\n" +
                   " /|";
        } 
        else if (currentAttempt == 6) {
            return "  0\n" +
                   "  |";
        } 
        else if (currentAttempt == 7) {
            return "  0 \n";
        }
        else {
            return ""; // Failsafe — shouldn't happen
        }
	}
	
	public boolean gameOver()
	{
		//GAME OVER
	    if (getLettersNoSpaces().equals(word)) {
            System.out.println("🎉 You win! The word was: " + word);
            return true;
        } 
        else if (currentAttempt >= maxAttempts) {
            System.out.println("💀 You lose! The word was: " + word);
            return true;
        }

        return false; // Game still in progress
	}	
	
	//Loads ArrayList letters with all underscores, one per letter from the random word.
	public void createTemplate()
	{
		for(int i = 0; i < word.length(); i++)
			letters.add('_');
	}
	
	//Loads dictionary.txt to the ArrayList dictionary.
	public void loadDictionary() throws FileNotFoundException
	{
		try
		{
			File file = new File("dictionary.txt");
			Scanner reader = new Scanner(file);

			while(reader.hasNextLine())
			   dictionary.add(reader.nextLine());
		}
		catch (FileNotFoundException e)
		{
			System.out.println("File not found.");
		}
	}
}