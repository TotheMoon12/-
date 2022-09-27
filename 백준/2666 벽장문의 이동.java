import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        final int CLOSET_COUNT = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int left = Integer.parseInt(st.nextToken());
        int right = Integer.parseInt(st.nextToken());

        final int ORDER_COUNT = Integer.parseInt(br.readLine());
        int[][][] dp = new int[ORDER_COUNT + 1][CLOSET_COUNT + 1][CLOSET_COUNT + 1];
        boolean[][][] visited = new boolean[ORDER_COUNT + 1][CLOSET_COUNT + 1][CLOSET_COUNT + 1];
        dp[0][left][right] = 0;
        visited[0][left][right] = true;


        int answer = Integer.MAX_VALUE;
        for (int orderCount = 1; orderCount <= ORDER_COUNT; ++orderCount) {
            int number = Integer.parseInt(br.readLine());

            for (int open1 = 1; open1 <= CLOSET_COUNT; ++open1) {
                for (int open2 = 1; open2 <= CLOSET_COUNT; ++open2) {
                    if (visited[orderCount - 1][open1][open2]) {
                        int prevCount = dp[orderCount - 1][open1][open2];

                        int dist = prevCount + Math.abs(number - open1);
                        if (visited[orderCount][number][open2]) {
                            dp[orderCount][number][open2] = Math.min(dp[orderCount][number][open2], dist);
                        } else {
                            dp[orderCount][number][open2] = dist;
                        }

                        dist = prevCount + Math.abs(number - open2);
                        if (visited[orderCount][open1][number]) {
                            dp[orderCount][open1][number] = Math.min(dp[orderCount][open1][number], dist);
                        } else {
                            dp[orderCount][open1][number] = dist;
                        }

                        visited[orderCount][number][open2] = true;
                        visited[orderCount][open1][number] = true;
                    }
                }
            }

            if (orderCount == ORDER_COUNT) {
                for (int open = 1; open <= CLOSET_COUNT; ++open) {
                    if (visited[orderCount][number][open]) {
                        answer = Math.min(answer, dp[orderCount][number][open]);
                    }

                    if (visited[orderCount][open][number]) {
                        answer = Math.min(answer, dp[orderCount][open][number]);
                    }
                }
            }
        }

        System.out.print(answer);
        br.close();
    }
}

