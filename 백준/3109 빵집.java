import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static final char BLANK = '.';
    static final char BUILDING = 'x';

    enum EPass {
        NOT_VISITED,
        PASS,
        NOT_PASS
    }

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int ROW = Integer.parseInt(st.nextToken());
        final int COL = Integer.parseInt(st.nextToken());

        EPass[][] visited = new EPass[ROW][COL];
        for (int row = 0; row < ROW; ++row) {
            String s = br.readLine();
            for (int col = 0; col < COL; ++col) {
                if (s.charAt(col) == BUILDING) {
                    visited[row][col] = EPass.NOT_PASS;
                } else {
                    visited[row][col] = EPass.NOT_VISITED;
                }
            }

            visited[row][0] = EPass.PASS;
        }

        for (int row = 0; row < ROW; ++row) {
            dfs(row, 0, visited);
        }

        int answer = 0;
        for (int row = 0; row < ROW; ++row) {
            if (visited[row][COL - 1] == EPass.PASS) {
                ++answer;
            }
        }

        System.out.print(answer);
        br.close();
    }

    public static EPass dfs(int row, int col, EPass[][] visited) {
        if (col == visited[0].length - 1) {
            visited[row][col] = EPass.PASS;
            return EPass.PASS;
        }

        final int NEXT_COL = col + 1;
        EPass result = EPass.NOT_PASS;
        if (row - 1 >= 0) {
            if (visited[row - 1][NEXT_COL] == EPass.NOT_VISITED) {
                result = dfs(row - 1, NEXT_COL, visited);
            }
        }

        if (result != EPass.PASS) {
            if (visited[row][NEXT_COL] == EPass.NOT_VISITED) {
                result = dfs(row, NEXT_COL, visited);
            }
        }

        if (row + 1 < visited.length && result != EPass.PASS) {
            if (visited[row + 1][NEXT_COL] == EPass.NOT_VISITED) {
                result = dfs(row + 1, NEXT_COL, visited);
            }
        }

        visited[row][col] = result;
        return result;
    }
}
