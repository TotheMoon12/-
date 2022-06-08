import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Pair implements Comparable<Pair> {
        int number;
        int cost;

        public Pair(int number, int cost) {
            this.number = number;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pair other) {
            return this.cost - other.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<Pair>[] map = new ArrayList[N + 1];
        for (int idx = 1; idx <= N; ++idx) {
            map[idx] = new ArrayList<>();
        }

        for (int count = 1; count <= M; ++count) {
            st = new StringTokenizer(br.readLine());
            int number1 = Integer.parseInt(st.nextToken());
            int number2 = Integer.parseInt(st.nextToken());

            map[number1].add(new Pair(number2, count));
            map[number2].add(new Pair(number1, count));
        }

        StringBuilder answer = new StringBuilder();
        for (int k = 0; k < K; ++k) {
            int cost = prim(map, N);
            answer.append(cost);
            answer.append(' ');

            if (cost == 0) {
                for (int count = k + 1; count < K; ++count) {
                    answer.append("0 ");
                }

                break;
            }
        }

        System.out.println(answer.toString());
    }

    public static int prim(ArrayList<Pair>[] map, int N) {
        Queue<Integer> q = new LinkedList<>();
        int start = 1;
        q.offer(start);
        boolean[] visited = new boolean[N + 1];
        visited[start] = true;
        PriorityQueue<Pair> pq = new PriorityQueue<>();

        int cost = 0;
        int edgeCount = 0;

        int minEdgeNumber1 = -1;
        int minEdgeNumber2 = -1;
        int minEdgeCost = Integer.MAX_VALUE;

        while (!q.isEmpty()) {
            int cur = q.poll();

            for (Pair next : map[cur]) {
                if (!visited[next.number]) {
                    pq.offer(next);
                }
            }

            while (!pq.isEmpty()) {
                Pair next = pq.poll();

                if (!visited[next.number]) {
                    visited[next.number] = true;
                    cost += next.cost;
                    q.offer(next.number);
                    ++edgeCount;

                    if (next.cost < minEdgeCost) {
                        minEdgeNumber1 = cur;
                        minEdgeNumber2 = next.number;
                        minEdgeCost = next.cost;
                    }
                    break;
                }
            }
        }

        if (minEdgeCost != Integer.MAX_VALUE) {
            for (Pair edge : map[minEdgeNumber1]) {
                if (edge.number == minEdgeNumber2) {
                    map[minEdgeNumber1].remove(edge);
                    break;
                }
            }

            for (Pair edge : map[minEdgeNumber2]) {
                if (edge.number == minEdgeNumber1) {
                    map[minEdgeNumber2].remove(edge);
                    break;
                }
            }
        }

        if (edgeCount == N - 1) {
            return cost;
        } else {
            return 0;
        }
    }
}

