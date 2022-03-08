import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 1];
        dp[1] = 1;
        for (int i = 2; i <= N; ++i) {
            int maxSquareRoot = (int) Math.sqrt(i);
            int minCount = i;

            for (int root = maxSquareRoot; root > 1; --root) {
                int number = i;
                int count = 0;

                int quotient = number / (root * root);
                int remain = number % (root * root);
                count += quotient;
                count += dp[remain];

                minCount = Math.min(minCount, count);
            }

            dp[i] = minCount;
        }

        System.out.println(dp[N]);
    }
}
