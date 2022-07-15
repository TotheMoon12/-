import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int[][] dp = new int[N][N];
        dp[0][0] = 1;
        for (int n = 1; n < N; ++n) {
            dp[n][0] = 1;
            int endIndex = n;
            for (int idx = 1; idx < endIndex; ++idx) {
                dp[n][idx] = dp[n - 1][idx - 1] + dp[n - 1][idx];
            }

            dp[n][endIndex] = 1;
        }

        System.out.println(dp[N - 1][k - 1]);
    }
}
