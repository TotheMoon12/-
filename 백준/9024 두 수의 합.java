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

        int t = Integer.parseInt(br.readLine());
        StringBuilder builder = new StringBuilder();
        while (t-- > 0) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            final int N = Integer.parseInt(st.nextToken());
            final int K = Integer.parseInt(st.nextToken());

            st = new StringTokenizer(br.readLine());
            int[] arr = new int[N];
            for (int n = 0; n < N; ++n) {
                arr[n] = Integer.parseInt(st.nextToken());
            }

            Arrays.sort(arr);

            int count = 0;
            int closestDiff = 200000001;
            int left = 0;
            int right = N - 1;

            while (left < right) {
                int sum = arr[left] + arr[right];
                int diff = Math.abs(K - sum);
                if (diff < closestDiff) {
                    count = 1;
                    closestDiff = diff;
                } else if (diff == closestDiff) {
                    ++count;
                }

                if (sum < K) {
                    ++left;
                } else if (sum > K) {
                    --right;
                } else {
                    ++left;
                    --right;
                }
            }

            builder.append(count);
            builder.append(System.lineSeparator());
        }

        bw.write(builder.toString());
        br.close();
        bw.close();
    }
}
