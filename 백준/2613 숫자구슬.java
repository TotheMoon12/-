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
        int max = 0;
        for (int n = 0; n < N; ++n) {
            arr[n] = Integer.parseInt(st.nextToken());
            max = Math.max(max, arr[n]);
        }

        int left = max;
        int right = 100 * N;
        int answer = Integer.MAX_VALUE;
        int groupCount = 1;
        int[] group = new int[M];
        while (left <= right) {
            int mid = (left + right) / 2;

            int count = 1;
            int sum = 0;
            int[] temp = new int[M];
            int prev = 0;
            for (int n = 0; n < N; ++n) {
                sum += arr[n];

                if (sum > mid) {
                    temp[count - 1] = n - prev;
                    prev = n;
                    ++count;
                    sum = arr[n];

                    if (count > M) {
                        break;
                    }
                }
            }

            if (count <= M) {
                temp[count - 1] = N - prev;
            }

            if (count <= M) {
                groupCount = count;
                answer = mid;
                group = temp;
                right = mid - 1;
            } else {
                left = mid + 1;
            }
        }

        int back = M - 1;
        int idx = groupCount - 1;
        while (group[back] == 0) {
            if (group[idx] == 0) {
                --idx;
            } else {
                group[back--] = 1;
                --group[idx];
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append(answer);
        builder.append(System.lineSeparator());
        for (int count : group) {
            builder.append(count);
            builder.append(' ');
        }

        bw.write(builder.toString());
        br.close();
        bw.close();
    }
}
