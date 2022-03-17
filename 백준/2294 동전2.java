import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        int[] dp = new int[100001];
        HashSet<Integer> coins = new HashSet<>();
        for (int i = 1; i <= n; ++i) {
            int coin = Integer.parseInt(br.readLine());
            coins.add(coin);
            dp[coin] = 1;
        }

        for (int i = 1; i <= k; ++i) {
            if (dp[i] == 1) {
                continue;
            }

            int min = -1;
            for (int coin : coins) {
                int remain = i - coin;
                if (remain > 0 && dp[remain] > 0) {
                    if (min == -1) {
                        min = dp[remain];
                    } else {
                        if (dp[remain] < min) {
                            min = dp[remain];
                        }
                    }
                }
            }

            if (min != -1) {
                dp[i] = min + 1;
            }
        }

        if (dp[k] == 0) {
            System.out.println(-1);
        } else {
            System.out.println(dp[k]);
        }
    }
}
