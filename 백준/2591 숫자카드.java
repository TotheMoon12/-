import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static int[] dp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final String number = br.readLine();
        dp = new int[number.length()];
        int answer = dfs(number, 0);

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }

    public static int dfs(String number, int start) {
        if (start >= number.length()) {
            return 1;
        }

        if (number.charAt(start) == '0') {
            return 0;
        }

        if (dp[start] != 0) {
            return dp[start];
        }

        int count = dfs(number, start + 1);
        if (start + 1 < number.length() && Integer.parseInt(number.substring(start, start + 2)) <= 34) {
            count += dfs(number, start + 2);
        }

        dp[start] = count;
        return count;
    }
}
