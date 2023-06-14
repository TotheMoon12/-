import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final int N = Integer.parseInt(br.readLine());
        final int k = Integer.parseInt(br.readLine());

        int left = 1;
        int right = 1000000000;
        int answer = 0;

        while (left <= right) {
            int mid = (left + right) / 2;

            long count = 0;
            for (int n = 1; n <= N; ++n) {
                count += Math.min(N, mid / n);
            }

            if (count >= k) {
                answer = mid;
                right = mid - 1;
            } else if (count < k) {
                left = mid + 1;
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
