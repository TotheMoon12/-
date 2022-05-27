import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int testNum = Integer.parseInt(br.readLine());

        long[] dp = new long[68];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        for (int idx = 4; idx < 68; ++idx) {
            dp[idx] = dp[idx - 1] + dp[idx - 2] + dp[idx - 3] + dp[idx - 4];
        }

        StringBuilder answer = new StringBuilder();
        for (int t = 0; t < testNum; ++t) {
            int n = Integer.parseInt(br.readLine());
            if (n < 2) {
                answer.append(1);
            } else if (n == 2) {
                answer.append(2);
            } else if (n == 3) {
                answer.append(4);
            } else {
                answer.append(dp[n]);
            }
            answer.append(System.lineSeparator());
        }

        System.out.print(answer.toString());
    }
}
