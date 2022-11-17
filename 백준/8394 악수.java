import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        final int N = Integer.parseInt(br.readLine());

        // 악수는 한 번에 한 사람과만 할 수 있다
        // 따라서 왼쪽 사람과 악수하면 오른쪽 사람과는 악수못한다. 한순간의 경우이기 때문에
        int[][] dp = new int[2][2];
        final int YES = 0;
        final int NO = 1;

        dp[0][NO] = 1;
        int index = 1;
        int prev = 0;
        for (int n = 1; n < N; ++n) {
            dp[index][YES] = dp[prev][NO];
            dp[index][YES] %= 10;
            dp[index][NO] = dp[prev][YES] + dp[prev][NO];
            dp[index][NO] %= 10;

            index = (index + 1) % 2;
            prev = (prev + 1) % 2;
        }

        System.out.println((dp[prev][YES] + dp[prev][NO]) % 10);
        br.close();
    }
}
