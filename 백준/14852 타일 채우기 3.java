import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(br.readLine());
        final int MAX_N = 1000000;
        int[] dp = new int[MAX_N + 1];
        dp[0] = 1;
        dp[1] = 2;
        dp[2] = 7;

        final int MOD = 1000000007;
        int sum = dp[1] + dp[2];
        for (int n = 3; n <= N; ++n) {
            dp[n] += dp[n - 2];
            dp[n] %= MOD;

            dp[n] += sum;
            dp[n] %= MOD;
            dp[n] += sum;
            dp[n] %= MOD;
            dp[n] += 2;
            dp[n] %= MOD;
            sum += dp[n];
            sum %= MOD;
        }

        System.out.print(dp[N] % MOD);
        br.close();
    }
}
