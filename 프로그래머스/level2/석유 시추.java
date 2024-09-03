import java.util.*;

class Solution {
    static int[] rowData = {-1,1,0,0};
    static int[] colData = {0,0,-1,1};

    class Info {
        int n;
        int m;

        Info(int n, int m) {
            this.n = n;
            this.m = m;
        }
    }

    public int solution(int[][] land) {
        int answer = 0;
        final int N = land.length;
        final int M = land[0].length;
        int landNumber = 1;
        int[][] landByNumber = new int[N][M];
        int[] oilCount = new int[N * M];

        boolean[][] visited = new boolean[N][M];
        for (int n = 0; n < N; ++n) {
            for (int m = 0; m < M; ++m) {
                if (!visited[n][m] && land[n][m] == 1) {
                    visited[n][m] = true;

                    Queue<Info> queue = new LinkedList<>();
                    queue.add(new Info(n, m));
                    int oil = 1;
                    landByNumber[n][m] = landNumber;
                    while (!queue.isEmpty()) {
                        Info info = queue.poll();

                        for (int i = 0; i < rowData.length; ++i) {
                            int nextRow = info.n + rowData[i];
                            int nextCol = info.m + colData[i];

                            if (nextRow < 0 || nextRow == N || nextCol < 0 || nextCol == M
                                    || visited[nextRow][nextCol] || land[nextRow][nextCol] == 0) {
                                continue;
                            }

                            visited[nextRow][nextCol] = true;
                            landByNumber[nextRow][nextCol] = landNumber;
                            ++oil;
                            queue.add(new Info(nextRow, nextCol));
                        }
                    }

                    oilCount[landNumber] = oil;

                    ++landNumber;
                }
            }
        }

        for (int m = 0; m < M; ++m) {
            int sum = 0;
            boolean[] check = new boolean[landNumber + 1];
            for (int n = 0; n < N; ++n) {
                if (land[n][m] == 1 && !check[landByNumber[n][m]]) {
                    check[landByNumber[n][m]] = true;
                    sum += oilCount[landByNumber[n][m]];
                }
            }

            answer = Math.max(answer, sum);
        }
        return answer;
    }
}
