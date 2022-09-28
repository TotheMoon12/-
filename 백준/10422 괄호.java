// 질문 검색의 카탈란 수를 통해 해결

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashSet;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int MAX_L = 5000;
        final int MOD = 1000000007;
        final int HALF_L = MAX_L / 2;
        int[][] dp = new int[HALF_L + 1][HALF_L + 1];

        for (int col = 0; col <= HALF_L; ++col) {
            dp[0][col] = 1;
        }

        for (int row = 1; row <= HALF_L; ++row) {
            dp[row][row] = dp[row - 1][row] % MOD;

            for (int col = row + 1; col <= HALF_L; ++col) {
                dp[row][col] = (dp[row - 1][col] + dp[row][col - 1]) % MOD;
            }
        }

        final int T = Integer.parseInt(br.readLine());
        StringBuilder answer = new StringBuilder();
        for (int t = 0; t < T; ++t) {
            int L = Integer.parseInt(br.readLine());
            if (L % 2 == 0) {
                answer.append(dp[L / 2][L / 2]);
            } else {
                answer.append(0);
            }

            answer.append(System.lineSeparator());
        }

        System.out.print(answer);
    }
}
