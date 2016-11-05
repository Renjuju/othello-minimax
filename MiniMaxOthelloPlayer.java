import java.util.ArrayList;
import java.util.List;

public class MiniMaxOthelloPlayer extends OthelloPlayer {


    private int depth;

    MiniMaxOthelloPlayer(int depth) {
        this.depth = depth;
    }

    public OthelloMove getMove(OthelloState state) {
        return miniMax(state);
    }

    private OthelloMove miniMax(OthelloState state) {
        List<OthelloMove> moves = state.generateMoves();

        int max = Integer.MIN_VALUE;
        OthelloMove bestMove = null;
        for(OthelloMove move : moves) {
            state = min(state.applyMoveCloning(move), depth);
            if(state.score() > max) {
                max = state.score();
                bestMove = move;
            }
        }
        return bestMove;
    }

    //see min
    private OthelloState max(OthelloState state, int depth) {
        depth--;
        OthelloState bestState = null;
        List<OthelloMove> moves = state.generateMoves();

        if(moves.size() == 0 || depth == 0) {
            return state;
        }

        int val = Integer.MIN_VALUE;

        OthelloState tempState;
        for(OthelloMove move : moves) {
            tempState = min(state.applyMoveCloning(move), depth);
            if(tempState.score() > val) {
                val = tempState.score();
                bestState = tempState;
            }
        }
        return bestState;
    }

    //see max
    private OthelloState min(OthelloState state, int depth) {
        depth--;

        OthelloState bestState = null;
        List<OthelloMove> moves = state.generateMoves();

        if(moves.size() == 0 || depth == 0) {
            return state;
        }

        int val = Integer.MAX_VALUE;
        OthelloState tempState;
        for(OthelloMove move : moves) {
            tempState = max(state.applyMoveCloning(move), depth);
            if(tempState.score() < val) {
                val = tempState.score();
                bestState = state;
            }
        }
        return bestState;
    }
}

//wiki reference
//        01 function minimax(node, depth, maximizingPlayer)
//        02     if depth = 0 or node is a terminal node
//        03         return the heuristic value of node
//
//        04     if maximizingPlayer
//        05         bestValue := −∞
//        06         for each child of node
//        07             v := minimax(child, depth − 1, FALSE)
//        08             bestValue := max(bestValue, v)
//        09         return bestValue
//
//        10     else    (* minimizing player *)
//        11         bestValue := +∞
//        12         for each child of node
//        13             v := minimax(child, depth − 1, TRUE)
//        14             bestValue := min(bestValue, v)
//        15         return bestValue

