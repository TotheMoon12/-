class Solution {
    int solution(int[][] land) {
        int answer = 0;
        final int ROW_SIZE = land.length;
        final int COL_SIZE = land[0].length;
        for (int i = 1; i < land.length; ++i) {
            for (int j = 0; j < COL_SIZE; ++j) {
                int max = 0;
                for (int k = 0; k < COL_SIZE; ++k) {
                    if (j == k) {
                        continue;
                    }
                    
                    max = Math.max(max, land[i - 1][k]);
                }
                
                land[i][j] += max;
            }
        }

        answer = Math.max(answer, land[ROW_SIZE - 1][0]);
        answer = Math.max(answer, land[ROW_SIZE - 1][1]);
        answer = Math.max(answer, land[ROW_SIZE - 1][2]);
        answer = Math.max(answer, land[ROW_SIZE - 1][3]);
        return answer;
    }
}
