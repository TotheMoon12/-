class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = new int[2];
        int front = 0;
        int back = 0;
        
        int minLength = Integer.MAX_VALUE;
        int sum = sequence[0];
        while (back < sequence.length) {
            if (sum == k) {
                int length = back - front;
                if (length < minLength) {
                    minLength = length;
                    answer[0] = front;
                    answer[1] = back;
                }
                
                ++back;
                if (back == sequence.length) {
                    break;
                }
                sum += sequence[back];
            } else if (sum > k) {
                sum -= sequence[front++];
            } else {
                ++back;
                if (back == sequence.length) {
                    break;
                }
                
                sum += sequence[back];
            }
        }
        
        return answer;
    }
}
