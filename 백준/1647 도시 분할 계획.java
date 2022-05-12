// 최소 스패닝 트리를 prim알고리즘을 통해서 구하고 그 중 가장 비용이 큰 도로를 하나 빼 주면 유지비용을 최소로 마을을 두 개로 분리할 수 있다

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Pair {
        int number;
        int cost;

        public Pair(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        ArrayList<Pair>[] map = new ArrayList[N + 1];
        for (int index = 1; index <= N; ++index) {
            map[index] = new ArrayList<>();
        }

        for (int index = 0; index < M; ++index) {
            st = new StringTokenizer(br.readLine());
            int house1 = Integer.parseInt(st.nextToken());
            int house2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            map[house1].add(new Pair(house2, cost));
            map[house2].add(new Pair(house1, cost));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.cost - o2.cost;
            }
        });

        int start = 1;
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);

        boolean[] visited = new boolean[N + 1];
        visited[start] = true;

        int totalCost = 0;
        int maxCost = 0;
        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for (Pair next : map[cur]) {
                if (!visited[next.number]) {
                    pq.offer(next);
                }
            }

            while (!pq.isEmpty()) {
                Pair next = pq.poll();

                if (!visited[next.number]) {
                    visited[next.number] = true;
                    totalCost += next.cost;
                    maxCost = Math.max(maxCost, next.cost);
                    queue.offer(next.number);
                    break;
                }
            }
        }

        System.out.println(totalCost - maxCost);
    }
}
