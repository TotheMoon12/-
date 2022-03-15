import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        int[] dp = new int[31];
        dp[2] = 3;
        for (int i = 3; i <= N; ++i) {
            if (i % 2 == 0) {
                dp[i] = dp[i - 2] * 3;
                dp[i] += 2;                    
                for (int j = i - 4; j >= 2; j -= 2) {
                    dp[i] += dp[j] * 2;
                }
            }
        }

        System.out.println(dp[N]);
    }
}
