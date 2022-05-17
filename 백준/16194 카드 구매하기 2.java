import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] costs = new int[N + 1];
        int[] dp = new int[N + 1];
        for (int index = 1; index <= N; ++index) {
            costs[index] = Integer.parseInt(st.nextToken());
            dp[index] = costs[index];
        }

        for (int targetCardNum = 1; targetCardNum <= N; ++targetCardNum) {
            int min = dp[targetCardNum];
            for (int cardNum = 1; cardNum <= N; ++cardNum) {
                int prevCardNum = targetCardNum - cardNum;
                if (prevCardNum > 0  && dp[prevCardNum] > 0) {
                    if (min == 0) {
                        min = dp[prevCardNum] + costs[cardNum];
                    } else {
                        min = Math.min(min, dp[prevCardNum] + costs[cardNum]);
                    }
                }
            }

            dp[targetCardNum] = min;
        }

        System.out.println(dp[N]);
    }
}
