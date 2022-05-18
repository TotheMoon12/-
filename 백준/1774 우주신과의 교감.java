import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static class Point {
        int x;
        int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Edge implements Comparable<Edge> {
        int number1;
        int number2;
        double length;

        public Edge(int number1, int number2, double length) {
            this.number1 = number1;
            this.number2 = number2;
            this.length = length;
        }

        @Override
        public int compareTo(Edge other) {
            if (this.length < other.length) {
                return -1;
            } else if (this.length == other.length) {
                return 0;
            } else {
                return 1;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] parent = new int[N + 1];
        for (int idx = 1; idx <= N; ++idx) {
            parent[idx] = idx;
        }

        Point[] points = new Point[N + 1];
        boolean[] visited = new boolean[N + 1];
        for (int idx = 1; idx <= N; ++idx) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[idx] = new Point(x, y);
        }

        for (int idx = 0; idx < M; ++idx) {
            st = new StringTokenizer(br.readLine());
            int number1 = Integer.parseInt(st.nextToken());
            int number2 = Integer.parseInt(st.nextToken());

            union(parent, number1, number2);

            visited[number1] = true;
            visited[number2] = true;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int idx = 1; idx < N; ++idx) {
            Point p1 = points[idx];
            for (int sIdx = idx + 1; sIdx <= N; ++sIdx) {
                if (!find(parent, idx, sIdx)) {
                    Point p2 = points[sIdx];
                    double length = Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
                    pq.offer(new Edge(idx, sIdx, length));
                }
            }
        }

        double minLength = 0l;
        while (!pq.isEmpty()) {
            Edge edge = pq.poll();

            if (!find(parent, edge.number1, edge.number2)) {
                minLength += edge.length;
                union(parent, edge.number1, edge.number2);
            }
        }

        System.out.printf("%.2f", minLength);
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
