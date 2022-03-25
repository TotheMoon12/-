import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static long[][][] dp = new long[51][51][51];

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        for (int i = 0; i <= 50; ++i) {
            for (int j = 0; j <= 50; ++j) {
                Arrays.fill(dp[i][j], 1);
            }
        }

        while (true) {
            st = new StringTokenizer(br.readLine());

            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int c = Integer.parseInt(st.nextToken());

            if (a == -1 && b == -1 && c == -1) {
                break;
            }

            StringBuilder sb = new StringBuilder();
            sb.append("w(");
            sb.append(a);
            sb.append(", ");
            sb.append(b);
            sb.append(", ");
            sb.append(c);
            sb.append(") = ");
            sb.append(w(a,b,c));
            System.out.println(sb.toString());
        }
    }

    public static long w(int a, int b, int c) {
        if (a <= 0 || b <= 0 || c <= 0) {
            return 1;
        } else if (a > 20 || b > 20 || c > 20) {
            if (dp[20][20][20] == 1) {
                dp[20][20][20] = w(20, 20, 20);
            }

            return dp[20][20][20];
        } else if (a < b && b < c) {
            if (dp[a][b][c - 1] == 1) {
                dp[a][b][c - 1] = w(a, b, c - 1);
            }

            if (dp[a][b - 1][c - 1] == 1) {
                dp[a][b - 1][c - 1] = w(a, b - 1, c - 1);
            }

            if (dp[a][b - 1][c] == 1) {
                dp[a][b - 1][c] = w(a, b - 1, c);
            }

            return dp[a][b][c - 1] + dp[a][b - 1][c - 1] - dp[a][b - 1][c];
        } else {
            if (dp[a - 1][b][c] == 1) {
                dp[a - 1][b][c] = w(a - 1, b, c);
            }

            if (dp[a - 1][b - 1][c] == 1) {
                dp[a - 1][b - 1][c] = w(a - 1, b - 1, c);
            }

            if (dp[a - 1][b][c - 1] == 1) {
                dp[a - 1][b][c - 1] = w(a - 1, b, c - 1);
            }

            if (dp[a - 1][b - 1][c - 1] == 1) {
                dp[a - 1][b - 1][c - 1] = w(a - 1, b - 1, c - 1);
            }

            return dp[a - 1][b][c] + dp[a - 1][b - 1][c] + dp[a - 1][b][c - 1] - dp[a - 1][b - 1][c - 1];
        }
    }
}
