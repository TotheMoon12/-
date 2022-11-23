import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        final int N = Integer.parseInt(br.readLine());
        int answer = 0;
        StringTokenizer st = new StringTokenizer(br.readLine());
        int start = Integer.parseInt(st.nextToken());
        int count = 0;
        for (int n = 1; n < N; ++n) {
            int height = Integer.parseInt(st.nextToken());

            if (start >= height) {
                ++count;
            } else {
                answer = Math.max(answer, count);
                count = 0;
                start = height;
            }
        }
        answer = Math.max(answer, count);

        bw.write(Integer.toString(answer));
        br.close();
        bw.close();
    }
}
