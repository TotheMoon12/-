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

        boolean[][] visited = new boolean[N + 1][N + 1];
        ArrayList<Integer>[] children = new ArrayList[N + 1];
        for (int n = 1; n <= N; ++n) {
            children[n] = new ArrayList<>();
        }

        int[] degree = new int[N + 1];
        for (int m = 0; m < M; ++m) {
            st = new StringTokenizer(br.readLine());
            int singerNum = Integer.parseInt(st.nextToken());
            ArrayList<Integer> parentList = new ArrayList<>();
            while (singerNum-- > 0) {
                int number = Integer.parseInt(st.nextToken());
                for (int parent : parentList) {
                    if (!visited[parent][number]) {
                        children[parent].add(number);
                        visited[parent][number] = true;
                        ++degree[number];
                    }
                }

                parentList.add(number);
            }
        }

        Queue<Integer> queue = new LinkedList<>();
        for (int n = 1; n <= N; ++n) {
            if (degree[n] == 0) {
                queue.offer(n);
            }
        }

        StringBuilder builder = new StringBuilder();
        int count = 0;
        while (!queue.isEmpty()) {
            int number = queue.poll();
            builder.append(number);
            builder.append(System.lineSeparator());
            ++count;

            for (int child : children[number]) {
                --degree[child];
                if (degree[child] == 0) {
                    queue.offer(child);
                }
            }
        }

        if (count == N) {
            bw.write(builder.toString());
        } else {
            bw.write("0");
        }

        br.close();
        bw.close();
    }
}
