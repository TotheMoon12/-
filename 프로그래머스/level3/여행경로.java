import java.util.*;

class Solution {
    static boolean isEnd = false;

    public String[] solution(String[][] tickets) {
        HashMap<String, ArrayList<String>> map = new HashMap<>();
        final String START = "ICN";

        HashMap<String, TreeMap<String, Integer>> visited = new HashMap<>();
        for (int i = 0; i < tickets.length; ++i) {
            String src = tickets[i][0];
            String dest = tickets[i][1];

            if (map.containsKey(src)) {
                ArrayList<String> list = map.get(src);
                list.add(dest);
            } else {
                ArrayList<String> list = new ArrayList<>();
                list.add(dest);
                map.put(src, list);
            }

            if (visited.containsKey(src)) {
                TreeMap<String, Integer> ticketMap = visited.get(src);
                if (ticketMap.containsKey(dest)) {
                    ticketMap.put(dest, ticketMap.get(dest) + 1);
                } else {
                    ticketMap.put(dest, 1);
                }
            } else {
                TreeMap<String, Integer> ticketMap = new TreeMap<>();
                ticketMap.put(dest, 1);
                visited.put(src, ticketMap);
            }
        }

        for (String src : map.keySet()) {
            ArrayList<String> destList = map.get(src);
            Collections.sort(destList);
        }

        ArrayList<String> path = new ArrayList<>();
        final int TICKET_COUNT = tickets.length;

        dfs(map, START, visited, TICKET_COUNT, path);
        String[] answer = new String[path.size()];
        for (int i = 0; i < path.size(); ++i) {
            answer[i] = path.get(i);
        }

        return answer;
    }

    public static void dfs(HashMap<String, ArrayList<String>> map, String src, HashMap<String, TreeMap<String, Integer>> visited, final int TICKET_COUNT, ArrayList<String> path) {
        path.add(src);
        if (path.size() - 1 == TICKET_COUNT) {
            isEnd = true;
            return;
        }

        if (map.containsKey(src)) {
            TreeMap<String, Integer> ticketMap = visited.get(src);
            ArrayList<String> destList = map.get(src);

            for (String next : destList) {
                int nextTicketCount = ticketMap.get(next);

                if (!isEnd && nextTicketCount > 0) {
                    ticketMap.put(next, nextTicketCount - 1);
                    dfs(map, next, visited, TICKET_COUNT, path);
                    if (!isEnd) {
                        ticketMap.put(next, nextTicketCount);
                    }
                }
            }
        }
        
        if (!isEnd) {
            path.remove(path.size() - 1);
        }
    }
}
