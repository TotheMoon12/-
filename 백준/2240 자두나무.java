import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int T = Integer.parseInt(st.nextToken());
        int W = Integer.parseInt(st.nextToken());

        int[] locations = new int[T + 1];
        for (int t = 1; t <= T; ++t) {
            locations[t] = Integer.parseInt(br.readLine()) - 1;
        }

        final int LEFT = 0;
        final int RIGHT = 1;
        int[][][] dp = new int[T + 1][W + 1][2];
        for (int w= 0; w <= W; ++w) {
            Arrays.fill(dp[1][w], -1);
        }


        if (locations[1] == LEFT) {
            dp[1][0][LEFT] = 1;
        } else {
            dp[1][0][LEFT] = 0;
            dp[1][1][RIGHT] = 1;
        }

        for (int t = 2; t <= T; ++t) {
            dp[t][0][LEFT] = dp[t - 1][0][LEFT];
            dp[t][0][RIGHT] = -1;

            if (locations[t] == LEFT) {
                ++dp[t][0][LEFT];

                for (int w = 1; w <= W; ++w) {
                    dp[t][w][LEFT] = -1;
                    dp[t][w][RIGHT] = -1;

                    if (dp[t - 1][w - 1][RIGHT] != -1) {
                        dp[t][w][LEFT] = dp[t - 1][w - 1][RIGHT] + 1;
                    }

                    if (dp[t - 1][w][LEFT] != -1) {
                        dp[t][w][LEFT] = Math.max(dp[t][w][LEFT], dp[t - 1][w][LEFT] + 1);
                    }

                    if (dp[t - 1][w - 1][LEFT] != -1) {
                        dp[t][w][RIGHT] = dp[t - 1][w - 1][LEFT];
                    }

                    if (dp[t - 1][w][RIGHT] != -1) {
                        dp[t][w][RIGHT] = Math.max(dp[t][w][RIGHT], dp[t - 1][w][RIGHT]);
                    }
                }
            } else {
                for (int w = 1; w <= W; ++w) {
                    dp[t][w][LEFT] = -1;
                    dp[t][w][RIGHT] = -1;

                    if (dp[t - 1][w - 1][RIGHT] != -1) {
                        dp[t][w][LEFT] = dp[t - 1][w - 1][RIGHT];
                    }

                    if (dp[t - 1][w][LEFT] != -1) {
                        dp[t][w][LEFT] = Math.max(dp[t][w][LEFT], dp[t - 1][w][LEFT]);
                    }

                    if (dp[t - 1][w - 1][LEFT] != -1) {
                        dp[t][w][RIGHT] = dp[t - 1][w - 1][LEFT] + 1;
                    }

                    if (dp[t - 1][w][RIGHT] != -1) {
                        dp[t][w][RIGHT] = Math.max(dp[t][w][RIGHT], dp[t - 1][w][RIGHT] + 1);
                    }
                }
            }
        }

        int answer = 0;
        for (int w = 0; w <= W; ++w) {
            answer = Math.max(answer, dp[T][w][LEFT]);
            answer = Math.max(answer, dp[T][w][RIGHT]);
        }

        System.out.println(answer);
    }
}
