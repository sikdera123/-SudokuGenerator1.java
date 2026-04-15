import java.util.Random;
import java.util.Scanner;

public class SudokuGenerator {

    public static void main(String[] args) {

        int[][] board = new int[9][9];

        fillBoard(board);

        int[][] solution = new int[9][9];
        copyBoard(board, solution);

        removeNumbers(board);

        playGame(board, solution);
    }

    public static void fillBoard(int[][] board) {

        int num = 1;
        for (int col = 0; col < 9; col++) {
            board[0][col] = num;
            num = num + 1;
        }

        for (int row = 1; row < 9; row++) {

            for (int col = 0; col < 9; col++) {
                board[row][col] = board[row - 1][col];
            }

            int shiftAmount;

            if (row == 3 || row == 6) {
                shiftAmount = 1;
            } else {
                shiftAmount = 3;
            }

            for (int s = 0; s < shiftAmount; s++) {

                int firstValue = board[row][0];

                for (int col = 0; col < 8; col++) {
                    board[row][col] = board[row][col + 1];
                }

                board[row][8] = firstValue;
            }
        }
    }

    public static void copyBoard(int[][] original, int[][] copy) {

        for (int row = 0; row < 9; row++) {

            for (int col = 0; col < 9; col++) {

                copy[row][col] = original[row][col];

            }
        }
    }

    public static void removeNumbers(int[][] board) {

        Random rand = new Random();

        int count = 40;

        while (count > 0) {

            int row = rand.nextInt(9);
            int col = rand.nextInt(9);

            if (board[row][col] != 0) {
                board[row][col] = 0;
                count = count - 1;
            }
        }
    }

    public static void playGame(int[][] board, int[][] solution) {

        Scanner input = new Scanner(System.in);

        boolean running = true;

        while (running) {

            printBoard(board);

            System.out.println("Enter row (1-9), column (1-9), number (1-9)");
            System.out.println("Enter 0 to quit");

            int row = input.nextInt();

            if (row == 0) {
                System.out.println("Exiting game");
                running = false;
            } else {

                int col = input.nextInt();
                int num = input.nextInt();

                row = row - 1;
                col = col - 1;

                if (board[row][col] != 0) {
                    System.out.println("Spot already filled");
                } else {

                    if (solution[row][col] == num) {
                        board[row][col] = num;
                        System.out.println("Correct");
                    } else {
                        System.out.println("Incorrect");
                    }
                }

                if (isComplete(board)) {
                    printBoard(board);
                    System.out.println("Puzzle complete!");
                    running = false;
                }
            }
        }
    }

    public static boolean isComplete(int[][] board) {

        for (int row = 0; row < 9; row++) {

            for (int col = 0; col < 9; col++) {

                if (board[row][col] == 0) {
                    return false;
                }
            }
        }

        return true;
    }

    public static void printBoard(int[][] board) {

        for (int row = 0; row < 9; row++) {

            if (row == 3 || row == 6) {
                System.out.println("+-----+-------+-----+");
            }

            for (int col = 0; col < 9; col++) {

                if (col == 3 || col == 6) {
                    System.out.print("| ");
                }

                if (board[row][col] == 0) {
                    System.out.print(". ");
                } else {
                    System.out.print(board[row][col] + " ");
                }
            }

            System.out.println();
        }
    }
}
