import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] sequence = new int[n];

        StringTokenizer st = new StringTokenizer(br.readLine());

        int max = -1000;
        for (int i = 0; i < n; ++i) {
            sequence[i] = Integer.parseInt(st.nextToken());
            max = Math.max(max, sequence[i]);
        }

        int[] dp = new int[n];
        dp[0] = sequence[0];

        for (int i = 1; i < n; ++i) {
            dp[i] = dp[i - 1] + sequence[i];
            max = Math.max(max, dp[i]);
        }

        for (int i = 1; i < n; ++i) {
            int value = 0;

            for (int j = i; j < n; ++j) {
                value += sequence[j];
                if (value <= dp[j] || value < 0) {
                    break;
                }

                dp[j] = value;
                max = Math.max(max, dp[j]);
            }
        }

        System.out.println(max);
    }
}
