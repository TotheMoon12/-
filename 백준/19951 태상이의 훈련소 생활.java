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

        int[] map = new int[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int n = 1; n <= N; ++n) {
            map[n] = Integer.parseInt(st.nextToken());
        }

        int[] mark = new int[N + 2];
        for (int m = 0; m < M; ++m) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken()) + 1;
            int k = Integer.parseInt(st.nextToken());

            mark[a] += k;
            mark[b] += -k;
        }

        StringBuilder builder = new StringBuilder(N);
        builder.append(map[1] + mark[1]);
        for (int n = 2; n <= N; ++n) {
            mark[n] += mark[n - 1];
            int height = map[n] + mark[n];
            builder.append(' ');
            builder.append(height);
        }

        bw.write(String.valueOf(builder));
        br.close();
        bw.close();
    }
}
