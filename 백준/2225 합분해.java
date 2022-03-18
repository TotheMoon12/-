// k개를 사용하여 어떤 n을 만드는 경우의 수는
// 현재 어떤 수 l(0보다 크거나 같고 n보다 작거나 같은 수)을 하나 사용했을 때
// 나머지 k - 1개의 정수를 사용하여 정수 n - l을 만드는 경우의 수들의 합이다
// dp[k][n] = sum(dp[k - 1][n - 0 ~ l ]) (단 n - l >= 0)

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());

        long[][] dp = new long[k + 1][n + 1];

        for (int i = 0; i <= n; ++i) {
            dp[1][i] = 1l;
        }
        for (int i = 2; i <= k; ++i) {
            for (int j = 0; j <= n; ++j) {
                for (int l = 0; l <= j; ++l) {
                    dp[i][j] += dp[i - 1][j - l] % 1000000000;
                }
            }
        }

        System.out.println(dp[k][n] % 1000000000);
    }
}
