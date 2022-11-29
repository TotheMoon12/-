import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];
        Arrays.fill(dp, 1);

        final int MOD = 1000000000;
        int number = 1;
        while (number <= N) {
            number *= 2;

            for (int n = number; n <= N; ++n) {
                dp[n] += dp[n - number];
                dp[n] %= MOD;
            }
        }

        bw.write(Integer.toString(dp[N]));
        br.close();
        bw.close();
    }
}
