class Solution {
    public int[] solution(int m, int n, int[][] picture) {
        int numberOfArea = 0;
        int maxSizeOfOneArea = 0;
        
        boolean[][] visited = new boolean[m][n];
        
        for (int i = 0; i < m; ++i) {
            for (int j = 0; j < n; ++j) {
                if (picture[i][j] != 0 && !visited[i][j]) {
                    int sizeOfArea = dfs(picture, m, n, i, j, visited);
                    ++numberOfArea;
                    maxSizeOfOneArea = Math.max(maxSizeOfOneArea, sizeOfArea);
                }
            }
        }

        int[] answer = new int[2];
        answer[0] = numberOfArea;
        answer[1] = maxSizeOfOneArea;
        return answer;
    }
    
    private static int dfs(int[][] picture, int m, int n, int row, int col, boolean[][] visited) {
        visited[row][col] = true;
        int sizeOfArea = 1;
        
        int[] rowData = {-1,1,0,0};
        int[] colData = {0,0,-1,1};
        
        for (int i = 0; i < 4; ++i) {
            int newRow = row + rowData[i];
            int newCol = col + colData[i];
            
            if (newRow < 0 || newRow >= m || newCol < 0 || newCol >= n) {
                continue;
            }
            
            if (picture[newRow][newCol] == picture[row][col] && !visited[newRow][newCol]) {
                sizeOfArea += dfs(picture, m, n, newRow, newCol, visited);
            }
        }
        
        return sizeOfArea;
    }
}
