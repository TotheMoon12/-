import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static char[][] map;
    static int[][] dp;
    static boolean[][] visited;
    static int N;
    static int M;
    static int[] rowData = {-1, 1, 0, 0};
    static int[] colData = {0, 0, -1, 1};
    static int answer = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new char[N][M];

        for (int n = 0; n < N; ++n) {
            String s = br.readLine();

            for (int m = 0; m < M; ++m) {
                map[n][m] = s.charAt(m);
            }
        }

        dp = new int[N][M];
        visited = new boolean[N][M];
        if (dfs(0,0,0)) {
            System.out.print(-1);
        } else {
            System.out.print(answer + 1);
        }
    }

    public static boolean dfs(int row, int col, int totalMoveCount) {
        answer = Math.max(answer, totalMoveCount);
        visited[row][col] = true;

        int move = map[row][col] - '0';

        for (int idx = 0; idx < rowData.length; ++idx) {
            int nextRow = row + rowData[idx] * move;
            int nextCol = col + colData[idx] * move;

            if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M || map[nextRow][nextCol] == 'H') {
                continue;
            }

            if (visited[nextRow][nextCol]) {
                return true;
            } else {
                int nextMoveCount = totalMoveCount + 1;
                if (nextMoveCount > dp[nextRow][nextCol]) {
                    dp[nextRow][nextCol] = nextMoveCount;

                    if (dfs(nextRow, nextCol, nextMoveCount)) {
                        return true;
                    }
                }
            }
        }

        visited[row][col] = false;
        return false;
    }
}
