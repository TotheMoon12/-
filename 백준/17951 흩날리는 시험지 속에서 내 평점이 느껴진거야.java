import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        StringTokenizer st = new StringTokenizer(br.readLine());
        final int N = Integer.parseInt(st.nextToken());
        final int K = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] scores = new int[N];
        for (int n = 0; n < N; ++n) {
            scores[n] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 20 * N;

        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;

            int count = 0;
            int sum = 0;
            for (int n = 0; n < N; ++n) {
                sum += scores[n];

                if (sum >= mid) {
                    ++count;
                    sum = 0;
                }
            }

            if (count >= K) {
                answer = mid;
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
