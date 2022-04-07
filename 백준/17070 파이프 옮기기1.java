import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][] house = new int[N][N];
        StringTokenizer st;
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; ++j) {
                house[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int[][][] dp = new int[N][N][3];
        final int BLANK = 0;
        final int HORIZON = 0;
        final int VERTICAL = 1;
        final int DIAGONAL = 2;
        dp[0][1][HORIZON] = 1;

        for (int i = 0; i < N; ++i) {
            for (int j = 0; j < N; ++j) {
                // 가로
                if (j < N - 1) {
                    if (house[i][j + 1] == BLANK) {
                        dp[i][j + 1][HORIZON] += dp[i][j][HORIZON];
                    }

                    if (i < N - 1 && house[i][j + 1] == BLANK && house[i + 1][j] == BLANK
                    && house[i + 1][j + 1] == BLANK) {
                        dp[i + 1][j + 1][DIAGONAL] += dp[i][j][HORIZON];
                    }
                }

                // 세로
                if (i < N - 1) {
                    if (house[i + 1][j] == BLANK) {
                        dp[i + 1][j][VERTICAL] += dp[i][j][VERTICAL];
                    }

                    if (j < N - 1 && house[i][j + 1] == BLANK && house[i + 1][j] == BLANK
                    && house[i + 1][j + 1] == BLANK) {
                        dp[i + 1][j + 1][DIAGONAL] += dp[i][j][VERTICAL];
                    }
                }

                // 대각
                if (j < N - 1 && house[i][j + 1] == BLANK) {
                    dp[i][j + 1][HORIZON] += dp[i][j][DIAGONAL];
                }

                if (i < N - 1) {
                    if (house[i + 1][j] == BLANK) {
                        dp[i + 1][j][VERTICAL] += dp[i][j][DIAGONAL];
                    }

                    if (j < N - 1 && house[i][j + 1] == BLANK  && house[i + 1][j] == BLANK
                    && house[i + 1][j + 1] == BLANK) {
                        dp[i + 1][j + 1][DIAGONAL] += dp[i][j][DIAGONAL];
                    }
                }
            }
        }

        System.out.println(dp[N - 1][N - 1][HORIZON] + dp[N - 1][N - 1][VERTICAL] + dp[N - 1][N - 1][DIAGONAL]);
    }
}
