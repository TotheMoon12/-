class Solution {
    public int solution(int n) {
        int[] dp = new int[3];
        dp[0] = 1;
        dp[1] = 1;
        dp[2] = 1;
        for (int i = 3; i <= n; ++i) {
            dp[2] = (dp[0] + dp[1]) % 1234567;
            if (i == n) {
                break;
            }
            
            dp[0] = dp[1];
            dp[1] = dp[2];
        }
        return dp[2];
    }
}
