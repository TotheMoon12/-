import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        final int MAX = 1000;
        boolean[] dp = new boolean[MAX + 1];
        dp[1] = true;
        dp[3] = true;
        dp[4] = true;

        for (int idx = 5; idx <= MAX; ++idx) {
            if (!dp[idx -1] || !dp[idx -3] || !dp[idx - 4]) {
                dp[idx] = true;
            }
        }

        if (dp[N]) {
            System.out.println("SK");
        } else {
            System.out.println("CY");
        }
    }
}
