class Solution {
    public int[] solution(int m, int n, int startX, int startY, int[][] balls) {
        int[] answer = new int[balls.length];

        final int X = 0;
        final int Y = 1;
        for (int idx = 0; idx < balls.length; ++idx) {
            int[] ball = balls[idx];
            int targetX = ball[X];
            int targetY = ball[Y];

            int min = Integer.MAX_VALUE;
            int distanceXZero = (int) (Math.pow(startX + targetX, 2) + Math.pow(startY - targetY, 2));
            int distanceXM = (int) (Math.pow(m + m - startX - targetX, 2) + Math.pow(startY - targetY, 2));
            int distanceYZero = (int) (Math.pow(startX - targetX, 2) + Math.pow(startY + targetY, 2));
            int distanceYN = (int) (Math.pow(startX - targetX, 2) + Math.pow(n + n - startY - targetY, 2));

            if (startX == targetX) {
                min = Math.min(min, distanceXZero);
                min = Math.min(min, distanceXM);

                if (startY < targetY) {
                    min = Math.min(min, distanceYZero);
                } else {
                    min = Math.min(min, distanceYN);
                }
            } else if (startY == targetY) {
                min = Math.min(min, distanceYZero);
                min = Math.min(min, distanceYN);

                if (startX < targetX) {
                    min = Math.min(min, distanceXZero);
                } else {
                    min = Math.min(min, distanceXM);
                }
            } else {
                min = Math.min(min, distanceXZero);
                min = Math.min(min, distanceXM);
                min = Math.min(min, distanceYZero);
                min = Math.min(min, distanceYN);
            }

            answer[idx] = min;
        }

        return answer;
    }
}
