class Solution {
    public String[] solution(String[] s) {
        String[] answer = new String[s.length];
        String pattern = "110";

        for (int i = 0; i < s.length; ++i) {
            String str = s[i];
            int patternCount = 0;

            
            StringBuilder te = new StringBuilder();
            for (int j = 0; j < str.length(); ++j) {
                char c = str.charAt(j);
                
                if (c == '0') {
                    if (te.length() > 1) {
                        if (te.substring(te.length() - 2).equals("11")) {
                            te.delete(te.length() - 2, te.length());
                            ++patternCount;
                            continue;
                        }
                    }
                } 
                
                te.append(c);
            }

            StringBuilder sb = new StringBuilder();
            final int LENGTH = te.length();

            int insertIndex = -1;
            for (int j = 0; j < LENGTH; ++j) {
                if (j + 3 <= LENGTH) {
                    if (te.substring(j, j + 3).compareTo(pattern) > 0) {
                        insertIndex = j;
                        break;
                    }
                } else {
                    String sub = te.substring(j);

                    if (sub.equals("0") || sub.equals("00") || sub.equals("10")) {
                        insertIndex = te.length();
                    } else if (sub.equals("1") || sub.equals("11")) {
                        insertIndex = j;
                    } else if (sub.equals("01")) {
                        insertIndex = j + 1;
                    }

                    break;
                }
            }

            if (insertIndex != -1) {
                sb.append(te.substring(0, insertIndex));
                while (patternCount-- > 0) {
                    sb.append(pattern);
                }
                sb.append(te.substring(insertIndex));
            } else {
                while (patternCount-- > 0) {
                    sb.append(pattern);
                }
            }


            answer[i] = sb.toString();
        }
        return answer;
    }
}
