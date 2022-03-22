// 크루스칼 알고리즘을 통하여 최소 스패팅 트리 찾기

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int n, int[][] costs) {
        Arrays.sort(costs, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[2] - o2[2];
            }
        });

        int answer = 0;
        ArrayList<Integer>[] graph = new ArrayList[n];
        for (int i = 0; i < n; ++i) {
            graph[i] = new ArrayList<>();
        }

        Arrays.fill(graph, -1);
        int count = 0;
        for (int i = 0; i < costs.length; ++i) {
            int v1 = costs[i][0];
            int v2 = costs[i][1];
            int cost = costs[i][2];

            // 두 정점 연결 확인
            boolean isConnected = false;
            boolean[] visited = new boolean[n];
            Queue<Integer> queue = new LinkedList<>();
            queue.add(v1);
            visited[v1] = true;

            while (!queue.isEmpty()) {
                int src = queue.poll();

                for (int next : graph[src]) {
                    if (next == v2) {
                        isConnected = true;
                        break;
                    }

                    if (!visited[next]) {
                        visited[next] = true;
                        queue.add(next);
                    }
                }
            }

            // 연결안 되어 있지 않은 경우 간선 적용
            if (!isConnected) {
                graph[v1].add(v2);
                graph[v2].add(v1);
                answer += cost;
            }

            if (count == n) {
                break;
            }
        }

        return answer;
    }
}
