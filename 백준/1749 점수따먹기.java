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

        int[][] prefixSum = new int[N + 1][M + 1];
        for (int n = 1; n <= N; ++n) {
            st = new StringTokenizer(br.readLine());
            for (int m = 1; m <= M; ++m) {
                int number = Integer.parseInt(st.nextToken());
                prefixSum[n][m] = prefixSum[n - 1][m] + prefixSum[n][m - 1] + number - prefixSum[n - 1][m - 1];
            }
        }

        int max = Integer.MIN_VALUE;
        for (int startN = 0; startN <= N; ++startN) {
            for (int startM = 0; startM <= M; ++startM) {
                for (int endN = startN + 1; endN <= N; ++endN) {
                    for (int endM = startM + 1; endM <= M; ++endM) {
                        int sum = prefixSum[endN][endM] - prefixSum[endN][startM] - prefixSum[startN][endM] + prefixSum[startN][startM];
                        max = Math.max(max, sum);
                    }
                }
            }
        }

        bw.write(String.valueOf(max));
        br.close();
        bw.close();
    }
}
