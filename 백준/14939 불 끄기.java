import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static char[][] board;
    static int SIZE = 10;
    static int answer = 100;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        board = new char[SIZE][SIZE];

        for (int r = 0; r < SIZE; ++r) {
            String s = br.readLine();

            for (int c = 0; c < SIZE; ++c) {
                board[r][c] = s.charAt(c);
            }
        }

        boolean[] firstRow = new boolean[SIZE];
        dfs(firstRow, 0);
        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }

    public static void dfs(boolean[] firstRow, int index) {
        if (index == SIZE) {
            char[][] temp = new char[SIZE][SIZE];
            for (int r = 0; r < SIZE; ++r) {
                for (int c = 0; c < SIZE; ++c) {
                    temp[r][c] = board[r][c];
                }
            }

            int count = 0;
            for (int c = 0; c < SIZE; ++c) {
                if (firstRow[c]) {
                    reverse(temp, 0, c);
                    ++count;
                }
            }

            for (int r = 1; r < SIZE; ++r) {
                for (int c = 0; c < SIZE; ++c) {
                    if (temp[r - 1][c] == 'O') {
                        reverse(temp, r, c);
                        ++count;
                    }
                }
            }

            boolean pass = true;
            for (int r = 0; r < SIZE; ++r) {
                for (int c = 0; c < SIZE; ++c) {
                    if (temp[r][c] == 'O') {
                        pass = false;
                    }
                }
            }

            if (pass) {
                answer = Math.min(answer, count);
            }

            return;
        }

        dfs(firstRow, index + 1);
        firstRow[index] = true;
        dfs(firstRow, index + 1);
        firstRow[index] = false;
    }

    public static void reverse(char[][] board, int row, int col) {
        int[] rowData = {0, -1, 1, 0, 0};
        int[] colData = {0, 0, 0, -1, 1};

        for (int i = 0; i < rowData.length; ++i) {
            int nextRow = row + rowData[i];
            int nextCol = col + colData[i];

            if (nextRow < 0 || nextRow == board.length || nextCol < 0 || nextCol == board.length) {
                continue;
            }

            if (board[nextRow][nextCol] == '#') {
                board[nextRow][nextCol] = 'O';
            } else {
                board[nextRow][nextCol] = '#';
            }
        }
    }
}
