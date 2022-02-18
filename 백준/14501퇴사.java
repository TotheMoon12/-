import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int TIME = 0;
        final int PRICE = 1;
        int N = Integer.parseInt(br.readLine());
        int[][] table = new int[N + 1][2];
        StringTokenizer st;
        for (int i = 1; i <= N; ++i) {
            st = new StringTokenizer(br.readLine());
            table[i][TIME] = Integer.parseInt(st.nextToken());
            table[i][PRICE] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N + 1];
        int max = 0;
        for (int i = 1; i <= N; ++i) {
            if (i + table[i][TIME] - 1 <= N) {
                dp[i] = table[i][PRICE];
            }

            int prevMax = 0;
            for (int j = i - 1; j >= 1; --j) {
                if (j + table[j][TIME] - 1 < i) {
                    prevMax = Math.max(prevMax, dp[j]);
                }
            }

            dp[i] += prevMax;
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
