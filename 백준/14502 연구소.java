import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static final int INSTALL_WALL_COUNT = 3;
    static int[][] map;
    static int N;
    static int M;
    static final int BLANK = 0;
    static final int WALL = 1;
    static final int VIRUS = 2;
    static int[] rowData = {-1, 1, 0, 0};
    static int[] colData = {0, 0, -1, 1};
    static int answer = 0;
    static int wallCount = 3;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        map = new int[N][M];
        for (int row = 0; row < N; ++row) {
            st = new StringTokenizer(br.readLine());

            for (int col = 0; col < M; ++col) {
                map[row][col] = Integer.parseInt(st.nextToken());

                if (map[row][col] == WALL) {
                    ++wallCount;
                }
            }
        }

        combination(0, N * M, 0);

        System.out.print(answer);
    }

    public static void combination(int number, int end, int installCount) {
        if (installCount == INSTALL_WALL_COUNT) {
            boolean[][] visited = new boolean[N][M];
            int virusCount = 0;
            for (int row = 0; row < N; ++row) {
                for (int col = 0; col < M; ++col) {
                    if (map[row][col] == VIRUS && !visited[row][col]) {
                        ++virusCount;
                        Queue<Integer> q = new LinkedList<>();
                        visited[row][col] = true;
                        q.offer(row);
                        q.offer(col);

                        while (!q.isEmpty()) {
                            int curRow = q.poll();
                            int curCol = q.poll();

                            for (int idx = 0; idx < rowData.length; ++idx) {
                                int nextRow = curRow + rowData[idx];
                                int nextCol = curCol + colData[idx];

                                if (nextRow < 0 || nextRow >= N || nextCol < 0 || nextCol >= M) {
                                    continue;
                                }

                                if (map[nextRow][nextCol] == BLANK && !visited[nextRow][nextCol]) {
                                    ++virusCount;
                                    visited[nextRow][nextCol] = true;
                                    q.offer(nextRow);
                                    q.offer(nextCol);
                                }
                            }
                        }
                    }
                }
            }

            int safeArea = N * M - wallCount - virusCount;
            answer = Math.max(answer, safeArea);
            return;
        } else {
            if (number < end) {
                int row = number / M;
                int col = number % M;
                if (map[row][col] == BLANK) {
                    map[row][col] = WALL;
                    combination(number + 1, end, installCount + 1);
                    map[row][col] = BLANK;
                }

                combination(number + 1, end, installCount);
            }
        }
    }
}
