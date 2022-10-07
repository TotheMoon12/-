import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int T = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][T + 1];
        for (int n = 1; n <= N; ++n) {
            st = new StringTokenizer(br.readLine());
            int K = Integer.parseInt(st.nextToken());
            int S = Integer.parseInt(st.nextToken());

            for (int t = 1; t < K && t <= T; ++t) {
                dp[n][t] = dp[n - 1][t];
            }

            for (int t = K; t <= T; ++t) {
                dp[n][t] = Math.max(S + dp[n - 1][t - K], dp[n - 1][t]);
            }
        }

        System.out.print(dp[N][T]);
    }
}
