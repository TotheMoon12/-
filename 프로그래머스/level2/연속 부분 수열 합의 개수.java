import java.util.HashSet;

class Solution {
    public int solution(int[] elements) {
        HashSet<Integer> set = new HashSet<>();
        for (int length = 1; length <= elements.length; ++length) {
            for (int start = 0; start < elements.length; ++start) {
                int bound = Math.min(elements.length, start + length);
                int sum = 0;

                for (int idx = start; idx < bound; ++idx) {
                    sum += elements[idx];
                }

                if (start + length > elements.length) {
                    bound = start + length - elements.length;

                    for (int idx = 0; idx < bound; ++idx) {
                        sum += elements[idx];
                    }
                }

                set.add(sum);
            }
        }

        return set.size();
    }
}
