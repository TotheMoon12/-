import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static class Edge implements Comparable<Edge> {
        int number1;
        int number2;
        double cost;

        public Edge(int number1, int number2, double cost) {
            this.number1 = number1;
            this.number2 = number2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Edge o) {
            return Double.compare(this.cost, o.cost);
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        StringBuilder sb = new StringBuilder();
        for (int n = 0; n < N; ++n) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int S = Integer.parseInt(st.nextToken());
            int P = Integer.parseInt(st.nextToken());

            int[] xArr = new int[P];
            int[] yArr = new int[P];
            int[] parent = new int[P];
            for (int idx = 0; idx < P; ++idx) {
                st = new StringTokenizer(br.readLine());
                int x = Integer.parseInt(st.nextToken());
                int y = Integer.parseInt(st.nextToken());

                xArr[idx] = x;
                yArr[idx] = y;

                parent[idx] = idx;
            }

            PriorityQueue<Edge> pq = new PriorityQueue<>();
            for (int idx = 0; idx < P; ++idx) {
                int x1 = xArr[idx];
                int y1 = yArr[idx];

                for (int sIdx = 0; sIdx < idx; ++sIdx) {
                    int x2 = xArr[sIdx];
                    int y2 = yArr[sIdx];

                    double cost = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));

                    pq.offer(new Edge(idx, sIdx, cost));
                }
            }

            double answer = 0;
            int count = 0;
            int edgeCount = P - S;
            while (count < edgeCount) {
                Edge edge = pq.poll();

                int parent1 = getParent(parent, edge.number1);
                int parent2 = getParent(parent, edge.number2);

                if (parent1 != parent2) {
                    if (parent1 > parent2) {
                        parent[parent1] = parent2;
                    } else {
                        parent[parent2] = parent1;
                    }
                    ++count;
                    answer = Math.max(answer, edge.cost);
                }
            }


            sb.append(String.format("%.2f\n", answer));
        }

        System.out.printf(sb.toString());
    }

    public static int getParent(int[] parent, int n) {
        if (parent[n] == n) {
            return n;
        }

        return getParent(parent, parent[n]);
    }
}
