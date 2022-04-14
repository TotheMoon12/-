import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static class Matrix {
        final int row;
        final int col;

        public Matrix(final int row, final int col) {
            this.row = row;
            this.col = col;
        }
    }
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Matrix[][] multi = new Matrix[N][N];
        for (int i = 0; i < N; ++i) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());
            multi[i][i] = new Matrix(row, col);
        }

        int[][] dp = new int[N][N];
        for (int length = 1; length < N; ++length) {
            for (int start = 0; start + length < N; ++start) {
                int min = Integer.MAX_VALUE;
                int minMid = 0;
                int end = start + length;
                for (int mid = start; mid < end; ++mid) {
                    Matrix m1 = multi[start][mid];
                    Matrix m2 = multi[mid + 1][end];
                    int multiCount = m1.row * m1.col * m2.col + dp[start][mid] + dp[mid + 1][end];

                    if (multiCount <= min) {
                        min = multiCount;
                        minMid = mid;
                    }
                }

                multi[start][end] = new Matrix(multi[start][minMid].row, multi[minMid + 1][end].col);
                dp[start][end] = min;
            }
        }

        System.out.println(dp[0][N - 1]);
    }
}
