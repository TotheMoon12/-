class Solution {
    public long solution(int w, int h) {
        long answer = 0;

        for (int x = 1; x < w; ++x) {
            int y = (int) (-h / (double) w * x + h);
            answer += y;
        }

        answer *= 2;
        return answer;
    }
}
