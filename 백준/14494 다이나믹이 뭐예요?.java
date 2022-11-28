import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        long[][] dp = new long[N][M];
        for (int n = 0; n < N; ++n) {
            dp[n][0] = 1L;
        }

        for (int m = 0; m < M; ++m) {
            dp[0][m] = 1L;
        }

        final int MOD = 1000000007;
        for (int n = 1; n < N; ++n) {
            for (int m = 1; m < M; ++m) {
                dp[n][m] += dp[n - 1][m];
                dp[n][m] += dp[n][m - 1];
                dp[n][m] += dp[n - 1][m - 1];
                dp[n][m] %= MOD;
            }
        }

        bw.write(Long.toString(dp[N - 1][M - 1]));
        br.close();
        bw.close();
    }
}
