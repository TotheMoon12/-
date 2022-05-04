import java.util.ArrayList;

class Solution {
    static int answer = 0;
    public static class Point {
        int row;
        int col;
        
        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }
    
    public int solution(int n) {
        ArrayList<Point> points = new ArrayList<>();
        boolean[] visited = new boolean[n];
        dfs(n, 0, points, visited);
        return answer;
    }
    
    public void dfs(int n, int order, ArrayList<Point> points, boolean[] visited) {
        if (order == n) {
            ++answer;
            return;
        }
        
        for (int i = 0; i < n; ++i) {
            if (!visited[i]) {
                boolean isRight = true;
                for (Point point : points) {
                    if (Math.abs(point.row - order) == Math.abs(point.col - i)) {
                        isRight = false;
                        break;
                    }
                }
                
                if (isRight) {
                    visited[i] = true;
                    points.add(new Point(order, i));
                    dfs(n, order + 1, points, visited);
                    visited[i] = false;
                    points.remove(points.size() - 1);
                }
            }
        }
    }
}
