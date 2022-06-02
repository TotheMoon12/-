import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int target = Integer.parseInt(br.readLine());

        int[] dp = new int[100001];
        dp[2] = 1;
        dp[4] = 2;
        dp[5] = 1;

        for (int money = 6; money <= target; ++money) {
            if (dp[money - 2] != 0 && dp[money - 5] != 0) {
                dp[money] = Math.min(dp[money - 2],dp[money - 5]) + 1;
            } else if (dp[money - 2] != 0) {
                dp[money] = dp[money - 2] + 1;
            } else if (dp[money - 5] != 0) {
                dp[money] = dp[money - 5] + 1;
            }
        }


        if (dp[target] == 0) {
            System.out.print(-1);
        } else {
            System.out.print(dp[target]);
        }
    }
}
