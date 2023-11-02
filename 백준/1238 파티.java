import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());
        final int X = Integer.parseInt(st.nextToken()) - 1;

        int[][] map = new int[N][N];
        final int INF = Integer.MAX_VALUE;
        for (int n = 0; n < N; ++n) {
            Arrays.fill(map[n], INF);
            map[n][n] = 0;
        }

        for (int m = 0; m < M; ++m) {
            StringTokenizer infoST = new StringTokenizer(br.readLine());
            int n1 = Integer.parseInt(infoST.nextToken()) - 1;
            int n2 = Integer.parseInt(infoST.nextToken()) - 1;
            int t = Integer.parseInt(infoST.nextToken());

            map[n1][n2] = t;
        }

        for (int mid = 0; mid < N; ++mid) {
            for (int start = 0; start < N; ++start) {
                if (map[start][mid] == INF) {
                    continue;
                }

                for (int dest = 0; dest < N; ++dest) {
                    if (map[mid][dest] != INF && map[start][mid] + map[mid][dest] < map[start][dest]) {
                        map[start][dest] = map[start][mid] + map[mid][dest];
                    }
                }
            }
        }

        int answer = 0;
        for (int n = 0; n < N; ++n) {
            if (X == n) {
                continue;
            }

            answer = Math.max(answer, map[n][X] + map[X][n]);
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
