import java.util.HashMap;

class Solution {
    public int solution(int n, int m, int[][] edge_list, int k, int[] gps_log) {
        HashMap<Integer, Boolean>[] map = new HashMap[n + 1];
        for (int i = 1; i <= n; ++i) {
            map[i] = new HashMap<>();
            map[i].put(i, true);
        }

        for (int i = 0; i < edge_list.length; ++i) {
            int v1 = edge_list[i][0];
            int v2 = edge_list[i][1];
            map[v1].put(v2, true);
            map[v2].put(v1, true);
        }

        final int INF = n + 1;
        int[][] dp = new int[gps_log.length][n + 1];
        int start = gps_log[0];
        for (int j = 1; j <= n; ++j) {
            if (map[start].containsKey(j)) {
                if (gps_log[1] != j) {
                    dp[1][j] = 1;
                }
            } else {
                dp[1][j] = INF;
            }
        }

        for (int i = 2; i < gps_log.length; ++i) {
            for (int j = 1; j <= n; ++j) {
                dp[i][j] = INF;
                for (int prev : map[j].keySet()) {
                    if (dp[i - 1][prev] != INF) {
                        if (gps_log[i] != j) {
                            dp[i][j] = Math.min(dp[i][j], dp[i - 1][prev] + 1);
                        } else {
                            dp[i][j] = Math.min(dp[i][j], dp[i - 1][prev]);
                        }
                    }
                }
            }
        }

        int answer = dp[gps_log.length - 1][gps_log[gps_log.length - 1]];
        if (answer == INF) {
            return -1;
        } else {
            return answer;
        }
    }
}
