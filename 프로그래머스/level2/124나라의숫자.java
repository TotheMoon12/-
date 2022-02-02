class Solution {
    public String solution(int n) {
        String answer = "";
        StringBuilder sb = new StringBuilder();
        int[] numbers = {1,2,4};
        
        int temp = n;
        
        while(temp > 0) {            
            --temp;
            sb.append(numbers[temp%3]);
            temp = temp / 3;            
        }        
        
        answer = sb.reverse().toString();
        return answer;
    }
}
