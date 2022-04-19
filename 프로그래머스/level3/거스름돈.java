// 알고리즘 스터디원의 설명을 듣고 풀이
// 현재 저장한 값을 활용하는 것을 잘 생각해보자

class Solution {
    public int solution(int n, int[] money) {
        final int COIN_NUM = money.length;
        int[][] dp = new int[COIN_NUM + 1][n + 1];

        for (int i = 0; i < money.length; ++i) {
            int coin = money[i];
            dp[i + 1][0] = 1;

            for (int targetValue = 1; targetValue <= n; ++targetValue) {
                if (coin > targetValue) {
                    dp[i + 1][targetValue] = dp[i][targetValue];
                } else {
                    dp[i + 1][targetValue] += dp[i][targetValue] + dp[i + 1][targetValue - coin];
                }
            }
        }

        return dp[money.length][n];
    }
}
