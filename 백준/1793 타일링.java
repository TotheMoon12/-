import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int MAX = 250;
        BigInteger[] dp = new BigInteger[MAX + 1];
        dp[0] = BigInteger.ONE;
        dp[1] = BigInteger.ONE;
        dp[2] = new BigInteger("3");

        for (int n = 3; n <= MAX; ++n) {
            dp[n] = dp[n - 1].add(dp[n - 2]);
            dp[n] = dp[n].add(dp[n - 2]);
        }

        StringBuilder builder = new StringBuilder();
        String input = br.readLine();

        while (input != null) {
            int N = Integer.parseInt(input);

            builder.append(dp[N]);
            builder.append(System.lineSeparator());

            input = br.readLine();
        }

        System.out.print(builder);
    }
}
