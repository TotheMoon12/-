import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static class Edge implements Comparable<Edge> {
        int number1;
        int number2;

        public Edge(int number1, int number2) {
            this.number1 = number1;
            this.number2 = number2;
        }

        @Override
        public int compareTo(Edge o) {
            if (this.number1 == o.number1) {
                return this.number2 - o.number2;
            }

            return this.number1 - o.number1;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        char[][] map = new char[N][N];
        boolean[][] isConnected = new boolean[N][N];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] parent = new int[N];

        for (int idx = 0; idx < N; ++idx) {
            parent[idx] = idx;

            String s = br.readLine();
            for (int sIdx = 0; sIdx < idx; ++sIdx) {
                char state = s.charAt(sIdx);
                map[sIdx][idx] = s.charAt(sIdx);

                if (state == 'Y') {
                    pq.offer(new Edge(sIdx, idx));
                }
            }
        }


        int edgeCount = 0;
        int extendCount = M - N + 1;
        int[] counts = new int[N];
        while ((edgeCount < N - 1 || extendCount > 0) && !pq.isEmpty()) {
            Edge edge = pq.poll();

            int parent1 = getParent(parent, edge.number1);
            int parent2 = getParent(parent, edge.number2);

            if (parent1 != parent2) {
                if (parent1 < parent2) {
                    parent[parent2] = parent1;
                } else {
                    parent[parent1] = parent2;
                }

                ++edgeCount;
                ++counts[edge.number1];
                ++counts[edge.number2];
            } else if (extendCount > 0) {
                --extendCount;
                ++counts[edge.number1];
                ++counts[edge.number2];
            }
        }

        if (edgeCount < N - 1 || extendCount > 0) {
            System.out.println(-1);
        } else {
            StringBuilder sb = new StringBuilder();
            for (int idx = 0; idx < N - 1; ++idx) {
                sb.append(counts[idx]);
                sb.append(' ');
            }
            sb.append(counts[N - 1]);

            System.out.println(sb.toString());
        }
    }

    public static int getParent(int[] parent, int n) {
        if (parent[n] == n) {
            return n;
        }

        return getParent(parent, parent[n]);
    }
}
