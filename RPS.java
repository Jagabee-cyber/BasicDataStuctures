
import java.util.Scanner;

public class RPS extends RPSAbstract {
    // Messages for the game.
    protected static final String GAME_NOT_IMPLEMENTED =
            "Game not yet implemented.";
    /**
     * Construct a new instance of RPS with the given possible moves.
     *
     * @param moves all possible moves in the game.
     */
    public RPS(String[] moves) {
        this.possibleMoves = moves;
        this.playerMoves = new String[MAX_GAMES];
        this.cpuMoves = new String[MAX_GAMES];
    }

    public static void main(String[] args) {
        // If command line args are provided use those as the possible moves
        String[] moves = new String[args.length];
        if (args.length >= MIN_POSSIBLE_MOVES) {
            System.arraycopy(args, 0, moves, 0, args.length);
        } else {
            moves = RPS.DEFAULT_MOVES;
        }
        // Create new game and scanner
        RPS game = new RPS(moves);
        Scanner in = new Scanner(System.in);

        // While user does not input "q", play game
        String response = "";
        String quit = "q";

        // TODO: Insert the code to play the game.
        // See the writeup for an example of the game play.
        // Hint: call the methods we/you have already written
        // to do most of the work! And don't forget Javadoc.

        while (true){
            System.out.println(RPS.PROMPT_MOVE);
            response = in.nextLine();
            if (response.equalsIgnoreCase(quit)) {
                break;
            }

            game.playRPS(response, game.genCPUMove());
            
        }

        game.displayStats();
        


        in.close();
    }

    @Override
    public int determineWinner(String playerMove, String cpuMove) {
        // TODO
        if (!isValidMove(playerMove) || !isValidMove(cpuMove)){
            return RPS.INVALID_INPUT_OUTCOME;
        }
        int length = this.possibleMoves.length;
        int playerInd = 0;
        int cpuInd = 0;

        for (int i = 0; i < length; ++i){
            if (this.possibleMoves[i].equals(playerMove)){
                playerInd = i;
            }

            if (this.possibleMoves[i].equals(cpuMove)){
                cpuInd = i;
            }
        }

        

        if (playerInd == 0) { //Establishing Player choosing the leftmost limit
            if (cpuInd == 1) {
                return RPS.CPU_WIN_OUTCOME;
            }
            else if (cpuInd == length-1) {
                return RPS.PLAYER_WIN_OUTCOME;
            }
            else {
                return RPS.TIE_OUTCOME;
            }
        }
        else if (playerInd == length-1) {
            if (cpuInd == 0) {
                return RPS.CPU_WIN_OUTCOME;
            }
            else if (cpuInd == length-2){
                return RPS.PLAYER_WIN_OUTCOME;
            }
            else {
                return RPS.TIE_OUTCOME;
            }
        }
        else if (cpuInd == 0) { 
            if (playerInd == 1) {
                return RPS.PLAYER_WIN_OUTCOME;
            }
            else if (playerInd == length-1) {
                return RPS.CPU_WIN_OUTCOME;
            }
            else {
                return RPS.TIE_OUTCOME;
            }
        }
        else if (cpuInd == length-1) {
            if (playerInd == 0) {
                return RPS.PLAYER_WIN_OUTCOME;
            }
            else if (playerInd == length-2){
                return RPS.CPU_WIN_OUTCOME;
            }
            else {
                return RPS.TIE_OUTCOME;
            }
        }
        else {
            if (playerInd - cpuInd == 1){
                return RPS.PLAYER_WIN_OUTCOME;
            }
            else if (cpuInd - playerInd == 1){
                return RPS.CPU_WIN_OUTCOME;
            }
            else {
                return RPS.TIE_OUTCOME;
            }
        }
    }
}
