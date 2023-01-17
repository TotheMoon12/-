import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public class Move {
        int x;
        int y;
        int count;
        String path;

        public Move(int x, int y, int count, String path) {
            this.x = x;
            this.y = y;
            this.count = count;
            this.path = path;
        }
    }

    public String solution(int n, int m, int x, int y, int r, int c, int k) {
        String answer = "";

        int distance = Math.abs(r - x) + Math.abs(c - y);
        if (distance > k) {
            answer = "impossible";
        } else if (distance == k) {
            // d < l < r < u, 아왼오위
            char LEFT = 'l';
            char RIGHT = 'r';
            char UP = 'u';
            char DOWN = 'd';
            char rowDirection;
            if (x > r) {
                rowDirection = UP;
            } else {
                rowDirection = DOWN;
            }

            char colDirection;
            if (y > c) {
                colDirection = LEFT;
            } else {
                colDirection = RIGHT;
            }

            StringBuilder builder = new StringBuilder(k);
            int xCount = Math.abs(x - r);
            int yCount = Math.abs(y - c);
            if (rowDirection < colDirection) {
                while (xCount-- > 0) {
                    builder.append(rowDirection);
                }

                while (yCount-- > 0) {
                    builder.append(colDirection);
                }
            } else {
                while (yCount-- > 0) {
                    builder.append(colDirection);
                }

                while (xCount-- > 0) {
                    builder.append(rowDirection);
                }
            }

            answer = builder.toString();
        } else {
            boolean[][][] visited = new boolean[n + 1][m + 1][k + 1];
            visited[x][y][k] = true;

            Queue<Move> queue = new LinkedList<>();
            queue.offer(new Move(x, y, k, ""));
            int[] rowData = {1, 0, 0, -1};
            int[] colData = {0, -1, 1, 0};
            char[] direction = {'d', 'l', 'r', 'u'};
            while (!queue.isEmpty()) {
                Move move = queue.poll();

                if (move.x == r && move.y == c && move.count == 0) {
                    answer = move.path;
                    break;
                }

                if (move.count == 0) {
                    continue;
                }

                // d < l < r < u, 아왼오위
                for (int idx = 0; idx < direction.length; ++idx) {
                    int nextX = move.x + rowData[idx];
                    int nextY = move.y + colData[idx];

                    if (nextX < 1 || nextX > n || nextY < 1 || nextY > m) {
                        continue;
                    }

                    if (visited[nextX][nextY][move.count - 1]) {
                        continue;
                    }

                    visited[nextX][nextY][move.count - 1] = true;
                    queue.offer(new Move(nextX, nextY, move.count - 1, move.path + direction[idx]));
                }
            }

            if (answer.equals("")) {
                answer = "impossible";
            }
        }

        return answer;
    }
}
