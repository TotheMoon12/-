import java.util.ArrayList;

class Solution {
    public int solution(String s) {
        int answer = s.length();
        final int half_length = s.length() / 2;

        for (int unit = 1; unit <= half_length; ++unit) {
            ArrayList<String> strings = new ArrayList<>();
            int start;
            int length = 0;

            for (start = 0; start + unit <= s.length(); start += unit) {
                String string = s.substring(start, start + unit);
                strings.add(string);
            }

            if (start < s.length()) {
                strings.add(s.substring(start));
            }


            int i;
            for (i = 0; i < strings.size() - 1; ) {
                int matchedCount = 0;

                String string = strings.get(i);

                for (int j = i + 1; j < strings.size(); ++j) {
                    String comparedString = strings.get(j);

                    if (string.equals(comparedString)) {
                        ++matchedCount;

                        if (j == strings.size() - 1) {
                            length += unit + (String.valueOf(matchedCount + 1).length());
                            i += matchedCount + 1;
                            break;
                        }
                    } else {
                        if (matchedCount > 0) {
                            length += unit + (String.valueOf(matchedCount + 1).length());
                            i += matchedCount + 1;
                        } else {
                            length += unit;
                            ++i;
                        }

                        break;
                    }
                }
            }

            if (i < strings.size()) {
                length += strings.get(i).length();
            }

            answer = Math.min(answer, length);
        }
        return answer;
    }
}
