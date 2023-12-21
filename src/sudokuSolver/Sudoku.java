/* 
 * Project: Sudoku Solver
   Project Code Details:

   - This Sudoku Solver project is designed to consolidate various programming concepts
     
   - The main objective is to provide hands-on experience working with 2-dimensional arrays
     by implementing a solver for Sudoku puzzles.

   - Sudoku Rules:
     - The puzzle must have numbers 1 through 9 in each row, column, and 3x3 box.
     - No duplication of numbers is allowed in any row, column, or 3x3 box.

   - Solution Strategies:
     - The program addresses three types of problems:
       1. Type 1 Problem: Single missing value in a row, column, or 3x3 box.
       2. Type 2 Problem: Two adjacent missing values in the same row or column.
       3. Type 3 Problem: Three adjacent missing values forming an "L" shape.

   - Implementation:
     - The solver iteratively reads a 9x9 Sudoku board, treating zeros as missing values.
     - It uses the described strategies to solve the puzzle until all cells are filled.
     - A helper method is employed to find the missing value in a given set of numbers.
     - Solved cells are printed in the format (row, column, value) for each fixed cell.

   - Usage:
     1. Input a Sudoku board, specifying missing values with zeros.
     2. The program iteratively solves the puzzle using the described strategies.
     3. The solved cells are displayed in the format (row, column, value).

   - Note:
     - The solver continues until all 81 cells are filled, indicating the successful
       completion of the Sudoku puzzle.

   - Example:
     - The provided example Sudoku board is used to demonstrate the solver's functionality.
     
7 2 4 8 6 5 1 3 9
5 1 9 2 4 3 8 7 6
3 0 6 7 9 1 5 4 2
1 7 8 6 2 9 4 5 3
9 4 3 1 5 8 2 6 7
6 5 2 3 7 4 9 1 8
2 3 1 5 8 6 7 9 4
8 9 5 4 3 7 6 1 2
4 6 7 9 1 2 3 8 5

7 2 4 8 6 5 1 3 9
5 1 9 2 4 3 8 7 6
3 8 6 7 9 1 5 4 2
1 7 8 6 2 9 4 0 3
9 4 3 1 5 8 2 0 7
6 5 2 3 7 4 9 1 8
2 3 1 5 8 6 7 9 4
8 9 5 4 3 7 6 1 2
4 6 7 9 1 2 3 8 5

7 2 4 8 6 5 1 3 9
5 1 9 2 4 3 8 7 6
3 8 6 7 9 1 5 4 2
1 7 8 6 2 9 4 5 3
9 4 3 1 5 8 2 6 7
6 5 2 3 7 4 0 0 8
2 3 1 5 8 6 7 0 4
8 9 5 4 3 7 6 1 2
4 6 7 9 1 2 3 8 5

0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0
0 0 0 0 0 0 0 0 0

   - Conclusion:
     - The project enhances understanding of 2-dimensional array manipulation and
       algorithmic approaches for solving Sudoku puzzles. It provides valuable practical
       experience in problem-solving and reinforces programming concepts.
*/

package sudokuSolver;
import java.util.*;

public class Sudoku 
{
			
    static int[][] board = new int[9][9];
	static int zeroes = 0; 
	// use this to count zeroes in board
    static int row = 0, col = 0; 
    // variables used to count zeroes in board
	static Scanner input = new Scanner(System.in);

	public static void main(String[] args) 
	{
	    do 
	    {                       
		    readBoard();
					
			if(zeroes <=3 ) 
			{
			    solveBoard();
			}
					
		}
	    while (zeroes < 81);
		System.out.println("END");
	}

	public static void readBoard() 
	{
		        // this method is used to read the 9*9 boards
        zeroes = 0;
		for (row = 0; row < 9; row++) 
		{
		    for (col = 0; col < 9; col++) 
		    {
		        board[row][col] = input.nextInt();
		        if (board[row][col] == 0)
		            zeroes++;
		    }
		}
    }

