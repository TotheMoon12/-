import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] map = new int[N][M];
        for (int i = 0; i < N; ++i) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; ++j) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int col = 1; col < M; ++col) {
            map[0][col] += map[0][col - 1];
        }

        for (int row = 1; row < N; ++row) {
            map[row][0] += map[row - 1][0];
        }

        for (int row = 1; row < N; ++row) {
            for (int col = 1; col < M; ++col) {
                int prevMax = map[row - 1][col];
                prevMax = Math.max(prevMax, map[row][col - 1]);
                prevMax = Math.max(prevMax, map[row - 1][col - 1]);

                map[row][col] += prevMax;
            }
        }

        System.out.println(map[N - 1][M - 1]);
    }
}
