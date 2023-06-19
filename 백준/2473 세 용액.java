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
        long[] arr = new long[N];
        for (int n = 0; n < N; ++n) {
            arr[n] = Long.parseLong(st.nextToken());
        }

        Arrays.sort(arr);

        long firstAnswer = arr[0];
        long secondAnswer = arr[1];
        long thirdAnswer = arr[2];
        long answer = 3000000000L;
        for (int first = 0; first < N - 2; ++first) {
            int small = first + 1;
            int big = N - 1;
            int front = small;
            int back = N - 1;

            long firstValue = arr[first];
            while (front < back - 1) {
                long current = Math.abs(firstValue + arr[small] + arr[big]);
                long case1 = Math.abs(firstValue + arr[front + 1] + arr[back]);
                long case2 = Math.abs(firstValue + arr[front] + arr[back - 1]);

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

            long result = Math.abs(firstValue + arr[small] + arr[big]);
            if (result < answer) {
                firstAnswer = firstValue;
                secondAnswer = arr[small];
                thirdAnswer = arr[big];
                answer = result;
            }
        }

        StringBuilder builder = new StringBuilder();
        builder.append(firstAnswer)
                .append(' ')
                .append(secondAnswer)
                .append(' ')
                .append(thirdAnswer);
        bw.write(builder.toString());
        br.close();
        bw.close();
    }
}
