import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine()) - 1;
        int K = Integer.parseInt(br.readLine());

        int[][] dp = new int[K + 1][N];
        Arrays.fill(dp[1], 1);

        for (int count = 2; count <= K; ++count) {
            int sum = 0;
            for (int idx = N - 3; idx >= 0; --idx) {
                int next = idx + 2;
                if (next < N) {
                    sum += dp[count - 1][idx + 2];
                    sum %= 1000000003;
                }

                dp[count][idx] = sum;
            }
        }

        int answer = 0;
        for (int idx = 0; idx < N; ++idx) {
            answer += dp[K][idx];
            answer %= 1000000003;
        }

        answer += dp[K][0];
        answer %= 1000000003;

        System.out.print(answer);
    }
}
