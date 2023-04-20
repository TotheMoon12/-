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
        final int K = Integer.parseInt(st.nextToken());

        int[][] blackStartPrefixSum = new int[N + 1][M + 1];
        int blackStartTurn = 0;
        final int BLACK = 0;
        final int WHITE = 1;

        for (int n = 1; n <= N; ++n) {
            String s = br.readLine();


            for (int m = 1; m <= M; ++m) {
                int type = BLACK;
                if (s.charAt(m - 1) == 'W') {
                    type = WHITE;
                }

                blackStartPrefixSum[n][m] += blackStartPrefixSum[n - 1][m] + blackStartPrefixSum[n][m - 1] - blackStartPrefixSum[n - 1][m - 1];
                if (blackStartTurn != type) {
                    ++blackStartPrefixSum[n][m];
                }

                blackStartTurn = (blackStartTurn + 1) % 2;
            }

            if (M % 2 == 0) {
                blackStartTurn = (blackStartTurn + 1) % 2;
            }
        }

        int min = Integer.MAX_VALUE;
        int square = K * K;
        for (int n = K; n <= N; ++n) {
            for (int m = K; m <= M; ++m) {
                int blackStartCount = blackStartPrefixSum[n][m] - blackStartPrefixSum[n - K][m] - blackStartPrefixSum[n][m - K] + blackStartPrefixSum[n - K][m - K];
                int whiteStartCount = square - blackStartCount; // 색이 무조건 반전되므로 k*k에서 black으로 시작되었을 때의 수정개수를 빼면된다

                min = Math.min(min, blackStartCount);
                min = Math.min(min, whiteStartCount);
            }
        }

        bw.write(String.valueOf(min));
        br.close();
        bw.close();
    }
}
