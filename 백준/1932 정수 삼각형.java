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

        final int N = Integer.parseInt(br.readLine());
        int[][] triangle = new int[N + 1][N + 1];
        for (int n = 1; n <= N; ++n) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int i = 1; i <= n; ++i) {
                triangle[n][i] = Integer.parseInt(st.nextToken());
                triangle[n][i] += Math.max(triangle[n - 1][i - 1], triangle[n - 1][i]);
            }
        }

        int answer = 0;
        for (int n = 1; n <= N; ++n) {
            answer = Math.max(answer, triangle[N][n]);
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
