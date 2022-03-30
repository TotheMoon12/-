import java.util.HashSet;

class Solution {
    public int solution(String dirs) {
        final int LENGTH = dirs.length();

        final int COUNT = 11;
        boolean[][][][] visited = new boolean[COUNT][COUNT][COUNT][COUNT];
        int x = 5;
        int y = 5;

        int answer = 0;
        for (int i = 0; i < LENGTH; ++i) {
            char c = dirs.charAt(i);

            int nextX = x;
            int nextY = y;

            if (c == 'U') {
                ++nextY;

                if (!isValid(nextY)) {
                    continue;
                }
            } else if (c == 'D') {
                --nextY;
                if (!isValid(nextY)) {
                    continue;
                }
            } else if (c == 'L')  {
                --nextX;
                if (!isValid(nextX)) {
                    continue;
                }
            } else {
                ++nextX;
                if (!isValid(nextX)) {
                    continue;
                }
            }

            if (!visited[x][y][nextX][nextY]) {
                visited[x][y][nextX][nextY] = true;
                visited[nextX][nextY][x][y] = true;
                ++answer;
            }

            x = nextX;
            y = nextY;
        }


        return answer;
    }

    public boolean isValid(int value) {
        final int MIN = 0;
        final int MAX = 10;

        if (value >= MIN && value <= MAX) {
            return true;
        } else {
            return false;
        }
    }
}
