// 문제가 최소 스패닝 트리를 구하는 것과 같으므로 프림 알고리즘을 통하여 비용을 구한다

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

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

        int N = Integer.parseInt(br.readLine());
        int M = Integer.parseInt(br.readLine());

        ArrayList<Pair>[] map = new ArrayList[N + 1];
        for (int i = 1; i <= N; ++i) {
            map[i] = new ArrayList<>();
        }

        for (int i = 0; i < M; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int vertex1 = Integer.parseInt(st.nextToken());
            int vertex2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            map[vertex1].add(new Pair(vertex2, cost));
            map[vertex2].add(new Pair(vertex1, cost));
        }

        PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
            @Override
            public int compare(Pair o1, Pair o2) {
                return o1.cost - o2.cost;
            }
        });

        Queue<Integer> queue = new LinkedList<>();
        boolean[] visited = new boolean[N + 1];
        int start = 1;
        visited[start] = true;
        queue.offer(start);

        int minCost = 0;
        while(!queue.isEmpty()) {
            int cur = queue.poll();

            for (Pair next : map[cur]) {
                if (!visited[next.number]) {
                    pq.add(next);
                }
            }

            while (!pq.isEmpty()) {
                Pair next = pq.poll();

                if (!visited[next.number]) {
                    visited[next.number] = true;
                    minCost += next.cost;
                    queue.offer(next.number);
                    break;
                }
            }
        }

        System.out.println(minCost);
    }
}
