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
        int[] prefixSum = new int[N + 1];
        int[] min = new int[N + 1];

        for (int n = 1; n <= N; ++n) {
            prefixSum[n] = prefixSum[n - 1] + Integer.parseInt(br.readLine());
            min[n] = Math.min(min[n - 1], prefixSum[n]);
        }

        int answer = 0;
        for (int n = M; n <= N; ++n) {
            answer = Math.max(answer, prefixSum[n] - min[n - M]);
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
