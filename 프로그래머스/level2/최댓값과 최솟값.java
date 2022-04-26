import java.util.StringTokenizer;

class Solution {
    public String solution(String s) {
        String answer = "";
        StringTokenizer st = new StringTokenizer(s);
        
        int min = Integer.MAX_VALUE;
        int max = Integer.MIN_VALUE;
        while (st.hasMoreTokens()) {
            int number = Integer.parseInt(st.nextToken());
            
            if (number > max) {
                max = number;
            }
            
            if (number < min) {
                min = number;
            }
        }
        
        answer += min + " " + max;
        return answer;
    }
}
