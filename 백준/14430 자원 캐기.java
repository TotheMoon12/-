import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int n = 0; n < N; ++n) {
            st = new StringTokenizer(br.readLine());

            for (int m = 0; m < M; ++m) {
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        for (int n = 1; n < N; ++n) {
            map[n][0] += map[n - 1][0];
        }

        for (int m = 1; m < M; ++m) {
            map[0][m] += map[0][m - 1];
        }

        for (int n = 1; n < N; ++n) {
            for (int m = 1; m < M; ++m) {
                map[n][m] += Math.max(map[n - 1][m], map[n][m - 1]);
            }
        }

        bw.write(String.valueOf(map[N - 1][M - 1]));
        br.close();
        bw.close();
    }
}