    public static void solveBoard() 
    {
         while (zeroes > 0) 
         {
		     if (zeroes == 1) 
		     {
		                // if there is only one missing value, then we need to figure out
		                // what row it is in. then we can look at everything else in the row
		                // and then determine the value that is missing
		         for (row = 0; row < 9; row++) 
		         {
		             for (col = 0; col < 9; col++) 
		             {
		                 if (board[row][col] == 0) 
		                 {
		                            // i will call helper to find the missing value
		                     board[row][col] = helper(board[row]);
		                     System.out.print("(" + row + "," + col + "," + board[row][col] + ")");
		                            
		                     zeroes--;
		                            
		                 }
		             }
		         }
		     }

		     else if (zeroes == 2) 
		     {
		                // if there are 2 missing values, we need to identify which two squares
		                // share the same row or column
		         for (row = 0; row < 9; row++) 
		         {
		             for (col = 0; col < 9; col++) 
		             {
		                 if (board[row][col] == 0) 
		                 {
		                     if (col!=8)
		                     {
		                         if(board[row][col+1]==0) 
		                         {
		                        			
		                        			
		                             int[] column = new int[] { board[0][col],board[1][col],board[2][col],board[3][col],board[4][col],board[5][col],board[6][col],board[7][col],board[8][col]};
				                     board[row][col] = helper (new int[] {board[0][col],board[1][col],board[2][col],board[3][col],board[4][col],board[5][col],board[6][col],board[7][col],board[8][col]});
				                     board[row][col] = helper(column);
				                     System.out.print("(" + row +","+ col +","+ board[row][col]+")");
				                            
				                     for (int i = 0; i < 9; i++) 
				                     {
				                         if (board[i][col] == 0) 
				                         {
				                             board[i][col] = helper(new int[] {board[0][col],board[1][col],board[2][col],board[3][col],board[4][col],board[5][col],board[6][col],board[7][col],board[8][col]});
				                             System.out.print("(" + i +","+ col +","+ board[i][col]+")");
				                                
				                         }
				                               
				                     }
				                            
		                          }
		                     }
		                            
		                        	 
	                      	}
		             }
		        }
		    }
		            
		    
		    else if(zeroes == 3)
		    {
		        int [][]threebyCount = new int [9][3];
		        int WhichBox = 0;
		            	
		        for(int row = 0;row < 9 ;row++)
		        {
		      	    for(int col = 0;col < 9; col++)
		      	    {
		      		    if( board[row][col] == 0) 
		      		    {
		      			    WhichBox = (row/3)*3+(col/3);
		      					  
		      			    threebyCount[WhichBox][0]++;
		      			    threebyCount[WhichBox][1] = row;
		      			    threebyCount[WhichBox][2] = col;
		      			    WhichBox +=1;
		      		     }
		      		}
		      	}
		      				 
		        for(int i = 0;i < 9; i++ )
		        {
		      	    if(threebyCount[i][0] == 1)
		      	    {
		      			        		int row = threebyCount[i][1];
		      			        		int col = threebyCount[i][2];
		      			        		int StartRow = (row / 3) * 3;
		      			        		int StartCol = (col / 3) * 3;
		      			        		int missing = 0;
		      			        		int[] val = new int[] { board[StartRow+0][StartCol], board[StartRow+0][StartCol+1], board[StartRow+0][StartCol+2], board[StartRow+1][StartCol], board[StartRow+1][StartCol+1], board[StartRow+1][StartCol+2], board[StartRow+2][StartCol], board[StartRow+2][StartCol+1],board[StartRow+2][StartCol+2]};
		      			        	    missing = helper(val);
		      			        	    board[row][col] = missing;
		      			                System.out.print("(" + row +","+ col +","+ board[row][col] + ")");
		      			              
		      	    }
		        }
		    }
		            zeroes--;
		           
	   }
	       System.out.println();
  }   
		      
		        
		    
		    
		   

			public static int helper(int[] X) 
			{

		        boolean[] search = 
		        	{ false, false, false, false, false, false, false, false, false, false 
		            };

		        for (int i = 0; i < 9; i++) 
		        {
		            search[X[i]] = true;
		        }

		        // now which value is missing
		        int missingValue = 0;
		        
		        for (int i = 0; i < 10; i++) 
		        {
		            if (!search[i]) 
		            {
		                missingValue = i;
		                break;
		            }
		        }

		        return missingValue;
		       
		    		   
		    		   
		      
			}
		    
}   

