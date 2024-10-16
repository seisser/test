import java.util.Random;

/**
 * Bla bla text
 *
 */
public class SudokuGenerator {


    /**
     * Bla bla text
     *
     */
    public void createNewSudokuGame() {
        int[][] gameBoard = getEmptyBoard();
        randomlyInitiallyPopulate(gameBoard);
        populateBoard(gameBoard);
        replaceNumbersWithZeros(gameBoard );
        printBoard(gameBoard);
    }



    /**
     * Bla bla text
     *
     */
    public int[][] getEmptyBoard() {
        return new int[][]{
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0},
                {0,0,0,0}
        };
    }

    /**
     * Bla bla text
     *
     */
    public void replaceNumbersWithZeros(int[][] board) {
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
     * Bla bla text
     *
     */
    public void randomlyInitiallyPopulate(int[][] board) {
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
     * Bla bla text
     *
     */
    public void printBoard(int[][] board) {
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
     * Bla bla text
     *
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
     * Bla bla text
     *
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
     * Bla bla text
     *
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
     * Bla bla text
     *
     */
    private boolean isValidPlacement(int[][] board, int number, int row, int column) {
        return !isNumberInRow(board,number,row) &&
                !isNumberInColumn(board,number,column) &&
                !isNumberInGrid(board,number,row,column);
    }


    /**
     * Bla bla text
     *
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