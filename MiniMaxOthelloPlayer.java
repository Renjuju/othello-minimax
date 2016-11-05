import java.util.ArrayList;
import java.util.List;

public class MiniMaxOthelloPlayer extends OthelloPlayer {


    public OthelloMove getMove(OthelloState state) {
        Node node = new Node(state);
//        Node node1 = search(node, 4);
//
//        System.out.println(node1.children.size());
        System.exit(0);
        return null;
    }

    public OthelloMove nonWorkingMiniMax(OthelloState initialState) {
        List<OthelloMove> moves = initialState.generateMoves();
        ArrayList<OthelloState> states = new ArrayList<>();
        ArrayList<OthelloState> minList = new ArrayList<>();

        for(OthelloMove move : moves) {
            states.add(initialState.applyMoveStateCloning(move));
        }

        for(OthelloState state : states) {
            List<OthelloMove> nextMoves = state.generateMoves();

            OthelloState minState = null;
            OthelloState tempState;

            int score = Integer.MAX_VALUE;
            for(OthelloMove nextMove : nextMoves) {
                tempState = state.applyMoveStateCloning(nextMove);
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

        if(minList.size() < 1) {
            return null;
        }

        for(OthelloState maxState : minList) {
            if(maxState != null && maxState.score() > maxScore) {
                miniMax = maxState;
            }
        }

        if(miniMax == null) {
            System.exit(0);
        }

        return miniMax.getPreviousMove();
    }
}
//01 function minimax(node, depth, maximizingPlayer)
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
