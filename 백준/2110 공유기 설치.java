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
        final int C = Integer.parseInt(st.nextToken());
        int[] xArr = new int[N];
        for (int n = 0; n < N; ++n) {
            xArr[n] = Integer.parseInt(br.readLine());
        }
        Arrays.sort(xArr);

        int left = 0;
        int right = 1000000000;

        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;

            int count = C - 1;
            int distance = 0;
            for (int idx = 1; idx < N; ++idx) {
                distance += xArr[idx] - xArr[idx - 1];
                if (distance >= mid) {
                    --count;
                    distance = 0;

                    if (count == 0) {
                        break;
                    }
                }
            }

            if (count == 0) {
                answer = Math.max(answer, mid);
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
