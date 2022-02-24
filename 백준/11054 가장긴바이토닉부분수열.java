import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] seq = new int[N];
        for (int i = 0; i < N; ++i) {
            seq[i] = Integer.parseInt(st.nextToken());
        }

        int[][][][] dp = new int[N][2][2][2];
        final int UP = 0;
        final int DOWN = 1;
        final int YES = 0;
        final int NO = 1;
        final int LENGTH = 0;
        final int INDEX = 1;
        dp[0][YES][UP][LENGTH] = 1;
        dp[0][YES][UP][INDEX] = 0;

        dp[0][YES][DOWN][LENGTH] = 1;
        dp[0][YES][DOWN][INDEX] = 0;

        dp[0][NO][UP][LENGTH] = 0;
        dp[0][NO][UP][INDEX] = 0;

        dp[0][NO][DOWN][LENGTH] = 0;
        dp[0][NO][DOWN][INDEX]= 0;

        int max = 1;
        for (int i = 1; i < N; ++i) {
            int downMax = 0;
            int downIndex = i;
            int upMax = 0;
            int upIndex = i;

            for (int j = i - 1; j >= 0; --j) {
                if (seq[i] > seq[j]) {
                    if (dp[j][YES][UP][LENGTH] > upMax) {
                        upMax = dp[j][YES][UP][LENGTH];
                    }

                } else if (seq[i] < seq[j]) {
                    if (dp[j][YES][DOWN][LENGTH] > downMax) {
                        downMax = dp[j][YES][DOWN][LENGTH];
                    }

                    if (dp[j][YES][UP][LENGTH] > downMax) {
                        downMax = dp[j][YES][UP][LENGTH];
                    }
                }

                if (dp[j][YES][UP][LENGTH] > dp[i][NO][UP][LENGTH]) {
                    dp[i][NO][UP][LENGTH] = dp[j][YES][UP][LENGTH];
                    dp[i][NO][UP][INDEX] = j;
                }

                if (dp[j][NO][UP][LENGTH] > dp[i][NO][UP][LENGTH]) {
                    dp[i][NO][UP][LENGTH] = dp[j][NO][UP][LENGTH];
                    dp[i][NO][UP][INDEX] = j;
                }

                if (dp[j][YES][DOWN][LENGTH] > dp[i][NO][DOWN][LENGTH]) {
                    dp[i][NO][DOWN][LENGTH] = dp[j][YES][DOWN][LENGTH];
                    dp[i][NO][DOWN][INDEX] = j;
                }

                if (dp[j][NO][DOWN][LENGTH] > dp[i][NO][DOWN][LENGTH]) {
                    dp[i][NO][DOWN][LENGTH] = dp[j][NO][DOWN][LENGTH];
                    dp[i][NO][DOWN][INDEX] = j;
                }
            }

            dp[i][YES][UP][LENGTH] = upMax + 1;
            dp[i][YES][UP][INDEX] = i;
            dp[i][YES][DOWN][LENGTH] = downMax + 1;
            dp[i][YES][DOWN][INDEX] = i;

            max = Math.max(max, dp[i][YES][UP][LENGTH]);
            max = Math.max(max, dp[i][YES][DOWN][LENGTH]);
            max = Math.max(max, dp[i][NO][UP][LENGTH]);
            max = Math.max(max, dp[i][NO][DOWN][LENGTH]);
        }

        System.out.println(max);
    }
}
