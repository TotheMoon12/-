import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static long[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] numbers = new int[N - 1];
        dp = new long[N - 1][21];
        for (int i = 0; i < N - 1; ++i) {
            numbers[i] = Integer.parseInt(st.nextToken());
            Arrays.fill(dp[i], -1);
        }

        int target = Integer.parseInt(st.nextToken());
        int index = N - 2;


        long answer = getCases(target, index, numbers);
        System.out.println(answer);
    }

    public static long getCases(int target, int index, int[] numbers) {
        if (index == 0) {
            if (target == numbers[0]) {
                return 1;
            } else {
                return 0;
            }
        }

        long count = 0;

        int nextTarget = target - numbers[index];
        if (nextTarget >= 0 && nextTarget <= 20) {
            if (dp[index][nextTarget] == -1) {
                long temp =getCases(nextTarget, index - 1, numbers);
                count += temp;
                dp[index][nextTarget] = temp;
            } else {
                count += dp[index][nextTarget];
            }
        }

        nextTarget = target + numbers[index];
        if (nextTarget >= 0 && nextTarget <= 20) {
            if (dp[index][nextTarget] == -1) {
                long temp =getCases(nextTarget, index - 1, numbers);
                count += temp;
                dp[index][nextTarget] = temp;
            } else {
                count += dp[index][nextTarget];
            }
        }

        return count;
    }
}
