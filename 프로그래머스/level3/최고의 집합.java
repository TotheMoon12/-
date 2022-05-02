import java.util.Arrays;

class Solution {
    public int[] solution(int n, int s) {
        int[] answer = new int[n];
        int index = 0;
        while (n > 0) {
            int number = s / n;
            
            if (number == 0) {
                break;
            }
            
            answer[index++] = number;
            s -= number;
            --n;
        }
        
        if (s != 0) {
            answer = new int[1];
            answer[0] = -1;
        }
        
        return answer;
    }
}
