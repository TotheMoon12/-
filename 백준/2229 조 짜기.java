import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] scores = new int[N];
        for (int n = 0; n < N; ++n) {
            int score = Integer.parseInt(st.nextToken());
            scores[n] = score;
        }

        int[][][] dp = new int[N][2][3];

        final int SCORE = 0;
        final int MIN = 1;
        final int MAX = 2;
        dp[0][0][MIN] = scores[0];
        dp[0][0][MAX] = scores[0];


        final int YES = 0;
        final int NO = 1;
        for (int n = 1; n < N; ++n) {
            dp[n][NO][SCORE] = Math.max(dp[n - 1][NO][SCORE], dp[n - 1][YES][SCORE]);

            int yesNewMax = Math.max(dp[n - 1][YES][MAX], scores[n]);
            int yesNewMin = Math.min(dp[n - 1][YES][MIN], scores[n]);
            int yesScore = dp[n - 1][YES][SCORE] + yesNewMax - yesNewMin - (dp[n - 1][YES][MAX] - dp[n - 1][YES][MIN]);

            int noNewMax = Math.max(scores[n], scores[n - 1]);
            int noNewMin = Math.min(scores[n], scores[n - 1]);
            int noScore = dp[n - 1][NO][SCORE] + noNewMax - noNewMin;
            if (yesScore > noScore) {
                dp[n][YES][SCORE] = yesScore;
                dp[n][YES][MIN] = yesNewMin;
                dp[n][YES][MAX] = yesNewMax;
            } else {
                dp[n][YES][SCORE] = noScore;
                dp[n][YES][MIN] = noNewMin;
                dp[n][YES][MAX] = noNewMax;
            }
        }

        bw.write(Integer.toString(dp[N - 1][YES][SCORE]));
        br.close();
        bw.close();
    }
}
