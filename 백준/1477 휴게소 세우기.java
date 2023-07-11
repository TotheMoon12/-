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
        final int M = Integer.parseInt(st.nextToken());
        final int L = Integer.parseInt(st.nextToken());

        int[] location = new int[N + 2];
        if (N > 0) {
            st = new StringTokenizer(br.readLine());
            for (int n = 1; n <= N; ++n) {
                location[n] = Integer.parseInt(st.nextToken());
            }
        }

        location[N + 1] = L;

        Arrays.sort(location);

        int[] distance = new int[N + 1];
        int prev = 0;
        for (int n = 1; n <= N + 1; ++n) {
            distance[n - 1] = location[n] - prev;
            prev = location[n];
        }

        Arrays.sort(distance);
        int left = 1;
        int right = distance[N];

        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;
            int count = M;

            boolean isPossible = true;
            for (int n = N; n >= 0; --n) {
                int current = distance[n];
                int divideCount = current / mid;
                if (current % mid == 0) {
                    --divideCount;
                }

                if (divideCount > count) {
                    isPossible = false;
                    break;
                }

                count -= divideCount;
            }

            if (isPossible) {
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
