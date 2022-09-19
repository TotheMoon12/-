import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // f(n) = f(n - 1) + f(n - 3), f(1) = f(2) = f(3) = 1
        int n = Integer.parseInt(br.readLine());
        final int MAX_N = 116;
        long[] dp = new long[MAX_N + 1];
        dp[1] = 1;
        dp[2] = 1;
        dp[3] = 1;

        for (int number = 4; number <= n; ++number) {
            dp[number] = dp[number - 1] + dp[number - 3];
        }

        System.out.print(dp[n]);
    }
}
