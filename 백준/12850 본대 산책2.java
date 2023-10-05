import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static int MOD = 1000000007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final int D = Integer.parseInt(br.readLine());
        long[][] map = {
                {0, 1, 1, 0, 0, 0, 0, 0},
                {1, 0, 1, 1, 0, 0, 0, 0},
                {1, 1, 0, 1, 1, 0, 0, 0},
                {0, 1, 1, 0, 1, 1, 0, 0},
                {0, 0, 1, 1, 0, 1, 1, 0},
                {0, 0, 0, 1, 1, 0, 0, 1},
                {0, 0, 0, 0, 1, 0, 0, 1},
                {0, 0, 0, 0, 0, 1, 1, 0}
        };

        long[][] matrix = pow(map, D);
        bw.write(String.valueOf(matrix[0][0]));
        br.close();
        bw.close();
    }

    public static long[][] pow(long[][] matrix, int n) {
        if (n == 1) {
            return matrix;
        }

        long[][] nextMatrix = pow(matrix, n / 2);
        if (n % 2 == 0) {
            return multiply(nextMatrix, nextMatrix);
        } else {
            return multiply(multiply(matrix, nextMatrix), nextMatrix);
        }
    }

    public static long[][] multiply(long[][] m1, long[][] m2) {
        long[][] out = new long[m1.length][m2[0].length];
        for (int r1 = 0; r1 < m1.length; ++r1) {
            for (int c2 = 0; c2 < m2.length; ++c2) {
                long sum = 0;
                int r2 = 0;
                int c1 = 0;

                while (r2 < m2.length) {
                    sum += m1[r1][c1++] * m2[r2++][c2];
                    sum %= MOD;
                }


                out[r1][c2] = sum;
            }
        }

        return out;
    }
}
