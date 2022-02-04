import java.util.ArrayList;

class Solution {
    public long solution(String expression) {
        long answer = 0;
        String[] priorityList = {"+-*","+*-","-+*","-*+","*+-","*-+"};

        // 수식 토큰화
        ArrayList<String> expressionTokens = new ArrayList<>();
        int start = 0;
        int end = 0;
        for (int i = 0; i < expression.length(); ++i) {
            char c = expression.charAt(i);

            if (c == '+' || c == '-' || c == '*') {
                if (start != end) {
                    String number = expression.substring(start, end);
                    expressionTokens.add(number);
                }

                expressionTokens.add(Character.toString(c));
                start = i + 1;
                end = start;
            }

            if (c >= 48 && c <= 57) {
                ++end;
            }
        }

        if (start != end) {
            String number = expression.substring(start, end);
            expressionTokens.add(number);
        }

        // 우선순위 경우의 수마다 수식을 계산
        for (String priority : priorityList) {
            ArrayList<String> temp = (ArrayList<String>) expressionTokens.clone();
            for (int i = 0; i < priority.length(); ++i) {
                String operator = Character.toString(priority.charAt(i));

                int index = temp.indexOf(operator);
                while (index != -1) {
                    long num1 = Long.parseLong(temp.get(index - 1));
                    long num2 = Long.parseLong(temp.get(index + 1));

                    long result = 0;
                    if (operator.equals("+")) {
                        result = num1 + num2;
                    } else if (operator.equals("-")) {
                        result = num1 - num2;
                    } else if (operator.equals("*")) {
                        result = num1 * num2;
                    }

                    temp.remove(index - 1);
                    temp.remove(index - 1);
                    temp.remove(index - 1);
                    temp.add(index - 1, Long.toString(result));

                    index = temp.indexOf(operator);
                }
            }

            answer = Math.max(answer, Math.abs(Long.parseLong(temp.get(0))));
        }

        return answer;
    }
}
