import java.util.ArrayList;
import java.util.HashMap;

class Solution {
    public int solution(String[][] clothes) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        for (int i = 0; i < clothes.length; ++i) {
            String name = clothes[i][0];
            String type = clothes[i][1];
            if (map.containsKey(type)) {
                ArrayList<String> list = map.get(type);
                list.add(name);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(name);
                map.put(type, list);
            }
        }


        int answer = 1;
        for (String type : map.keySet()) {
            answer *= (map.get(type).size() + 1);
        }
        
        --answer;
        return answer;
    }
}
