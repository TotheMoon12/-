import java.util.ArrayList;
import java.util.Collections;

class Solution {
    static boolean[][][] visited;
    static final int DIRECTION_COUNT = 4;
    static final int UP = 0;
    static final int DOWN = 1;
    static final int LEFT = 2;
    static final int RIGHT = 3;


    public int[] solution(String[] grid) {
        int[] answer;

        ArrayList<Integer> cycleLengthList = new ArrayList<>();
        visited = new boolean[grid.length][grid[0].length()][DIRECTION_COUNT];
        int rowSize = grid.length;
        int colSize = grid[0].length();

        for (int row = 0; row < rowSize; ++row) {
            for (int col = 0; col < colSize; ++col) {
                if (!visited[row][col][UP]) {
                    cycleLengthList.add(getCycleLength(grid, row, col, UP));
                }

                if (!visited[row][col][DOWN]) {
                    cycleLengthList.add(getCycleLength(grid, row, col, DOWN));
                }

                if (!visited[row][col][LEFT]) {
                    cycleLengthList.add(getCycleLength(grid, row, col, LEFT));
                }

                if (!visited[row][col][RIGHT]) {
                    cycleLengthList.add(getCycleLength(grid, row, col, RIGHT));
                }
            }
        }

        Collections.sort(cycleLengthList);
        answer = new int[cycleLengthList.size()];
        for (int i = 0; i < cycleLengthList.size(); ++i) {
            answer[i] = cycleLengthList.get(i);
        }

        return answer;
    }

    public int getCycleLength(String[] grid, int row, int col, int direction) {
        int length = 0;
        while (!visited[row][col][direction]) {
            visited[row][col][direction] = true;
            ++length;

            if (direction == UP) {
                --row;
                if (row < 0) {
                    row = grid.length - 1;
                }
            } else if (direction == DOWN) {
                ++row;

                if (row >= grid.length) {
                    row = 0;
                }
            } else if (direction == LEFT) {
                --col;

                if (col < 0) {
                    col = grid[0].length() - 1;
                }
            } else {
                ++col;

                if (col >= grid[0].length()) {
                    col = 0;
                }
            }

            char type = grid[row].charAt(col);
            if (type == 'R') {
                if (direction == UP) {
                    direction = RIGHT;
                } else if (direction == DOWN) {
                    direction = LEFT;
                } else if (direction == LEFT) {
                    direction = UP;
                } else {
                    direction = DOWN;
                }
            } else if (type == 'L') {
                if (direction == UP) {
                    direction = LEFT;
                } else if (direction == DOWN) {
                    direction = RIGHT;
                } else if (direction == LEFT) {
                    direction = DOWN;
                } else {
                    direction = UP;
                }
            }
        }

        return length;
    }
}
