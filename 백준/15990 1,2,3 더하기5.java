import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCaseNum = Integer.parseInt(br.readLine());
        final int MAX = 100000;
        int[][] dp = new int[MAX + 4][3];
        final int ONE = 0;
        final int TWO = 1;
        final int THREE = 2;
        dp[1][ONE] = 1;
        dp[2][TWO] = 1;
        dp[3][THREE] = 1;

        for (int index = 1; index <= MAX; ++index) {
            if (dp[index][ONE] > 0) {
                dp[index + 2][TWO] += dp[index][ONE];
                dp[index + 2][TWO] %= 1000000009;
                dp[index + 3][THREE] += dp[index][ONE];
                dp[index + 3][THREE] %= 1000000009;
            }

            if (dp[index][TWO] > 0) {
                dp[index + 1][ONE] += dp[index][TWO];
                dp[index + 1][ONE] %= 1000000009;
                dp[index + 3][THREE] += dp[index][TWO];
                dp[index + 3][THREE] %= 1000000009;
            }

            if (dp[index][THREE] > 0) {
                dp[index + 1][ONE] += dp[index][THREE];
                dp[index + 1][ONE] %= 1000000009;
                dp[index + 2][TWO] += dp[index][THREE];
                dp[index + 2][TWO] %= 1000000009;
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int index = 0; index < testCaseNum; ++index) {
            int n = Integer.parseInt(br.readLine());
            int cases = (dp[n][ONE] + dp[n][TWO]) % 1000000009;
            cases = (cases + dp[n][THREE]) % 1000000009;
            answer.append(cases);
            answer.append(System.lineSeparator());
        }

        System.out.println(answer.toString());
    }
}
