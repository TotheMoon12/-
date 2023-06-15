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
        int n;
        int weight;

        public Pair(int n, int weight) {
            this.n = n;
            this.weight = weight;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        ArrayList<Pair>[] map = new ArrayList[N + 1];
        for (int n = 1; n <= N; ++n) {
            map[n] = new ArrayList<>();
        }

        for (int m = 0; m < M; ++m) {
            st = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(st.nextToken());
            int n2 = Integer.parseInt(st.nextToken());
            int weight = Integer.parseInt(st.nextToken());

            map[n1].add(new Pair(n2, weight));
            map[n2].add(new Pair(n1, weight));
        }

        st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int dest = Integer.parseInt(st.nextToken());

        int left = 1;
        int right = 1000000000;
        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;

            Queue<Integer> queue = new LinkedList<>();
            boolean[] visited = new boolean[N + 1];
            visited[start] = true;
            queue.offer(start);
            boolean isPossible = false;
            while (!queue.isEmpty()) {
                int current = queue.poll();
                if (current == dest) {
                    isPossible = true;
                    break;
                }

                for (Pair next : map[current]) {
                    if (!visited[next.n]) {
                        if (next.weight >= mid) {
                            visited[next.n] = true;
                            queue.offer(next.n);
                        }
                    }
                }
            }

            if (isPossible) {
                answer = Math.max(answer, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
