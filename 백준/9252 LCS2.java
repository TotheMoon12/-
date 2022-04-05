import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        String str1 = br.readLine();
        String str2 = br.readLine();

        final int STR1_LENGTH = str1.length();
        final int STR2_LENGTH = str2.length();
        int[][][] dp = new int[STR1_LENGTH + 1][STR2_LENGTH + 1][4];

        for (int i = 1; i <= str1.length(); ++i) {
            char c = str1.charAt(i - 1);

            for (int j = 1; j <= str2.length(); ++j) {
                if (str2.charAt(j - 1) == c) {
                    dp[i][j][0] = dp[i - 1][j - 1][0] + 1;
                    dp[i][j][1] = j - 1;
                    dp[i][j][2] = i - 1;
                    dp[i][j][3] = j - 1;
                } else {
                    if (dp[i - 1][j][0] > dp[i][j][0]) {
                        dp[i][j][0] = dp[i - 1][j][0];
                        dp[i][j][1] = -1;
                        dp[i][j][2] = i - 1;
                        dp[i][j][3] = j;
                    }

                    if (dp[i][j - 1][0] > dp[i][j][0]) {
                        dp[i][j][0] = dp[i][j - 1][0];
                        dp[i][j][1] = -1;
                        dp[i][j][2] = i;
                        dp[i][j][3] = j - 1;
                    }
                }
            }
        }

        int length = dp[STR1_LENGTH][STR2_LENGTH][0];
        System.out.println(length);
        int row = STR1_LENGTH;
        int col = STR2_LENGTH;

        ArrayList<Character> lcs = new ArrayList<>();
        while (length > 0) {
            if (dp[row][col][1] != -1) {
                lcs.add(str2.charAt(dp[row][col][1]));
                --length;
            }

            int currentRow = row;
            row = dp[row][col][2];
            col = dp[currentRow][col][3];
        }

        for (int i = lcs.size() - 1; i >= 0; --i) {
            bw.write(lcs.get(i));
        }

        bw.flush();
    }
}
