import java.util.Random;

/**
 * A class representing a Sudoku generator
 */
public class SudokuGenerator {


    /**
     *Creates and prints a new Sudoku game.
     */
    protected void createNewSudokuGame() {
        int[][] gameBoard = getEmptyBoard();
        randomlyInitiallyPopulate(gameBoard);
        populateBoard(gameBoard);
        replaceNumbersWithZeros(gameBoard );
        printBoard(gameBoard);
    }



    /**
     * Returns an empty Sudoku board
     * @return int[][] - A Sudoku board
     */
    private int[][] getEmptyBoard() {
        return new int[][]{
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0}
        };
    }

    /**
     * Replaces the complete Sudoku with zeros, representing empty spaces that the user fills in.
     * @param board - The Sudoku board
     */
    private void replaceNumbersWithZeros(int[][] board) {
        Random random = new Random();
        for (int i = 0; i < board.length; i++) {
            int first = random.nextInt(4);
            int second = random.nextInt(4);
            int third = random.nextInt(4);
            board[i][first] = 0;
            board[i][second] = 0;
            board[i][third] = 0;
        }
    }

    /**
     * Randomly places three numbers on the board, used before the total population of the board. To get more randomness.
     * @param board - The sudoku board
     */
    private void randomlyInitiallyPopulate(int[][] board) {
        Random random = new Random();

        for (int i = 0; i < 3; i++) {
            int row = random.nextInt(4);
            int col = random.nextInt(4);
            int number = random.nextInt(4 + 1);

            if (board[row][col] == 0 && isValidPlacement(board,number, row, col)) {
                board[row][col] = number;
            }
        }

    }

    /**
     * Prints the board in the console for the user
     * @param board - The sudoku board
     */
    private void printBoard(int[][] board) {
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board.length; j++) {
                System.out.print(board[i][j]);
                if (j == 1) {
                    System.out.print(" | ");
                }
                if (j == 3) {
                    System.out.print("\n");
                    if (i == 1) {
                        System.out.print("--------" + "\n");
                    }
                }

            }
        }
    }

    /**
     * Checks if the number is already in the row, since that is not allowed
     * @param board - The sudoku board
     * @param number - The number to be placed on the board
     * @param row - The row that the number will be placed in
     * @return boolean - The result of the check
     */
    private boolean isNumberInRow(int[][] board, int number, int row) {
        for (int i = 0; i < 4; i++) {
            if (board[row][i] == number) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the number is already in the column, since that is not allowed.
     *  @param board - The sudoku board
     *  @param number - The number to be placed on the board
     *  @param column - The column that the number will be placed in
     *  @return boolean - The result of the check
     */
    private boolean isNumberInColumn(int[][] board, int number, int column) {
        for (int i = 0; i < 4; i++) {
            if (board[i][column] == number) {
                return true;
            }
        }
        return false;
    }

    /**
     * Checks if the number is in the 4*4 grid, by calculating the position of the upper left square in the grid
     * and then performs the check from there in a nested for loop.
     *  @param board - The sudoku board
     *  @param number - The number to be placed on the board
     *  @param row - The row that the number will be placed in
     *  @param column - The column that the number will be placed in
     *  @return boolean - The result of the check
     */
    private boolean isNumberInGrid(int[][] board, int number, int row, int column) {
        int localBoxRow = row - row % 2;
        int localBoxColumn = column - column % 2;

        for (int i = localBoxRow; i < localBoxRow + 2; i++) {
            for (int j = localBoxColumn; j < localBoxColumn + 2; j++) {
                if (board[i][j] == number) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Checks if the placement is valid, and not violating the Sudoku rules (not in the same row, column or square).
     *  @param board - The sudoku board
     *  @param number - The number to be placed on the board
     *  @param row - The row that the number will be placed in
     *  @param column - The column that the number will be placed in
     *  @return boolean - The result of the check
     */
    private boolean isValidPlacement(int[][] board, int number, int row, int column) {
        return !isNumberInRow(board,number,row) &&
                !isNumberInColumn(board,number,column) &&
                !isNumberInGrid(board,number,row,column);
    }


    /**
     * A recursive method that populates the board with numbers according to the rules. If the placement is valid,
     * it continues to do the population process on step at a time. If placement is invalid, it starts backtracking
     * and undo the previous step so that it can try again and choose a different path since the current was not successful.
     * @param board - The sudoku board
     * @return boolean - The result of the recursive method
     */
    private boolean populateBoard(int[][] board) {
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {
                if (board[row][col] == 0) {
                    for (int placedSudokuNumber = 1; placedSudokuNumber <= 4; placedSudokuNumber++) {
                        if (isValidPlacement(board, placedSudokuNumber, row, col)) {
                            board[row][col] = placedSudokuNumber;
                            if (populateBoard(board)) {
                                return true;
                            }
                            else {
                                board[row][col] = 0;
                            }
                        }
                    }
                    return false;
                }
            }
        }
        return true;
    }
}
