import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static class Edge {
        int dest;
        long distance;

        public Edge(int dest, long distance) {
            this.dest = dest;
            this.distance = distance;
        }
    }

    public static class Move implements Comparable<Move> {
        int node;
        int pavementCount;
        long distance;

        public Move(int node, int pavementCount, long distance) {
            this.node = node;
            this.pavementCount = pavementCount;
            this.distance = distance;
        }

        @Override
        public int compareTo(Move o) {
            if (this.distance < o.distance) {
                return -1;
            } else if (this.distance > o.distance) {
                return 1;
            } else {
                return 0;
            }
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        ArrayList<Edge>[] map = new ArrayList[N + 1];
        for (int n = 1; n <= N; ++n) {
            map[n] = new ArrayList<>();
        }

        for (int m = 0; m < M; ++m) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int distance = Integer.parseInt(st.nextToken());

            map[n1].add(new Edge(n2, distance));
            map[n2].add(new Edge(n1, distance));
        }


        long[][] distances = new long[N + 1][K + 1];
        for (int n = 1; n <= N; ++n) {
            for (int k = 0; k <= K; ++k) {
                distances[n][k] = Long.MAX_VALUE;
            }
        }

        PriorityQueue<Move> pq = new PriorityQueue<>();
        pq.offer(new Move(1, 0, 0));
        distances[1][0] = 0;

        while (!pq.isEmpty()) {
            Move move = pq.poll();
            int node = move.node;
            long distance = move.distance;

            if (distance > distances[node][move.pavementCount]) {
                continue;
            }

            for (int idx = 0; idx < map[node].size(); ++idx) {
                Edge edge = map[node].get(idx);
                
                if (move.pavementCount < K && distance < distances[edge.dest][move.pavementCount + 1]) {
                    distances[edge.dest][move.pavementCount + 1] = distance;
                    if (edge.dest != N) {
                        pq.offer(new Move(edge.dest, move.pavementCount + 1, distance));
                    }
                }
                
                if (distance + edge.distance < distances[edge.dest][move.pavementCount]) {
                    distances[edge.dest][move.pavementCount] = distance + edge.distance;
                    if (edge.dest != N) {
                        pq.offer(new Move(edge.dest, move.pavementCount, distance + edge.distance));
                    }
                }
            }
        }

        long answer = Long.MAX_VALUE;
        for (int k = 1; k <= K; ++k) {
            answer = Math.min(answer, distances[N][k]);
        }

        System.out.print(answer);
        br.close();
    }
}
