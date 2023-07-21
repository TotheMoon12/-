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

        long answer = 0L;
        for (int i = 0; i < N - 2; ++i) {
            if (arr[i] > 0) {
                break;
            }

            int target = -arr[i];

            int front = i + 1;
            int back = N - 1;

            while (front < back) {
                int sum = arr[front] + arr[back];

                if (sum == target) {
                    int currentFrontValue = arr[front];
                    int currentBackValue = arr[back];
                    if (currentFrontValue == currentBackValue) {
                        int count = back - front + 1;
                        answer += (long) count * (count - 1) / 2;
                        break;
                    }

                    int currentFront = front;
                    int currentBack = back;
                    while (arr[front] == currentFrontValue) {
                        ++front;
                    }

                    while (arr[back] == currentBackValue) {
                        --back;
                    }

                    answer += (front - currentFront) * (long) (currentBack - back);
                } else if (sum < target) {
                    ++front;
                } else {
                    --back;
                }
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
