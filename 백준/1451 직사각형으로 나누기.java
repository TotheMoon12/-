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
            String numbers = br.readLine();
            int numbersIndex = 0;
            for (int m = 1; m <= M; ++m) {
                prefixSum[n][m] = (numbers.charAt(numbersIndex++) - '0') + prefixSum[n - 1][m]
                        + prefixSum[n][m - 1] - prefixSum[n - 1][m - 1];
            }
        }

        long max = 0L;
        long total = prefixSum[N][M];
        for (int r = 1; r < N; ++r) {
            for (int c = 1; c < M; ++c) {
                long rect1 = prefixSum[r][c];
                long rect2 = prefixSum[N][c] - rect1;
                long rect3 = total - rect1 - rect2;
                long result = rect1 * rect2 * rect3;
                max = Math.max(max, result);

                rect2 = prefixSum[r][M] - rect1;
                rect3 = total - rect1 - rect2;
                result = rect1 * rect2 * rect3;
                max = Math.max(max, result);
            }

            // rect1이 열끝까지 차지할 때
            long rect1 = prefixSum[r][M];
            
            // 열로 나눌 때
            for (int c = 1; c < M; ++c) {
                long rect2 = prefixSum[N][c] - prefixSum[r][c];
                long rect3 = total - rect1 - rect2;
                long result = rect1 * rect2 * rect3;
                max = Math.max(max, result);
            }

            // 행으로 나눌 때
            for (int rect2Row = r + 1; rect2Row < N; ++rect2Row) {
                long rect2 = prefixSum[rect2Row][M] - rect1;
                long rect3 = total - rect1 - rect2;
                long result = rect1 * rect2 * rect3;
                max = Math.max(max, result);
            }
        }

        for (int rect1Col = 1; rect1Col < M; ++rect1Col) {
            long rect1 = prefixSum[N][rect1Col];

            for (int r = 1; r < N; ++r) {
                long rect2 = prefixSum[r][M] - prefixSum[r][rect1Col];
                long rect3 = total - rect1 - rect2;
                long result = rect1 * rect2 * rect3;
                max = Math.max(max, result);
            }

            for (int c = rect1Col + 1; c < M; ++c) {
                long rect2 = prefixSum[N][c] - rect1;
                long rect3 = total - rect1 - rect2;
                long result = rect1 * rect2 * rect3;
                max = Math.max(max, result);
            }
        }

        bw.write(String.valueOf(max));
        br.close();
        bw.close();
    }
}
