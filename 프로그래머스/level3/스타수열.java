import java.util.HashMap;

class Solution {
    public int solution(int[] a) {
        int answer = 0;
        if (a.length == 1) {
            return 0;
        }

        HashMap<Integer, Integer> map = new HashMap<>();
        HashMap<Integer, Integer> prev = new HashMap<>();
        HashMap<Integer, Integer> prevPair = new HashMap<>();

        for (int i = 0; i < a.length; ++i) {
            int num = a[i];

            if (prevPair.containsKey(num)) {
                int prevIndex = prev.get(num);
                int prevPairIndex = prevPair.get(num);
                if (i > prevIndex && i > prevPairIndex) {
                    for (int j = Math.max(prevPair.get(num), prev.get(num)) + 1; j < a.length; ++j) {
                        int pair = a[j];
                        if (pair != num) {
                            prevPair.put(num, j);
                            prev.put(num, i);
                            map.put(num, map.get(num) + 1);
                            break;
                        }
                    }
                }
            } else {
                for (int j = 0; j < a.length; ++j) {
                    int pair = a[j];
                    if (pair != num) {
                        prev.put(num, i);
                        prevPair.put(num, j);
                        map.put(num, 1);
                        break;
                    }
                }
            }
        }

        for (int key : map.keySet()) {
            answer = Math.max(answer, map.get(key));
        }

        return answer * 2;
    }
}
