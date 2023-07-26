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
        final int A = Integer.parseInt(st.nextToken());
        final int B = Integer.parseInt(st.nextToken());
        final int C = Integer.parseInt(st.nextToken());

        double left = 0;
        double right = 100000000;
        final double ERROR = 0.0000000001;

        while (left < right) {
            double mid = (left + right) / 2;
            double value = A * mid + B * Math.sin(mid);

            if (Math.abs(value - C) <= ERROR) {
                bw.write(String.format("%.10f", mid));
                break;
            } else if (value < C) {
                left = mid;
            } else {
                right = mid;
            }
        }

        br.close();
        bw.close();
    }
}
