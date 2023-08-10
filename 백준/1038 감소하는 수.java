import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    static final int MAX_LENGTH = 10;
    static final int MAX_DIGIT = 9;
    static int[][] dp = new int[MAX_LENGTH + 1][MAX_DIGIT + 1];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        int N = Integer.parseInt(br.readLine());

        long total = 10;
        for (int i = 0; i <= MAX_DIGIT; ++i) {
            dp[1][i] = 1;
        }

        for (int length = 2; length <= MAX_LENGTH; ++length) {
            for (int digit = 1; digit <= MAX_DIGIT; ++digit) {
                for (int prev = 0; prev < digit; ++prev) {
                    dp[length][digit] += dp[length - 1][prev];

                }

                total += dp[length][digit];
            }
        }

        if (N >= total) {
            bw.write("-1");
            br.close();
            bw.close();
            return;
        }

        if (N == 0) {
            bw.write("0");
            br.close();
            bw.close();
            return;
        }

        StringBuilder builder = new StringBuilder(MAX_LENGTH);
        int length = 1;
        int count = 0;
        int number = 0;
        while (count <= N && length <= MAX_LENGTH) {
            count += dp[length][number];
            number = (number + 1) % 10;
            if (number == 0) {
                ++length;
            }
        }

        --number;
        if (number == -1) {
            number = 9;
            --length;
        }

        count -= dp[length][number];
        builder.append(number);
        N -= count;
        --length;

        int prevNumber = number;
        while (N > 0) {
            count = 0;
            int startNumber;
            if (prevNumber == length) {
                startNumber = prevNumber - 1;
            } else {
                startNumber = length - 1;
            }

            while (startNumber < prevNumber && count <= N) {
                count += dp[length][startNumber++];
            }

            if (count >= N) {
                --startNumber;
            }

            if (count > N) {
                count -= dp[length][startNumber];
            }

            builder.append(startNumber);
            --length;
            prevNumber = startNumber;
            N -= count;
        }

        if (N == 0) {
            while (length > 0) {
                builder.append(length - 1);
                --length;
            }
        }

        bw.write(builder.toString());
        br.close();
        bw.close();
    }
}
