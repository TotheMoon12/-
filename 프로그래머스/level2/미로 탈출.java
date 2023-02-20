import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public class Move {
        int row;
        int col;
        int time;

        public Move(int row, int col, int time) {
            this.row = row;
            this.col = col;
            this.time = time;
        }
    }

    public int solution(String[] maps) {
        final int ROW_LENGTH = maps.length;
        final int COL_LENGTH = maps[0].length();
        boolean[][] visited = new boolean[ROW_LENGTH][COL_LENGTH];

        Move startMove = null;
        final char START = 'S';
        final char EXIT = 'E';
        final char LEVER = 'L';

        for (int row = 0; row < ROW_LENGTH; ++row) {
            for (int col = 0; col < COL_LENGTH; ++col) {
                if (maps[row].charAt(col) == START) {
                    startMove = new Move(row, col, 0);
                    visited[row][col] = true;
                    break;
                }
            }
        }

        final int IMPOSSIBLE = -1;
        Move outLastMove = new Move(0,0,0);
        int timeToLever = getMinTime(maps, visited, LEVER, startMove, outLastMove);
        if (timeToLever != IMPOSSIBLE) {
            visited = new boolean[ROW_LENGTH][COL_LENGTH];
            visited[outLastMove.row][outLastMove.col] = true;
            int timeToExit = getMinTime(maps, visited, EXIT, outLastMove, new Move(0, 0, 0));
            return timeToExit;
        }

        return IMPOSSIBLE;
    }

    private int getMinTime(String[] maps, boolean[][] visited, final char target, Move startMove, Move outLastMove) {
        final char WALL = 'X';

        final int ROW_LENGTH = maps.length;
        final int COL_LENGTH = maps[0].length();

        final int[] rowData = {-1, 1, 0, 0};
        final int[] colData = {0, 0, -1, 1};
        Queue<Move> queue = new LinkedList<>();
        queue.offer(startMove);

        final int IMPOSSIBLE = -1;
        while (!queue.isEmpty()) {
            Move move = queue.poll();

            if (maps[move.row].charAt(move.col) == target) {
                outLastMove.row = move.row;
                outLastMove.col = move.col;
                outLastMove.time = move.time;
                return move.time;
            }

            for (int idx = 0; idx < rowData.length; ++idx) {
                int nextRow = move.row + rowData[idx];
                int nextCol = move.col + colData[idx];

                if (nextRow < 0 || nextRow >= ROW_LENGTH || nextCol < 0 || nextCol >= COL_LENGTH
                        || maps[nextRow].charAt(nextCol) == WALL
                        || visited[nextRow][nextCol]) {
                    continue;
                }

                visited[nextRow][nextCol] = true;
                queue.offer(new Move(nextRow, nextCol, move.time + 1));
            }
        }

        return IMPOSSIBLE;
    }
}
