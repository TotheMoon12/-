import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        BigInteger[] dp = new BigInteger[10001];
        dp[0] = BigInteger.ZERO;
        dp[1] = BigInteger.ONE;
        for (int idx = 2; idx <= n; ++idx) {
            dp[idx] = dp[idx - 1].add(dp[idx - 2]);
        }

        System.out.println(dp[n]);
    }
}
