import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] boxes = new int[N];
        for (int i = 0; i < N; ++i) {
            boxes[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        int totalMax = 1;
        for (int i = 0; i < N; ++i) {
            dp[i] = 1;
            int max = 0;
            int maxIndex = -1;
            for (int j = i - 1; j >=0; --j) {
                if (boxes[j] < boxes[i] && dp[j] > max) {
                    max = dp[j];
                    maxIndex = j;
                }
            }

            if (maxIndex != -1) {
                dp[i] += max;
            }

            totalMax = Math.max(totalMax, dp[i]);
        }

        System.out.println(totalMax);
    }
}
