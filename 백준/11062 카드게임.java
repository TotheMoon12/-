import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int[][][] dp;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int T = Integer.parseInt(br.readLine());

        StringBuilder answer = new StringBuilder();
        for (int t = 0; t < T; ++t) {
            int N = Integer.parseInt(br.readLine());

            StringTokenizer st = new StringTokenizer(br.readLine());
            int[] cards = new int[N];

            for (int n = 0; n < N; ++n) {
                cards[n] = Integer.parseInt(st.nextToken());
            }

            dp = new int[N][N][2];
            game(cards, 0, 0, N - 1);
            answer.append(dp[0][N - 1][0]);
            answer.append(System.lineSeparator());
        }

        System.out.print(answer);
    }

    public static void game(int[] cards, int turn, int left, int right) {
        int self = 0;
        int opponent = 1;

        if (left == right) {
            dp[left][right][self] = cards[left];
            return;
        } else {
            if (dp[left + 1][right][self] == 0 && dp[left + 1][right][opponent] == 0) {
                game(cards, turn + 1, left + 1, right);
            }

            if (dp[left][right - 1][self] == 0 && dp[left][right - 1][opponent] == 0) {
                game(cards, turn + 1, left, right - 1);
            }

            int leftScore = dp[left + 1][right][opponent] + cards[left];
            int rightScore = dp[left][right - 1][opponent] + cards[right];

            if (leftScore > rightScore) {
                dp[left][right][self] = leftScore;
                dp[left][right][opponent] = dp[left + 1][right][self];
            } else {
                dp[left][right][self] = rightScore;
                dp[left][right][opponent] = dp[left][right - 1][self];
            }
        }
    }
}
