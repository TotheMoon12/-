import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static class Pair {
        int v;
        int prev;
        int distance;

        public Pair(int v, int prev, int distance) {
            this.v = v;
            this.prev = prev;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int V = Integer.parseInt(br.readLine());
        ArrayList<Pair>[] map = new ArrayList[V + 1];

        for (int v = 1; v <= V; ++v) {
            map[v] = new ArrayList<>();
        }

        int[] visited = new int[V + 1];
        int[][] info = new int[V + 1][2];
        final int DISTANCE = 0;
        final int PREV = 1;

        Queue<Pair> queue = new LinkedList<>();
        for (int v = 0; v < V; ++v) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int current = Integer.parseInt(st.nextToken());
            int other = Integer.parseInt(st.nextToken());
            while (other != -1) {
                int distance = Integer.parseInt(st.nextToken());
                map[current].add(new Pair(other, -1, distance));

                other = Integer.parseInt(st.nextToken());
            }

            visited[current] = map[current].size();
            if (map[current].size() == 1) {
                visited[current] = -1;
                queue.add(new Pair(current, -1, 0));
            }
        }

        int answer = 0;
        while (!queue.isEmpty()) {
            Pair pair = queue.poll();

            for (Pair next : map[pair.v]) {
                if (pair.prev == next.v) {
                    continue;
                }

                int distance = info[next.v][DISTANCE] + pair.distance + next.distance;
                if (distance > answer) {
                    answer = distance;
                }

                distance = pair.distance + next.distance;
                if (distance > info[next.v][DISTANCE]) {
                    info[next.v][DISTANCE] = distance;
                    info[next.v][PREV] = pair.v;

                }

                --visited[next.v];

                if (visited[next.v] == 1) {
                    queue.offer(new Pair(next.v, info[next.v][PREV], info[next.v][DISTANCE]));
                }
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
