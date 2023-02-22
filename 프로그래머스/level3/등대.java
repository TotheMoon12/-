import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int n, int[][] lighthouse) {
        int answer = Integer.MAX_VALUE;

        ArrayList<Integer>[] map = new ArrayList[n + 1];
        for (int idx = 1; idx <= n; ++idx) {
            map[idx] = new ArrayList<>();
        }


        for (int idx = 0; idx < lighthouse.length; ++idx) {
            int a = lighthouse[idx][0];
            int b = lighthouse[idx][1];

            map[a].add(b);
            map[b].add(a);
        }

        int[][] dp = new int[n + 1][2];
        final int BELONG = 0;
        final int NOT_BELONG = 1;

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[n + 1];
        int[] connectionCounts = new int[n + 1];
        for (int idx = 1; idx <= n; ++idx) {
            dp[idx][BELONG] = 1;
            connectionCounts[idx] = map[idx].size();

            if (map[idx].size() == 1) {
                queue.add(idx);
            }
        }

        while (!queue.isEmpty()) {
            int number = queue.poll();

            int notVisitedNumber = 0;
            for (int next : map[number]) {
                if (!visited[next]) {
                    notVisitedNumber = next;
                }
            }

            int count = connectionCounts[number];
            if (count == 1) {
                visited[number] = true;
                --connectionCounts[notVisitedNumber];
                dp[notVisitedNumber][BELONG] += Math.min(dp[number][BELONG], dp[number][NOT_BELONG]);
                dp[notVisitedNumber][NOT_BELONG] += dp[number][BELONG];

                if (connectionCounts[notVisitedNumber] == 1) {
                    queue.add(notVisitedNumber);
                }
            } else if (count == 0) {
                answer = Math.min(dp[number][BELONG], dp[number][NOT_BELONG]);
                break;
            }
        }

        return answer;
    }
}
