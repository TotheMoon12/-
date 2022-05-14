import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());

        final int A = 0;
        final int B = 1;

        int[] dp = new int[2];
        dp[A] = 1;

        for (int index = 0; index < K; ++index) {
            int nextACount = dp[B];
            int nextBCount = dp[A] + dp[B];
            dp[A] = nextACount;
            dp[B] = nextBCount;
        }

        System.out.println(dp[A] + " " + dp[B]);
    }
}


