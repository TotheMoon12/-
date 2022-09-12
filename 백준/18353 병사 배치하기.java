import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        int[][] dp = new int[N][2];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int maxSoldierCount = 1;
        for (int n = 0; n < N; ++n) {
            int power = Integer.parseInt(st.nextToken());
            dp[n][0] = power;
            dp[n][1] = 1;

            int max = 0;
            for (int prev = n - 1; prev >= 0; --prev) {
                if (dp[prev][0] > power && dp[prev][1] > max) {
                    max = dp[prev][1];
                }
            }

            dp[n][1] += max;
            maxSoldierCount = Math.max(maxSoldierCount, dp[n][1]);
        }

        System.out.print(N - maxSoldierCount);
    }
}
