// 스택을 이용하여 괄호의 순서가 올바른지를 확인한다

import java.util.Stack;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        Stack<Character> stack = new Stack<>();
        final int LENGTH = s.length();
        for (int i = 0; i < LENGTH; ++i) {
            char c = s.charAt(i);
            
            if (c == '(') {
                stack.push(c);            
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    return false;
                }
                
                char pair = stack.pop();
                if (pair == ')') {
                    return false;
                }
            }
        }
        
        if (!stack.isEmpty()) {
            return false;
        }

        return answer;
    }
}
