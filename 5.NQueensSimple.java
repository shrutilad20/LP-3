public class NQueensSimple {

    static final int N = 4; // You can change N to 8, etc.
    // Function to print the board
    static void printBoard(int board[][]) {
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                System.out.print((board[i][j] == 1 ? "Q " : ". "));
            }
            System.out.println();
        }
        System.out.println();
    }
    // Function to check if placing a queen is safe
    static boolean isSafe(int board[][], int row, int col) {
        // Check this row on the left
        for (int i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;
        // Check upper diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;
        // Check lower diagonal
        for (int i = row, j = col; i < N && j >= 0; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }
    // Recursive function to solve N-Queens problem
    static boolean solveNQ(int board[][], int col) {
        if (col >= N)
            return true;
        for (int i = 0; i < N; i++) {
            if (isSafe(board, i, col)) {
                board[i][col] = 1;

                if (solveNQ(board, col + 1))
                    return true;
                // Backtrack
                board[i][col] = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int board[][] = new int[N][N];

        // Try placing the first queen in the first column (user can change position)
        board[0][0] = 1;

        // Start solving from the next column
        if (solveNQ(board, 1)) {
            System.out.println("Final N-Queens Board:");
            printBoard(board);
        } else {
            System.out.println("No solution found with first Queen at (0,0).");
            System.out.println("Trying different first positions...");
            // Try placing the first Queen in other rows if needed
            boolean found = false;
            for (int i = 0; i < N; i++) {
                board = new int[N][N];
                board[i][0] = 1;
                if (solveNQ(board, 1)) {
                    System.out.println("Solution found with first Queen at row " + i + ":");
                    printBoard(board);
                    found = true;
                    break;
                }
            }
            if (!found)
                System.out.println("Solution does not exist for any first position!");
        }
    }
}
