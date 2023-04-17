// 직사각형이 이동했을 때 직사각형의 위치에 벽이 있는지를 누적합을 통해서 구한다.

import java.io.*;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static class Move {
        final int row;
        final int col;
        final int count;

        public Move(int row, int col, int count) {
            this.row = row;
            this.col = col;
            this.count = count;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N + 1][M + 1];
        for (int n = 1; n <= N; ++n) {
            st = new StringTokenizer(br.readLine());
            for (int m = 1; m <= M; ++m) {
                map[n][m] = Integer.parseInt(st.nextToken()) + map[n - 1][m] + map[n][m - 1] - map[n - 1][m - 1];
            }
        }

        st = new StringTokenizer(br.readLine());
        final int H = Integer.parseInt(st.nextToken());
        final int W = Integer.parseInt(st.nextToken());
        final int SR = Integer.parseInt(st.nextToken());
        final int SC = Integer.parseInt(st.nextToken());
        final int FR = Integer.parseInt(st.nextToken());
        final int FC = Integer.parseInt(st.nextToken());

        boolean[][] visited = new boolean[N + 1][M + 1];
        visited[SR][SC] = true;
        Queue<Move> queue = new LinkedList<>();
        queue.add(new Move(SR, SC, 0));

        int[] rowData = {-1, 1, 0, 0};
        int[] colData = {0, 0, -1, 1};

        int answer = -1;
        while (!queue.isEmpty()) {
            Move move = queue.poll();

            if (move.row == FR && move.col == FC) {
                answer = move.count;
                break;
            }

            for (int idx = 0; idx < rowData.length; ++idx) {
                int nextRow = rowData[idx] + move.row;
                int nextCol = colData[idx] + move.col;

                if (nextRow <= 0 || nextRow + H > map.length || nextCol <= 0 || nextCol + W > map[0].length) {
                    continue;
                }

                if (!visited[nextRow][nextCol] && canMove(map, nextRow, nextCol, H, W)) {
                    visited[nextRow][nextCol] = true;
                    queue.add(new Move(nextRow, nextCol, move.count + 1));
                }
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }

    public static boolean canMove(int[][] map, final int row, final int col, final int height, final int width) {
        int edgeRow = row + height - 1;
        int edgeCol = col + width - 1;
        int sum = map[edgeRow][edgeCol] - map[row - 1][edgeCol] - map[edgeRow][col - 1] + map[row - 1][col - 1];
        return sum == 0 ? true : false;
    }
}
