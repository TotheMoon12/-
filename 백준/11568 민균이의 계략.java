import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());
        int[] dp = new int[N];
        int[] numbers = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        int answer = 0;
        for (int n = 0; n < N; ++n) {
            numbers[n] = Integer.parseInt(st.nextToken());

            int max = 0;
            for (int prev = n - 1; prev >= 0; --prev) {
                if (numbers[prev] < numbers[n]) {
                    max = Math.max(dp[prev], max);
                }
            }

            dp[n] = max + 1;
            answer = Math.max(dp[n], answer);
        }

        bw.write(Integer.toString(answer));
        br.close();
        bw.close();
    }
}
