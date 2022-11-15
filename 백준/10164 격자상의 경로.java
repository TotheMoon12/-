import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int ROW_SIZE = Integer.parseInt(st.nextToken());
        final int COL_SIZE = Integer.parseInt(st.nextToken());
        final int circleNumber = Integer.parseInt(st.nextToken());

        int[][] dp = new int[ROW_SIZE][COL_SIZE];
        dp[0][0] = 1;
        int startRow = 0;
        int startCol = 0;

        if (circleNumber == 0) {
            search(startRow, startCol, ROW_SIZE - 1, COL_SIZE - 1, dp);
        } else {
            int circleRow = circleNumber / COL_SIZE;
            int circleCol = circleNumber % COL_SIZE;
            if(circleCol == 0) {
                --circleRow;
                circleCol = COL_SIZE - 1;
            } else {
                --circleCol;
            }

            search(startRow, startCol, circleRow, circleCol, dp);
            search(circleRow, circleCol, ROW_SIZE - 1, COL_SIZE - 1, dp);
        }

        System.out.println(dp[ROW_SIZE - 1][COL_SIZE - 1]);
        br.close();
    }

    public static void search(int startRow, int startCol, int targetRow, int targetCol, int[][] dp) {
        int startValue = dp[startRow][startCol];
        for (int row = startRow + 1; row <= targetRow; ++row) {
            dp[row][startCol] = startValue;
        }

        for (int col = startCol + 1; col <= targetCol; ++col) {
            dp[startRow][col] = startValue;
        }

        for (int row = startRow + 1; row <= targetRow; ++row) {
            for (int col = startCol + 1; col <= targetCol; ++col) {
                dp[row][col] = dp[row - 1][col] + dp[row][col - 1];
            }
        }
    }
}

