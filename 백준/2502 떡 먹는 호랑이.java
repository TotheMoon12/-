import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int D = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        int[] dp = new int[D + 1];
        dp[0] = 1;
        dp[1] = 0;

        for (int d = 2; d <= D; ++d) {
            dp[d] = dp[d - 1] + dp[d - 2];
        }

        int A = 1;
        double B;
        int alpha = dp[D - 1];
        int beta = dp[D];
        while (true) {
            B = (K - alpha * A) / (double) beta;
            if (Double.compare(B, (int) B) == 0) {
                break;
            }

            ++A;
        }

        System.out.println(A);
        System.out.println((int) B);
    }
}
