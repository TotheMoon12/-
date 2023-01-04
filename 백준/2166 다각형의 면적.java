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
        double[][] points = new double[N +1][2];

        for (int n = 0; n < N; ++n) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            final double x = Double.parseDouble(st.nextToken());
            final double y = Double.parseDouble(st.nextToken());

            points[n][0] = x;
            points[n][1] = y;
        }

        points[N][0] = points[0][0];
        points[N][1] = points[0][1];

        double answer = 0;
        for (int n = 0; n < N; ++n) {
            answer += (points[n][0] * points[n + 1][1] - points[n][1] * points[n + 1][0]);
        }

        answer /= 2;
        answer = Math.abs(answer);
        answer = Math.round(answer * 10);
        answer /= 10;

        bw.write(String.format("%.1f", answer));
        br.close();
        bw.close();
    }
}
