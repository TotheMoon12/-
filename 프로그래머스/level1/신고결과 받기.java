import java.util.HashSet;
import java.util.HashMap;
import java.util.StringTokenizer;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        
        
        HashMap<String, Integer> indexMap = new HashMap<>();
        HashSet<String>[] reportListByUser = new HashSet[id_list.length];
        HashMap<String, Integer> reportedCount = new HashMap<>();
        for (int i = 0; i < id_list.length; ++i) {
            indexMap.put(id_list[i], i);
            reportListByUser[i] = new HashSet<>();
            reportedCount.put(id_list[i], 0);
        }
        
        
        StringTokenizer st;
        for (int i = 0; i < report.length; ++i) {
            st = new StringTokenizer(report[i]);
            
            String reporter = st.nextToken();
            String reported = st.nextToken();
            
            if (!reportListByUser[indexMap.get(reporter)].contains(reported)) {
                reportListByUser[indexMap.get(reporter)].add(reported);
                reportedCount.put(reported, reportedCount.get(reported) + 1);
            }            
        }
        
        int[] answer = new int[id_list.length];
        for (int i = 0; i < id_list.length; ++i) {
            String user = id_list[i];
            int count = 0;
            for (String reported : reportListByUser[indexMap.get(user)]) {
                if (reportedCount.get(reported) >= k) {
                    ++count;
                }
            }
            
            answer[i] = count;
        }
        return answer;
    }
}
