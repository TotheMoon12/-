import java.util.HashMap;

class Solution {
    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;

        HashMap<String, Integer> map = new HashMap<>();
        HashMap<String, Integer> requiredCountMap = new HashMap<>();
        for (int idx = 0; idx < want.length; ++idx) {
            String name = want[idx];
            int count = number[idx];

            map.put(name, 0);
            requiredCountMap.put(name, count);
        }

        int totalCount = 10;
        for (int idx = 0; idx < 10; ++idx) {
            String name = discount[idx];
            if (map.containsKey(name)) {
                int requiredCount = requiredCountMap.get(name);
                int count = map.get(name);
                if (count < requiredCount) {
                    --totalCount;
                }
                
                map.put(name, count + 1);
            }
        }

        if (totalCount == 0) {
            ++answer;
        }

        for (int idx = 10; idx < discount.length; ++idx) {
            String name = discount[idx];
            String removeName = discount[idx - 10];

            if (map.containsKey(removeName)) {
                int requiredCount = requiredCountMap.get(removeName);
                int count = map.get(removeName) - 1;
                map.put(removeName, count);
                if (count < requiredCount) {
                    ++totalCount;                    
                }
            }

            if (map.containsKey(name)) {
                int requiredCount = requiredCountMap.get(name);
                int count = map.get(name);
                if (count < requiredCount) {
                    --totalCount;
                }

                map.put(name, count + 1);
            }

            if (totalCount == 0) {
                ++answer;
            }
        }

        return answer;
    }
}
