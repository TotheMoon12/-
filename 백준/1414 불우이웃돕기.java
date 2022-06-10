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
        public int compareTo(Edge o) {
            return this.length - o.length;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        int totalLength = 0;
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        int[] parent = new int[n];
        for (int idx = 0; idx < n; ++idx) {
            parent[idx] = idx;

            String s = br.readLine();
            for (int sIdx = 0; sIdx < n; ++sIdx) {
                 char c = s.charAt(sIdx);
                 if (c == '0') {
                     continue;
                 }

                 int length = 0;
                 if (c >= 'a' && c <= 'z') {
                     length = (c - 'a' + 1);
                 } else if (c >= 'A' && c <= 'Z') {
                    length = (c - 'A' + 27);
                }

                totalLength += length;

                 if (idx != sIdx) {
                     pq.offer(new Edge(idx, sIdx, length));
                 }
            }
        }

        int count = 0;
        int minLength = 0;
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

                ++count;
                minLength += edge.length;
            }
        }

        if (count == n - 1) {
            System.out.println(totalLength - minLength);
        } else {
            System.out.println(-1);
        }
    }

    public static int getParent(int[] parent, int n) {
        if (parent[n] == n) {
            return n;
        }

        return getParent(parent, parent[n]);
    }
}
