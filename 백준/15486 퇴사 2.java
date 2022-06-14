import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());


        int max = 0;
        int[] dp = new int[N + 1];
        for (int idx = 0; idx < N; ++idx) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int time = Integer.parseInt(st.nextToken());
            int profit = Integer.parseInt(st.nextToken());

            int start = idx + time;
            int last = start + 50 - 1;
            for (int next = start; next <= N && next <= last; ++next) {
                if (dp[next] == 0) {
                    dp[next] = dp[idx] + profit;
                } else {
                    dp[next] = Math.max(dp[next], dp[idx] + profit);
                }
            }

            max = Math.max(max, dp[idx]);
        }

        max = Math.max(max, dp[N]);
        System.out.println(max);
    }
}
