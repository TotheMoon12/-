import java.util.ArrayList;

class Solution {
    public int[] solution(int rows, int columns, int[][] queries) {
        int[] answer;
        ArrayList<Integer> result = new ArrayList<>();
        
        int[][] board = new int[rows][columns];
        
        for (int i = 0; i < rows; ++i) {
            for (int j = 0; j < columns; ++j) {
                board[i][j] = i * columns + (j + 1);
            }
        }
        
        for (int i = 0; i < queries.length; ++i) {
            int x1 = queries[i][0] - 1;
            int y1 = queries[i][1] - 1;
            int x2 = queries[i][2] - 1;
            int y2 = queries[i][3] - 1;
            
            int x1y1 = board[x1][y1];
            int x1y2 = board[x1][y2];
            int x2y2 = board[x2][y2];
            int x2y1 = board[x2][y1];
            
            int min = x1y1;
            min = Math.min(min, x1y2);
            min = Math.min(min, x2y2);
            min = Math.min(min, x2y1);
            
            for (int y = y2; y >= y1 + 2; --y) {
                board[x1][y] = board[x1][y - 1];
                min = Math.min(min, board[x1][y]);
            }
            
            for (int x = x2; x >= x1 + 2; --x) {
                board[x][y2] = board[x - 1][y2];
                min = Math.min(min, board[x][y2]);
            }
            
            for (int y = y1; y <= y2 - 2; ++y) {
                board[x2][y] = board[x2][y + 1];
                min = Math.min(min, board[x2][y]);
            }
            
            for (int x = x1; x <= x2 - 2; ++x) {
                board[x][y1] = board[x + 1][y1];
                min = Math.min(min, board[x][y1]);
            }
            
            board[x1][y1 + 1] = x1y1;
            board[x1 + 1][y2] = x1y2;
            board[x2][y2 - 1] = x2y2;
            board[x2 - 1][y1] = x2y1;
            
            result.add(min);
        }
        
        answer = new int[result.size()];
        for (int i = 0; i < result.size(); ++i) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}
