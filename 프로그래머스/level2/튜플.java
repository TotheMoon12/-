import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.StringTokenizer;

class Solution {
    public int[] solution(String s) {
        int[] answer;
        StringBuilder sb = new StringBuilder(s);
        sb.deleteCharAt(0);
        sb.deleteCharAt(sb.length() - 1);

        ArrayList<String> elements = new ArrayList<>();
        StringBuilder temp = new StringBuilder();
        for (int i = 0; i < sb.length(); ++i) {
            char c = sb.charAt(i);

            if (c == '{') {
                temp = new StringBuilder();
            } else if (c == '}') {
                elements.add(temp.toString());
            } else {
                temp.append(c);
            }
        }

        Collections.sort(elements, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });

        ArrayList<Integer> tuple = new ArrayList<>();
        for (String element : elements) {
            StringTokenizer st = new StringTokenizer(element, ",");

            while (st.hasMoreTokens()) {
                int number = Integer.parseInt(st.nextToken());

                if (!tuple.contains(number)) {
                    tuple.add(number);
                }
            }
        }

        answer = new int[tuple.size()];
        for (int i = 0; i < tuple.size(); ++i) {
            answer[i] = tuple.get(i);
        }

        return answer;
    }
}
