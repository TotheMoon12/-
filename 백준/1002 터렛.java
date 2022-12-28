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
        final int T = Integer.parseInt(br.readLine());

        StringBuilder builder = new StringBuilder();
        for (int t = 0; t < T; ++t) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int x1 = Integer.parseInt(st.nextToken());
            int y1 = Integer.parseInt(st.nextToken());
            int r1 = Integer.parseInt(st.nextToken());
            int x2 = Integer.parseInt(st.nextToken());
            int y2 = Integer.parseInt(st.nextToken());
            int r2 = Integer.parseInt(st.nextToken());

            double distance = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
            int answer;

            if (distance == 0 && r1 == r2) {
                answer = -1;
            } else if (r1 + r2 < distance) {
                answer = 0;
            } else if (r1 + r2 == distance) {
                answer = 1;
            } else {
                if (distance + r1 == r2 || distance + r2 == r1) {
                    answer = 1;
                } else if (r1 > r2 && distance + r2 < r1 || r1 < r2 && distance + r1 < r2) {
                    answer = 0;
                } else {
                    answer = 2;
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
