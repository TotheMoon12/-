import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.ArrayList;

class Solution {
    public String[] solution(String[] record) {
        HashMap<String, String> map = new HashMap<>();
        ArrayList<String> results = new ArrayList<>();
        
        for (int i = 0; i < record.length; ++i) {
            String s = record[i];
            StringTokenizer st = new StringTokenizer(s, " ");
            String action = st.nextToken();
            String userId = st.nextToken();
            String nickName;
            
            if (!action.equals("Leave")) {
                nickName = st.nextToken();
                map.put(userId, nickName);
            }
        }
        
        for (int i = 0; i < record.length; ++i) {
            String s = record[i];
            StringTokenizer st = new StringTokenizer(s, " ");
            String action = st.nextToken();
            String userId = st.nextToken();
            String nickName = map.get(userId);
            
            if (action.equals("Enter")) {
                StringBuilder sb = new StringBuilder();
                sb.append(nickName);
                sb.append("님이 들어왔습니다.");
                results.add(sb.toString());
            } else if (action.equals("Leave")) {
                StringBuilder sb = new StringBuilder();
                sb.append(nickName);
                sb.append("님이 나갔습니다.");
                results.add(sb.toString());
            }            
        }
        
        String[] answer = new String[results.size()];
        answer = results.toArray(answer);
        
        
        return answer;
    }
}
