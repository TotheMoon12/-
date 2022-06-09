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
        public int compareTo(Edge o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        int m = Integer.parseInt(st.nextToken());

        int[] parent = new int[n + 1];
        for (int idx = 1; idx <= n; ++idx) {
            parent[idx] = idx;
        }

        for (int count = 0; count < m; ++count) {
            st = new StringTokenizer(br.readLine());
            int computer1 = Integer.parseInt(st.nextToken());
            int computer2 = Integer.parseInt(st.nextToken());

            union(parent, computer1, computer2);
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int row = 1; row <= n; ++row) {
            st = new StringTokenizer(br.readLine());
            for (int col = 1; col <= row; ++col) {
                int cost = Integer.parseInt(st.nextToken());

                if (col < row && row != 1 && col != 1) {
                    pq.offer(new Edge(row, col, cost));
                }
            }
        }

        int cost = 0;
        int count = 0;
        StringBuilder connectedPairs = new StringBuilder();
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            int parent1 = getParent(parent, edge.number1);
            int parent2 = getParent(parent, edge.number2);
            if (parent1 != parent2) {
                cost += edge.cost;
                ++count;
                union(parent, edge.number1, edge.number2);
                connectedPairs.append(edge.number1);
                connectedPairs.append(' ');
                connectedPairs.append(edge.number2);
                connectedPairs.append(System.lineSeparator());
            }
        }

        System.out.println(cost + " " + count);
        if (count > 0) {
            System.out.println(connectedPairs.toString());
        }
    }

    public static int getParent(int[] parent, int n) {
        if (parent[n] == n) {
            return n;
        }

        return getParent(parent, parent[n]);
    }

    public static void union(int[] parent, int n1, int n2) {
        int parent1 = getParent(parent, n1);
        int parent2 = getParent(parent, n2);

        if (parent1 < parent2) {
            parent[parent2] = parent1;
        } else {
            parent[parent1] = parent2;
        }
    }
}
