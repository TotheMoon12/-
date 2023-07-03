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

        final int N = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] arr = new int[N];
        for (int n = 0; n < N; ++n) {
            arr[n] = Integer.parseInt(st.nextToken());
        }

        int[] answer = new int[N];
        answer[0] = arr[0];
        int count = 1;

        for (int n = 1; n < N; ++n) {
            int pair = arr[n];

            if (pair > answer[count - 1]) {
                answer[count++] = pair;
                continue;
            }

            int left = 0;
            int right = count - 1;
            int location = 0;
            while (left <= right) {
                int mid = (left + right) / 2;
                int number = answer[mid];

                if (number > pair) {
                    location = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            answer[location] = pair;
        }

        bw.write(String.valueOf(N - count));
        br.close();
        bw.close();
    }
}
