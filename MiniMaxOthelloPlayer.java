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

    private OthelloState max(OthelloState state, int depth) {
        depth--;
        OthelloState maxState = null;
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
                maxState = tempState;
            }
        }
        return maxState;
    }

    private OthelloState min(OthelloState state, int depth) {
        depth--;

        OthelloState minState = null;
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
                minState = state;
            }
        }
        return minState;
    }
}

