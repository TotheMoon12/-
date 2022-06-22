import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[][][] visited;
    static int[][][] dp;
    static final int DIRECTION_NUM = 3;
    static final int LEFT = 0;
    static final int RIGHT = 1;
    static final int DOWN = 2;
    static int[] rowData = {0, 0, 1};
    static int[] colData = {-1, 1, 0};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int n = 0; n < N; ++n) {
            st = new StringTokenizer(br.readLine());

            for (int m = 0; m < M; ++m) {
                map[n][m] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[N][M][DIRECTION_NUM];
        dp = new int[N][M][DIRECTION_NUM];
        dp[0][0][LEFT] = map[0][0];
        dp[0][0][RIGHT] = map[0][0];
        dp[0][0][DOWN] = map[0][0];
        dfs(map, 0,0,RIGHT);

        int answer = Math.max(dp[N - 1][M - 1][DOWN], dp[N - 1][M - 1][RIGHT]);
        System.out.println(answer);
    }

    public static void dfs(int[][] map, int row, int col, int direction) {
        int ROW_SIZE = map.length;
        int COL_SIZE = map[0].length;

        for (int idx = 0; idx < rowData.length; ++idx) {
            int nextRow = row + rowData[idx];
            int nextCol = col + colData[idx];

            if (nextRow < 0 || nextRow >= ROW_SIZE || nextCol < 0 || nextCol >= COL_SIZE) {
                continue;
            }

            if ((direction == LEFT && idx == RIGHT) || (direction == RIGHT && idx == LEFT)) {
                continue;
            }

            int value = dp[row][col][direction] + map[nextRow][nextCol];
            if (!visited[nextRow][nextCol][idx]) {
                visited[nextRow][nextCol][idx] = true;
                dp[nextRow][nextCol][idx] = value;
                dfs(map, nextRow, nextCol, idx);
            } else {
                if (value > dp[nextRow][nextCol][idx]) {
                    dp[nextRow][nextCol][idx] = value;
                    dfs(map, nextRow, nextCol, idx);
                }
            }
        }
    }
}
