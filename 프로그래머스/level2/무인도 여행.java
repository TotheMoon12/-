import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

class Solution {
    public class Point  {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public int[] solution(String[] maps) {
        final int ROW_SIZE = maps.length;
        final int COL_SIZE = maps[0].length();
        boolean[][] visited = new boolean[ROW_SIZE][COL_SIZE];

        int[] rowData = {-1, 1, 0, 0};
        int[] colData = {0, 0, -1, 1};
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int row = 0; row < ROW_SIZE; ++row) {
            for (int col = 0; col < COL_SIZE; ++col) {
                char startDay = maps[row].charAt(col);
                if (startDay != 'X' && !visited[row][col]) {
                    visited[row][col] = true;

                    int day = startDay - '0';

                    Queue<Point> queue = new LinkedList<>();
                    queue.offer(new Point(row, col));
                    while (!queue.isEmpty()) {
                        Point point = queue.poll();

                        for (int idx = 0; idx < rowData.length; ++idx) {
                            int nextRow = point.row + rowData[idx];
                            int nextCol = point.col + colData[idx];

                            if (nextRow < 0 || nextRow >= ROW_SIZE || nextCol < 0 || nextCol >= COL_SIZE) {
                                continue;
                            }

                            char c = maps[nextRow].charAt(nextCol);
                            if (!visited[nextRow][nextCol] && c != 'X') {
                                visited[nextRow][nextCol] = true;

                                day += c - '0';
                                queue.offer(new Point(nextRow, nextCol));
                            }
                        }
                    }

                    pq.offer(day);
                }
            }
        }

        int[] answer;
        if (pq.isEmpty()) {
            answer = new int[1];
            answer[0] = -1;
        } else {
            answer = new int[pq.size()];
            int idx = 0;
            while (!pq.isEmpty()) {
                answer[idx++] = pq.poll();
            }
        }

        return answer;
    }
}
