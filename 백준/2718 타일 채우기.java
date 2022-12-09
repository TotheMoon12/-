import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final int T = Integer.parseInt(br.readLine());

        final int MAX_N = 24;
        int[] dp = new int[MAX_N + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int n = 2; n <= MAX_N; ++n) {
            dp[n] += dp[n - 1] + dp[n - 2] * 4;
            for (int prev = n - 3; prev >= 0; prev -= 2) {
                dp[n] += dp[prev] * 2;
            }

            for (int prev = n - 4; prev >= 0; prev -= 2) {
                dp[n] += dp[prev] * 3;
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int t = 0; t < T; ++t) {
            final int N = Integer.parseInt(br.readLine());
            builder.append(dp[N]);
            builder.append(System.lineSeparator());
        }

        bw.write(builder.toString());
        br.close();
        bw.close();
    }
}
