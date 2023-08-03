import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static class Move {
        int n;
        int passCount;
        int maxCost;

        public Move(int n, int passCount, int maxCost) {
            this.n = n;
            this.passCount = passCount;
            this.maxCost = maxCost;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int P = Integer.parseInt(st.nextToken());
        final int K = Integer.parseInt(st.nextToken());

        int[][] map = new int[N + 1][N + 1];

        int maxCost = 0;
        for (int p = 0; p < P; ++p) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());

            maxCost = Math.max(maxCost, cost);
            map[n1][n2] = cost;
            map[n2][n1] = cost;
        }

        int answer = Integer.MAX_VALUE;
        int[][] visited = new int[N + 1][K + 1];
        for (int n = 1; n <= N; ++n) {
            Arrays.fill(visited[n], Integer.MAX_VALUE);
        }

        Queue<Move> queue = new LinkedList<>();
        for (int n = 2; n <= N; ++n) {
            if (map[1][n] != 0) {
                queue.offer(new Move(n, 0, map[1][n]));
                visited[n][0] = map[1][n];

                if (K > 0) {
                    queue.offer(new Move(n, 1, 0));
                    visited[n][1] = 0;
                }
            }
        }

        while (!queue.isEmpty()) {
            Move move = queue.poll();

            if (move.n == N) {
                answer = Math.min(answer, move.maxCost);
            }

            for (int n = 2; n <= N; ++n) {
                if (move.n == n) {
                    continue;
                }

                int cost = map[move.n][n];
                if (cost != 0) {
                    int max = Math.max(move.maxCost, cost);
                    if (max < visited[n][move.passCount]) {
                        queue.offer(new Move(n, move.passCount, Math.max(move.maxCost, cost)));
                        visited[n][move.passCount] = max;
                    }

                    if (move.passCount < K && move.maxCost < visited[n][move.passCount + 1]) {
                        visited[n][move.passCount + 1] = move.maxCost;
                        queue.offer(new Move(n, move.passCount + 1, move.maxCost));
                    }
                }
            }
        }

        if (answer == Integer.MAX_VALUE) {
            answer = -1;
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
