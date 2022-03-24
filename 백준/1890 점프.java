import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] map = new int[N][N];

        StringTokenizer st;
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        long[][] dp = new long[N][N];
        dp[0][0] = 1;
        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                int jumpCount = map[i][j];

                if (jumpCount != 0) {
                    if (i + jumpCount < N) {
                        dp[i + jumpCount][j] += dp[i][j];
                    }

                    if (j + jumpCount < N) {
                        dp[i][j + jumpCount] += dp[i][j];
                    }
                }
            }
        }

        System.out.println(dp[N - 1][N - 1]);
    }
}
