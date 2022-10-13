import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int[][][] dp = new int[N][N][M + 1];

        for (int n = 0; n < N; ++n) {
            dp[n][n][1] = Integer.parseInt(br.readLine());
        }

        for (int from = 0; from < N; ++from) {
            int max = dp[from][from][1];
            int sum = dp[from][from][1];
            for (int to = from + 1; to < N; ++to) {
                if (sum + dp[to][to][1] > dp[to][to][1]) {
                    sum += dp[to][to][1];
                } else {
                    sum = dp[to][to][1];
                }

                max = Math.max(max, sum);
                dp[from][to][1] = max;
            }
        }

        for (int m = 2; m <= M; ++m) {
            for (int from = 0; from < N; ++from) {
                for (int to = from + 2 * m - 2; to < N; ++to) {
                    int max = Integer.MIN_VALUE;
                    for (int bound = from + 1; bound < to; ++bound) {
                        for (int left = 1; left < m; ++left) {
                            if (from + 2 * left - 2 <= bound - 1 && bound + 1 + 2 * (m - left) - 2 <= to) {

                                max = Math.max(max, dp[from][bound - 1][left] + dp[bound + 1][to][m - left]);
                            }
                        }
                    }

                    dp[from][to][m] = max;
                }
            }
        }

        System.out.print(dp[0][N - 1][M]);
    }
}
