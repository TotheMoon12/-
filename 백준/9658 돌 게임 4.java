import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        final int MAX = 1000;
        boolean[] dp = new boolean[MAX + 1];
        dp[2] = true;
        dp[4] = true;

        for (int n = 5; n <= N; ++n) {
            if (!dp[n - 1] || !dp[n - 3] || !dp[n - 4]) {
                dp[n] = true;
            }
        }

        if (dp[N]) {
            System.out.println("SK");
        } else {
            System.out.println("CY");
        }
    }
}
