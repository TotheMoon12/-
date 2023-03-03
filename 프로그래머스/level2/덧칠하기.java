class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int start = 0;
        int end = 0;
        for (int number : section) {
            if (number > end) {
                ++answer;
                start = number;
                end = start + m - 1;
            }
        }
        return answer;
    }
}
