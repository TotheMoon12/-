class Solution {
    public int solution(int x, int y, int n) {
        if (x == y) {
            return 0;
        }

        int answer = 0;
        int[] dp = new int[y + 1];
        for (int number = x; number < y; ++number) {
            if (number == x || dp[number] != 0) {
                int count = dp[number] + 1;
                int next = number + n;
                if (next <= y) {
                    if (dp[next] == 0) {
                        dp[next] = count;
                    } else {
                        dp[next] = Math.min(dp[next], count);
                    }
                }

                next = number * 2;
                if (next <= y) {
                    if (dp[next] == 0) {
                        dp[next] = count;
                    } else {
                        dp[next] = Math.min(dp[next], count);
                    }
                }

                next = number * 3;
                if (next <= y) {
                    if (dp[next] == 0) {
                        dp[next] = count;
                    } else {
                        dp[next] = Math.min(dp[next], count);
                    }
                }
            }
        }

        if (dp[y] == 0) {
            answer = -1;
        } else {
            answer = dp[y];
        }

        return answer;
    }
}
