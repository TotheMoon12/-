import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));


        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        final int MAX_COUPON = (N / 5) * 2 + 3;
        int[][] dp = new int[N + 6][MAX_COUPON + 1];

        boolean[] impossibleDay = new boolean[N + 1];
        if (M > 0) {
            st = new StringTokenizer(br.readLine());

            for (int m = 1; m <= M; ++m) {
                int day = Integer.parseInt(st.nextToken());
                impossibleDay[day] = true;
            }
        }

        int firstDay = N + 1;
        for (int day = 1; day <= N; ++day) {
            if (!impossibleDay[day]) {
                firstDay = day;

                dp[day + 1][0] = 10000;
                dp[day + 3][1] = 25000;
                dp[day + 5][2] = 37000;
                break;
            }
        }

        for (int day = firstDay + 1; day <= N; ++day) {
            if (impossibleDay[day]) {
                for (int coupon = 0; coupon <= MAX_COUPON; ++coupon) {
                    if (dp[day][coupon] == 0) {
                        continue;
                    }

                    int nextDay = day + 1;
                    if (dp[nextDay][coupon] == 0) {
                        dp[nextDay][coupon] = dp[day][coupon];
                    } else {
                        dp[nextDay][coupon] = Math.min(dp[nextDay][coupon], dp[day][coupon]);
                    }
                }
            } else {
                for (int coupon = 0; coupon <= MAX_COUPON; ++coupon) {
                    if (dp[day][coupon] == 0) {
                        continue;
                    }

                    int nextDay = day + 1;
                    if (dp[nextDay][coupon] == 0) {
                        dp[nextDay][coupon] = dp[day][coupon] + 10000;
                    } else {
                        dp[nextDay][coupon] = Math.min(dp[nextDay][coupon], dp[day][coupon] + 10000);
                    }

                    nextDay = day + 3;
                    if (dp[nextDay][coupon + 1] == 0) {
                        dp[nextDay][coupon + 1] = dp[day][coupon] + 25000;
                    } else {
                        dp[nextDay][coupon + 1] = Math.min(dp[nextDay][coupon + 1], dp[day][coupon] + 25000);
                    }


                    nextDay = day + 5;
                    if (dp[nextDay][coupon + 2] == 0) {
                        dp[nextDay][coupon + 2] = dp[day][coupon] + 37000;
                    } else {
                        dp[nextDay][coupon + 2] = Math.min(dp[nextDay][coupon + 2], dp[day][coupon] + 37000);
                    }

                    if (coupon >= 3) {
                        nextDay = day + 1;

                        if (dp[nextDay][coupon - 3] == 0) {
                            dp[nextDay][coupon - 3] = dp[day][coupon];
                        } else {
                            dp[nextDay][coupon - 3] = Math.min(dp[nextDay][coupon - 3], dp[day][coupon]);
                        }
                    }
                }
            }
        }

        int answer = 0;
        for (int day = N + 1; day < N + 6; ++day) {
            for (int coupon = 0; coupon <= MAX_COUPON; ++coupon) {
                if (dp[day][coupon] == 0) {
                    continue;
                }

                if (answer == 0) {
                    answer = dp[day][coupon];
                } else {
                    answer = Math.min(answer, dp[day][coupon]);
                }
            }
        }

        System.out.print(answer);
    }
}
