import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    static BigInteger[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());
        dp = new BigInteger[n + 1][m + 1];
        BigInteger answer = getCombination(n, m);
        System.out.println(answer);
    }

    public static BigInteger getCombination(int n, int m) {
        if (n == m) {
            dp[n][m] = new BigInteger("1");
            return dp[n][m];
        }

        if (m == 1) {
            dp[n][m] = new BigInteger(Integer.toString(n));
            return dp[n][m];
        }

        BigInteger value1;

        if (dp[n - 1][m - 1] != null) {
            value1 = dp[n - 1][m - 1];
        } else {
            value1 = getCombination(n - 1, m - 1);
            dp[n - 1][m - 1] = value1;
        }

        BigInteger value2;
        if (dp[n - 1][m] != null) {
            value2 = dp[n - 1][m];
        } else {
            value2 = getCombination(n - 1, m);
            dp[n - 1][m] = value2;
        }

        return value1.add(value2);
    }
}
