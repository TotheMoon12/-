import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(br.readLine());

        int[][] map = new int[N][N];
        for (int row = 0; row < N; ++row) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            for (int col = 0; col < N; ++col) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        final int BLANK = 0;
        final int WALL = 1;
        final int DIRECTION_NUM = 3;
        final int HORIZON = 0;
        final int VERTICAL = 1;
        final int DIAGONAL = 2;
        long[][][] dp = new long[N][N][DIRECTION_NUM];

        dp[0][1][HORIZON] = 1L;

        for (int col = 2; col < N; ++col) {
            if (map[0][col] == WALL) {
                break;
            }

            dp[0][col][HORIZON] = 1L;
        }

        for (int row = 1; row < N; ++row) {
            for (int col = 2; col < N; ++col) {
                if (map[row][col] == WALL) {
                    continue;
                }

                dp[row][col][HORIZON] = dp[row][col - 1][HORIZON] + dp[row][col - 1][DIAGONAL];
                dp[row][col][VERTICAL] = dp[row - 1][col][VERTICAL] + dp[row - 1][col][DIAGONAL];

                if (map[row - 1][col] == BLANK && map[row][col - 1] == BLANK) {
                    long diagonalCount = 0;
                    diagonalCount += dp[row - 1][col - 1][HORIZON];
                    diagonalCount += dp[row - 1][col - 1][VERTICAL];
                    diagonalCount += dp[row - 1][col - 1][DIAGONAL];
                    dp[row][col][DIAGONAL] = diagonalCount;
                }
            }
        }

        final int LAST_INDEX = N - 1;
        long answer = dp[LAST_INDEX][LAST_INDEX][HORIZON];
        answer += dp[LAST_INDEX][LAST_INDEX][VERTICAL];
        answer += dp[LAST_INDEX][LAST_INDEX][DIAGONAL];
        System.out.print(answer);
    }
}
