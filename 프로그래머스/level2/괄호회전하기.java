import java.util.Stack;

class Solution {
    public int solution(String s) {
        int answer = 0;
        StringBuilder sb = new StringBuilder(s);
        
        int length = s.length();
        for (int i = 0; i < length; ++i) {
            if (isValid(sb)) {
                ++answer;
            }
            
            sb.append(sb.charAt(0));
            sb.delete(0,1);
        }
        
        
        return answer;
    }
    
    private boolean isValid(final StringBuilder s) {
        Stack<Character> stack = new Stack<>();
        
        final int length = s.length();
        for (int i = 0; i < length; ++i) {
            char c = s.charAt(i);
            
            if (c == '{' || c == '[' || c == '(') {
                stack.push(c);
            } else {
                if (!stack.isEmpty()) {
                    char matched = stack.peek();
                    if (matched == '{' && c == '}' || matched == '[' && c == ']' || matched == '(' && c == ')') {
                        stack.pop();
                    } else {
                        return false;
                    }                    
                } else {
                    return false;
                }                
            }
        }
        
        if (!stack.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
}
