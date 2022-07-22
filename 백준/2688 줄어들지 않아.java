import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int MAX = 64;
        final int MAX_NUMBER = 9;
        long[][] dp = new long[MAX + 1][MAX_NUMBER + 1];
        long[] cases = new long[MAX + 1];
        cases[1] = 10;

        Arrays.fill(dp[1], 1);
        for (int count = 2; count <= MAX; ++count) {
            long total = 0;
            long sum = 0;

            for (int number = 0; number <= MAX_NUMBER; ++number) {
                sum += dp[count - 1][number];
                dp[count][number] = sum;
                total += sum;
            }

            cases[count] = total;
        }

        StringBuilder answer = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; ++t) {
            int n = Integer.parseInt(br.readLine());

            answer.append(cases[n]);
            answer.append(System.lineSeparator());
        }

        System.out.print(answer);
    }
}
