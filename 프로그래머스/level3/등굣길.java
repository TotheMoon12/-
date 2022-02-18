import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(int m, int n, int[][] puddles) {
        int[][] dp = new int[n][m];
        int[][] map = new int[n][m];
        for (int i = 0; i < puddles.length; ++i) {
            int row = puddles[i][1] - 1;
            int col = puddles[i][0] - 1;

            map[row][col] = 1;
        }

        for (int row = 0; row < n; ++row) {
            if (map[row][0] == 0) {
                dp[row][0] = 1;
            } else {
                break;
            }
        }

        for (int col = 0; col < m; ++col) {
            if (map[0][col] == 0) {
                dp[0][col] = 1;
            } else {
                break;
            }
        }

        for (int row = 1; row < n; ++row) {
            for (int col = 1; col < m; ++col) {
                if (map[row][col] == 0) {
                    dp[row][col] = (dp[row - 1][col] + dp[row][col - 1]) % 1000000007;
                }
            }
        }

        return dp[n - 1][m - 1];
    }
}
