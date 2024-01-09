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
        final int T = Integer.parseInt(st.nextToken());

        int[][] room = new int[R][C];
        for (int r = 0; r < R; ++r) {
            StringTokenizer dustST = new StringTokenizer(br.readLine());

            for (int c = 0; c < C; ++c) {
                room[r][c] = Integer.parseInt(dustST.nextToken());
            }
        }

        int[] machine = new int[2];
        for (int r = 0; r < R; ++r) {
            if (room[r][0] == -1) {
                machine[0] = r;
                machine[1] = r + 1;
                break;
            }
        }

        int[] rowData = {-1, 1, 0, 0};
        int[] colData = {0, 0, -1, 1};
        for (int t = 0; t < T; ++t) {
            // 확산
            int[][] info = new int[R][C];
            for (int r = 0; r < R; ++r) {
                for (int c = 0; c < C; ++c) {
                    if (room[r][c] == -1) {
                        continue;
                    }

                    int dust = room[r][c];
                    int moveDust = dust / 5;
                    for (int i = 0; i < rowData.length; ++i) {
                        int nextRow = r + rowData[i];
                        int nextCol = c + colData[i];

                        if (nextRow < 0 || nextRow == R || nextCol < 0 || nextCol == C || room[nextRow][nextCol] == -1) {
                            continue;
                        }

                        room[r][c] -= moveDust;
                        info[nextRow][nextCol] += moveDust;
                    }
                }
            }

            for (int r = 0; r < R; ++r) {
                for (int c = 0; c < C; ++c) {
                    room[r][c] += info[r][c];
                }
            }
            
            // 순환
            final int LAST_ROW = R - 1;
            final int LAST_COL = C - 1;
            for (int r = machine[0] - 1; r > 0; --r) {
                room[r][0] = room[r - 1][0];
            }

            for (int c = 0; c < LAST_COL; ++c) {
                room[0][c] = room[0][c + 1];
            }

            for (int r = 0; r < machine[0]; ++r) {
                room[r][LAST_COL] = room[r + 1][LAST_COL];
            }

            for (int c = LAST_COL; c > 1; --c) {
                room[machine[0]][c] = room[machine[0]][c - 1];
            }

            room[machine[0]][1] = 0;

            for (int r = machine[1] + 1; r < LAST_ROW; ++r) {
                room[r][0] = room[r + 1][0];
            }

            for (int c = 0; c < LAST_COL; ++c) {
                room[LAST_ROW][c] = room[LAST_ROW][c + 1];
            }

            for (int r = LAST_ROW; r > machine[1]; --r) {
                room[r][LAST_COL] = room[r - 1][LAST_COL];
            }

            for (int c = LAST_COL; c > 1; --c) {
                room[machine[1]][c] = room[machine[1]][c - 1];
            }

            room[machine[1]][1] = 0;
        }

        int total = 2; // 공기청정기 생각해서 2로 시작
        for (int r = 0; r < R; ++r) {
            for (int c = 0; c < C; ++c) {
                total += room[r][c];
            }
        }

        bw.write(String.valueOf(total));
        br.close();
        bw.close();
    }
}
