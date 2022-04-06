import java.util.HashMap;
import java.util.ArrayList;

class Solution {
    public int[] solution(String msg) {        
        HashMap<String, Integer> dictionary = new HashMap<>();
        for (int i = 1; i <= 26; ++i) {
            String s = "" + (char)('A' + i - 1);
            dictionary.put(s,i);
        }
        
        int start = 0;
        ArrayList<Integer> list = new ArrayList<>();
        StringBuilder w = new StringBuilder();
        while (start < msg.length()) {
            w.setLength(0);
            
            boolean isExisted = true;
            while (start < msg.length()) {
                w.append(msg.charAt(start));
            
                if (dictionary.containsKey(w.toString())) {
                    ++start;
                } else {
                    dictionary.put(w.toString(), dictionary.size() + 1);
                    isExisted = false;
                    break;
                }
            }
            
            if (!isExisted) {
                w.setLength(w.length() - 1);
            }
            
            list.add(dictionary.get(w.toString()));    
        }
        
        final int SIZE = list.size();
        int[] answer = new int[SIZE];
        for (int i = 0; i < SIZE; ++i) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}
