import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] maze = new int[N];
        for (int idx = 0; idx < N; ++idx) {
            maze[idx] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[N];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for (int idx = 0; idx < N; ++idx) {
            int count = dp[idx];
            if (count != -1) {
                final int MAX_JUMP = maze[idx];
                int nextCount = count + 1;
                for (int jump = 1; jump <= MAX_JUMP; ++jump) {
                    int next = idx + jump;
                    if (next < N) {
                        if (dp[next] == -1) {
                            dp[next] = nextCount;
                        } else {
                            dp[next] = Math.min(dp[next], nextCount);
                        }
                    }
                }
            }
        }

        System.out.println(dp[N - 1]);
    }
}
