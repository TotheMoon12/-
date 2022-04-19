import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int MAX = 1000000;
        int[] dp = new int[MAX + 1];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        for (int i = 3; i <= MAX; ++i) {
            dp[i] += dp[i - 1];
            dp[i] %= 1000000009;
            dp[i] += dp[i - 2];
            dp[i] %= 1000000009;
            dp[i] += dp[i - 3];
            dp[i] %= 1000000009;
        }

        StringBuilder answer = new StringBuilder();
        int testCaseNum = Integer.parseInt(br.readLine());
        for (int i = 0; i < testCaseNum; ++i) {
            int n = Integer.parseInt(br.readLine());
            answer.append(dp[n]);
            answer.append(System.lineSeparator());
        }
        System.out.println(answer.toString());
    }
}
