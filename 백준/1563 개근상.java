import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(br.readLine());
        int[][][] dp = new int[N][2][3];
        dp[0][0][0] = 1;
        dp[0][0][1] = 1;
        dp[0][1][0] = 1;

        final int MAX_LATE = 1;
        final int MAX_SUCCESSIVE_ABSENT = 2;
        final int MOD = 1000000;

        for (int n = 1; n < N; ++n) {
            // 출석
            dp[n][0][0] += dp[n - 1][0][0];
            dp[n][0][0] += dp[n - 1][0][1];
            dp[n][0][0] += dp[n - 1][0][2];
            dp[n][0][0] %= MOD;

            dp[n][1][0] += dp[n - 1][1][0];
            dp[n][1][0] += dp[n - 1][1][1];
            dp[n][1][0] += dp[n - 1][1][2];
            dp[n][1][0] %= MOD;
            
            // 지각
            dp[n][1][0] += dp[n - 1][0][0];
            dp[n][1][0] += dp[n - 1][0][1];
            dp[n][1][0] += dp[n - 1][0][2];
            dp[n][1][0] %= MOD;

            // 결석
            dp[n][0][1] += dp[n - 1][0][0];
            dp[n][0][1] %= MOD;
            dp[n][0][2] += dp[n - 1][0][1];
            dp[n][0][2] %= MOD;

            dp[n][1][1] += dp[n - 1][1][0];
            dp[n][1][1] %= MOD;
            dp[n][1][2] += dp[n - 1][1][1];
            dp[n][1][2] %= MOD;
        }

        int answer = 0;
        for (int late = 0; late <= MAX_LATE; ++late) {
            for (int absent = 0; absent <= MAX_SUCCESSIVE_ABSENT; ++absent) {
                answer += dp[N - 1][late][absent];
                answer %= MOD;
            }
        }

        System.out.print(answer);
        br.close();
    }
}
