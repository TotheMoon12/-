import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int ROW_LENGTH = 2;

        int testCaseNum = Integer.parseInt(br.readLine());
        for (int test = 0; test < testCaseNum; ++test) {
            final int COL_LENGTH = Integer.parseInt(br.readLine());

            int[][] stickers = new int[ROW_LENGTH][COL_LENGTH];
            for (int i = 0; i < ROW_LENGTH; ++i) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                for (int j = 0; j < COL_LENGTH; ++j) {
                    stickers[i][j] = Integer.parseInt(st.nextToken());
                }
            }

            if (COL_LENGTH > 1) {
                stickers[0][1] = stickers[1][0] + stickers[0][1];
                stickers[1][1] = stickers[0][0] + stickers[1][1];
            }

            for (int col = 2; col < COL_LENGTH; ++col) {
                stickers[0][col] = Math.max(stickers[1][col - 1], stickers[1][col - 2]) + stickers[0][col];
                stickers[1][col] = Math.max(stickers[0][col - 1], stickers[0][col - 2]) + stickers[1][col];
            }

            System.out.println(Math.max(stickers[0][COL_LENGTH - 1], stickers[1][COL_LENGTH - 1]));
        }

    }
}
