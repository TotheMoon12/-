class Solution {
    public long solution(int n, int m, int x, int y, int[][] queries) {
        long answer = 0;
        final int LEFT = 0;
        final int RIGHT = 1;
        final int UP = 2;
        final int DOWN = 3;

        long leftTopX = x;
        long leftTopY = y;
        long rightBottomX = x;
        long rightBottomY = y;
        long N = n;
        long M = m;
        for (int i = queries.length - 1; i >= 0; --i) {
            int command = queries[i][0];
            long move = queries[i][1];

            if (command == LEFT) {
                // 오른
                rightBottomY += move;
                if (leftTopY != 0l) {
                    leftTopY += move;
                }

                if (leftTopY > M && rightBottomY > M) {
                    return 0l;
                } else {
                    rightBottomY = Math.min(rightBottomY, M - 1);
                }
            } else if (command == RIGHT) {
                // 왼
                leftTopY -= move;
                if (rightBottomY != M - 1) {
                    rightBottomY -= move;
                }

                if (leftTopY < 0l && rightBottomY < 0l) {
                    return 0l;
                } else {
                    leftTopY = Math.max(leftTopY, 0l);
                }
            } else if (command == DOWN) {
                // 위
                leftTopX -= move;
                if (rightBottomX != N - 1) {
                    rightBottomX -= move;
                }

                if (leftTopX < 0l && rightBottomX < 0l) {
                    return 0l;
                } else {
                    leftTopX = Math.max(leftTopX, 0l);
                }
            } else if (command == UP) {
                // 아래
                rightBottomX += move;
                if (leftTopX != 0l) {
                    leftTopX += move;
                }

                if (leftTopX > N && rightBottomX > N) {
                    return 0l;
                } else {
                    rightBottomX = Math.min(rightBottomX, N - 1);
                }
            }
        }

        answer = (rightBottomX - leftTopX + 1) * (rightBottomY - leftTopY + 1);
        return answer;
    }
}
