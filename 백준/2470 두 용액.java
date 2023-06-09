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

        Arrays.sort(arr);

        int small = 0;
        int big = N - 1;
        int front = 0;
        int back = N - 1;

        while (front < back - 1) {
            int current = Math.abs(arr[small] + arr[big]);
            int case1 = Math.abs(arr[front + 1] + arr[back]);
            int case2 = Math.abs(arr[front] + arr[back - 1]);

            int count = 0;
            if (case1 < current) {
                small = front + 1;
                big = back;
                current = case1;
                ++front;
                ++count;
            }

            if (case2 < current) {
                small = front;
                big = back - 1;
                --back;
                ++count;
            }

            if (count == 0) {
                if (case1 < case2) {
                    ++front;
                } else {
                    --back;
                }
            }
        }

        bw.write(arr[small] + " " + arr[big]);
        br.close();
        bw.close();
    }
}
