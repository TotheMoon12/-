import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int[][] board) {
        int answer = 0;
        final int DIRECTION_ROW = 0;
        final int DIRECTION_COL = 1;

        Queue<Integer> queue = new LinkedList<>();
        queue.add(0); // row
        queue.add(0); // col
        queue.add(DIRECTION_ROW); // direction
        queue.add(0); // money
        int[] rowData = {-1, 1, 0, 0};
        int[] colData = {0, 0, -1, 1};

        int[][][] dp = new int[board.length][board.length][2];
        while (!queue.isEmpty()) {
            int row = queue.poll();
            int col = queue.poll();
            int direction = queue.poll();
            int money = queue.poll();

            for (int i = 0; i < rowData.length; ++i) {
                int nextRow = row + rowData[i];
                int nextCol = col + colData[i];

                if (nextRow < 0 || nextRow >= board.length || nextCol < 0 || nextCol >= board.length) {
                    continue;
                }

                if (board[nextRow][nextCol] == 0) {
                    int nextDirection = (rowData[i] != 0) ? DIRECTION_ROW : DIRECTION_COL;
                    int nextMoney = money + 100;
                    if (!(row == 0 && col == 0) && direction != nextDirection) {
                        nextMoney += 500;
                    }

                    if (nextRow == board.length - 1 && nextCol == board.length - 1) {
                        if (dp[nextRow][nextCol][nextDirection] == 0 || nextMoney <= dp[nextRow][nextCol][nextDirection]) {
                            dp[nextRow][nextCol][nextDirection] = nextMoney;
                        }
                    } else if (!(nextRow == 0 && nextCol == 0) && dp[nextRow][nextCol][nextDirection] == 0 || nextMoney < dp[nextRow][nextCol][nextDirection]){
                        dp[nextRow][nextCol][nextDirection] = nextMoney;
                        queue.add(nextRow);
                        queue.add(nextCol);
                        queue.add(nextDirection);
                        queue.add(nextMoney);
                    }
                }
            }
        }

        int rowAnswer = dp[board.length - 1][board.length - 1][0];
        int colAnswer = dp[board.length - 1][board.length - 1][1];
        if (rowAnswer != 0 && colAnswer != 0) {
            return Math.min(rowAnswer, colAnswer);
        } else if (rowAnswer == 0) {
            return colAnswer;
        } else {
            return rowAnswer;
        }
    }
}
