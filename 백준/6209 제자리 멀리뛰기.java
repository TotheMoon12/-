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
        final int D = Integer.parseInt(st.nextToken());
        final int N = Integer.parseInt(st.nextToken());
        final int M = Integer.parseInt(st.nextToken());

        if (N == 0) {
            bw.write(String.valueOf(D));
            br.close();
            bw.close();
            return;
        }

        int[] stones = new int[N];
        for (int n = 0; n < N; ++n) {
            stones[n] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(stones);

        int left = 0;
        int right = D;

        int answer = 0;
        while (left <= right) {
            int mid = (left + right) / 2;

            int count = 0;
            int prev = 0;
            for (int n = 0; n < N; ++n) {
                int dis = stones[n] - prev;
                if (dis < mid) {
                    ++count;
                } else {
                    prev = stones[n];
                }
            }

            if (prev == stones[N - 1] && D - stones[N - 1] < mid) {
                ++count;
            }

            if (count <= M) {
                answer = mid;
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
