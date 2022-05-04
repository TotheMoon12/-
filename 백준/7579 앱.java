import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int targetBytes = Integer.parseInt(st.nextToken());

        StringTokenizer byteSt = new StringTokenizer(br.readLine());
        StringTokenizer costSt = new StringTokenizer(br.readLine());

        int[] bytes = new int[n + 1];
        int[] costs = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            bytes[i] = Integer.parseInt(byteSt.nextToken());
            costs[i] = Integer.parseInt(costSt.nextToken());

        }

        final int MAX_COST = 10000;
        int answer = MAX_COST;
        int[][] dp = new int[n + 1][MAX_COST + 1];
        for (int i = 1; i <= n; ++i) {
            int appCost = costs[i];
            int appByte = bytes[i];

            for (int cost = 0; cost <= MAX_COST; ++cost) {
                if (cost - appCost >= 0) {
                    dp[i][cost] = Math.max(dp[i - 1][cost], appByte + dp[i - 1][cost - appCost]);
                } else {
                    dp[i][cost] = dp[i - 1][cost];
                }

                if (dp[i][cost] >= targetBytes) {
                    answer = Math.min(answer, cost);
                }
            }
        }

        System.out.println(answer);
    }
}
