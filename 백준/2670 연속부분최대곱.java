import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        double[] dp = new double[N];
        dp[0] = Double.parseDouble(br.readLine());
        double max = dp[0];
        for (int i = 1; i < N; ++i) {
            double number = Double.parseDouble(br.readLine());

            if (dp[i - 1] * number > number) {
                dp[i] = dp[i - 1] * number;
            } else {
                dp[i] = number;
            }

            max = Math.max(max, dp[i]);
        }

        System.out.printf("%.3f", max);
    }
}
