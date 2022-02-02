import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        String[] answer;
        ArrayList<String> result = new ArrayList<>();
        
        // 정렬 후 방식 용
        ArrayList<String> sortedOrders = new ArrayList<>();
        for(String order : orders) {
            char[] orderCharArray = order.toCharArray();
            Arrays.sort(orderCharArray);
            sortedOrders.add(String.valueOf(orderCharArray));
        }
        
        for (int i = 0; i < course.length; ++i) {
            int count = course[i];

            HashMap<String, Integer> combination = new HashMap<>();

            // 정렬 후 방식
            for (String order : sortedOrders) {
                makeCombination(order, 0, "", true, count, combination);
                makeCombination(order, 0, "", false, count, combination);
            }
            
            // 조합 만들고 정렬하는 방식
//             for (int j = 0; j < orders.length; ++j) {
//                 String order = orders[j];

//                 makeCombination(order, 0, "", true, count, combination);
//                 makeCombination(order, 0, "", false, count, combination);
//             }

            int max = 1;
            for (String key : combination.keySet()) {
                int comboCount = combination.get(key);
                if (comboCount > max) {
                    max = comboCount;
                }
            }

            if (max > 1) {
                for (String key : combination.keySet()) {
                    if (combination.get(key) == max) {
                        result.add(key);
                    }
                }
            }
        }

        Collections.sort(result);
        answer = new String[result.size()];
        for (int i = 0; i < result.size(); ++i) {
            answer[i] = result.get(i);
        }

        return answer;
    }

    public void makeCombination(String input, int index, String part, boolean isAdded, int count, HashMap<String, Integer> combination) {
        if (index == input.length()) {
            return;
        }

        if (isAdded) {
            part += input.charAt(index);
        }

        if (part.length() == count) {
            // 미리 정렬하지 않은 방식
//             char[] partCharArray = part.toCharArray();
//             Arrays.sort(partCharArray);

//             String combo = String.valueOf(partCharArray);
//             if (combination.containsKey(combo)) {
//                 combination.put(combo, combination.get(combo) + 1);
//             } else {
//                 combination.put(combo, 1);
//             }
            
            // 미리 정렬 한 방식
            if (combination.containsKey(part)) {
                combination.put(part, combination.get(part) + 1);
            } else {
                combination.put(part, 1);
            }

            return;
        }

        makeCombination(input, index + 1, part, true, count, combination);
        makeCombination(input, index + 1, part, false, count, combination);
    }
}
