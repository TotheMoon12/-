import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];
        dp[0] = 1;

        if (N > 0) {
            dp[1] = 1;
        }

        final int MOD = 1000000007;
        for (int n = 2; n <= N; ++n) {
            dp[n] = dp[n - 1] + dp[n - 2] + 1;
            dp[n] %= MOD;
        }

        bw.write(String.valueOf(dp[N]));
        br.close();
        bw.close();
    }
}
