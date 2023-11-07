import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int T = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        for (int t = 0; t < T; ++t) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            final int N = Integer.parseInt(st.nextToken());
            final int M = Integer.parseInt(st.nextToken());
            final int W = Integer.parseInt(st.nextToken());

            final int INF = 1000000000;
            int[][] map = new int[N + 1][N + 1];
            for (int n = 1; n <= N; ++n) {
                Arrays.fill(map[n], INF);
            }

            for (int m = 0; m < M; ++m) {
                st = new StringTokenizer(br.readLine());
                final int n1 = Integer.parseInt(st.nextToken());
                final int n2 = Integer.parseInt(st.nextToken());
                final int time = Integer.parseInt(st.nextToken());

                map[n1][n2] = Math.min(map[n1][n2], time);
                map[n2][n1] = Math.min(map[n2][n1], time);
            }

            for (int w = 0; w < W; ++w) {
                st = new StringTokenizer(br.readLine());
                final int n1 = Integer.parseInt(st.nextToken());
                final int n2 = Integer.parseInt(st.nextToken());
                final int time = -Integer.parseInt(st.nextToken());

                map[n1][n2] = Math.min(map[n1][n2], time);
            }

            for (int mid = 1; mid <= N; ++mid) {
                for (int start = 1; start <= N; ++start) {
                    if (map[start][mid] == INF) {
                        continue;
                    }

                    for (int dest = 1; dest <= N; ++dest) {
                        if (map[mid][dest] == INF) {
                            continue;
                        }

                        int distance = map[start][mid] + map[mid][dest];
                        if (distance < map[start][dest]) {
                            map[start][dest] = distance;
                        }
                    }
                }
            }

            String answer = "NO";
            for (int n = 1; n <= N; ++n) {
                if (map[n][n] < 0) {
                    answer = "YES";
                    break;
                }
            }

            builder.append(answer).append(System.lineSeparator());
        }

        bw.write(builder.toString());
        br.close();
        bw.close();
    }
}
