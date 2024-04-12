import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    public static class Point {
        int row;
        int col;

        public Point(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        final int AIR = 0;
        final int CHEESE = 1;
        int cheeseCount = 0;
        for (int n = 0; n < N; ++n) {
            st = new StringTokenizer(br.readLine());
            for (int m = 0; m < M; ++m) {
                map[n][m] = Integer.parseInt(st.nextToken());

                if (map[n][m] == CHEESE) {
                    ++cheeseCount;
                }
            }
        }

        int[] rowData = {-1, 1, 0, 0};
        int[] colData = {0, 0, -1, 1};

        int answer = 0;
        while (cheeseCount > 0) {
            ++answer;

            boolean[][] visited = new boolean[N][M];
            visited[0][0] = true;
            Queue<Point> queue = new LinkedList<>();
            queue.add(new Point(0, 0));
            
            int[][] airTouch = new int[N][M];
            while (!queue.isEmpty()) {
                Point point = queue.poll();

                for (int i = 0; i < rowData.length; ++i) {
                    int nearRow = point.row + rowData[i];
                    int nearCol = point.col + colData[i];

                    if (nearRow < 0 || nearRow == N || nearCol < 0 || nearCol == M) {
                        continue;
                    }

                    if (map[nearRow][nearCol] == CHEESE) {
                        ++airTouch[nearRow][nearCol];
                    } else {
                        if (!visited[nearRow][nearCol]) {
                            visited[nearRow][nearCol] = true;
                            queue.add(new Point(nearRow, nearCol));
                        }
                    }
                }
            }

            for (int n = 0; n < N; ++n) {
                for (int m = 0; m < M; ++m) {
                    if (map[n][m] == CHEESE && airTouch[n][m] >= 2) {
                        map[n][m] = AIR;
                        --cheeseCount;
                    }
                }
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
