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
        final double W = Double.parseDouble(st.nextToken());
        final double H = Double.parseDouble(st.nextToken());
        final double X = Double.parseDouble(st.nextToken());
        final double Y = Double.parseDouble(st.nextToken());
        final double P = Double.parseDouble(st.nextToken());
        final double R = H / 2.0;

        int answer = 0;
        for (int p = 0; p < P; ++p) {
            st = new StringTokenizer(br.readLine());
            double x = Double.parseDouble(st.nextToken());
            double y = Double.parseDouble(st.nextToken());

            if (x >= X - R && x < X) {
                double temp = R * R - (x - X) * (x - X);
                double result = Math.sqrt(temp) + Y + R;
                double symmetryResult = result - (result - Y - R) * 2;

                if (y >= symmetryResult && y <= result) {
                    ++answer;
                }
            } else if (x >= X && x <= X + W) {
                if (y >= Y && y <= Y + H) {
                    ++answer;
                }
            } else if (x >= X + W && x <= X + W + R) {
                double temp = R * R - (x - (X + W)) * (x - (X + W));
                double result = Math.sqrt(temp) + Y + R;
                double symmetryResult = result - (result - Y - R) * 2;

                if (y >= symmetryResult && y <= result) {
                    ++answer;
                }
            }
        }

        bw.write(String.valueOf(answer));
        br.close();
        bw.close();
    }
}
