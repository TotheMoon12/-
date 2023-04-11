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

        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] honey = new int[N];
        int[] prefixSum = new int[N];

        honey[0] = Integer.parseInt(st.nextToken());
        prefixSum[0] = honey[0];
        for (int n = 1; n < N; ++n) {
            honey[n] = Integer.parseInt(st.nextToken());
            prefixSum[n] = prefixSum[n - 1] + honey[n];
        }

        int answer = 0;
        for (int idx = 1; idx < N - 1; ++idx) {
            answer = Math.max(answer, prefixSum[N - 1] - honey[0] - honey[idx] + prefixSum[N - 1] - prefixSum[idx]);
            answer = Math.max(answer, prefixSum[N - 1] - honey[N - 1] - honey[idx] + prefixSum[idx - 1]);
            answer = Math.max(answer, prefixSum[N - 1] - honey[0] - honey[N - 1] + honey[idx]);
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
