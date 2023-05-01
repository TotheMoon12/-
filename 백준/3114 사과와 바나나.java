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
        final int R = Integer.parseInt(st.nextToken());
        final int C = Integer.parseInt(st.nextToken());

        int[][] bananaMap = new int[R + 1][C + 1];
        int[][] appleMap = new int[R + 1][C + 1];

        for (int r = 1; r <= R; ++r) {
            st = new StringTokenizer(br.readLine());
            for (int c = 1; c <= C; ++c) {
                String s = st.nextToken();
                char type = s.charAt(0);
                int count = Integer.parseInt(s.substring(1));

                if (type == 'B') {
                    bananaMap[r][c] = count;
                } else {
                    appleMap[r][c] = count;
                }
            }
        }

        int[][] bananaSum = new int[R + 1][C + 1];
        int[][] appleSum = new int[R + 1][C + 1];
        for (int c = 1; c <= C; ++c) {
            for (int r = 1; r <= R; ++r) {
                bananaSum[r][c] = bananaMap[r][c] + bananaSum[r - 1][c];
            }
        }

        for (int c = C; c >= 1; --c) {
            for (int r = R; r >= 1; --r) {
                appleSum[r][c] += appleMap[r][c];
                appleSum[r - 1][c] += appleSum[r][c];
            }
        }

        int[][] sum = new int[R + 1][C + 1];
        for (int c = 1; c <= C; ++c) {
            sum[1][c] = sum[1][c - 1] + appleSum[2][c];
        }

        for (int r = 2; r <= R; ++r) {
            sum[r][1] = sum[r - 1][1] - appleMap[r][1];
            for (int c = 2; c <= C; ++c) {
                int up = sum[r - 1][c] - appleMap[r][c];
                int left = sum[r][c - 1] + bananaSum[r - 1][c];
                int diagonal = sum[r - 1][c - 1] + bananaSum[r - 1][c];

                if (r < R) {
                    left += appleSum[r + 1][c];
                    diagonal += appleSum[r + 1][c];
                }

                sum[r][c] = Math.max(up, Math.max(left, diagonal));
            }
        }

        bw.write(String.valueOf(sum[R][C]));
        br.close();
        bw.close();
    }
}
