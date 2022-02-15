import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));

        int sequenceSize = Integer.parseInt(bf.readLine());
        int[] sequence = new int[sequenceSize];

        StringTokenizer st = new StringTokenizer(bf.readLine());
        for (int i = 0; i < sequenceSize; ++i) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        int[][] dp = new int[sequenceSize][2];
        dp[0][0] = 1;
        dp[0][1] = 0;

        int totalMax = 1;
        for (int i = 1; i < sequenceSize; ++i) {
            int max = 0;
            int curNumber = sequence[i];
            for (int j = 0; j < i; ++j) {
                if (sequence[j] < curNumber) {
                    max = Math.max(max, dp[j][0]);
                }
            }

            dp[i][0] = max + 1;
            dp[i][1] = totalMax;

            totalMax = Math.max(totalMax, dp[i][0]);
        }

        System.out.println(totalMax);
    }
}
