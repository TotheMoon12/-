import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        boolean[] dp = new boolean[1001];
        dp[2] = true;
        for (int i = 4; i <= N; ++i) {
            if (!dp[i - 1] || !dp[i - 3]) {
                dp[i] = true;
            }
        }

        if (dp[N]) {
            System.out.println("SK");
        } else {
            System.out.println("CY");
        }
    }
}
