class Solution {
    public int solution(int n, int m, int[] section) {
        int answer = 0;
        int end = 0;
        for (int number : section) {
            if (number > end) {
                ++answer;
                end = number + m - 1;
            }
        }
        return answer;
    }
}
