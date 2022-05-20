import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] dp = new int[n + 1];
        dp[1] = 1;
        for (int idx = 2; idx <= n; ++idx) {
            int min = 4;
            for (int sIdx = 1; sIdx * sIdx <= idx; ++sIdx) {
                int number = sIdx * sIdx;
                int count = dp[idx - number] + 1;
                min = Math.min(min, count);
            }

            dp[idx] = min;            
        }

        System.out.println(dp[n]);
    }
}
