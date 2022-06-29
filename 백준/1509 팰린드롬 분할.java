import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String s = br.readLine();
        final int LENGTH = s.length();
        boolean[][] isPelin = new boolean[LENGTH][LENGTH];

        for (int length = 0; length < LENGTH; ++length) {
            for (int idx = 0; idx + length < LENGTH; ++idx) {
                if (s.charAt(idx) == s.charAt(idx + length)) {
                    if (length > 1) {
                        if (isPelin[idx + 1][idx + length - 1]) {
                            isPelin[idx][idx + length] = true;
                        }
                    } else {
                        isPelin[idx][idx + length] = true;
                    }
                }
            }
        }

        int[] dp = new int[LENGTH];
        for (int idx = 0; idx < LENGTH; ++idx) {
            int min = LENGTH;

            if (isPelin[0][idx]) {
                dp[idx] = 1;
            } else {
                for (int start = 0; start < idx; ++start) {
                    if (isPelin[start + 1][idx]) {
                        min = Math.min(min, dp[start] + 1);
                    }
                }

                dp[idx] = min;
            }
        }

        System.out.println(dp[LENGTH - 1]);
    }
}
