import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());
        final int MAX_N = 30;
        int[] dp = new int[MAX_N + 1];
        dp[1] = 1;
        dp[2] = 3;
        for (int n = 3; n <= N; ++n) {
            dp[n] += dp[n - 1] + dp[n - 2] * 2;
        }

        int answer = dp[N];
        if (N > 2) {
            int perfectSymmetryCount = dp[N / 2];
            if (N % 2 == 0) {
                perfectSymmetryCount += dp[N / 2 - 1] * 2;
            }

            answer = (dp[N] - perfectSymmetryCount) / 2 + perfectSymmetryCount;
        }

        bw.write(Integer.toString(answer));
        br.close();
        bw.close();
    }
}
