import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] seq = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; ++i) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        dp[0] = 1;
        int maxLength = 1;
        for (int i = 1; i < N; ++i) {
            int max = 0;
            for (int j = i - 1; j >= 0; --j) {
                if (seq[j] > seq[i] && dp[j] > max) {
                    max = dp[j];
                }
            }

            dp[i] = max + 1;

            maxLength = Math.max(maxLength, dp[i]);
        }

        System.out.println(maxLength);
    }
}
