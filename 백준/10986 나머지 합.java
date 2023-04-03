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
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] remain = new int[M];
        int sum = 0;
        for (int n = 0; n < N; ++n) {
            sum += Integer.parseInt(st.nextToken());
            sum %= M;
            ++remain[sum];
        }

        ++remain[0];
        long answer = 0;
        for (int m = 0; m < M; ++m) {
            long count = remain[m];
            if (count > 1) {
                answer += (count * (count - 1)) / 2;
            }
        }

        bw.write(String.valueOf(answer));
        bw.close();
        br.close();
    }
}
