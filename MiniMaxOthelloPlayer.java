import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MiniMaxOthelloPlayer extends OthelloPlayer {

    Random r = new Random();

    public OthelloMove getMove(OthelloState state) {
        return miniMax(state);
    }

    public OthelloMove miniMax(OthelloState initialState) {
        List<OthelloMove> moves = initialState.generateMoves();
        ArrayList<OthelloState> states = new ArrayList<>();
        ArrayList<OthelloState> minList = new ArrayList<>();

        for(OthelloMove move : moves) {
            states.add(initialState.applyMoveCloning(move));
        }

        for(OthelloState state : states) {
            List<OthelloMove> nextMoves = state.generateMoves();

            OthelloState minState = null;
            OthelloState tempState;

            int score = Integer.MAX_VALUE;
            for(OthelloMove nextMove : nextMoves) {
                tempState = state.applyMoveCloning(nextMove);
                if(tempState != null && tempState.score() < score) {
                    score = tempState.score();
                    minState = tempState;
                    minState.setPreviousMove(nextMove);
                }
            }
            minList.add(minState);
        }

        OthelloState miniMax = null;
        int maxScore = Integer.MIN_VALUE;
        for(OthelloState maxState : minList) {
            if(maxState.score() > maxScore) {
                miniMax = maxState;
            }
        }

        return miniMax.getPreviousMove();
    }

    public OthelloMove maxValue() {
        return null;
    }

    public OthelloMove minValue() {
        return null;
    }

}
