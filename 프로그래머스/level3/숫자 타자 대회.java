class Solution {
    public int solution(String numbers) {
        int[][] values = {
                {1, 7, 6, 7, 5, 4, 5, 3, 2, 3},
                {7, 1, 2, 4, 2, 3, 5, 4, 5, 6},
                {6, 2, 1, 2, 3, 2, 3, 5, 4, 5},
                {7, 4, 2, 1, 5, 3, 2, 6, 5, 4},
                {5, 2, 3, 5, 1, 2, 4, 2, 3, 5},
                {4, 3, 2, 3, 2, 1, 2, 3, 2, 3},
                {5, 5, 3, 2, 4, 2, 1, 5, 3, 2},
                {3, 4, 5, 6, 2, 3, 5, 1, 2, 4},
                {2, 5, 4, 5, 3, 2, 3, 2, 1, 2},
                {3, 6, 5, 4, 5, 3, 2, 4, 2, 1},
        };

        final int TYPE_COUNT = 10;
        final int LENGTH = numbers.length();
        int[][][] dp = new int[numbers.length()][TYPE_COUNT][TYPE_COUNT];
        int first = numbers.charAt(0) - '0';
        if (first == 4 || first == 6) {
            dp[0][4][6] = 1;
        } else {
            dp[0][first][6] = values[4][first];
            dp[0][4][first] = values[6][first];
        }

        for (int idx = 1; idx < LENGTH; ++idx) {
            int target = numbers.charAt(idx)- '0';
            int prevHand = numbers.charAt(idx - 1) - '0';

            for (int otherHand = 0; otherHand < TYPE_COUNT; ++otherHand) {
                updateMinSum(dp, prevHand, otherHand, target, numbers, idx, values); // 이전에 왼손을 움직였던 경우
                updateMinSum(dp, otherHand, prevHand, target, numbers, idx, values); // 이전에 오른손을 움직였던 경우
            }
        }

        int answer = Integer.MAX_VALUE;
        int lastIndex = LENGTH - 1;
        int lastTarget = numbers.charAt(lastIndex) - '0';
        for (int otherHand = 0; otherHand < TYPE_COUNT; ++otherHand) {
            int sum = dp[lastIndex][lastTarget][otherHand];
            if (sum != 0) {
                answer = Math.min(answer, sum);
            }

            sum = dp[lastIndex][otherHand][lastTarget];
            if (sum != 0) {
                answer = Math.min(answer, sum);
            }
        }

        return answer;
    }

    private void updateMinSum(int[][][] dp, int left, int right, int target, String numbers, int index, int[][] values) {
        int prevSum = dp[index - 1][left][right]; // 이전이 왼손인 경우
        if (prevSum != 0) {
            if (right != target) { // 왼손 움직이는 경우
                if (dp[index][target][right] == 0) {
                    dp[index][target][right] = prevSum + values[left][target];
                } else {
                    dp[index][target][right] = Math.min(dp[index][target][right], prevSum + values[left][target]);
                }
            }

            if (left != target) { // 오른손 움직이는 경우
                if (dp[index][left][target] == 0) {
                    dp[index][left][target] = prevSum + values[right][target];
                } else {
                    dp[index][left][target] = Math.min(dp[index][left][target], prevSum + values[right][target]);
                }
            }
        }
    }
}
