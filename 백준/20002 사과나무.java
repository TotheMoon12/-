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
        int[][] prefixSum = new int[N + 1][N + 1];

        for (int r = 1; r <= N; ++r) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= N; ++c) {
                prefixSum[r][c] = prefixSum[r - 1][c] + prefixSum[r][c - 1] - prefixSum[r - 1][c - 1]
                        + Integer.parseInt(st.nextToken());
            }
        }

        int max = -300000;
        for (int k = 1; k <= N; ++k) {
            for (int prevR = 0; prevR <= N - k; ++prevR) {
                for (int prevC = 0; prevC <= N - k; ++prevC) {
                    int r = prevR + k;
                    int c = prevC + k;
                    int sum = prefixSum[r][c] + prefixSum[prevR][prevC] - prefixSum[r][prevC] - prefixSum[prevR][c];
                    max = Math.max(max, sum);
                }
            }
        }

        bw.write(String.valueOf(max));
        br.close();
        bw.close();
    }
}
