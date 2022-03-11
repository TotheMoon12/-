// 파스칼 삼각형의 값을 저장하여 이항계수를 구한다

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[][] dp = new int[N + 1][N + 1];
        dp[0][0] = 1;
        for (int i = 1; i <= N; ++i) {
            dp[i][0] = dp[i - 1][0];
            dp[i][i] = dp[i - 1][i - 1];
            for (int j = 1; j < i; ++j) {
                dp[i][j] = (dp[i - 1][j - 1] + dp[i - 1][j]) % 10007;
            }
        }

        System.out.println(dp[N][K]);
    }
}
