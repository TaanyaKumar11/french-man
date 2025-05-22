import java.io.IOException;
import java.util.Scanner;

public class Main 
{	
	public static void main(String[] args) throws IOException
	{
		Scanner scan = new Scanner(System.in);
		
		boolean play = true;
		
		//WELCOME MESSAGE
		System.out.println("Bonjour and bienvenue French Man! Try to guess the word before you are sent to the guillotine");
		
		
		//Main loop to play the game
		while(play)
		{
			//CREATE A NEW GAME
			Game game = new Game(); 
			
			//Loop to play the current word until the game is over.
			while(!game.gameOver())
			{
				//DRAW FIGURE 
				System.out.println(game.draw()); 
				
				//PRINT GUESSED LETTERS
				System.out.println("Word: " + game.getLetters());
				
				//GET GUESS FROM USER
				System.out.println("Enter a letter: ");
				String input = scan.nextLine().toLowerCase();
                char guess = input.charAt(0);
                
				//CHECK IF LETTER GUESSED ALREADY
				while (game.guessedLetterAlready(guess)) {
                    System.out.print("You already guessed that! Try again: ");
                    input = scan.nextLine().toLowerCase();
                    guess = input.charAt(0);
                }
				
				//CHECK GUESS
				boolean correct = game.guess(guess);
                if (correct) {
                    System.out.println("Correct!");
                } 
                else {
                    System.out.println("Wrong!");
                }
			}
			
			//PLAY AGAIN
			if (game.gameOver()) {
			    System.out.print("Do you want to play again? (y/n): ");
                
                String again = scan.nextLine().toLowerCase();
                if (!again.equals("y")) {
                    play = false;
                }
			}
		}
		//EXIT MESSAGE
		System.out.println("Merci for playing Frenchman! Au revoir! 🥖");
	}
}