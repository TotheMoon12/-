import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        final int N = Integer.parseInt(br.readLine());
        int[] predictedRank = new int[N + 1];

        for (int n = 1; n <= N; ++n) {
            predictedRank[n] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(predictedRank);
        long answer = 0;
        for (int n = 1; n <= N; ++n) {
            answer += Math.abs(n - predictedRank[n]);
        }

        bw.write(Long.toString(answer));
        br.close();
        bw.close();
    }
}
