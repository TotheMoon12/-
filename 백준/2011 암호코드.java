import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();

        int[][] dp = new int[s.length() + 1][2];
        dp[0][0] = 1;

        final int LENGTH = s.length();
        for (int i = 0; i < LENGTH; ++i) {
            char c = s.charAt(i);

            if (c != '0') {
                dp[i + 1][0] += dp[i][0];
                dp[i + 1][0] %= 1000000;
                dp[i + 1][0] += dp[i][1];
                dp[i + 1][0] %= 1000000;

                if (i < LENGTH - 1) {
                    int num = Integer.parseInt(s.substring(i, i + 2));

                    if (num <= 26) {
                        dp[i + 2][1] += dp[i][0];
                        dp[i + 2][1] %= 1000000;

                        dp[i + 2][1] += dp[i][1];
                        dp[i + 2][1] %= 1000000;
                    }
                }
            }
        }

        System.out.println((dp[LENGTH][0] + dp[LENGTH][1]) % 1000000);
    }
}
