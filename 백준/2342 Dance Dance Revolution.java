import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        final int COMMAND_COUNT = st.countTokens() - 1;
        final int LOCATION_COUNT = 5;
        int[][][] dp = new int[COMMAND_COUNT][LOCATION_COUNT][LOCATION_COUNT];
        int first = Integer.parseInt(st.nextToken());
        dp[0][first][0] = 2;
        dp[0][0][first] = 2;

        int[][] movePower =
                {
                        {1, 2, 2, 2, 2},
                        {2, 1, 3, 4, 3},
                        {2, 3, 1, 3, 4},
                        {2, 4, 3, 1, 3},
                        {2, 3, 4, 3, 1}
                };

        for (int count = 1; count < COMMAND_COUNT; ++count) {
            int command = Integer.parseInt(st.nextToken());

            for (int left = 0; left < LOCATION_COUNT; ++left) {
                for (int right = 0; right < LOCATION_COUNT; ++right) {
                    int prevPower = dp[count - 1][left][right];

                    if (prevPower == 0 || left == right) {
                        continue;
                    }

                    // 두 번째 분 코드를 보니 굳이 이미 이동할 때의 생기는 power를 계산해놓았기 때문에
                    // 같은 지점을 밟은 부분을 따로 조건으로 처리해줄 필요가 없었음
                    int power = prevPower + movePower[left][command];
                    if (command != right) {
                        if (dp[count][command][right] == 0 || power < dp[count][command][right]) {
                            dp[count][command][right] = power;
                        }
                    }

                    power = prevPower + movePower[right][command];
                    if (command != left) {
                        if (dp[count][left][command] == 0 || power < dp[count][left][command]) {
                            dp[count][left][command] = power;
                        }
                    }
                }
            }
        }

        int answer = Integer.MAX_VALUE;
        for (int left = 0; left < LOCATION_COUNT; ++left) {
            for (int right = 0; right < LOCATION_COUNT; ++right) {
                int power = dp[COMMAND_COUNT - 1][left][right];
                if (power != 0) {
                    answer = Math.min(answer, power);
                }
            }
        }

        System.out.print(answer);
    }
}
