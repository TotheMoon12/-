import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public class Move {
        int row;
        int col;
        int count;

        public Move(int row, int col, int count) {
            this.row = row;
            this.col = col;
            this.count = count;
        }
    }


    public int solution(String[] board) {
        int answer = -1;

        Move start = null;
        Move dest = null;
        final int ROW_SIZE = board.length;
        final int COL_SIZE = board[0].length();
        for (int row = 0; row < ROW_SIZE; ++row) {
            for (int col = 0; col < COL_SIZE; ++col) {
                if (board[row].charAt(col) == 'R') {
                    start = new Move(row, col, 0);
                } else if (board[row].charAt(col) == 'G') {
                    dest = new Move(row, col, 0);
                }
            }
        }

        final int[] rowData = {-1, 1, 0, 0};
        final int[] colData = {0, 0, -1, 1};

        final boolean[][] visited = new boolean[ROW_SIZE][COL_SIZE];
        visited[start.row][start.col] = true;
        Queue<Move> queue = new LinkedList<>();
        queue.add(start);
        while (!queue.isEmpty()) {
            Move move = queue.poll();

            if (move.row == dest.row && move.col == dest.col) {
                answer = move.count;
                break;
            }

            for (int idx = 0; idx < rowData.length; ++idx) {
                int nextRow = move.row;
                int nextCol = move.col;
                while (nextRow >= 0 && nextRow < ROW_SIZE
                        && nextCol >= 0 && nextCol < COL_SIZE
                        && board[nextRow].charAt(nextCol) != 'D') {
                    nextRow += rowData[idx];
                    nextCol += colData[idx];
                }

                nextRow -= rowData[idx];
                nextCol -= colData[idx];

                if (!visited[nextRow][nextCol]) {
                    visited[nextRow][nextCol] = true;
                    queue.add(new Move(nextRow, nextCol, move.count + 1));
                }
            }
        }

        return answer;
    }
}
