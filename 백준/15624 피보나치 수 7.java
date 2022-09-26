import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int N = Integer.parseInt(br.readLine());

        final int MOD = 1000000007;
        int[] dp = new int[N + 1];

        if (N >= 1) {
            dp[1] = 1;
            for (int n = 2; n <= N; ++n) {
                dp[n] = dp[n - 1] + dp[n - 2];
                dp[n] %= MOD;
            }
        }

        System.out.print(dp[N]);
    }
}
