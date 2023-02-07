class Solution {
    public long solution(int k, int d) {
        long answer = 0;
        long squareD = d * (long) d;
        long squareK = k * (long) k;

        for (long a = 0; a <= d; a += k) {
            long squareX = a * a;
            long squareY = squareD - squareX;
            answer += (long) Math.sqrt(squareY / squareK) + 1;
        }

        return answer;
    }
}
