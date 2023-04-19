import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final int N = Integer.parseInt(br.readLine());
        int[] numbers = new int[N];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; ++n) {
            numbers[n] = Integer.parseInt(st.nextToken());
        }

        int[] left = new int[N];
        int[] right = new int[N];
        left[0] = numbers[0];
        right[0] = numbers[N - 1];

        for (int n = 1; n < N - 1; ++n) {
            if (left[n - 1] > numbers[n]) {
                left[n] = getGCD(left[n - 1], numbers[n]);
            } else {
                left[n] = getGCD(numbers[n], left[n - 1]);
            }

            if (right[N - n] > numbers[N - n - 1]) {
                right[n] = getGCD(right[n - 1], numbers[N - n - 1]);
            } else {
                right[n] = getGCD(numbers[N - n - 1], right[n - 1]);
            }
        }

        int maxGCD = -1;
        int number = -1;

        if (numbers[0] % right[N - 2] != 0) {
            maxGCD = right[N - 2];
            number = numbers[0];
        }

        if (numbers[N - 1] % left[N - 2] != 0 && left[N - 2] > maxGCD) {
            maxGCD = left[N - 2];
            number = numbers[N - 1];
        }

        for (int n = 1; n < N - 1; ++n) {
            int gcd = getGCD(left[n - 1], right[N - n - 2]); // n + 1

            if (numbers[n] % gcd != 0 && gcd > maxGCD) {
                maxGCD = gcd;
                number = numbers[n];
            }
        }

        if (maxGCD == -1) {
            bw.write("-1");
        } else {
            bw.write(String.valueOf(maxGCD) + " " + String.valueOf(number));
        }

        br.close();
        bw.close();
    }

    public static int getGCD(int big, int small) {
        int mod = big % small;

        while (mod != 0) {
            big = small;
            small = mod;

            mod = big % small;
        }

        return small;
    }
}
