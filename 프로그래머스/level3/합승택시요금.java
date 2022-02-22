import java.util.ArrayList;

class Solution {
    static final int INF = -1;

    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[][] map = new int[n + 1][n + 1];

        for (int i = 1; i <= n; ++i) {
            for (int j = 1; j <= n; ++j) {
                if (i != j) {
                    map[i][j] = INF;
                }
            }
        }

        int min = 0;
        for (int i = 0; i < fares.length; ++i) {
            int v1 = fares[i][0];
            int v2 = fares[i][1];
            int fare = fares[i][2];

            map[v1][v2] = fare;
            map[v2][v1] = fare;
            min += fare;
        }

        for (int k = 1; k <= n; ++k) {
            for (int i = 1; i <= n; ++i) {
                if (i != k && map[i][k] != INF) {
                    for (int j = 1; j <= n; ++j) {
                        if (map[k][j] != INF) {
                            int time = map[i][k] + map[k][j];

                            if (map[i][j] == INF || time < map[i][j]) {
                                map[i][j] = time;
                            }
                        }

                    }
                }                
            }
        }

        for (int i = 1; i <= n; ++i) {
            if (map[s][i] != INF && map[i][a] != INF && map[i][b] != INF) {
                int time = map[s][i] + map[i][a] + map[i][b];
                min = Math.min(min, time);
            }
        }

        return min;
    }
}
