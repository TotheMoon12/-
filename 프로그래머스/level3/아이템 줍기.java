import java.util.ArrayList;

class Solution {
    static int[] rowData = {-1, 1, 0, 0};
    static int[] colData = {0, 0, -1, 1};
    static final int MAX = 50;
    static boolean[][] map = new boolean[MAX + 1][MAX + 1];
    static int answer = -1;

    public int solution(int[][] rectangle, int characterX, int characterY, int itemX, int itemY) {
        int rectIndex = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < rectangle.length; ++i) {
            int leftBottomX = rectangle[i][0];
            int leftBottomY = MAX - rectangle[i][1];
            int rightTopX = rectangle[i][2];
            int rightTopY = MAX - rectangle[i][3];

            for (int y = rightTopY; y <= leftBottomY; ++y) {
                map[y][leftBottomX] = true;
                map[y][rightTopX] = true;
            }


            for (int x = leftBottomX; x <= rightTopX; ++x) {
                map[leftBottomY][x] = true;
                map[rightTopY][x] = true;
            }

            if ((characterX == leftBottomX || characterX == rightTopX) && MAX - characterY >= rightTopY && MAX - characterY <= leftBottomY) {
                list.add(i);
            }

            if ((MAX - characterY == leftBottomY || MAX - characterY == rightTopY) && characterX >= leftBottomX && characterX <= rightTopX) {
                list.add(i);
            }
        }

      // 교차점에서 시작할 경우 사각형의 순서에 따라 현재 위치에서 바로 다른 사각형에서 갈 수 있어서 list에 사각형의 위치를 다 넣고 dfs
        for (int idx : list) {
            dfs(rectangle, characterX, MAX - characterY, itemX, MAX - itemY, 0, idx);
        }

        return answer;
    }

    public void dfs(int[][] rectangle, int characterX, int characterY, final int itemX, final int itemY, int length, int currentRectIdx) {
        if (characterX == itemX && characterY == itemY) {
            if (answer == -1) {
                answer = length;
            } else {
                answer = Math.min(answer, length);
            }

            return;
        }

        map[characterY][characterX] = false;

        for (int i = 0; i < rowData.length; ++i) {
            int nextX = characterX + colData[i];
            int nextY = characterY + rowData[i];

            if (nextX < 0 || nextX > MAX || nextY < 0 || nextY > MAX) {
                continue;
            }

            if (map[nextY][nextX]) {
                boolean isRightPath = true;
                for (int j = 0; j < rectangle.length; ++j) {
                    int leftBottomX = rectangle[j][0];
                    int leftBottomY = MAX - rectangle[j][1];
                    int rightTopX = rectangle[j][2];
                    int rightTopY = MAX - rectangle[j][3];

                    if (nextX > leftBottomX && nextX < rightTopX && nextY > rightTopY && nextY < leftBottomY) {
                        isRightPath = false;
                        map[nextY][nextX] = false;
                        break;
                    }

                    // 위에서 아래로 가는 경우
                    if (characterX > leftBottomX && characterX < rightTopX && characterY == rightTopY) {
                        if (nextX > leftBottomX && nextX < rightTopX && nextY == leftBottomY) {
                            isRightPath = false;
                            break;
                        }
                    }

                    // 아래에서 위로 가는 경우
                    if (characterX > leftBottomX && characterX < rightTopX && characterY == leftBottomY) {
                        if (nextX > leftBottomX && nextX < rightTopX && nextY == rightTopY) {
                            isRightPath = false;
                            break;
                        }
                    }

                    // 좌측에서 우측 가는 경우
                    if (characterY > rightTopY && characterY < leftBottomY && characterX == leftBottomX) {
                        if (nextY > rightTopY && nextY < leftBottomY && nextX == rightTopX) {
                            isRightPath = false;
                            break;
                        }
                    }

                    // 우측에서 좌측 으로 가는 경우
                    if (characterY > rightTopY && characterY < leftBottomY && characterX == rightTopX) {
                        if (nextY > rightTopY && nextY < leftBottomY && nextX == leftBottomX) {
                            isRightPath = false;
                            break;
                        }
                    }
                }

                if (isRightPath) {
                    boolean isOnRectangle = false;
                    int curLeftBottomX = rectangle[currentRectIdx][0];
                    int curLeftBottomY = MAX - rectangle[currentRectIdx][1];
                    int curRightTopX = rectangle[currentRectIdx][2];
                    int curRightTopY = MAX - rectangle[currentRectIdx][3];

                    if ((nextX == curLeftBottomX || nextX == curRightTopX) && nextY >= curRightTopY && nextY <= curLeftBottomY) {
                        isOnRectangle = true;
                    }

                    if ((nextY == curLeftBottomY || nextY == curRightTopY) && nextX >= curLeftBottomX && nextX <= curRightTopX) {
                        isOnRectangle = true;
                    }

                    if (isOnRectangle) {
                        int nextRectIndex = currentRectIdx;
                        for (int j = 0; j < rectangle.length; ++j) {
                            if (j == currentRectIdx) {
                                continue;
                            }

                            int leftBottomX = rectangle[j][0];
                            int leftBottomY = MAX - rectangle[j][1];
                            int rightTopX = rectangle[j][2];
                            int rightTopY = MAX - rectangle[j][3];

                            if ((nextX == leftBottomX || nextX == rightTopX) && nextY >= rightTopY && nextY <= leftBottomY) {
                                nextRectIndex = j;
                            }

                            if ((nextY == leftBottomY || nextY == rightTopY) && nextX >= leftBottomX && nextX <= rightTopX) {
                                nextRectIndex = j;
                            }
                        }

                        dfs(rectangle, nextX, nextY, itemX, itemY, length + 1, nextRectIndex);
                    }
                }
            }
        }
    }
}
