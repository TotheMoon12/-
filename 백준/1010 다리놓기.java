import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int MAX_M = 29;

        int[] dp = new int[MAX_M + 1];
        int testCaseNum = Integer.parseInt(br.readLine());
        StringTokenizer st;
        int N;
        int M;
      
        for (int i = 0; i < testCaseNum; ++i) {
            st = new StringTokenizer(br.readLine());
            N = Integer.parseInt(st.nextToken());
            M = Integer.parseInt(st.nextToken());

            int m = M - N + 1;
            int r = 1;
            dp[0] = m / r;
            for (int j = 1; j < N; ++j) {
                ++m;
                ++r;
                dp[j] = dp[j - 1] * m / r;
            }

            System.out.println(dp[N - 1]);
        }
    }
}
