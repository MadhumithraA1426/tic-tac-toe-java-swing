package model;

public class GameLogic {

    private String[][] board;

    public GameLogic() {
        board = new String[3][3];
    }

    public boolean makeMove(int row, int col, String player) {

        if (board[row][col] != null) {
            return false;
        }

        board[row][col] = player;
        return true;
    }

    public boolean checkWinner(String player) {

        for (int i = 0; i < 3; i++) {

            if (player.equals(board[i][0]) &&
                    player.equals(board[i][1]) &&
                    player.equals(board[i][2])) {
                return true;
            }

            if (player.equals(board[0][i]) &&
                    player.equals(board[1][i]) &&
                    player.equals(board[2][i])) {
                return true;
            }
        }

        if (player.equals(board[0][0]) &&
                player.equals(board[1][1]) &&
                player.equals(board[2][2])) {
            return true;
        }

        return player.equals(board[0][2]) &&
                player.equals(board[1][1]) &&
                player.equals(board[2][0]);
    }

    public boolean isDraw() {

        for (int i = 0; i < 3; i++) {

            for (int j = 0; j < 3; j++) {

                if (board[i][j] == null) {
                    return false;
                }
            }
        }

        return true;
    }

    public void resetBoard() {
        board = new String[3][3];
    }
}