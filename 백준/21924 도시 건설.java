import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static class Edge implements Comparable<Edge> {
        int number1;
        int number2;
        int cost;

        public Edge(int number1, int number2, int cost) {
            this.number1 = number1;
            this.number2 = number2;
            this.cost = cost;
        }


        @Override
        public int compareTo(Edge other) {
            return this.cost - other.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        long totalCost = 0;
        for (int count = 0; count < M; ++count) {
            st = new StringTokenizer(br.readLine());
            int number1 = Integer.parseInt(st.nextToken());
            int number2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            totalCost += cost;

            pq.add(new Edge(number1, number2, cost));
        }

        int[] parent = new int[N + 1];
        for (int idx = 1; idx <= N; ++idx) {
            parent[idx] = idx;
        }

        int edgeCount = 0;
        long cost = 0;
        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            int parent1 = getParent(parent, edge.number1);
            int parent2 = getParent(parent, edge.number2);
            if (parent1 != parent2) {
                if (parent1 < parent2) {
                    parent[parent2] = parent1;
                } else {
                    parent[parent1] = parent2;
                }

                cost += edge.cost;
                ++edgeCount;
            }
        }

        if (edgeCount != N - 1) {
            System.out.println(-1);
        } else {
            System.out.println(totalCost - cost);
        }
    }

    public static int getParent(int[] parent, int n) {
        if (parent[n] == n) {
            return n;
        }

        return getParent(parent, parent[n]);
    }
}

