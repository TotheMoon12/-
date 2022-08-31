import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        //f(n) = f(n + 2) - f(n + 1) n < 0
        // f(0) = 0
        // f(1) = 1
        // f(n) = f(n - 1) + f(n - 2) n > 1

        int[] dp = new int[2];
        dp[1] = 1;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int sign;
        int answer;
        final int MOD = 1000000000;
        if (N == 0) {
            sign = 0;
            answer = 0;
        } else if (N == 1) {
            sign = 1;
            answer = 1;
        } else if (N < 0) {
            for (int n = -1; n >= N; --n) {
                int temp = (dp[1] - dp[0]) % MOD;
                dp[1] = dp[0];
                dp[0] = temp;
            }

            if (N % 2 == 0) {
                sign = -1;
            } else {
                sign = 1;
            }
            answer = dp[0];
        } else {
            for (int n = 2; n <= N; ++n) {
                int temp = (dp[0] + dp[1]) % MOD;
                dp[0] = dp[1];
                dp[1] = temp;
            }

            sign = 1;
            answer = dp[1];
        }

        answer = Math.abs(answer % MOD);
        System.out.println(sign);
        System.out.println(answer);
    }
}
