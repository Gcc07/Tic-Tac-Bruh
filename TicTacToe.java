import java.util.Scanner;

public class TicTacToe {
    public static Scanner input = new Scanner(System.in);
    public static char[][] board = new char[3][3];

    public static void main(String[] args) {
        initializeBoard();
        char currentPlayer = 'X';
        boolean gameOver = false;

        // Main game loop
        while (!gameOver) {
            printBoard();

            // Get player move
            System.out.println("Player " + currentPlayer + ", enter row (0-2): ");
            int row = input.nextInt();
            System.out.println("Player " + currentPlayer + ", enter column (0-2): ");
            int col = input.nextInt();

            // REVIEW 1: The program places the mark and checks if it’s valid.
            boolean validMove = placeMark(row, col, currentPlayer);
            if (!validMove) {
                System.out.println("Invalid move, try again.");
                continue; // skip rest of loop and ask again
            }

            // REVIEW 2: The program checks if the current player has won.
            if (checkWin(currentPlayer)) {
                printBoard();
                System.out.println("Player " + currentPlayer + " wins!");
                gameOver = true;
            }
            // REVIEW 3: The program checks if the board is full, meaning a draw.
            else if (isBoardFull()) {
                printBoard();
                System.out.println("It's a draw!");
                gameOver = true;
            }
            // REVIEW 4: The program switches to the other player for the next turn.
            else {
                currentPlayer = (currentPlayer == 'X') ? 'O' : 'X';
            }
        }
    }

    // Initializes the board with spaces
    public static void initializeBoard() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                board[i][j] = ' ';
            }
        }
    }

    // Prints the board with grid lines
    public static void printBoard() {
        System.out.println("-------------");
        for (int i = 0; i < 3; i++) {
            System.out.print("| ");
            for (int j = 0; j < 3; j++) {
                System.out.print(board[i][j] + " | ");
            }
            System.out.println();
            System.out.println("-------------");
        }
    }

    // TODO 5: Complete this method
    // Purpose: Place the player's mark on the board if the space is empty.
    // Return true if successful, or false if the move is invalid.
    public static boolean placeMark(int row, int col, char mark) {
        if (board[row][col] == ' ') {
            board[row][col] = mark;
            return true;
        }
        else {
            return false;
        }
    }

    // TODO 6: Complete this method
    // Purpose: Return true if the given player has three in a row.
    // Check all rows, columns, and both diagonals.

    // I ended up doing three seperate check types for each condition. Kind of weird, but it works.
    public static boolean checkWin(char mark) {
        // Check row wins - Counting system
        int mark_counter = 0;
        for (char[] row : board) {
            for (char row_mark : row) {
                if (row_mark == mark) {mark_counter++;}
                else {break;}
            }
            if (mark_counter == 3) {return true;} // If there are three in a row, return true.
            mark_counter = 0; // Reset counter for next row.
        }
        // Check column wins - Assume no win
        for (int c = 0; c < board.length; c++) { // for each column
            boolean columnWin = true; // assume a win.
            for (int r = 0; r < board.length; r++) { // for each row,
                if (board[r][c] != mark) { // If the mark isn't there,
                    columnWin = false; // NO WINNER
                    break;
                }
            }
            if (columnWin) return true;
        }
        // Check diagonal wins (This is where I get lazy.)
        if ((board[0][0] == mark && board[1][1] == mark && board[2][2] == mark) ||
            (board[0][2] == mark && board[1][1] == mark && board[2][0] == mark)) {
            return true;
        }
        return false; // temporary
    }

    // TODO 7: Complete this method
    // Purpose: Return true if the board is full (no spaces left)
    public static boolean isBoardFull() {
        int mark_counter = 0;
        for (char[] row : board) {
            for (char row_mark : row) {
                if (row_mark == 'X' || row_mark == 'O') {mark_counter++;}
                else {break;}
            }
            if (mark_counter == 9) {return true;} // If there are three in a row, return true.
            mark_counter = 0; // Reset counter for next row.
        }
        return false; // temporary
    }   
}
