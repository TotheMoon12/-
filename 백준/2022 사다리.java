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
        double x = Double.parseDouble(st.nextToken());
        double y = Double.parseDouble(st.nextToken());
        double c = Double.parseDouble(st.nextToken());

        double left = 0.000001;
        double right = Math.min(x, y);

        while (left <= right) {
            double mid = (left + right) / 2;
            double sqrtA = Math.sqrt(x * x - mid * mid);
            double sqrtB = Math.sqrt(y * y - mid * mid);
            double cx = sqrtA * mid / (sqrtB + sqrtA);
            double cy = sqrtB / mid * cx;

            double diff = Math.abs(c - cy);
            if (cx < mid) {
                if (diff <= 0.001) {
                    double answer = Math.floor(mid * 1000) / 1000;
                    bw.write(String.valueOf(answer));
                    break;
                } else {
                    if (cy < c) {
                        right = mid - 0.000001;
                    } else {
                        left = mid + 0.000001;
                    }
                }
            } else {
                right = mid - 0.00001;
            }
        }

        br.close();
        bw.close();
    }
}   
