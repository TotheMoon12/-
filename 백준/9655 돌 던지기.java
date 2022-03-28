import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        final boolean LOSE = false;
        final boolean WIN = true;

        boolean[] dp = new boolean[10001];
        dp[1] = WIN;
        dp[2] = LOSE;
        dp[3] = WIN;

        for (int i = 4; i <= N; ++i) {
            if (dp[i - 3] == LOSE || dp[i - 1] == LOSE) {
                dp[i] = WIN;
            }
        }

        System.out.println(dp[N] == WIN ? "SK" : "CY");
    }
}
