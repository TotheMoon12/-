import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final int MAX_N = 1000000;
        long[] dp = new long[MAX_N + 1];
        dp[1] = 1;

        for (int n = 2; n <= MAX_N; ++n) {
            int temp = n;

            while (temp <= MAX_N) {
                dp[temp] += n;
                temp += n;
            }

            dp[n] += dp[n - 1] + 1;
        }


        final int T = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder(T);
        for (int t = 0; t < T; ++t) {
            int n = Integer.parseInt(br.readLine());
            builder.append(dp[n]);
            builder.append(System.lineSeparator());
        }

        bw.write(builder.toString());
        bw.close();
        br.close();
    }
}
