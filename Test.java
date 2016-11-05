import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeSet;

/**
 *
 * @author santi
 */
public class Test {
    
    public static void main(String args[]) {
        // Create the game state with the initial position for an 8x8 board:
        Scanner in = new Scanner(System.in);
        OthelloState state = new OthelloState(8);
        System.out.println("Which player do you want to be? 0 or 1? (invalid inputs = 1)");
        String result = in.nextLine();

        System.out.println("Depth of search? (more than 5 is really slow");
        Integer depth = 2;
        try {
            depth = Integer.parseInt(in.nextLine());
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }

        OthelloPlayer players[] = {new OthelloRandomPlayer(), new MiniMaxOthelloPlayer(depth)};
        if(result.equals("0")) {
             players[0] = new MiniMaxOthelloPlayer(depth);
             players[1] = new OthelloRandomPlayer();
        }

        do{
            // Display the current state in the console:
            System.out.println("\nCurrent state, " + OthelloState.PLAYER_NAMES[state.nextPlayerToMove] + " to move:");
            //state.printBoard();
            System.out.println(state);
            // Get the move from the player:
            OthelloMove move = players[state.nextPlayerToMove].getMove(state);
           // System.out.println(move);
            state = state.applyMoveCloning(move);

        }while(!state.gameOver());

        // Show the result of the game:
        System.out.println("\nFinal state with score: " + state.score());
        //state.printBoard();
        System.out.println(state);
    }

}
