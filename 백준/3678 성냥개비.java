import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        // 0~9를 만드는데 필요한 성냥개비 수
        int[] numbers = {6, 2, 5, 5, 4, 5, 6, 3, 7, 6};
        final int MAX_N = 100;
        long[] dp = new long[MAX_N + 1];
        dp[1] = -1;

        for (int n = 2; n <= MAX_N; ++n) {
            dp[n] = Long.MAX_VALUE;
            for (int idx = 1; idx < numbers.length; ++idx) {
                int matchstickCount = numbers[idx];

                int tempN = n - matchstickCount;
                StringBuilder minBuilder = new StringBuilder();
                minBuilder.append(idx);

                if (tempN == 0) {
                    long number = Long.parseLong(minBuilder.toString());
                    dp[n] = Math.min(dp[n], number);
                } else if (tempN > 0 && dp[tempN] != -1) {
                    minBuilder.append(dp[tempN]);

                    long number = Long.parseLong(minBuilder.toString());
                    dp[n] = Math.min(dp[n], number);
                }

                // 뒤에 0이 들어가는 경우
                minBuilder = new StringBuilder();
                minBuilder.append(idx);
                while (tempN > 0) {
                    tempN -= numbers[0];

                    minBuilder.append(0);
                    if (tempN == 0) {
                        long number = Long.parseLong(minBuilder.toString());
                        dp[n] = Math.min(dp[n], number);
                    } else if (tempN > 0 && dp[tempN] != -1) {
                        minBuilder.append(dp[tempN]);

                        long number = Long.parseLong(minBuilder.toString());
                        dp[n] = Math.min(dp[n], number);
                        minBuilder.setLength(minBuilder.length() - String.valueOf(dp[tempN]).length());
                    } else {
                        break;
                    }
                }
            }
        }

        final int T = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder(T);
        for (int t = 0; t < T; ++t) {
            final int N = Integer.parseInt(br.readLine());

            builder.append(dp[N]);
            builder.append(' ');

            int count;
            if (N % 2 == 0) {
                count = N / 2;
            } else {
                builder.append('7');
                count = (N - numbers[7]) / 2;
            }

            while (count-- > 0) {
                builder.append('1');
            }

            builder.append(System.lineSeparator());
        }

        bw.write(builder.toString());
        br.close();
        bw.close();
    }
}
