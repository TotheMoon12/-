import java.util.Queue;
import java.util.LinkedList;

class Solution {
    public int solution(int[][] maps) {
        int answer = -1;
        
        int rowSize = maps.length;
        int colSize = maps[0].length;
        boolean[][] visited = new boolean[rowSize][colSize];        
        Queue<Integer> queue = new LinkedList<>();
        queue.add(0);
        queue.add(0);
        queue.add(1);
        visited[0][0] = true;
        
        int[] rowData = {-1,1,0,0};
        int[] colData = {0,0,-1,1};
        
        while (!queue.isEmpty()) {
            int row = queue.poll();
            int col = queue.poll();
            int depth = queue.poll();
            
            for (int i = 0; i < rowData.length; ++i) {
                int nextRow = row + rowData[i];
                int nextCol = col + colData[i];
                
                if (nextRow < 0 || nextRow >= rowSize || nextCol < 0 || nextCol >= colSize) {
                    continue;
                }
                
                if (nextRow == rowSize - 1 && nextCol == colSize - 1) {
                    return depth + 1;
                }
                
                if(!visited[nextRow][nextCol] && maps[nextRow][nextCol] == 1) {
                    visited[nextRow][nextCol] = true;
                    queue.add(nextRow);
                    queue.add(nextCol);
                    queue.add(depth + 1);
                }
            }   
        }
        
        return answer;
    }
}
