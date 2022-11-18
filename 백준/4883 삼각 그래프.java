import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        int k = 1;

        final int COL_SIZE = 3;
        StringBuilder sb = new StringBuilder();
        int N = Integer.parseInt(br.readLine());
        final int NOT_VISITED = -10000000;
        while (N != 0) {
            int[][] dp = new int[N][COL_SIZE];
            int[][] graph = new int[N][COL_SIZE];
            for (int n = 0; n < N; ++n) {
                StringTokenizer st = new StringTokenizer(br.readLine());

                for (int col = 0; col < COL_SIZE; ++col) {
                    graph[n][col] = Integer.parseInt(st.nextToken());
                    dp[n][col] = NOT_VISITED;
                }
            }

            dp[0][1] = graph[0][1];
            for (int n = 0; n < N; ++n) {
                for (int col = 0; col < COL_SIZE; ++col) {
                    if (dp[n][col] != NOT_VISITED) {
                        // 오른쪽
                        if (col + 1 < COL_SIZE) {
                            if (dp[n][col + 1] == NOT_VISITED) {
                                dp[n][col + 1] = dp[n][col] + graph[n][col + 1];
                            } else {
                                dp[n][col + 1] = Math.min(dp[n][col + 1], dp[n][col] + graph[n][col + 1]);
                            }
                        }


                        if (n + 1 < N) {
                            // 아래
                            if (dp[n + 1][col] == NOT_VISITED) {
                                dp[n + 1][col] = dp[n][col] + graph[n + 1][col];
                            } else {
                                dp[n + 1][col] = Math.min(dp[n + 1][col], dp[n][col] + graph[n + 1][col]);
                            }

                            // 왼쪽 대각
                            if (col - 1 >= 0) {
                                if (dp[n + 1][col - 1] == NOT_VISITED) {
                                    dp[n + 1][col - 1] = dp[n][col] + graph[n + 1][col - 1];
                                } else {
                                    dp[n + 1][col - 1] = Math.min(dp[n + 1][col - 1], dp[n][col] + graph[n + 1][col - 1]);
                                }
                            }

                            // 오른쪽 대각
                            if (col + 1 < COL_SIZE) {
                                if (dp[n + 1][col + 1] == NOT_VISITED) {
                                    dp[n + 1][col + 1] = dp[n][col] + graph[n + 1][col + 1];
                                } else {
                                    dp[n + 1][col + 1] = Math.min(dp[n + 1][col + 1], dp[n][col] + graph[n + 1][col + 1]);
                                }
                            }
                        }
                    }
                }
            }

            sb.append(k);
            sb.append(". ");
            sb.append(dp[N - 1][1]);
            sb.append(System.lineSeparator());
            N = Integer.parseInt(br.readLine());
            ++k;
        }

        bw.write(sb.toString());
        bw.flush();
        br.close();
        bw.close();
    }
}
