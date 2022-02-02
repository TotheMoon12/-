class Solution {
    public String solution(String p) {
        String answer = "";

        // 1
        if (p.equals("")) {
            return p;
        }

        // 2
        StringBuilder u = new StringBuilder();
        StringBuilder v = new StringBuilder();

        int openCount = 0;
        int closeCount = 0;

        boolean isCorrect = true;
        for (int i = 0; i < p.length(); ++i) {
            char c = p.charAt(i);

            u.append(c);
            if (c == '(') {
                ++openCount;
            } else {
                ++closeCount;
            }

            int diff = openCount - closeCount;
            if (diff < 0) {
                isCorrect = false;
            }

            if (diff == 0) {
                v.append(p.substring(i + 1));
                break;
            }
        }

        if (isCorrect) {
            // 3
            u.append(solution(v.toString()));
            answer = u.toString();
        } else {
            // 4
            StringBuilder temp = new StringBuilder();
            temp.append('(');
            temp.append(solution(v.toString()));
            temp.append(')');
            u.deleteCharAt(0);
            u.deleteCharAt(u.length() - 1);
            for (int i = 0; i <u.length(); ++i) {
                char c = u.charAt(i);

                if (c == '(') {
                    u.setCharAt(i, ')');
                } else {
                    u.setCharAt(i, '(');;
                }
            }
            
            temp.append(u);
            answer = temp.toString();
        }

        return answer;
    }
}
