import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static class Move {
        int index;
        int count;

        public Move(int index, int count) {
            this.index = index;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int T = Integer.parseInt(st.nextToken());

        int[][] points = new int[N + 1][2];
        for (int n = 1; n <= N; ++n) {
            st = new StringTokenizer(br.readLine());
            points[n][0] = Integer.parseInt(st.nextToken());
            points[n][1] = Integer.parseInt(st.nextToken());
        }

        ArrayList<Integer>[] map = new ArrayList[N + 1];
        for (int n = 0; n <= N; ++n) {
            map[n] = new ArrayList<>();
        }

        Arrays.sort(points, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                if (o1[1] == o2[1]) {
                    return o1[0] - o2[0];
                }

                return o1[1] - o2[1];
            }
        });

        for (int n = 0; n < N; ++n) {
            int x = points[n][0];
            int y = points[n][1];

            for (int next = n + 1; next <= N; ++next) {
                int nextX = points[next][0];
                int nextY = points[next][1];

                if (Math.abs(nextX - x) > 2 || y - nextY > 2) {
                    continue;
                }

                if (nextY - y > 2) {
                    break;
                }

                map[n].add(next);
                map[next].add(n);
            }
        }

        int answer = -1;
        Queue<Move> queue = new LinkedList<>();
        queue.offer(new Move(0, 0));
        boolean[] visited = new boolean[N + 1];

        while (!queue.isEmpty()) {
            Move move = queue.poll();

            int y = points[move.index][1];
            if (y == T) {
                answer = move.count;
                break;
            }

            for (int next : map[move.index]) {
                if (!visited[next]) {
                    queue.offer(new Move(next, move.count + 1));
                    visited[next] = true;
                }
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
