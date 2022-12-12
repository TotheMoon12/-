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
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());
        final int K = Integer.parseInt(st.nextToken());

        String[] board = new String[N];
        for (int n = 0; n < N; ++n) {
            board[n] = br.readLine();
        }

        String word = br.readLine();
        final int LENGTH = word.length();

        int[][][] dp = new int[N][M][LENGTH];
        char firstChar = word.charAt(0);
        for (int n = 0; n < N; ++n) {
            for (int m = 0; m < M; ++m) {
                if (board[n].charAt(m) == firstChar) {
                    dp[n][m][0] = 1;
                }
            }
        }

        for (int idx = 1; idx < LENGTH; ++idx) {
            char c = word.charAt(idx);
            char prevChar = word.charAt(idx - 1);
            for (int n = 0; n < N; ++n) {
                for (int m = 0; m < M; ++m) {
                    if (board[n].charAt(m) == prevChar && dp[n][m][idx - 1] != 0) {
                        int minRow = Math.max(0, n - K);
                        int maxRow = Math.min(N - 1, n + K);
                        for (int row = minRow; row <= maxRow; ++row) {
                            if (row == n) {
                                continue;
                            }

                            if (board[row].charAt(m) == c) {
                                dp[row][m][idx] += dp[n][m][idx - 1];
                            }
                        }

                        int minCol = Math.max(0, m - K);
                        int maxCol = Math.min(M - 1, m + K);
                        for (int col = minCol; col <= maxCol; ++col) {
                            if (col == m) {
                                continue;
                            }

                            if (board[n].charAt(col) == c) {
                                dp[n][col][idx] += dp[n][m][idx - 1];
                            }
                        }
                    }
                }
            }
        }

        int answer = 0;
        char lastChar = word.charAt(LENGTH - 1);
        for (int n = 0; n < N; ++n) {
            for (int m = 0; m < M; ++m) {
                if (board[n].charAt(m) == lastChar) {
                    answer += dp[n][m][LENGTH - 1];
                }
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
