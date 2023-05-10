import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());
        int[] map = new int[N * 2];
        int total = 0;
        for (int n = 0; n < N; ++n) {
            map[n] = Integer.parseInt(br.readLine());
            map[N + n] = map[n];
            total += map[n];
        }

        int start = 0;
        int end = 0;

        int answer = 0;
        int prev = 0;
        int current = 0;
        final int LAST = 2 * N;
        while (end < LAST && start <= end) {
            int temp = current + map[end];
            int distance = Math.min(temp, total - temp);
            if (distance < prev) {
                current -= map[start++];
                distance = Math.min(current, total - current);
            } else {
                current = temp;
                ++end;
            }

            prev = distance;
            answer = Math.max(answer, distance);
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
