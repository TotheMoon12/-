import java.util.Arrays;

class Solution {
    public int[] solution(int target) {
        int[] answer = new int[2];
        int[][] dp = new int[target + 1][2];
        int[] dartSpecialScores = {21, 22, 24, 26, 28, 30, 32, 33, 34, 36, 38, 39, 40, 42, 45, 48, 51, 54, 57, 60};

        final int DART_COUNT = 0;
        final int BOOL_SINGLE_COUNT = 1;

        for (int score = 0; score < target; ++score) {
            int dartCount = dp[score][DART_COUNT] + 1;
            int boolSingleCount = dp[score][BOOL_SINGLE_COUNT];
            int plusBoolSingleCount = boolSingleCount + 1;

            int boolScore = 50 + score;
            if (boolScore <= target) {
                if (dp[boolScore][DART_COUNT] == 0) {
                    dp[boolScore][DART_COUNT] = dartCount;
                    dp[boolScore][BOOL_SINGLE_COUNT] = plusBoolSingleCount;
                } else {
                    if (dartCount < dp[boolScore][DART_COUNT]) {
                        dp[boolScore][DART_COUNT] = dartCount;
                        dp[boolScore][BOOL_SINGLE_COUNT] = plusBoolSingleCount;
                    } else if (dartCount == dp[boolScore][DART_COUNT]) {
                        dp[boolScore][BOOL_SINGLE_COUNT] = Math.max(dp[boolScore][BOOL_SINGLE_COUNT], plusBoolSingleCount);
                    }
                }
            }

            for (int dart = 1; dart <= 20; ++dart) {
                int next = score + dart;

                if (next <= target) {
                    if (dp[next][DART_COUNT] == 0) {
                        dp[next][DART_COUNT] = dartCount;
                        dp[next][BOOL_SINGLE_COUNT] = plusBoolSingleCount;
                    } else {
                        if (dartCount < dp[next][DART_COUNT]) {
                            dp[next][DART_COUNT] = dartCount;
                            dp[next][BOOL_SINGLE_COUNT] = plusBoolSingleCount;
                        } else if (dartCount == dp[next][DART_COUNT]) {
                            dp[next][BOOL_SINGLE_COUNT] = Math.max(dp[next][BOOL_SINGLE_COUNT], plusBoolSingleCount);
                        }
                    }
                }
            }

            for (int dart : dartSpecialScores) {
                int next = score + dart;

                if (next <= target) {
                    if (dp[next][DART_COUNT] == 0) {
                        dp[next][DART_COUNT] = dartCount;
                        dp[next][BOOL_SINGLE_COUNT] = boolSingleCount;
                    } else {
                        if (dartCount < dp[next][DART_COUNT]) {
                            dp[next][DART_COUNT] = dartCount;
                            dp[next][BOOL_SINGLE_COUNT] = boolSingleCount;
                        } else if (dartCount == dp[next][DART_COUNT]) {
                            dp[next][BOOL_SINGLE_COUNT] = Math.max(dp[next][BOOL_SINGLE_COUNT], boolSingleCount);
                        }
                    }
                }
            }
        }

        answer[0] = dp[target][DART_COUNT];
        answer[1] = dp[target][BOOL_SINGLE_COUNT];
        return answer;
    }
}
