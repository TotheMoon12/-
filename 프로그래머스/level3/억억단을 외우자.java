import java.util.ArrayList;
import java.util.Collections;

class Solution {
    public class Pair implements Comparable<Pair> {
        int start;
        int idx;

        public Pair(int start, int idx) {
            this.start = start;
            this.idx = idx;
        }

        @Override
        public int compareTo(Pair o) {
            return o.start - this.start;
        }
    }

    public int[] solution(int e, int[] starts) {
        int[] answer = new int[starts.length];
        int[] counts = new int[e + 1];
        for (int row = 1; row <= e; ++row) {
            for (int col = 1; col <= e; ++col) {
                long value = row * (long)col;
                if (value > e) {
                    break;
                }

                counts[(int)value] += 1;
            }
        }

        ArrayList<Pair> list = new ArrayList<>();
        for (int idx = 0; idx < starts.length; ++idx) {
            list.add(new Pair(starts[idx], idx));
        }

        int maxCount = 0;
        int number = 0;
        Collections.sort(list);
        int prev = e + 1;
        for (int idx = 0; idx < starts.length; ++idx) {
            Pair pair = list.get(idx);

            for (int value = prev - 1; value >= pair.start; --value) {
                if (counts[value] >= maxCount) {
                    maxCount = counts[value];
                    number = value;
                }
            }

            answer[pair.idx] = number;
            prev = pair.start;
        }

        return answer;
    }
}
