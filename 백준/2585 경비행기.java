import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static class Point {
        final int x;
        final int y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static class Move {
        final int node;
        final int count;

        public Move(int node, int count) {
            this.node = node;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int K = Integer.parseInt(st.nextToken());

        Point[] points = new Point[N + 2];
        points[0] = new Point(0, 0);
        points[N + 1] = new Point(10000, 10000);

        for (int n = 1; n <= N; ++n) {
            st = new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());
            points[n] = new Point(x, y);
        }

        int[][] fuelArr = new int[N + 2][N + 2];
        for (int n = 0; n <= N; ++n) {
            for (int pair = n + 1; pair <= N + 1; ++pair) {
                int fuel = (int) Math.ceil(Math.sqrt(Math.pow(points[n].x - points[pair].x, 2) + Math.pow(points[n].y - points[pair].y, 2)) / 10);
                fuelArr[n][pair] = fuel;
                fuelArr[pair][n] = fuel;
            }
        }

        int left = 1;
        int right = (int) Math.ceil(Math.sqrt(Math.pow(points[0].x - points[N + 1].x, 2) + Math.pow(points[0].y - points[N + 1].y, 2)) / 10);
        int answer = right;
        while (left <= right) {
            int mid = (left + right) / 2;

            Queue<Move> queue = new LinkedList<>();
            boolean[] visited = new boolean[N + 2];
            visited[0] = true;
            for (int n = 1; n <= N + 1; ++n) {
                int fuel = fuelArr[0][n];

                if (fuel <= mid) {
                    queue.offer(new Move(n, 1));
                    visited[n] = true;
                }
            }

            boolean isPossible = false;
            while (!queue.isEmpty()) {
                Move move = queue.poll();

                if (move.node == N + 1) {
                    isPossible = true;
                    break;
                }

                for (int n = 1; n <= N + 1; ++n) {
                    if (!visited[n] && n != move.node && fuelArr[move.node][n] <= mid && move.count <= K) {
                        visited[n] = true;
                        queue.offer(new Move(n, move.count + 1));
                    }
                }
            }

            if (isPossible) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
