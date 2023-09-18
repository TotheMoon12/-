import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        int[] degree = new int[N + 1];
        ArrayList<Integer>[] children = new ArrayList[N + 1];
        for (int n = 1; n <= N; ++n) {
            children[n] = new ArrayList<>();
        }

        for (int m = 0; m < M; ++m) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            children[parent].add(child);
            ++degree[child];
        }

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int n = 1; n <= N; ++n) {
            if (degree[n] == 0) {
                pq.offer(n);
            }
        }

        StringBuilder builder = new StringBuilder();
        while (!pq.isEmpty()) {
            int current = pq.poll();
            builder.append(current);
            builder.append(" ");

            for (int child : children[current]) {
                --degree[child];
                if (degree[child] == 0) {
                    pq.offer(child);
                }
            }
        }

        bw.write(builder.toString());
        br.close();
        bw.close();
    }
}
