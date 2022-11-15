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

    public static class Pipe implements Comparable<Pipe> {
        int number1;
        int number2;
        int cost;

        public Pipe(int number1, int number2, int cost) {
            this.number1 = number1;
            this.number2 = number2;
            this.cost = cost;
        }

        @Override
        public int compareTo(Pipe o) {
            return this.cost - o.cost;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int C = Integer.parseInt(st.nextToken());

        int[] parent = new int[N];
        Point[] fields = new Point[N];
        for (int idx = 0; idx < N; ++idx) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            fields[idx] = new Point(x, y);
            parent[idx] = idx;
        }

        PriorityQueue<Pipe> pq = new PriorityQueue<>();
        for (int idx = 0; idx < N; ++idx) {
            Point field1 = fields[idx];
            for (int sIdx = idx + 1; sIdx < N; ++sIdx) {
                Point field2 = fields[sIdx];

                int cost = (int)(Math.pow(field1.x - field2.x, 2) + Math.pow(field1.y - field2.y, 2));
                if (cost >= C) {
                    pq.offer(new Pipe(idx, sIdx, cost));
                }
            }
        }

        int count = 0;
        int cost = 0;
        while (!pq.isEmpty()) {
            Pipe p = pq.poll();

            int parent1 = getParent(parent, p.number1);
            int parent2 = getParent(parent, p.number2);

            if (parent1 != parent2) {
                if (parent1 < parent2) {
                    parent[parent2] = parent1;
                } else {
                    parent[parent1] = parent2;
                }

                ++count;
                cost += p.cost;
            }
        }

        if (count == N - 1) {
            System.out.println(cost);
        } else {
            System.out.println(-1);
        }
        
        br.close();
    }

    public static int getParent(int[] parent, int n) {
        if (parent[n] == n) {
            return n;
        }

        return getParent(parent, parent[n]);
    }
}
