import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int passengerCarCount = Integer.parseInt(br.readLine());
        int[] passengers = new int[passengerCarCount];
        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int idx = 0; idx < passengerCarCount; ++idx) {
            passengers[idx] = Integer.parseInt(st.nextToken());
        }

        final int MAX_TRANSPORT_PASSENGER_CAR_COUNT = Integer.parseInt(br.readLine());
        final int SELECT_COUNT = 3;
        int[][] dp = new int[SELECT_COUNT + 1][passengerCarCount];

        int sum = 0;
        int count = 0;
        for (int idx = passengerCarCount - 1; idx >= 0; --idx) {
            ++count;
            sum += passengers[idx];

            if (count > MAX_TRANSPORT_PASSENGER_CAR_COUNT) {
                sum -= passengers[idx + MAX_TRANSPORT_PASSENGER_CAR_COUNT];
            }

            dp[1][idx] = sum;
        }


        for (int select = 2; select <= SELECT_COUNT; ++select) {
            int max = 0;
            for (int idx = passengerCarCount - 1; idx >= 0; --idx) {
                dp[select][idx] = dp[1][idx];

                if (idx + MAX_TRANSPORT_PASSENGER_CAR_COUNT < passengerCarCount) {
                    max = Math.max(max, dp[select - 1][idx + MAX_TRANSPORT_PASSENGER_CAR_COUNT]);
                    dp[select][idx] += max;
                }
            }
        }

        int answer = 0;
        for (int idx = 0; idx < passengerCarCount; ++idx) {
            answer = Math.max(answer, dp[SELECT_COUNT][idx]);
        }

        System.out.print(answer);
    }
}
