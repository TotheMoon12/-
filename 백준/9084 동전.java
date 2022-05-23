import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int t = 0; t < T; ++t) {
            int N = Integer.parseInt(br.readLine());
            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] coins = new int[N + 1];
            for (int idx = 1; idx <= N; ++idx) {
                coins[idx] = Integer.parseInt(st.nextToken());
            }

            int targetValue = Integer.parseInt(br.readLine());
            int[][] dp = new int[N + 1][targetValue + 1];
            for (int idx = 1; idx <= N; ++idx) {
                int coin = coins[idx];
                dp[idx][0] = 1;
                for (int target = 1; target <= targetValue; ++target) {
                    if (coin > target) {
                        dp[idx][target] = dp[idx - 1][target];
                    } else {
                        dp[idx][target] = dp[idx][target - coin] + dp[idx - 1][target];
                    }
                }
            }

            sb.append(dp[N][targetValue]);
            sb.append(System.lineSeparator());
        }

        System.out.print(sb.toString());
    }
}
