import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer stLeft = new StringTokenizer(br.readLine());
        StringTokenizer stRight = new StringTokenizer(br.readLine());

        int[] leftDummy = new int[N + 1];
        int[] rightDummy = new int[N + 1];
        for (int n = 0; n < N; ++n) {
            leftDummy[n] = Integer.parseInt(stLeft.nextToken());
            rightDummy[n] = Integer.parseInt(stRight.nextToken());
        }

        int[][] dp = new int[N + 1][N + 1];
        int answer = 0;

        for (int left = 0; left <= N; ++left) {
            for (int right = 1; right <= N; ++right) {
                int max = -1;

                if (right - 1 >= 0 && dp[left][right - 1] != -1) {
                    if (leftDummy[left] > rightDummy[right - 1]) {
                        max = dp[left][right - 1] + rightDummy[right - 1];
                    }
                }

                if (left - 1 >= 0 && dp[left - 1][right] != -1) {
                    max = Math.max(max, dp[left - 1][right]);
                }

                if (left - 1 >= 0 && right - 1 >= 0 && dp[left - 1][right - 1] != -1) {
                    max = Math.max(max, dp[left - 1][right - 1]);
                }

                dp[left][right] = max;
                answer = Math.max(answer, max);
            }
        }

        System.out.print(answer);
    }
}
