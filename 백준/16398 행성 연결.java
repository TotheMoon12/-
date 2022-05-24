import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static class Edge implements Comparable<Edge> {
        int planet1;
        int planet2;
        int cost;

        public Edge(int planet1, int planet2, int cost) {
            this.planet1 = planet1;
            this.planet2 = planet2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge other) {
            return this.cost - other.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(br.readLine());


        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] parent = new int[N];
        for (int row = 0; row < N; ++row) {
            parent[row] = row;

            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; ++col) {
                int cost = Integer.parseInt(st.nextToken());
                if (col <= row) {
                    continue;
                }

                pq.offer(new Edge(row, col, cost));
            }
        }

        long cost = 0;
        int count = 0;
        while (count < N - 1) {
            Edge edge = pq.poll();

            if (!find(parent, edge.planet1, edge.planet2)) {
                cost += edge.cost;
                ++count;
                union(parent, edge.planet1, edge.planet2);
            }
        }

        System.out.println(cost);
    }

    public static int getParent(int[] parent, int n) {
        if (parent[n] == n) {
            return n;
        }

        return getParent(parent, parent[n]);
    }

    public static boolean find(int[] parent, int n1, int n2) {
        int parent1 = getParent(parent, n1);
        int parent2 = getParent(parent, n2);

        return parent1 == parent2;
    }

    public static void union(int[] parent, int n1, int n2) {
        int parent1 = getParent(parent, n1);
        int parent2 = getParent(parent, n2);

        if (parent1 < parent2) {
            parent[parent1] = parent2;
        } else {
            parent[parent2] = parent1;
        }
    }
}
