import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[][] dp = new int[100000][3];

        int answer = 0;
        dp[0][0] = 1;
        dp[0][1] = 1;
        dp[0][2] = 1;
        for (int i = 0; i < N - 1; ++i) {
            dp[i + 1][0] += dp[i][1];
            dp[i + 1][0] %= 9901;
            dp[i + 1][0] += dp[i][2];
            dp[i + 1][0] %= 9901;

            dp[i + 1][1] += dp[i][0];
            dp[i + 1][1] %= 9901;
            dp[i + 1][1] += dp[i][2];
            dp[i + 1][1] %= 9901;

            dp[i + 1][2] += dp[i][0];
            dp[i + 1][2] %= 9901;
            dp[i + 1][2] += dp[i][1];
            dp[i + 1][2] %= 9901;
            dp[i + 1][2] += dp[i][2];
            dp[i + 1][2] %= 9901;
        }

        answer += dp[N - 1][0];
        answer %= 9901;
        answer += dp[N - 1][1];
        answer %= 9901;
        answer += dp[N - 1][2];
        answer %= 9901;
        System.out.println(answer);
    }
}
