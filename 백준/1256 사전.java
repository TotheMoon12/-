import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        BigInteger[][] dp = new BigInteger[M + 1][N + 1];
        for (int m = 1; m <= M; ++m) {
            dp[m][0] = new BigInteger("1");

            for (int n = 1; n <= N; ++n) {
                dp[m][n] = dp[m][n - 1].multiply(new BigInteger(Integer.toString(m + n)));
                dp[m][n] = dp[m][n].divide(new BigInteger(Integer.toString(n)));
            }
        }

        BigInteger target = new BigInteger(Integer.toString(K));
        if (target.compareTo(dp[M][N]) == 1) {
            System.out.println(-1);
        } else {
            int n = N;
            int m = M;
            StringBuilder answer = new StringBuilder();
            while (m > 0 && n > 0) {
                if (target.compareTo(dp[m][n - 1]) != 1) {
                    answer.append('a');
                    --n;
                } else {
                    answer.append('z');
                    target = target.subtract(dp[m][n - 1]);
                    --m;
                }
            }

            while (n-- > 0) {
                answer.append('a');
            }

            while (m-- > 0) {
                answer.append('z');
            }

            System.out.println(answer.toString());
        }
    }
}
