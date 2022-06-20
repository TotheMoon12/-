import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static class Pair implements Comparable<Pair> {
        int number;
        int slope;

        public Pair(int number, int slope) {
            this.number = number;
            this.slope = slope;
        }

        @Override
        public int compareTo(Pair o) {
            return this.slope - o.slope;
        }
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] heightInfo = new int[N][N];
        ArrayList<Pair>[] map = new ArrayList[N * N];
        for (int idx = 0; idx < N * N; ++idx) {
            map[idx] = new ArrayList<>();
        }

        for (int idx = 0; idx < N; ++idx) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int sIdx = 0; sIdx < N; ++sIdx) {
                int height = Integer.parseInt(st.nextToken());

                heightInfo[idx][sIdx] = height;
            }
        }

        for (int idx = 1; idx < N; ++idx) {
            int horizonSlope = Math.abs(heightInfo[0][idx] - heightInfo[0][idx - 1]);
            map[idx].add(new Pair(idx - 1, horizonSlope));
            map[idx - 1].add(new Pair(idx, horizonSlope));

            int verticalSlope = Math.abs(heightInfo[idx][0] - heightInfo[idx - 1][0]);
            int number = N * idx;
            int up = number - N;
            map[number].add(new Pair(up, verticalSlope));
            map[up].add(new Pair(number, verticalSlope));
        }

        for (int idx = 1; idx < N; ++idx) {
            for (int sIdx = 1; sIdx < N; ++sIdx) {
                int number = N * idx + sIdx;

                int horizonSlope = Math.abs(heightInfo[idx][sIdx] - heightInfo[idx][sIdx - 1]);
                int left = number - 1;
                map[number].add(new Pair(left, horizonSlope));
                map[left].add(new Pair(number, horizonSlope));

                int verticalSlope = Math.abs(heightInfo[idx][sIdx] - heightInfo[idx - 1][sIdx]);
                int up = number - N;
                map[number].add(new Pair(up, verticalSlope));
                map[up].add(new Pair(number, verticalSlope));
            }
        }

        Queue<Integer> q = new LinkedList<>();
        int start = 0;
        q.offer(start);
        boolean[] visited = new boolean[N * N];
        visited[start] = true;

        PriorityQueue<Pair> pq = new PriorityQueue<>();
        int end = N * N - 1;
        long maxSlope = 0;
        while (!q.isEmpty() && !visited[end]) {
            int cur = q.poll();

            for (Pair next : map[cur]) {
                if (!visited[next.number]) {
                    pq.offer(next);
                }
            }

            while (!pq.isEmpty()) {
                Pair next = pq.poll();

                if (!visited[next.number]) {
                    visited[next.number] = true;
                    q.offer(next.number);
                    maxSlope = Math.max(maxSlope, next.slope);
                    break;
                }
            }
        }

        System.out.println(maxSlope);
    }
}
