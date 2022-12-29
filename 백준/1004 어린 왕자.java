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

        StringBuilder builder = new StringBuilder();
        final int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; ++t) {
            StringTokenizer st = new StringTokenizer(br.readLine());

            final int startX = Integer.parseInt(st.nextToken());
            final int startY = Integer.parseInt(st.nextToken());
            final int endX = Integer.parseInt(st.nextToken());
            final int endY = Integer.parseInt(st.nextToken());

            final int N = Integer.parseInt(br.readLine());

            int answer = 0;
            for (int n = 0; n < N; ++n) {
                st = new StringTokenizer(br.readLine());

                final int x = Integer.parseInt(st.nextToken());
                final int y = Integer.parseInt(st.nextToken());
                final int r = Integer.parseInt(st.nextToken());

                int squareR = r * r;
                int startDistance = (x - startX) * (x - startX) + (y - startY) * (y - startY);
                int endDistance = (x - endX) * (x - endX) + (y - endY) * (y - endY);

                if (squareR > startDistance && squareR < endDistance || squareR < startDistance && squareR > endDistance) {
                    ++answer;
                }
            }

            builder.append(answer);
            builder.append(System.lineSeparator());
        }

        bw.write(builder.toString());
        br.close();
        bw.close();
    }
}
