import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int TARGET = Integer.parseInt(br.readLine());
        final int COIN_KIND = Integer.parseInt(br.readLine());

        int[] coins = new int[COIN_KIND + 1];
        int[] coinCounts = new int[COIN_KIND + 1];
        for (int coinKind = 1; coinKind <= COIN_KIND; ++coinKind) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            coins[coinKind] = Integer.parseInt(st.nextToken());
            coinCounts[coinKind] =Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[COIN_KIND + 1][TARGET + 1];
        dp[0][0] = 1;
        for (int coinKind = 1; coinKind <= COIN_KIND; ++coinKind) {
            final int COIN = coins[coinKind];
            final int COUNT = coinCounts[coinKind];

            dp[coinKind][0] = 1;
            for (int value = 1; value <= TARGET; ++value) {
                for (int count = 0; count <= COUNT && COIN * count <= value; ++count) {
                    dp[coinKind][value] += dp[coinKind - 1][value - COIN * count];
                }
            }
        }

        System.out.print(dp[COIN_KIND][TARGET]);
    }
}
