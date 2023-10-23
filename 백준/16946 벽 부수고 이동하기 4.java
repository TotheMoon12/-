import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
    static int[] rowData = {-1, 1, 0, 0};
    static int[] colData = {0, 0, -1, 1};
    static int N;
    static int M;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        int[][] map = new int[N][M];
        int[] areas = new int[N * M + 1];

        for (int n = 0; n < N; ++n) {
            String s = br.readLine();

            for (int m = 0; m < M; ++m) {
                int type = s.charAt(m) - '0';
                if (type == 1) {
                    map[n][m] = -1;
                } else {
                    map[n][m] = type;
                }
            }
        }

        int areaNumber = 0;
        for (int n = 0; n < N; ++n) {
            for (int m = 0; m < M; ++m) {
                int type = map[n][m];

                if (type == 0) {
                    ++areaNumber;
                    int count = dfs(map, n, m, areaNumber);
                    areas[areaNumber] = count;
                }
            }
        }

        StringBuilder builder = new StringBuilder();
        final int MOD = 10;
        for (int n = 0; n < N; ++n) {
            for (int m = 0; m < M; ++m) {
                int type = map[n][m];

                if (type == -1) {
                    HashSet<Integer> set = new HashSet<>();
                    int count = 1;
                    for (int i = 0; i < rowData.length; ++i) {
                        int nextRow = n + rowData[i];
                        int nextCol = m + colData[i];

                        if (nextRow < 0 || nextRow == N || nextCol < 0 || nextCol == M) {
                            continue;
                        }

                        int number = map[nextRow][nextCol];
                        if (number != -1 && !set.contains(number)) {
                            set.add(number);
                            count += areas[number];
                        }
                    }

                    builder.append(count % MOD);
                } else {
                    builder.append("0");
                }
            }

            builder.append(System.lineSeparator());
        }

        bw.write(builder.toString());
        br.close();
        bw.close();
    }

    public static int dfs(int[][] map, int row, int col, int areaNumber) {
        int count = 1;
        map[row][col] = areaNumber;
        for (int i = 0; i < rowData.length; ++i) {
            int nextRow = row + rowData[i];
            int nextCol = col + colData[i];

            if (nextRow < 0 || nextRow == N || nextCol < 0 || nextCol == M || map[nextRow][nextCol] != 0) {
                continue;
            }

            count += dfs(map, nextRow, nextCol, areaNumber);
        }

        return count;
    }
}
