import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int houseNum = Integer.parseInt(br.readLine());

        final int COLOR_NUM = 3;
        final int R = 0;
        final int G = 1;
        final int B = 2;
        final int COST = 0;
        final int FIRST_COLOR = 1;
        int[][] costs = new int[houseNum][3];
        for (int idx = 0; idx < houseNum; ++idx) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            costs[idx][R] = Integer.parseInt(st.nextToken());
            costs[idx][G] = Integer.parseInt(st.nextToken());
            costs[idx][B] = Integer.parseInt(st.nextToken());
        }

        int[][][] dp = new int[houseNum][COLOR_NUM][COLOR_NUM];
        dp[1][R][R] = Integer.MAX_VALUE;
        dp[1][R][G] = costs[0][R] + costs[1][G];
        dp[1][R][B] = costs[0][R] + costs[1][B];

        dp[1][G][G] = Integer.MAX_VALUE;
        dp[1][G][R] = costs[0][G] + costs[1][R];
        dp[1][G][B] = costs[0][G] + costs[1][B];

        dp[1][B][B] = Integer.MAX_VALUE;
        dp[1][B][R] = costs[0][B] + costs[1][R];
        dp[1][B][G] = costs[0][B] + costs[1][G];

        for (int idx = 2; idx < houseNum; ++idx) {
            dp[idx][R][R] = Math.min(dp[idx - 1][R][G], dp[idx - 1][R][B]) + costs[idx][R];
            dp[idx][R][G] = Math.min(dp[idx - 1][R][R], dp[idx - 1][R][B]) + costs[idx][G];
            dp[idx][R][B] = Math.min(dp[idx - 1][R][R], dp[idx - 1][R][G]) + costs[idx][B];

            dp[idx][G][R] = Math.min(dp[idx - 1][G][G], dp[idx - 1][G][B]) + costs[idx][R];
            dp[idx][G][G] = Math.min(dp[idx - 1][G][R], dp[idx - 1][G][B]) + costs[idx][G];
            dp[idx][G][B] = Math.min(dp[idx - 1][G][R], dp[idx - 1][G][G]) + costs[idx][B];

            dp[idx][B][R] = Math.min(dp[idx - 1][B][G], dp[idx - 1][B][B]) + costs[idx][R];
            dp[idx][B][G] = Math.min(dp[idx - 1][B][R], dp[idx - 1][B][B]) + costs[idx][G];
            dp[idx][B][B] = Math.min(dp[idx - 1][B][R], dp[idx - 1][B][G]) + costs[idx][B];
        }

        int lastHouse = houseNum - 1;
        int min = dp[lastHouse][R][G];
        min = Math.min(min, dp[lastHouse][R][B]);
        min = Math.min(min, dp[lastHouse][G][R]);
        min = Math.min(min, dp[lastHouse][G][B]);
        min = Math.min(min, dp[lastHouse][B][R]);
        min = Math.min(min, dp[lastHouse][B][G]);

        System.out.println(min);
    }
}

