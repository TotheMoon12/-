import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // dp[m][n] = max(dp[m - 1][n - 0~n] + money[m][0~n])
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[][] data = new int[M + 1][N + 1];
        for (int n = 1; n <= N; ++n) {
            st = new StringTokenizer(br.readLine());

            int money = Integer.parseInt(st.nextToken());
            for (int m = 1; m <= M; ++m) {
                int profit = Integer.parseInt(st.nextToken());
                data[m][money] = profit;
            }
        }

        int[][] dp = new int[M + 1][N + 1];
        int[][][] moneyByCompany = new int[M + 1][N + 1][M + 1];
        for (int m = 1; m <= M; ++m) {
            for (int n = 0; n <= N; ++n) {
                int max = 0;
                int maxMoney = 0;
                for (int money = 0; money <= n; ++money) {
                    int profit = dp[m - 1][n - money] + data[m][money];
                    if (profit > max) {
                        max = profit;
                        maxMoney = money;
                    }
                }

                for (int company = 1; company <= m; ++company) {
                    moneyByCompany[m][n][company] = moneyByCompany[m - 1][n - maxMoney][company];
                }

                moneyByCompany[m][n][m] = maxMoney;
                dp[m][n] = max;
            }
        }

        StringBuilder answer = new StringBuilder();
        answer.append(dp[M][N]);
        answer.append(System.lineSeparator());
        for (int company = 1; company < M; ++company) {
            answer.append(moneyByCompany[M][N][company]);
            answer.append(' ');
        }
        answer.append(moneyByCompany[M][N][M]);

        System.out.print(answer);
    }
}
