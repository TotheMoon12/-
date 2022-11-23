import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int[][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());
        StringTokenizer st = new StringTokenizer(br.readLine());

        int[] numbers = new int[N];
        for (int n = 0; n < N; ++n) {
            numbers[n] = Integer.parseInt(st.nextToken());
        }

        dp = new int[N][N];
        for (int n = 0; n < N; ++n) {
            Arrays.fill(dp[n], -1);
        }

        int answer = dfs(numbers, 0, N - 1);
        bw.write(Integer.toString(answer));
        br.close();
        bw.close();
    }

    public static int dfs(int[] numbers, int front, int back) {
        if (dp[front][back] != -1) {
            return dp[front][back];
        }

        if (front >= back) {
            return 0;
        }

        int count;
        if (numbers[front] != numbers[back]) {
            count = dfs(numbers, front + 1, back) + 1;
            count = Math.min(count, dfs(numbers, front, back - 1) + 1);
        } else {
            count = dfs(numbers, front + 1, back - 1);
        }

        dp[front][back] = count;
        return count;
    }
}
