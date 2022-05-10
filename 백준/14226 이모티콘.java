import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int S = Integer.parseInt(br.readLine());

        final int MAX = 10000;
        int[] dp = new int[MAX + 1];

        for (int i = 2; i <= MAX; ++i) {
            dp[i] = i;
        }

        for (int start = 2; start < MAX; ++start) {
            if (dp[start + 1] + 1 < dp[start]) {
                dp[start] = dp[start + 1] + 1;
            }


            int time = dp[start];
            for (int i = start * 2; i <= MAX; i *= 2) {
                time += 2;
                int clip = i / 2;
                if (time < dp[i]) {
                    dp[i] = time;
                }

                int temp = time;
                for (int j = i + clip; j <= MAX; j += clip) {
                    ++temp;
                    if (temp < dp[j]) {
                        dp[j] = temp;
                    }
                }
            }
        }

        for (int i = 2; i < MAX; ++i) {
            if (dp[i + 1] + 1 < dp[i]) {
                dp[i] = dp[i + 1] + 1;
            }
        }

        System.out.println(dp[S]);
    }
}
