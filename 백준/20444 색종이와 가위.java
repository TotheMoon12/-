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
        final long K = Long.parseLong(st.nextToken());

        long left = 1;
        long right = Math.min(K / 2 + 1, N);

        boolean result = false;
        while (left <= right) {
            long row = (left + right) / 2;
            long col = N - (row - 1) + 1;

            long paper = row * col;
            if (paper == K) {
                result = true;
                break;
            } else if (paper < K) {
                left = row + 1;
            } else {
                right = row - 1;
            }
        }

        if (result) {
            bw.write("YES");
        } else {
            bw.write("NO");
        }
        br.close();
        bw.close();
    }
}
