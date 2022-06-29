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

        int N = Integer.parseInt(br.readLine());
        int[] xArr = new int[N];
        int[] yArr = new int[N];
        int[] parent = new int[N];
        for (int idx = 0; idx < N; ++idx) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            xArr[idx] = x;
            yArr[idx] = y;
            parent[idx] = idx;
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>();
        for (int idx = 0; idx < N; ++idx) {
            int x1 = xArr[idx];
            int y1 = yArr[idx];
            for (int sIdx = 0; sIdx < idx; ++sIdx) {
                int cost = (int) (Math.pow(x1 - xArr[sIdx], 2) + Math.pow(y1 - yArr[sIdx], 2));
                pq.offer(new Edge(idx, sIdx, cost));
            }
        }

        int answer = 0;
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

                answer = Math.max(answer, edge.cost);
            }
        }

        System.out.println(answer);
    }

    public static int getParent(int[] parent, int n) {
        if (parent[n] == n) {
            return n;
        }

        return getParent(parent, parent[n]);
    }
}
