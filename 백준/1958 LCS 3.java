import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s1 = br.readLine();
        String s2 = br.readLine();
        String s3 = br.readLine();

        final int S1_LENGTH = s1.length();
        final int S2_LENGTH = s2.length();
        final int S3_LENGTH = s3.length();
        int[][][] dp = new int[S1_LENGTH + 1][S2_LENGTH + 1][S3_LENGTH + 1];

        for (int s1Idx = 1; s1Idx <= S1_LENGTH; ++s1Idx) {
            char s1Char = s1.charAt(s1Idx - 1);
            for (int s2Idx = 1; s2Idx <= S2_LENGTH; ++s2Idx) {
                char s2Char = s2.charAt(s2Idx - 1);

                for (int s3Idx = 1; s3Idx <= S3_LENGTH; ++s3Idx) {
                    char s3Char = s3.charAt(s3Idx - 1);

                    int count;
                    if (s1Char == s2Char && s2Char == s3Char) {
                        count = Math.max(dp[s1Idx - 1][s2Idx - 1][s3Idx - 1] + 1, dp[s1Idx][s2Idx - 1][s3Idx]);
                        count = Math.max(count, dp[s1Idx][s2Idx][s3Idx - 1]);
                    } else {
                        count = Math.max(dp[s1Idx][s2Idx - 1][s3Idx], dp[s1Idx][s2Idx][s3Idx - 1]);
                    }
                    count = Math.max(count, dp[s1Idx - 1][s2Idx][s3Idx]);

                    dp[s1Idx][s2Idx][s3Idx] = count;
                }
            }
        }

        System.out.println(dp[S1_LENGTH][S2_LENGTH][S3_LENGTH]);
    }
}
