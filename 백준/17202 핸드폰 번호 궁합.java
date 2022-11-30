import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        String number1 = br.readLine();
        String number2 = br.readLine();

        int length = 16;
        int[] dp = new int[length];

        for (int idx = 0; idx < number1.length(); ++idx) {
            dp[idx * 2] = number1.charAt(idx) - '0';
            dp[idx * 2 + 1] = number2.charAt(idx) - '0';
        }

        --length;
        for (; length >= 2; --length) {
            for (int idx = 0; idx < length; ++idx) {
                dp[idx] = dp[idx] + dp[idx + 1];
                dp[idx] %= 10;
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append(dp[0]);
        builder.append(dp[1]);
        bw.write(builder.toString());
        br.close();
        bw.close();
    }
}
