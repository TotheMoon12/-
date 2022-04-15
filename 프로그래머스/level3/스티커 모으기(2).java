import java.util.Arrays;

class Solution {
    public int solution(int sticker[]) {
        int answer = 0;
        final int STICKER_NUM = sticker.length;
        int[] dp = new int[STICKER_NUM];
        dp[0] = sticker[0];
        answer = sticker[0];
        if (STICKER_NUM > 1) {
            dp[1] = sticker[1];
            answer = Math.max(answer, sticker[1]);
        }

        if (STICKER_NUM > 2) {
            dp[2] = sticker[2];
            answer = Math.max(answer, sticker[2]);
        }

        if (STICKER_NUM > 3) {
            dp[2] += dp[0];
            answer = Math.max(answer, dp[2]);

            for (int i = 3; i < STICKER_NUM - 1; ++i) {
                dp[i] = sticker[i] + Math.max(dp[i - 2], dp[i - 3]);
                answer = Math.max(answer, dp[i]);
            }

            dp[0] = 0;
            dp[1] = sticker[1];
            dp[2] = sticker[2];
            for (int i = 3; i < STICKER_NUM; ++i) {
                dp[i] = sticker[i] + Math.max(dp[i - 2], dp[i - 3]);
                answer = Math.max(answer, dp[i]);
            }
        }


        return answer;
    }
}
