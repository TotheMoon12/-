import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());

        long[] t = new long[N + 1];
        t[0] = 1;

        for (int n = 1; n <= N; ++n) {
            int start = 0;
            int end = n - 1;

            long value = 0;
            while (start <= n - 1) {
                value += t[start++] * t[end--];
            }

            t[n] = value;
        }

        System.out.print(t[N]);
    }
}
