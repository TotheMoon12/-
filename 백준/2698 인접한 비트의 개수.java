import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());

        StringBuilder builder = new StringBuilder();
        for (int t = 0; t < T; ++t) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int K = Integer.parseInt(st.nextToken());

            int[][][] dp = new int[N][K + 1][2];
            dp[0][0][0] = 1;
            dp[0][0][1] = 1;

            for (int n = 0; n < N - 1; ++n) {
                for (int k = 0; k <= K; ++k) {
                    if (k < K) {
                        dp[n + 1][k + 1][1] += dp[n][k][1];
                    }
                    dp[n + 1][k][1] += dp[n][k][0];
                    dp[n + 1][k][0] += dp[n][k][1] + dp[n][k][0];
                }
            }

            builder.append(dp[N - 1][K][0] + dp[N - 1][K][1]);
            builder.append(System.lineSeparator());
        }

        System.out.print(builder);
        br.close();
    }
}
