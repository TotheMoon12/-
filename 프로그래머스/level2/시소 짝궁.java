import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

class Solution {
    public long solution(int[] weights) {
        long answer = 0;

        Arrays.sort(weights);
        HashMap<Integer, Long> map = new HashMap<>();
        map.put(weights[0] * 2, 1L);
        map.put(weights[0] * 3, 1L);
        map.put(weights[0] * 4, 1L);

        int sameWeightCount = 0;
        for (int idx = 1; idx < weights.length; ++idx) {
            int weight = weights[idx];

            if (weight == weights[idx - 1]) {
                ++sameWeightCount;
            } else {
                sameWeightCount = 0;
            }

            for (int distance = 2; distance <= 4; ++distance) {
                int power = weight * distance;
                if (map.containsKey(power)) {
                    long count = map.get(power);
                    answer += map.get(power);
                    map.put(power, count + 1);
                } else {
                    map.put(power, 1L);
                }
            }

            answer -= sameWeightCount * 2;
        }

        return answer;
    }
}
