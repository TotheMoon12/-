import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int R = Integer.parseInt(st.nextToken());
        final int C = Integer.parseInt(st.nextToken());
        final int W = Integer.parseInt(st.nextToken());

        final int LAST_LINE = R + W;
        int[][] dp = new int[LAST_LINE + 1][LAST_LINE + 1];
        dp[1][1] = 1;
        for (int idx = 2; idx <= LAST_LINE; ++idx) {
            dp[idx][1] = dp[idx - 1][1];
            dp[idx][idx] = dp[idx - 1][idx - 1];
            for (int sIdx = 2; sIdx < idx; ++sIdx) {
                dp[idx][sIdx] = dp[idx - 1][sIdx - 1] + dp[idx - 1][sIdx];
            }
        }

        int answer = 0;
        for (int w = 0; w < W; ++w) {
            for (int idx = C; idx <= C + w; ++idx) {
                answer += dp[R + w][idx];
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
