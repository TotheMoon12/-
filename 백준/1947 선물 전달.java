import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        
        // 완전수열 문제 해답 참조함
        final int N = Integer.parseInt(br.readLine());
        final int MAX = 1000000;
        long[] dp = new long[MAX + 1];
        dp[1] = 0;
        dp[2] = 1;
        final int MOD = 1000000000;
        for (int n = 3; n <= N; ++n) {
            dp[n] = (n - 1) * ((dp[n - 2] + dp[n - 1]) % MOD);
            dp[n] %= MOD;
        }

        bw.write(String.valueOf(dp[N]));
        br.close();
        bw.close();
    }
}
