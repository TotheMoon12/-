import java.io.*;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main2 {
    public static class Node implements Comparable<Node> {
        int n;
        int cost;

        public Node(int n, int cost) {
            this.n = n;
            this.cost = cost;
        }

        @Override
        public int compareTo(Node o) {
            return this.cost - o.cost;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());
        final int M = Integer.parseInt(br.readLine());

        final int INF = Integer.MAX_VALUE;
        int[][] busMap = new int[N + 1][N + 1];
        for (int n = 1; n <= N; ++n) {
            Arrays.fill(busMap[n], INF);
        }

        for (int m = 0; m < M; ++m) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int src = Integer.parseInt(st.nextToken());
            int dest = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            busMap[src][dest] = Math.min(busMap[src][dest], cost);
        }

        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int end = Integer.parseInt(st.nextToken());

        PriorityQueue<Node> pq = new PriorityQueue<>();
        for (int n = 1; n <= N; ++n) {
            if (busMap[start][n] != INF) {
                pq.offer(new Node(n, busMap[start][n]));
            }
        }

        int answer = 0;
        boolean[] visited = new boolean[N + 1];
        visited[start] = true;
        while (!pq.isEmpty()) {
            Node node = pq.poll();

            if (visited[node.n]) {
                continue;
            }

            visited[node.n] = true;
            if (node.n == end) {
                answer = node.cost;
                break;
            }

            for (int next = 1; next <= N; ++next) {
                int cost = busMap[node.n][next];
                if (!visited[next] && cost != INF) {
                    pq.offer(new Node(next, cost + node.cost));
                }
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
