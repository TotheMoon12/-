import java.util.Arrays;

class Solution {
    public int solution(String[] board) {
        int answer = 1;

        int oCount = 0;
        int xCount = 0;
        final int SIZE = board.length;
        for (int row = 0; row < SIZE; ++row) {
            for (int col = 0; col < SIZE; ++col) {
                char c = board[row].charAt(col);
                if (c == 'O') {
                    ++oCount;
                } else if (c == 'X') {
                    ++xCount;
                }
            }
        }

        // diff > 1, diff < 0
        int diff = oCount - xCount;
        if (diff > 1 || diff < 0) {
            answer = 0;
        } else {
            if (oCount == 0 && xCount == 0) {
                return 1;
            }

            char[][] simulationBoard = new char[SIZE][SIZE];
            for (char[] row : simulationBoard) {
                Arrays.fill(row, '.');
            }

            if (!isPossible(board, simulationBoard, 'O')) {
                answer = 0;
            }
        }

        return answer;
    }

    public boolean isPossible(String[] targetBoard, char[][] board, char turn) {
        char nextTurn;
        if (turn == 'O') {
            nextTurn = 'X';
        } else {
            nextTurn = 'O';
        }

        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board.length; ++col) {
                if (board[row][col] == '.') {
                    board[row][col] = turn;
                    if (isSame(targetBoard, board)) {
                        return true;
                    }

                    if (existBingo(board)) {
                        board[row][col] = '.';
                        continue;
                    }

                    if (isPossible(targetBoard, board, nextTurn)) {
                        return true;
                    }

                    board[row][col] = '.';
                }
            }
        }

        return false;
    }

    public boolean isSame(String[] targetBoard, char[][] board) {
        for (int row = 0; row < board.length; ++row) {
            for (int col = 0; col < board.length; ++col) {
                if (targetBoard[row].charAt(col) != board[row][col]) {
                    return false;
                }
            }
        }

        return true;
    }

    public boolean existBingo(char[][] board) {
        for (int row = 0; row < board.length; ++row) {
            char c1 = board[row][0];
            char c2 = board[row][1];
            char c3 = board[row][2];
            if (c1 != '.' && c1 == c2 && c2 == c3) {
                return true;
            }
        }

        for (int col = 0; col < board.length; ++col) {
            char c1 = board[0][col];
            char c2 = board[1][col];
            char c3 = board[2][col];
            if (c1 != '.' && c1 == c2 && c2 == c3) {
                return true;
            }
        }

        char c1 = board[0][0];
        char c2 = board[1][1];
        char c3 = board[2][2];

        if (c1 != '.' && c1 == c2 && c2 == c3) {
            return true;
        }

        c1 = board[0][2];
        c3 = board[2][0];

        if (c1 != '.' && c1 == c2 && c2 == c3) {
            return true;
        }

        return false;
    }
}
