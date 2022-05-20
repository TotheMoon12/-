import java.io.BufferedReader;
import java.io.InputStreamReader;
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
        public int compareTo(Edge other) {
            return this.length - other.length;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        while (true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int m = Integer.parseInt(st.nextToken());
            int n = Integer.parseInt(st.nextToken());

            if (m == 0 && n == 0) {
                break;
            }

            int[] parent = new int[m];
            for (int idx = 0; idx < m; ++idx) {
                parent[idx] = idx;
            }

            PriorityQueue<Edge> pq = new PriorityQueue<>();
            int totalCost = 0;
            for (int idx = 0; idx < n; ++idx) {
                st = new StringTokenizer(br.readLine());
                int number1 = Integer.parseInt(st.nextToken());
                int number2 = Integer.parseInt(st.nextToken());
                int length = Integer.parseInt(st.nextToken());

                totalCost += length;
                pq.offer(new Edge(number1, number2, length));
            }

            int minCost = 0;
            while (!pq.isEmpty()) {
                Edge edge = pq.poll();

                if (!find(parent, edge.number1, edge.number2)) {
                    minCost += edge.length;
                    union(parent, edge.number1, edge.number2);
                }
            }

            System.out.println(totalCost - minCost);
        }
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
            parent[parent2] = parent1;
        } else {
            parent[parent1] = parent2;
        }
    }
}

