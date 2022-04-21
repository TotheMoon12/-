import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(br.readLine());

        final int FROM_UP = 0;
        final int FROM_LEFT = 1;
        int[][][] map = new int[N + 1][M + 1][2];
        for (int i = 0; i < K; ++i) {
            st = new StringTokenizer(br.readLine());

            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());

            if (x1 > x2) {
                map[x1][y1][FROM_UP] = 1;
            } else if (x1 == x2) {
                if (y1 > y2) {
                    map[x1][y1][FROM_LEFT] = 1;
                } else {
                    map[x2][y2][FROM_LEFT] = 1;
                }
            } else {
                map[x2][y2][FROM_UP] = 1;
            }
        }

        long[][] dp = new long[N + 1][M + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= N; ++i) {
            if (map[i][0][FROM_UP] == 0) {
                dp[i][0] = 1;
            } else {
                break;
            }
        }

        for (int i = 1; i <= M; ++i) {
            if (map[0][i][FROM_LEFT] == 0) {
                dp[0][i] = 1;
            } else {
                break;
            }
        }

        for (int i = 1; i <= N; ++i) {
            for (int j = 1; j <= M; ++j) {
                if (map[i][j][FROM_UP] != 1) {
                    dp[i][j] += dp[i - 1][j];
                }

                if (map[i][j][FROM_LEFT] != 1) {
                    dp[i][j] += dp[i][j - 1];
                }
            }
        }

        System.out.println(dp[N][M]);
    }
}
