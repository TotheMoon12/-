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

                    int power;
                    if (left == command || right == command) {
                        power = prevPower + 1;

                        if (left == command) {
                            if (dp[count][command][right] != 0) {
                                dp[count][command][right] = Math.min(dp[count][command][right], power);
                            } else {
                                dp[count][command][right] = power;
                            }
                        } else {
                            if (dp[count][left][command] != 0) {
                                dp[count][left][command] = Math.min(dp[count][left][command], power);
                            } else {
                                dp[count][left][command] = power;
                            }
                        }
                    } else {
                        power = prevPower + movePower[left][command];

                        if (command != right) {
                            if (dp[count][command][right] != 0) {
                                dp[count][command][right] = Math.min(dp[count][command][right], power);
                            } else {
                                dp[count][command][right] = power;
                            }
                        }

                        power = prevPower + movePower[right][command];
                        if (command != left) {
                            if (dp[count][left][command] != 0) {
                                dp[count][left][command] = Math.min(dp[count][left][command], power);
                            } else {
                                dp[count][left][command] = power;
                            }
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
