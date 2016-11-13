// Name: Athena Xiao
// Email: chyxiao@brandeis.edu 
// Date: 12 Nov. 2016 
// PA 6 Problem4
// Bug: If you want a string as a condition for while, but you also need to re-assign it, what do you do?

import java.util.*;
public class Problem4{
	public static void main(String[] args){

		Scanner input = new Scanner(System.in);

		System.out.println("Let's play tic-tac-toe together!");
		System.out.println();

		int result;
		String s2 = "";

		do{
			int [][] game = new int[3][3];
			printPattern(game);
			System.out.print("Do you want to play first? ");
			String s = input.next();
			System.out.println();

			if(s.equalsIgnoreCase("y")||s.equalsIgnoreCase("yes")){
 				result = checkResult(game);
 				gameIsOn(result, game);	
			}
			else{
 				result = checkResult(game);
 				gameIsOn2(result, game);
  			}

  			System.out.println("Do you want to play again? ");
  			s2 = (s2 + input.next()).toLowerCase();
  			System.out.println();

 		} while(s2.endsWith("y")||s2.endsWith("yes"));
	}

		public static void gameIsOn(int result, int [][] game){
			int time = 0; 
			do{
 				getInput(game);
 				printPattern(game);
 				time++;
 				
 				result = checkResult(game);

 				if(result == 1){
 					 System.out.println("You win!");
 					 return;
  				}
 				else if(result == -1){
 					System.out.println("I win!");
 					return;
  				}
 				else if(time == 9){
 					System.out.println("It's a tie!");
 					return;
  				}

 				else{
					if(attack(game) == 0){
						if(defense(game) == 0){
							movePriority(game);
						}
					}

					System.out.println("My turn:");
					System.out.println();
					printPattern(game);
					time++;

					result = checkResult(game);
					if(result == 1){
 						 System.out.println("You win!");
 						 return;
  					}
 					else if(result == -1){
 						System.out.println("I win!");
 						return;
  					}
 				}

			} while(time < 9);  		
 			System.out.println("It's a tie!");
 		}

 		public static void gameIsOn2(int result, int [][] game){
			int time = 0; 
			do{
				if(attack(game) == 0){
					if(defense(game) == 0){
							movePriority(game);
					}
				}

				System.out.println("My turn:");
				System.out.println();
				time++;
				printPattern(game);

				result = checkResult(game);
				if(result == 1){
					System.out.println("You win!");
 					return;
  				}
 				else if(result == -1){
 					System.out.println("I win!");
 					return;
  				}
  				else if(time == 9){
 					System.out.println("It's a tie!");
  					return;
  				}

  				else{
 					getInput(game);
 					time++;
 					printPattern(game);
 				
 					result = checkResult(game);

	 				if(result == 1){
 						 System.out.println("You win!");
 						 return;
  					}
 					else if(result == -1){
 						System.out.println("I win!");
 						return;
  					}
  				}	
			} while(time < 9);  		
 			System.out.println("It's a tie!");
 		}	



	public static void getInput(int [][] game){
		Scanner input = new Scanner(System.in);	
		int a, b;

 		do{
 			System.out.print("Please input the number of row and column: ");
 			a = input.nextInt();
			b = input.nextInt();	
		} while(game[a][b] != 0);
		
		System.out.println();
 		game[a][b] = 1;
 	}

	// Sequence of move if you cannot either attack or defend
	public static void movePriority(int [][] game){
		if(game[1][1] == 0){
			game[1][1] = -1;
		}
		else if(game[0][0] == 0){
			game[0][0] = -1;
		}
		else if(game[0][2] == 0){
			game[0][2] = -1;
		}
		else if(game[2][0] == 0){
			game[2][0] = -1;
		}
		else if(game[2][2] == 0){
			game[2][2] = -1; 
		}
		else if(game[0][1] == 0){
			game[0][1] = -1;
		}
		else if(game[1][2] == 0){
			game[1][2] = -1;
		}
		else if(game[2][1] == 0){
			game[2][1] = -1;
		}
		else if(game[1][0] == 0){
			game[1][0] = -1;
		}
 	}

 	// Figure out how to know if you change something or not
	// Strategy 1: check if you can win after this move
	public static int attack(int [][] game){
		int k = 0; 
		for(int i = 0; i <= 2; i++){
			for(int j = 0; j <= 2; j++){
				if(game[i][j] == 0){					   // If the place hasn't been taken	
					game[i][j] = -1;					   // Let me put my attack and see if I can win somehow 
					k = 1;                                 // This shows that you move
					if(checkResult(game) != -1){		   // After your move, check the possible result												  
						game[i][j] = 0;					   // If you cannot win, then you move back
						k = 0;							   
					}
					else{
						return k;						   // If you can win, tell the program you takes the step
					}
				}
			}
		}
		return 0;
	}

	// Strategy 2: predict if users will win or not
	public static int defense(int [][] game){
		int m = 0;	
		for(int i = 0; i <= 2; i++){			 
			for(int j = 0; j <= 2; j++){
				if(game[i][j] == 0){			  // If the place hasn't been taken yet 
					game[i][j] = 1;				  // Try if the user can will if you don't take this place
					if(checkResult(game) == 1){   // Check the result 
						game[i][j] = -1;		  // If the user can will, definitely take this place
						m = 1;					  // Tell the program you have already make your move
						return m;		
					}
					else{						  // If the user cannot win anyway
						game[i][j] = 0;           // Move back
					}
				}
			}
		}
	return m;
	}

		public static void printPattern(int [][] game){

			char mark;

			for(int i = 0; i <= 2; i++){
				for(int j = 0; j <= 2; j++){
	 				switch(game[i][j]){
	 					case 1: mark = (char) 79;
	 					break;
	 					case -1: mark = (char) 88;
	 					break;
	 					default: mark = (char) 32;
	 				}

	  				if(j <= 1){
						System.out.print(mark + " | ");
	 				}
	 				else{
						System.out.print(mark);
	 				}
	 			}

	 			System.out.println();
	 			
	 			if(i <= 1){
	 				System.out.println("---------");
	 			}
	  		}

	  		System.out.println();
		}

	// Check the result after every move
	public static int checkResult(int [][] game){

 		int [] temp = {1, 1, 1};
 		int [] temp2 = {-1, -1, -1};

 		int [] column0 = {game[0][0], game[1][0], game[2][0]};
 		int [] column1 = {game[0][1], game[1][1], game[2][1]};
 		int [] column2 = {game[0][2], game[1][2], game[2][2]};

 		int [] diangle0 ={game[0][0], game[1][1], game[2][2]};
 		int [] diangle1 ={game[0][2], game[1][1], game[2][0]};

		
		if(Arrays.equals(game[0], temp)||Arrays.equals(game[1], temp)||Arrays.equals(game[2], temp)){
 			return 1;
		}		
		else if(Arrays.equals(column0, temp)||Arrays.equals(column1, temp)||Arrays.equals(column2, temp)){
			return 1;
		}
		else if(Arrays.equals(diangle0, temp)||Arrays.equals(diangle1, temp)){
			return 1;
		}
		else if(Arrays.equals(game[0], temp2)||Arrays.equals(game[1], temp2)||Arrays.equals(game[2], temp2)){
 			return -1;
		}
		else if(Arrays.equals(column0, temp2)||Arrays.equals(column1, temp2)||Arrays.equals(column2, temp2)){
			return -1;
		}
		else if(Arrays.equals(diangle0, temp2)||Arrays.equals(diangle1, temp2)){
			return -1;
		}
		else{
			return 0;
		}
	}
}


