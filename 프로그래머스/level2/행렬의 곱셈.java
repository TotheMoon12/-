class Solution {
    public int[][] solution(int[][] arr1, int[][] arr2) {
        final int ROW_SIZE = arr1.length;
        final int COL_SIZE = arr2[0].length;
        int[][] answer = new int[ROW_SIZE][COL_SIZE];        
        for (int row = 0; row < ROW_SIZE; ++row) {
            for (int col = 0; col < COL_SIZE; ++col) {
                int sum = 0;
                for (int i = 0; i < arr2.length; ++i) {
                    sum += arr1[row][i] * arr2[i][col];
                }
                
                answer[row][col] = sum;
            }
        }
        return answer;
    }
}
