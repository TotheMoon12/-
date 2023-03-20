class Solution {
    public int solution(int[][] matrix_sizes) {
        int[][] dp = new int[matrix_sizes.length][matrix_sizes.length];

        for (int length = 1; length < matrix_sizes.length; ++length) {
            for (int start = 0; start < matrix_sizes.length - length; ++start) {
                int min = Integer.MAX_VALUE;
                final int END = start + length;

                for (int bound = start + 1; bound <= END; ++bound) {
                    int count = dp[start][bound - 1] + dp[bound][END];
                    count += matrix_sizes[start][0] * matrix_sizes[bound - 1][1] * matrix_sizes[END][1];
                    min = Math.min(min, count);
                }

                dp[start][END] = min;
            }
        }

        int answer = dp[0][matrix_sizes.length - 1];
        return answer;
    }
}
