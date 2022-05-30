import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int max = 80;
        long[] dp = new long[max + 1];
        dp[1] = 1;
        dp[2] = 1;

        for (int idx = 3; idx <= max; ++idx) {
            dp[idx] = dp[idx - 1] + dp[idx - 2];
        }

        long answer = dp[n] * 2 + (dp[n - 1] + dp[n]) * 2;
        System.out.println(answer);
    }
}
