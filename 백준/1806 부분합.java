import javax.print.DocFlavor;
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
        final int S = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int[] sequence = new int[N];
        for (int n = 0; n < N; ++n) {
            sequence[n] = Integer.parseInt(st.nextToken());
        }

        int answer = Integer.MAX_VALUE;
        int start = 0;
        int end = 0;
        int sum = sequence[0];
        while (end < N) {
            if (sum >= S) {
                answer = Math.min(answer, end - start + 1);
                sum -= sequence[start++];
            } else {
                ++end;
                if (end == N) {
                    break;
                }

                sum += sequence[end];
            }
        }

        if (answer == Integer.MAX_VALUE) {
            answer = 0;
        }
        
        bw.write(String.valueOf(answer));
        bw.close();
        br.close();
    }
}
