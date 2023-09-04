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
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        ArrayList<Integer>[] parents = new ArrayList[N + 1];
        ArrayList<Integer>[] children = new ArrayList[N + 1];
        for (int n = 1; n <= N; ++n) {
            parents[n] = new ArrayList<>();
            children[n] = new ArrayList<>();
        }


        for (int m = 0; m < M; ++m) {
            st = new StringTokenizer(br.readLine());
            int parent = Integer.parseInt(st.nextToken());
            int child = Integer.parseInt(st.nextToken());

            parents[child].add(parent);
            children[parent].add(child);
        }

        int[] parentCount = new int[N + 1];
        Queue<Integer> queue = new LinkedList<>();
        for (int n = 1; n <= N; ++n) {
            parentCount[n] = parents[n].size();
            if (parents[n].isEmpty()) {
                queue.offer(n);
            }
        }

        StringBuilder builder = new StringBuilder();
        while (!queue.isEmpty()) {
            int number = queue.poll();
            builder.append(number);
            builder.append(" ");

            for (int child : children[number]) {
                --parentCount[child];
                if (parentCount[child] == 0) {
                    queue.offer(child);
                }
            }
        }

        bw.write(builder.toString());
        br.close();
        bw.close();
    }
}
