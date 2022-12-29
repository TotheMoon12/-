import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());
        String s = br.readLine();
        int[] dp = new int[N];

        for (int idx = 0; idx < N; ++idx) {
            if (idx != 0 && dp[idx] == 0) {
                continue;
            }

            char cur = s.charAt(idx);

            char nextChar;
            if (cur == 'B') {
                nextChar = 'O';
            } else if (cur == 'O') {
                nextChar = 'J';
            } else {
                nextChar = 'B';
            }

            for (int next = idx + 1; next < N; ++next) {
                char c = s.charAt(next);

                if (c == nextChar) {
                    int energy = (next - idx) * (next - idx);
                    if (dp[next] == 0) {
                        dp[next] = dp[idx] + energy;
                    } else {
                        dp[next] = Math.min(dp[next], dp[idx] + energy);
                    }
                }
            }
        }

        int answer;
        if (dp[N - 1] == 0) {
            answer = -1;
        } else {
            answer = dp[N - 1];
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
