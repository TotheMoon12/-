import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

class Solution {
    public int solution(int k, int[] tangerine) {
        int answer = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int size : tangerine) {
            if (map.containsKey(size)) {
                map.put(size, map.get(size) + 1);
            } else {
                map.put(size, 1);
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        for (int count : map.values()) {
            list.add(count);
        }

        Collections.sort(list, Collections.reverseOrder());
        for (int idx = 0; idx < list.size(); ++idx) {
            ++answer;
            k -= list.get(idx);

            if (k <= 0) {
                break;
            }
        }
        return answer;
    }
}
