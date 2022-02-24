class Solution {
    static int MOD = 20170805;
    static int DIRECTION_COUNT = 2;
    static final int DIRECTION_ROW = 0;
    static final int DIRECTION_COL = 1;
    static int[][][] dp;
    static int[] rowData = {1, 0};
    static int[] colData = {0, 1};
    static final int FREE = 0;
    static final int TURN_BAN = 2;
    static final int START = -1;

    public int solution(int m, int n, int[][] cityMap) {
        int answer = 0;

        dp = new int[m][n][DIRECTION_COUNT];
        for (int i = 0; i < m ; ++i) {
            for (int j = 0; j < n; ++j) {
                dp[i][j][DIRECTION_ROW] = -1;
                dp[i][j][DIRECTION_COL] = -1;
            }
        }
        dp[m - 1][n - 1][DIRECTION_ROW] = 1;
        dp[m - 1][n - 1][DIRECTION_COL] = 1;

        answer = dfs(cityMap, 0, 0, START);
        return answer;
    }

    public static int dfs(int[][] cityMap, int row, int col, int direction) {
        int count = 0;

        for (int i = 0; i < rowData.length; ++i) {
            int nextRow = row + rowData[i];
            int nextCol = col + colData[i];

            if (nextRow < 0 || nextRow >= cityMap.length || nextCol < 0 || nextCol >= cityMap[0].length) {
                continue;
            }

            if (cityMap[row][col] == FREE) {
                if (dp[nextRow][nextCol][i] == -1) {
                    count += dfs(cityMap, nextRow, nextCol, i) % MOD;
                } else {
                    count += dp[nextRow][nextCol][i] % MOD;
                }
            } else if (cityMap[row][col] == TURN_BAN && direction == i) {
                if (dp[nextRow][nextCol][i] == -1) {
                    count += dfs(cityMap, nextRow, nextCol, i) % MOD;
                } else {
                    count += dp[nextRow][nextCol][i] % MOD;
                }
            }
        }

        if (direction == START) {
            dp[row][col][DIRECTION_ROW] = count % MOD;
        } else {
            dp[row][col][direction] = count % MOD;
        }

        return count % MOD;
    }
}
