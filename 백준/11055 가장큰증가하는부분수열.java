import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] seq = new int[N];
        for (int i = 0; i < N; ++i) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        dp[0] = seq[0];
        int answer = seq[0];
        for (int i = 1; i < N; ++i) {
            dp[i] = seq[i];
            int minIndex = -1;
            int max = 0;
            for (int j = i - 1; j >= 0; --j) {
                if (seq[j] < seq[i] && dp[j] > max) {
                    minIndex = j;
                    max = dp[j];
                }
            }

            if (minIndex != -1) {
                dp[i] += max;
            }

            answer = Math.max(answer, dp[i]);
        }

        System.out.println(answer);
    }
}
