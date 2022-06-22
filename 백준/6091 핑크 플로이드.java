import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static class Edge implements Comparable<Edge> {
        int number1;
        int number2;
        int length;

        public Edge(int number1, int number2, int length) {
            this.number1 = number1;
            this.number2 = number2;
            this.length = length;
        }

        @Override
        public int compareTo(Edge o) {
            return this.length - o.length;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer>[] map = new PriorityQueue[N + 1];
        for (int idx = 1; idx <= N; ++idx) {
            map[idx] = new PriorityQueue<>();
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] parent = new int[N + 1];
        for (int idx = 1; idx < N; ++idx) {
            parent[idx] = idx;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int sIdx = idx + 1; sIdx <= N; ++sIdx) {
                int length = Integer.parseInt(st.nextToken());
                pq.offer(new Edge(idx, sIdx, length));
            }
        }

        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            int parent1 = getParent(parent, edge.number1);
            int parent2 = getParent(parent, edge.number2);

            if (parent1 != parent2) {
                if (parent1 < parent2) {
                    parent[parent2] = parent1;
                } else {
                    parent[parent1] = parent2;
                }

                map[edge.number1].add(edge.number2);
                map[edge.number2].add(edge.number1);
            }
        }

        StringBuilder answer = new StringBuilder();
        for (int idx = 1; idx <= N; ++idx) {
            answer.append(map[idx].size());
            while (!map[idx].isEmpty()) {
                answer.append(" ");
                answer.append(map[idx].poll());
            }

            answer.append(System.lineSeparator());
        }

        System.out.printf(answer.toString());
    }

    public static int getParent(int[] parent, int n) {
        if (parent[n] == n) {
            return n;
        }

        return getParent(parent, parent[n]);
    }
}
