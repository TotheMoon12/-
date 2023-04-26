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
        final int M = Integer.parseInt(st.nextToken());
        final int N = Integer.parseInt(st.nextToken());
        final int K = Integer.parseInt(br.readLine());

        int[][] jungleSum = new int[M + 1][N + 1];
        int[][] oceanSum = new int[M + 1][N + 1];
        int[][] iceSum = new int[M + 1][N + 1];

        for (int m = 1; m <= M; ++m) {
            String s = br.readLine();
            for (int n = 1; n <= N; ++n) {
                char type = s.charAt(n - 1);
                if (type == 'J') {
                    ++jungleSum[m][n];
                } else if (type == 'O') {
                    ++oceanSum[m][n];
                } else {
                    ++iceSum[m][n];
                }

                jungleSum[m][n] += jungleSum[m - 1][n] + jungleSum[m][n - 1] - jungleSum[m - 1][n - 1];
                oceanSum[m][n] += oceanSum[m - 1][n] + oceanSum[m][n - 1] - oceanSum[m - 1][n - 1];
                iceSum[m][n] += iceSum[m - 1][n] + iceSum[m][n - 1] - iceSum[m - 1][n - 1];
            }
        }

        StringBuilder builder = new StringBuilder();
        for (int k = 0; k < K; ++k) {
            StringTokenizer coordinateST = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(coordinateST.nextToken()) - 1;
            int y1 = Integer.parseInt(coordinateST.nextToken()) - 1;
            int x2 = Integer.parseInt(coordinateST.nextToken());
            int y2 = Integer.parseInt(coordinateST.nextToken());

            int jungleCount = jungleSum[x2][y2] - jungleSum[x2][y1] - jungleSum[x1][y2] + jungleSum[x1][y1];
            int oceanCount = oceanSum[x2][y2] - oceanSum[x2][y1] - oceanSum[x1][y2] + oceanSum[x1][y1];
            int iceCount = iceSum[x2][y2] - iceSum[x2][y1] - iceSum[x1][y2] + iceSum[x1][y1];

            builder.append(jungleCount);
            builder.append(' ');
            builder.append(oceanCount);
            builder.append(' ');
            builder.append(iceCount);
            builder.append(System.lineSeparator());
        }

        bw.write(builder.toString());
        br.close();
        bw.close();
    }
}
