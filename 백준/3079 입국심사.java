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

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        int[] panel = new int[N];
        for (int n = 0; n < N; ++n) {
            panel[n] = Integer.parseInt(br.readLine());
        }

        long answer = 0L;
        long left = 0;
        long right = M / N;
        if (M % N != 0) {
            ++right;
        }

        right *= 1000000000L;

        while (left <= right) {
            long mid = (left + right) / 2;

            long count = 0L;
            for (int n = 0; n < N; ++n) {
                count += mid / panel[n];
            }

            if (count >= M) {
                answer = mid;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
