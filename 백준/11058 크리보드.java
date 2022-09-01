import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.Buffer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());        
        long[][] dp = new long[N + 1][2];
        final int SCREEN = 0;
        final int BUFFER = 1;
        
        for (int n  = 1; n <= N; ++n) {
            dp[n][SCREEN] = dp[n - 1][SCREEN] + 1;
            dp[n][BUFFER] = dp[n - 1][BUFFER];

            for (int prev = n - 3; prev >= 0; --prev) {
                if (dp[prev][SCREEN] * (n - 3 - prev + 2) > dp[n][SCREEN]) {
                    dp[n][SCREEN] = dp[prev][SCREEN] * (n - 3 - prev + 2);
                    dp[n][BUFFER] = dp[prev][SCREEN];
                }
            }

            for (int prev = n - 1; prev >= 0; --prev) {
                if (dp[prev][SCREEN] + dp[prev][BUFFER] * (n - prev) > dp[n][SCREEN]) {
                    dp[n][SCREEN] = dp[prev][SCREEN] + dp[prev][BUFFER] * (n - prev);
                    dp[n][BUFFER] = dp[prev][BUFFER];
                }
            }
        }

        System.out.println(dp[N][SCREEN]);
    }
}
