class Solution {
    public String solution(String s) {
        StringBuilder answer = new StringBuilder();        
        boolean isFirst = true;
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            
            if (isFirst && c != ' ') {
                if (c >= 'a' && c <= 'z') {
                    char upperC = (char)(c & ~0x20);
                    answer.append(upperC);
                } else {
                    answer.append(c);
                }
                
                isFirst = false;
            } else {
                if (c == ' ') {
                    isFirst = true;
                } else {
                    c = (char) (c | 0x20);
                }
                
                answer.append(c);
            }
        }
        
        return answer.toString();
    }
}
