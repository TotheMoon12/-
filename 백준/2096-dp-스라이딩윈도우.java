import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[][][] board = new int[N][3][2];
        final int MAX = 0;
        final int MIN = 1;

        StringTokenizer st;
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());

            board[i][0][0] = Integer.parseInt(st.nextToken());
            board[i][0][1] = board[i][0][0];

            board[i][1][0] = Integer.parseInt(st.nextToken());
            board[i][1][1] = board[i][1][0];

            board[i][2][0] = Integer.parseInt(st.nextToken());
            board[i][2][1] = board[i][2][0];
        }

        for (int i = 1; i < N; ++i) {
            board[i][0][MAX] += Math.max(board[i - 1][0][MAX], board[i - 1][1][MAX]);
            board[i][1][MAX] += Math.max(Math.max(board[i - 1][0][MAX], board[i - 1][1][MAX]), board[i - 1][2][MAX]);
            board[i][2][MAX] += Math.max(board[i - 1][1][MAX], board[i - 1][2][MAX]);

            board[i][0][MIN] += Math.min(board[i - 1][0][MIN], board[i - 1][1][MIN]);
            board[i][1][MIN] += Math.min(Math.min(board[i - 1][0][MIN], board[i - 1][1][MIN]), board[i - 1][2][MIN]);
            board[i][2][MIN] += Math.min(board[i - 1][1][MIN], board[i - 1][2][MIN]);
        }

        int max = Math.max(Math.max(board[N - 1][0][MAX], board[N - 1][1][MAX]), board[N - 1][2][MAX]);
        int min = Math.min(Math.min(board[N - 1][0][MIN], board[N - 1][1][MIN]), board[N - 1][2][MIN]);
        System.out.println(max + " " + min);
    }
}
