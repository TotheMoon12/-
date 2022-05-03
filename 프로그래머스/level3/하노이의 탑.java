import java.util.ArrayList;

class Solution {
    public int[][] solution(int n) {
        int moveCount = 1;
        for (int i = 2; i <= n; ++i) {
            moveCount = 1 + 2 * moveCount;
        }

        int[][] answer = new int[moveCount][2];

        final int current = 0;
        final int target = 1;
        final int remain = 2;
        ArrayList<int[]>[] movePathArr = new ArrayList[21];
        for (int i = 1; i <= 20; ++i) {
            movePathArr[i] = new ArrayList<>();
        }

        int[] firstMove = {current,target};
        movePathArr[1].add(firstMove);
        for (int i = 2; i <= n; ++i) {
            for (int j = 0; j < movePathArr[i - 1].size(); ++j) {
                int[] path = movePathArr[i - 1].get(j);
                int[] newPath = new int[2];

                if (path[1] == target) {
                    newPath[1] = remain;
                } else if (path[1] == remain) {
                    newPath[1] = target;
                } else {
                    newPath[1] = path[1];
                }

                if (path[0] == target) {
                    newPath[0] = remain;
                } else if (path[0] == remain) {
                    newPath[0] = target;
                } else {
                    newPath[0] = path[0];
                }

                movePathArr[i].add(newPath);
            }

            int[] middlePath = {current, target};
            movePathArr[i].add(middlePath);

            for (int j = 0; j < movePathArr[i - 1].size(); ++j) {
                int[] path = movePathArr[i - 1].get(j);
                int[] newPath = new int[2];
                // current -> remain, remain -> current
                if (path[0] == current) {
                    newPath[0] = remain;
                } else if (path[0] == remain) {
                    newPath[0] = current;
                } else {
                    newPath[0] = path[0];
                }

                if (path[1] == current) {
                    newPath[1] = remain;
                } else if (path[1] == remain) {
                    newPath[1] = current;
                } else {
                    newPath[1] = path[1];
                }

                movePathArr[i].add(newPath);
            }
        }

        for (int i = 0; i < movePathArr[n].size(); ++i) {
            int[] path = movePathArr[n].get(i);
            if (path[0] == current) {
                answer[i][0] = 1;
            } else if (path[0] == target) {
                answer[i][0] = 3;
            } else {
                answer[i][0] = 2;
            }

            if (path[1] == current) {
                answer[i][1] = 1;
            } else if (path[1] == target) {
                answer[i][1] = 3;
            } else {
                answer[i][1] = 2;
            }
        }
        return answer;
    }
}
