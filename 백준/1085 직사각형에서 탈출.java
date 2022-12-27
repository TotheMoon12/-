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
        final int x = Integer.parseInt(st.nextToken());
        final int y = Integer.parseInt(st.nextToken());
        final int w = Integer.parseInt(st.nextToken());
        final int h = Integer.parseInt(st.nextToken());

        int answer = Math.min(x, w - x);
        answer = Math.min(answer, y);
        answer = Math.min(answer, h - y);

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
