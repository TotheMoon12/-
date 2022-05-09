import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        final int COUNT = 0;
        final int PREV = 1;
        int[][] dp = new int[N + 1][2];
        for (int i = 1; i <= N; ++i) {
            dp[i][COUNT] = Integer.MAX_VALUE;
        }

        dp[N][COUNT] = 0;
        for (int i = N; i >= 2; --i) {
            if (i % 3 == 0) {
                int next = i / 3;
                if (dp[i][COUNT] + 1 < dp[next][COUNT]) {
                    dp[next][COUNT] = dp[i][COUNT] + 1;
                    dp[next][PREV] = i;
                }
            }

            if (i % 2 == 0) {
                int next = i / 2;
                if (dp[i][COUNT] + 1 < dp[next][COUNT]) {
                    dp[next][COUNT] = dp[i][COUNT] + 1;
                    dp[next][PREV] = i;
                }
            }

            int next = i - 1;
            if (dp[i][COUNT] + 1 < dp[next][COUNT]) {
                dp[next][COUNT] = dp[i][COUNT] + 1;
                dp[next][PREV] = i;
            }
        }

        ArrayList<Integer> list = new ArrayList<>();
        int start = 1;
        while (start < N) {
            list.add(start);
            start = dp[start][PREV];
        }

        System.out.println(dp[1][COUNT]);
        StringBuilder sb = new StringBuilder();
        sb.append(N);
        sb.append(' ');
        for (int i = list.size() - 1; i >= 0; --i) {
            sb.append(list.get(i));
            sb.append(' ');
        }

        System.out.println(sb.toString());
    }
}
