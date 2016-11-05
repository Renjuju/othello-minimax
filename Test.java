import java.util.ArrayList;
import java.util.TreeSet;

/**
 *
 * @author santi
 */
public class Test {
    
    
    public static void main(String args[]) {
        // Create the game state with the initial position for an 8x8 board:
        OthelloState state = new OthelloState(8);
        OthelloPlayer players[] = {new MiniMaxOthelloPlayer(3), new OthelloRandomPlayer()};

        int count = 0;
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
        //stats();
    }

    public static void stats() {
        int wins = 0;
        int loses = 0;

        int counter = 100;
        while(counter > 0) {
            OthelloState state = new OthelloState(8);
            OthelloPlayer players[] = {new MiniMaxOthelloPlayer(4), new OthelloRandomPlayer()};
            do{
                OthelloMove move = players[state.nextPlayerToMove].getMove(state);
                state = state.applyMoveCloning(move);
            } while(!state.gameOver());
            if(state.score() > 0) {
                wins++;
            } else {
                loses++;
            }
            counter--;
        }
        System.out.println("Number of wins: " + wins);
        System.out.println("Number of loses " + loses);
    }
}
