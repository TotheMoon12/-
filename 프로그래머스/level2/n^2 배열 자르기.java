class Solution {
    public int[] solution(int n, long left, long right) {
        int[] answer = new int[(int)(right - left + 1)];
        
        for (int i = 0; i < answer.length; ++i) {
            int row = (int)(left / n + 1);
            int col = (int)(left % n + 1);
            
            if (row < col) {
                answer[i] = col;
            } else {
                answer[i] = row;
            }
            
            ++left;
        }

        return answer;
    }
}
