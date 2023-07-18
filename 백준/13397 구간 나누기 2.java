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

        st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int n = 0; n < N; ++n) {
            arr[n] = Integer.parseInt(st.nextToken());
        }

        int left = 0;
        int right = 10000;
        int answer = 10000;

        while (left <= right) {
            int mid = (left + right) / 2;

            int min = arr[0];
            int max = arr[0];
            int count = 1;

            for (int n = 0; n < N; ++n) {
                min = Math.min(min, arr[n]);
                max = Math.max(max, arr[n]);
                if (max - min > mid) {
                    max = arr[n];
                    min = arr[n];
                    ++count;
                }
            }

            if (count <= M) {
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
