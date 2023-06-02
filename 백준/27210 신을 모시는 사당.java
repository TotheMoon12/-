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

        final int N = Integer.parseInt(br.readLine());

        int[] left = new int[N];
        int[] right = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int n = 0; n < N; ++n) {
            int direction = Integer.parseInt(st.nextToken());

            if (direction == 1) {
                left[n] = 1;
                right[n] = -1;
            } else {
                left[n] = -1;
                right[n] = 1;
            }
        }

        int leftSum = 0;
        int rightSum = 0;
        int max = 0;
        for (int n = 0; n < N; ++n) {
            if (leftSum + left[n] > left[n]) {
                leftSum += left[n];
            } else {
                leftSum = left[n];
            }

            if (rightSum + right[n] > right[n]) {
                rightSum += right[n];
            } else {
                rightSum = right[n];
            }

            max = Math.max(max, Math.max(Math.abs(leftSum), Math.abs(rightSum)));
        }

        bw.write(String.valueOf(max));
        br.close();
        bw.close();
    }
}
