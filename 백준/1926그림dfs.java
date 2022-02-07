import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int n;
    static int m;
    static int[][] map;
    static boolean[][] visited;
    static int[] rowData = {-1,1,0,0};
    static int[] colData = {0,0,-1,1};

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        map = new int[n][m];
        for (int row = 0; row < n; ++row) {
            st = new StringTokenizer(br.readLine());

            for (int col = 0; col < m; ++col) {
                map[row][col] = Integer.parseInt(st.nextToken());
            }
        }

        visited = new boolean[n][m];

        int maxArea = 0;
        int pictureCount = 0;
        for (int row = 0; row < n; ++row) {
            for (int col = 0; col < m; ++col) {
                if (!visited[row][col] && map[row][col] == 1) {
                    int area = dfs(row, col);
                    ++pictureCount;
                    maxArea = Math.max(maxArea, area);
                }
            }
        }

        System.out.println(pictureCount);
        System.out.println(maxArea);
    }

    public static int dfs(int row, int col) {
        visited[row][col] = true;

        int count = 1;
        for (int i = 0; i < rowData.length; ++i) {
            int nextRow = row + rowData[i];
            int nextCol = col + colData[i];

            if (nextRow < 0 || nextRow >= n || nextCol < 0 || nextCol >= m) {
                continue;
            }

            if (!visited[nextRow][nextCol] && map[nextRow][nextCol] == 1) {
                count += dfs(nextRow, nextCol);
            }
        }

        return count;
    }
}
